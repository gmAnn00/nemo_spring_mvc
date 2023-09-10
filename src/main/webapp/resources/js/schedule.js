let schedulesThisMonthList = [];
let schedulesThisMonthArr = [];
let year = 0;
let month = 0;
let memNum = 0; // 소모임 멤버 받아오기

let date = 0;
let time = 0;

//외부 폼태그가 enter키를 먹지 않도록
function handleOuterFormKeyDown(event) {
    if (event.keyCode === 13) {
        return false;
    }
}

function fn_add_schedule(obj) {
    obj.action = "/nemo/group/schedule/addschedule";
    obj.submit();
}

function fn_delete_schedule() {
	let searchParams = new URL(location.href).searchParams;
	let group_id = searchParams.get("group_id");
    let result = confirm("삭제하시겠습니까?");
    if (result) {
        let schedule_id = $("#schedule_id").val();
        location.href = "/nemo/group/schedule/delschedule?schedule_id=" + schedule_id + "&group_id=" + group_id;
    }
}

function toSchedule(obj, group_id) {
    location.href = "/nemo/group/schedule?group_id=" + group_id;
}

function fn_modify_schedule(obj) {
    obj.action = "/nemo/group/schedule/modschedule";
    obj.submit();
}

function fn_mod_able() {
	$("#schedule_h3").text("일정 수정하기");

    $("#submit_button").css("display", "none");
    $("#modify_button").css("display", "none");
    $("#submit_modify_button").css("display", "block");

    $("#scheduleTitle").prop("disabled", false);
    $("#sche_cont").prop("disabled", false);
    $("#sche_dateTime").prop("disabled", false);
    $("#schedule_id").prop("disabled", false);
    $("#location").prop("disabled", false);
    $("#keyword").val($("#location").val());
    $("#menu_wrap").show();
    
    $(".memberArea").hide();

    // true : 수정 가능, false : 수정 불가
    setTimeout(function () {
        createKakaoMap(true);
    }, 1);
}

function fn_join_schedule() {
    let schedule_id = $("#schedule_id").val();
    location.href = "/nemo/group/schedule/joinschedule?schedule_id=" + schedule_id;
}

function fn_cancel_schedule() {
    let schedule_id = $("#schedule_id").val();
    location.href = "/nemo/group/schedule/cancelschedule?schedule_id=" + schedule_id;
}

function scheduleChk(selScheDate) {
    let locationURL = location.href;
    let searchParams = new URL(locationURL).searchParams;
    let group_id = searchParams.get("group_id");

    let url = contextPath + "/group/schedule/checkschedule";

    $.ajax({
        type: "get",
        dataType: "text",
        async: true,
        url: url,
        data: { group_id: group_id, selScheDate: selScheDate },
        success: function (data) {
            //매개변수들은 내가 정해주는 것이지만 보통 쓰는 단어들이 있다.
            console.log(data);

            let scheduleInfo = JSON.parse(data);
            let attendUsersList;
            let usingScheduleVO;

            console.log("scheduleInfo=", scheduleInfo);

            if (memNum == 0) {
                $(".btnEventPrev").hide();
                $(".btnEventNext").hide();
            } else {
                $(".btnEventPrev").show();
                $(".btnEventNext").show();
            }

            if (scheduleInfo.isScheduleExist) {
                // 일정이 이미 존재함
                attendUsersList = JSON.parse(scheduleInfo.attendUsersList);
                usingScheduleVO = JSON.parse(scheduleInfo.usingScheduleVO);
                memNum = attendUsersList.length;
                $("#attendCnt").text("현재 참석인원 " + memNum + " 명");
                $(".scheduleDetailArea").show();

                setTimeout(function () {
                    // true : 수정 가능, false : 수정 불가
                    createKakaoMap(false);
                }, 1);

                $("#scheduleTitle").val(usingScheduleVO.schedule_title).prop("disabled", true);
                $("#sche_cont").text(usingScheduleVO.schedule_content).prop("disabled", true);
                $("#sche_dateTime").val(usingScheduleVO.schedule_date_time).prop("disabled", true);
                $("#schedule_id").val(usingScheduleVO.schedule_id).prop("disabled", true);
                $("#location").val(usingScheduleVO.location).prop("disabled", true);
                $("#keyword").val($("#location").val());
                $("#menu_wrap").hide();
                $("#schedule_h3").text("일정 상세보기");
                console.log("memberArea show");
                $(".memberArea").show();

                if ($("#user_id_hidden").val() !== usingScheduleVO.user_id) {
                    $("#modify_button").css("display", "none");
                }else{
                	$("#modify_button").css("display", "block");
                }

                $("#submit_button").css("display", "none");
                $("#submit_modify_button").css("display", "none");

                date = usingScheduleVO.schedule_date;
                time = usingScheduleVO.schedule_time;

                //console.log("isAttend=", scheduleInfo.isAttend);
                if (scheduleInfo.isAttend) {
                    let fn_name = "fn_cancel_schedule()";
                    $("#joinSchedule").text("참석 취소");
                    $("#joinSchedule").attr("onclick", fn_name);
                } else {
                    let fn_name = "fn_join_schedule()";
                    $("#joinSchedule").text("참석");
                    $("#joinSchedule").attr("onclick", fn_name);
                }
                let content = "";
                $("#sliderPanel_id").html("");
                for (key in attendUsersList) {
                    content += `
							<div class="slideContent">
								<div class="memImg">
					                <img src="/nemo/userimagedownload?user_id=${attendUsersList[key].user_id}&user_img=${attendUsersList[key].user_img}" alt="프로필사진" />
					            </div>
					            <br/>
					            <div class="memName">
					                <span>${attendUsersList[key].nickname}</span>
					            </div>
				          	</div>  
				            `;
                }
                $("#sliderPanel_id").html(content);
            } else {
                // 일정이 존재하지 않음=> 새 일정 등록
                $("#schedule_h3").text("일정 등록하기");
                
                $(".scheduleDetailArea").show();

                let clickDate = selScheDate + "T12:00";
                $("#sche_dateTime").prop("disabled", false);
                $("#sche_dateTime").val(clickDate);
                $("#scheduleTitle").val("").prop("disabled", false);
                $("#sche_cont").empty().prop("disabled", false);
                $("#schedule_id").empty().prop("disabled", true);
                $("#location").empty().prop("disabled", false);
                $("#keyword").val("서울광역시");
                $("#clickLatlng").text("지도를 클릭해서 일정 장소를 선택하세요.");
                $("#menu_wrap").show();
                console.log("memberArea hide");
                $(".memberArea").hide();

                $("#submit_button").css("display", "block");
                $("#modify_button").css("display", "none");
                $("#submit_modify_button").css("display", "none");

                setTimeout(function () {
                    createKakaoMap(true);
                }, 1);
            }
        },
        error: function (xhr, status, error) {
            alert("에러 발생: " + error + ", " + status);
        },
    });
}

