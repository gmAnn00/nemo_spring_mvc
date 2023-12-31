//let schedulesList = [];
let schedulesThisMonthList = [];
let schedulesThisMonthArr = [];
let year = 0;
let month = 0;

$(document).ready(function () {
    calendarMaker($("#myScheduleCalendarArea"), new Date());

    let nowDate = new Date();

    function calendarMaker(target, date) {
        if (date == null || undefined) {
            date = new Date();
        }
        let nowDate = date;

        if ($(target).length > 0) {
            year = nowDate.getFullYear();
            month = nowDate.getMonth() + 1;
            $(target).empty().append(assembly(year, month));
        } else {
            console.error("calendar Target is empty");
            return;
        }

        let arr = [];
        let promise = fn_ajax(year, month);
        promise.then((value) => {
            arr = value;
            //console.log("typeofvalue=" + typeof value);
            // console.log("value=" + value);
            //console.log("arr=" + arr);

            //console.log("schedulesList" + schedulesList);
            let thisMonth = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1);
            let thisLastDay = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, 0);

            let tag = "<tr>";
            let cnt = 0;

            // 빈 공백 만들어주기
            for (let i = 0; i < thisMonth.getDay(); i++) {
                tag += "<td></td>";
                cnt++;
            }

            // 날짜 채우기

            for (let i = 1; i <= thisLastDay.getDate(); i++) {
                let newI = i;
                if (newI < 10) {
                    newI = "0" + newI;
                }
                newI = newI.toString();
                if (arr.includes(newI)) {
                    //console.log("i=" + i);
                    if (cnt % 7 == 0) {
                        tag += "<tr>";
                    }
                    tag += "<td><div class='displayReserveContainer scheduleDate'></div>" + i + "</td>";
                    cnt++;
                    if (cnt % 7 == 0) {
                        tag += "</tr>";
                    }
                } else {
                    // 날짜 채우기
                    // for (let i = 1; i <= thisLastDay.getDate(); i++) {
                    if (cnt % 7 == 0) {
                        tag += "<tr>";
                    }
                    tag += "<td><div class='displayReserveContainer'></div>" + i + "</td>";
                    cnt++;
                    if (cnt % 7 == 0) {
                        tag += "</tr>";
                    }
                } //else End
            }

            $(target).find("#setDate").append(tag);

            calMoveEvtFn();
        }); // end of promise - then

        fn_ajax_select_month(year, month);

        function assembly(year, month) {
            let calendarHTMLCode =
                "<table class='calendarTable'>" +
                "<caption class='calDate'>" +
                "<button type='button' class='prev btn'>< 이전 달</button>" +
                "<span>" +
                year +
                "년 " +
                month +
                "월</span>" +
                "<button type='button' class='today btn'>오늘</button>" +
                "<button type='button' class='next btn'>다음 달 ></button>" +
                "</caption>" +
                "<thead  class='calWeek'>" +
                "<th class='red'>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th class='blue'>토</th>" +
                "</thead>" +
                "<tbody id='setDate'>" +
                "</tbody>" +
                "</table>";
            return calendarHTMLCode;
        } // end of assembly

        function calMoveEvtFn() {
            // 이전 달 클릭
            $(".calendarTable").on("click", ".prev", function () {
                nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() - 1, nowDate.getDate());
                calendarMaker($(target), nowDate);
                passedDay(nowDate);
            });

            // 다음달 클릭
            $(".calendarTable").on("click", ".next", function () {
                nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, nowDate.getDate());
                calendarMaker($(target), nowDate);
                passedDay(nowDate);
            });

            //일자 선택 클릭
            $(".calendarTable").on("click", "td", function () {
                let cName = $(this).attr("class");
                //   console.log(cName);
                let canSelect = cName.indexOf("passedDay");
                $("td.selectDay").removeClass("selectDay");
                $(this).removeClass("selectDay").addClass("selectDay");
                if (canSelect == -1) {
                    console.log("지나간날임");
                }
            });

            // 오늘 클릭
            $(".calendarTable").on("click", ".today", function () {
                nowDate = new Date();
                calendarMaker($(target), nowDate);
                passedDay(nowDate);
                $("td")
                    .filter(function () {
                        return $(this).text() == nowDate.getDate();
                    })
                    .removeClass("selectDay")
                    .addClass("selectDay");
            });

            // 빈칸 선택 안함
            $("td")
                .filter(function () {
                    return $(this).text() !== "";
                })
                .addClass("hasDay");

            // 토요일들 파란색 줌
            $("td")
                .filter(function () {
                    return $(this).index("td") % 7 == 6;
                })
                .addClass("blue");

            // 일요일들 빨간색 줌
            $("td")
                .filter(function () {
                    return $(this).index("td") % 7 == 0;
                })
                .addClass("red");
        } // end of calMoveEvtFn

        // 오늘 이전은 선택/호버 안되게 함
        function passedDay(date) {
            let today = new Date();
        }
    } // end of calendarMaker

    year = nowDate.getFullYear();
    month = nowDate.getMonth() + 1;
    let ajaxYear = year % 100;
    let ajaxMonth = month;
    // 이번달 일정 가져오는 아작스
}); // end of $(document).ready