window.onload = function () {
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
            console.log("arr=" + arr);

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
                }

                //추가
                let selectedYear = nowDate.getFullYear(); // 선택한 년도
                let selectedMonth = nowDate.getMonth() + 1; // 선택한 월
                let selectedDate = $(this).text(); // 선택한 일

                if (selectedMonth < 10) {
                    selectedMonth = "0" + selectedMonth;
                }
                if (selectedDate < 10) {
                    selectedDate = "0" + selectedDate;
                }

                let selScheDate = selectedYear + "-" + selectedMonth + "-" + selectedDate;

                console.log("선택된 날짜:", selScheDate);

                scheduleChk(selScheDate);
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
}; // end of window.onload

function fn_ajax(year, month) {
    return new Promise((resolve, reject) => {
        //이번달 일정 있는 날/없는 날 구분해서 채우기 아작스
        let ajaxYear = year % 100;
        let ajaxMonth = month;
        if (ajaxMonth < 10) {
            ajaxMonth = "0" + month;
        }

        let locationURL = location.href;
        let searchParams = new URL(locationURL).searchParams;
        let group_id = searchParams.get("group_id");
        console.log("group_id=", group_id);
        console.log("ajaxMonth=", ajaxMonth);
        $.ajax({
            type: "get",
            cache: false,
            contentType: false,
            ajax: false,
            url: "/nemo/group/schedule/ajaxcalendar?group_id=" + group_id + "&year=" + ajaxYear + "&month=" + ajaxMonth,
            //data: { "jsonInfo": jsonInfo },
            success: function (data, textStatus) {
                let jsonInfo = JSON.parse(data);

                console.log(jsonInfo);
                resolve(jsonInfo);
            },
            error: function (data, textStatus, error) {},
        });
    });
} // end of fn_ajax

function fn_addr_search() {
    setTimeout(function () {
        createKakaoMap(true);
    }, 1);
}

// true : 수정 가능, false : 수정 불가
function createKakaoMap(flag) {
    let markers = [];

    let container = document.getElementById("map"); //지도를 표시할 div
    let options = {
        center: new kakao.maps.LatLng(37.569947, 126.986187), //지도의 중심좌표
        level: 3,
        //지도 확대레벨
    };

    // 주소-좌표 변환 객체를 생성합니다
    let geocoder = new kakao.maps.services.Geocoder();
    
    //지도를 생성
	let map = new kakao.maps.Map(container, options);
	
	
    if (!flag) {
        
        
        let address = $("#location").val();
        geocoder.addressSearch(address, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                let message = "일정 장소: " + address;
                $("#clickLatlng").text(message);

                let marker = new kakao.maps.Marker({
                    map: map,
                    position: coords,
                });

                let infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>',
                });
                infowindow.open(map, marker);

                map.setCenter(coords);
            }
        });

        return;
    }
    console.log("hi");
    // 장소 검색 객체를 생성합니다
    let ps = new kakao.maps.services.Places();

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
        zIndex: 1,
    });

    // 키워드로 장소를 검색합니다
    searchPlaces();

    // 키워드 검색을 요청하는 함수입니다
    function searchPlaces() {
        let keyword = document.getElementById("keyword").value;
        console.log("keyword=" + keyword);

        if (!keyword.replace(/^\s+|\s+$/g, "")) {
            alert("키워드를 입력해주세요!");
            return false;
        }

        // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
        ps.keywordSearch(keyword, placesSearchCB);
    }

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
            // 정상적으로 검색이 완료됐으면
            // 검색 목록과 마커를 표출합니다
            displayPlaces(data);

            // 페이지 번호를 표출합니다
            //displayPagination(pagination);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert("검색 결과가 존재하지 않습니다.");
            return;
        } else if (status === kakao.maps.services.Status.ERROR) {
            alert("검색 결과 중 오류가 발생했습니다.");
            return;
        }
    }

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    function displayPlaces(places) {
        let listEl = document.getElementById("placesList"),
            menuEl = document.getElementById("menu_wrap"),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds(),
            listStr = "";

        // 검색 결과 목록에 추가된 항목들을 제거합니다
        removeAllChildNods(listEl);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        for (let i = 0; i < places.length; i++) {
            // 마커를 생성하고 지도에 표시합니다
            let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
                marker = addMarker(placePosition, i),
                itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(placePosition);

            // 마커와 검색결과 항목에 mouseover 했을때
            // 해당 장소에 인포윈도우에 장소명을 표시합니다
            // mouseout 했을 때는 인포윈도우를 닫습니다
            (function (marker, title) {
                kakao.maps.event.addListener(marker, "mouseover", function () {
                    displayInfowindow(marker, title);
                });
                kakao.maps.event.addListener(marker, "mouseout", function () {
                    infowindow.close();
                });

                itemEl.onmouseover = function () {
                    displayInfowindow(marker, title);
                };

                itemEl.onmouseout = function () {
                    infowindow.close();
                };
            })(marker, places[i].place_name);
            fragment.appendChild(itemEl);
        }

        // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
        listEl.appendChild(fragment);
        menuEl.scrollTop = 0;

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    }

    // 검색결과 항목을 Element로 반환하는 함수입니다
    function getListItem(index, places) {
        let el = document.createElement("li"),
            itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' + '<div class="info">' + "   <h5>" + places.place_name + "</h5>";

        if (places.road_address_name) {
            itemStr += "    <span>" + places.road_address_name + "</span>" + '   <span class="jibun gray">' + places.address_name + "</span>";
        } else {
            itemStr += "    <span>" + places.address_name + "</span>";
        }

        itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

        el.innerHTML = itemStr;
        el.className = "item";

        return el;
    }

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, idx, title) {
        let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
            imgOptions = {
                spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                offset: new kakao.maps.Point(13, 37),
                // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage,
            });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker); // 배열에 생성된 마커를 추가합니다

        return marker;
    }

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    function removeMarker() {
        for (let i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    function displayPagination(pagination) {
        let paginationEl = document.getElementById("pagination"),
            fragment = document.createDocumentFragment(),
            i;

        // 기존에 추가된 페이지번호를 삭제합니다
        while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.lastChild);
        }

        for (i = 1; i <= pagination.last; i++) {
            let el = document.createElement("a");
            el.href = "#";
            el.innerHTML = i;

            if (i === pagination.current) {
                el.className = "on";
            } else {
                el.onclick = (function (i) {
                    return function () {
                        pagination.gotoPage(i);
                    };
                })(i);
            }

            fragment.appendChild(el);
        }
        paginationEl.appendChild(fragment);
    }

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    function displayInfowindow(marker, title) {
        let content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

        infowindow.setContent(content);
        infowindow.open(map, marker);
    }

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    function removeAllChildNods(el) {
        while (el.hasChildNodes()) {
            el.removeChild(el.lastChild);
        }
    }

    //////////////////////////////////////////////////////////////

    let marker = new kakao.maps.Marker(); // 클릭한 위치를 표시할 마커입니다

    // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);

    // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(map, "click", function (mouseEvent) {
        searchDetailAddrFromCoords(mouseEvent.latLng, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                let detailAddr = !!result[0].road_address ? "<div>도로명주소 : " + result[0].road_address.address_name + "</div>" : "";
                detailAddr += "<div>지번 주소 : " + '<span id="jibun_addr">' + result[0].address.address_name + "</span>" + "</div>";

                let content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + "</div>";

                // 마커를 클릭한 위치에 표시합니다
                marker.setPosition(mouseEvent.latLng);
                marker.setMap(map);

                let spanDetailAddr = result[0].address.address_name;
                $("#location").val(spanDetailAddr);
                $("#clickLatlng").text("선택 위치: " + spanDetailAddr);

                // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                infowindow.setContent(content);
                infowindow.open(map, marker);
            }
        });
    });

    // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다

    kakao.maps.event.addListener(map, "idle", function () {
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    });

    function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    }

    function searchDetailAddrFromCoords(coords, callback) {
        // 좌표로 법정동 상세 주소 정보를 요청합니다
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }

    // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다

    function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            //let infoDiv = document.getElementById("centerAddr");

            for (let i = 0; i < result.length; i++) {
                // 행정동의 region_type 값은 'H' 이므로
                if (result[i].region_type === "H") {
                    //infoDiv.innerHTML = result[i].address_name;
                    break;
                }
            }
        }
    }
}