function fn_ajax(year, month) {
    return new Promise((resolve, reject) => {
        //이번달 일정 있는 날/없는 날 구분해서 채우기 아작스
        let ajaxYear = year % 100;
        let ajaxMonth = month;
        if (ajaxMonth < 10) {
            ajaxMonth = "0" + month;
        }

        //let arr = [];
        $.ajax({
            type: "get",
            cache: false,
            contentType: false,
            ajax: false,
            url: "/nemo/mypage/myschedule/ajaxschedule?year=" + ajaxYear + "&month=" + ajaxMonth,
            success: function (data, textStatus) {
                let jsonInfo = JSON.parse(data);
                //console.log(jsonInfo);
                resolve(jsonInfo);
            },
            error: function (data, textStatus, error) {
                //console.log(data);
                //console.log(textStatus);
                //console.log(error);
                //alert("찜 추가/삭제 에러 발생");
            },
        });
    });
} // end of fn_ajax

function fn_ajax_select_month(year, month) {
    return new Promise((resolve, reject) => {
        //이번달 일정 있는 날/없는 날 구분해서 채우기 아작스
        let ajaxYear = year % 100;
        let ajaxMonth = month;
        if (ajaxMonth < 10) {
            ajaxMonth = "0" + month;
        }

        $.ajax({
            type: "get",
            cache: false,
            contentType: false,
            dataType: "json",
            ajax: false,
            url: "/nemo/mypage/myschedule/ajaxselectmonthschedule?year=" + ajaxYear + "&month=" + ajaxMonth,
            success: function (data, textStatus) {
                //console.log("data=", data);
                //let thisMonthList = data;
                //console.log("thisMonthList=", thisMonthList);
                //console.log("typeofthisMonthList=",typeof thisMonthList);
                let thisMMS = $(".thisMonthMyScheduleList");
                thisMMS.html("");
                //console.log("data length", data.length);
                if (data.length == 0) {
                    let content = `
				<h3>${month}월의 일정</h3>
				<p>등록된 일정이 없습니다.</p>
			`;
                    thisMMS.html(content);
                } else {
                    let content = `<h3>${month}월의 일정</h3>`;
                    for (key in data) {
                        content += `
					<div class="mySchedule">
	                  	<a href="/nemo/group/schedule?group_id=${data[key].group_id}">
	                    <p class="myScheduleDate">${data[key].schedule_date}</p>
	                    <div class="myScheduleImgContent">
	                      <div class="groupImg">
	                        <img src="/nemo/groupimagedownload?group_id=${data[key].group_id}&group_img=${data[key].group_img}" alt="소모임 사진" />
	                      </div>	                      
	                      <div class="myScheduleContent">
	                        <p class="myScheduleGroupName">${data[key].group_name}</p>
	                        <p class="contents">${data[key].schedule_title}</p>
	                        <p class="contents"><i class="fa-solid fa-location-dot"></i> ${data[key].location}</p>
	                      </div>
	                    </div>
	                    </a>
	                  </div>
				`;
                    } // end of for
                    thisMMS.html(content);
                } // end of else

                resolve(data);
            },
            error: function (data, textStatus, error) {
                //console.log(data);
                //console.log(textStatus);
                //console.log(error);
                //alert("찜 추가/삭제 에러 발생");
            },
        });
    });
} // end of fn_ajax_select_month
