<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모: 회원가입</title>

<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/js/header.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="${contextPath}/resources/js/join.js"></script>

    <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/join.css" />
</head>
<body>

<jsp:include page="../header.jsp" flush="true"></jsp:include>



	<!-- 콘텐츠 영역 -->
    <div id="contentArea">
      <div class="progress-bar">
        <div class="step step-3"><span>3단계. 관심사 입력하기</span></div>
        <div class="step step-2"><span>2단계. 회원가입하기</span></div>
        <div class="step step-1"><span>1단계. 약관동의</span></div>
      </div>
      <!-- 회원가입 영역 -->
      <div id="loginForm">
        <h2>회원가입</h2>
        <h3>회원이 되어 다양한 사람들과 만나보세요!</h3>
        <!-- <h4>(모든 항목을 입력해주세요)</h4> -->
        <form id="frm" name="frm" action="${contextPath}/signup/join" method="post">
          <div>
            <label for="user_id">아이디</label>
            
            <input
              type="text"
              id="user_id"
              name="user_id"
              placeholder="아이디 입력(5~20자)"
              maxlength="21"
              pattern="^([a-z0-9_]){5,20}$"
              oninput="fn_IdCheck()"
              required
            />
            
            <div id="resultMsgId" class="alert resultMsg" style=display:none></div>
          </div>          
          
          
          <div>
            <label for="password">비밀번호</label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="비밀번호 입력(문자, 숫자, 특수문자포함 8~20자)"
              minlength="8"
              maxlength="20"
              oninput="fn_pwdCheck()"
              required
            />
            <div id="resultMsgPwd" class="alert resultMsg" style=display:none></div>
          </div>
          <div>
            <label for="passwordCheck">비밀번호 확인</label>
            <input
              type="password"
              id="passwordCheck"
              name="passwordCheck"
              placeholder="비밀번호 재입력"
              minlength="8"
              maxlength="20"
              required
              oninput="fn_pwdDupCheck()"
            />
           
	          <div class="alert alertSuccess" id="alertSuccess">
	            비밀번호가 일치합니다.
	          </div>
	          <div class="alert alertDanger" id="alertDanger">
	            비밀번호가 일치하지않습니다.
	          </div>
            
          </div>
          
          <div>
            <label for="user_name">이름</label>
            <input
              type="text"
              id="name"
              name="user_name"
              placeholder="이름을 입력해주세요"
              required
            />
          </div>
          <div>
            <label for="nickname">닉네임</label>

            <input
              type="text"
              id="nickname"
              name="nickname"
              placeholder="닉네임을 입력해주세요"
              oninput="fn_nicknameCheck()" 
              required
            />
                                   
            <div id="resultMsgNick" class="alert resultMsg" style=display:none></div>
          </div>
         
            
          <div>
            <label for="findZipcode">주소</label>
            <button type="button" class="findZipcode button" id="findZipcode">
              우편번호 찾기
            </button>
            <input
              type="text"
              id="zipcode"
              name="zipcode"
              maxlength="10"
              size="10"
              placeholder="우편 번호"
              readonly
              required
            />
            <input
              type="text"
              id="user_addr1"
              name="user_addr1"
              maxlength="100"
              size="60"
              placeholder="주소"
              readonly
              required
            />
            <input
              type="text"
              id="user_addr2"
              name="user_addr2"
              maxlength="100"
              size="60"
              placeholder="상세주소를 적어주세요"
            />
          </div>
          <script>
            window.onload = function () {
              document
                .getElementById("findZipcode")
                .addEventListener("click", function () {
                  new daum.Postcode({
                    oncomplete: function (data) {
                      document.getElementById("zipcode").value = data.zonecode; // 우편번호를 zipcode input 태그에 할당
                      document.getElementById("user_addr1").value =
                        data.address; // 주소를 address input 태그에 할당

                      $("#zipcode").css({
                        background: "var(--sub3-color)",
                      });
                      $("#user_addr1").css({
                        background: "var(--sub3-color)",
                      });
                      document.getElementById("user_addr2").focus();
                    },
                  }).open();
                });
            };
          </script>
          <div>
            <label for="birthdate">생년월일</label>
            <input
              type="date"
              id="birthdate"
              name="birthdate"
              min="1900-01-01"
              required
            />
          </div>
          
	      <script>
		  function getCurrentDate() {
		    var today = new Date();
		    var year = today.getFullYear();
		    var month = today.getMonth() + 1;
		    var day = today.getDate();

		    if (month < 10) {
		      month = '0' + month;
		    }
		    if (day < 10) {
		      day = '0' + day;
		    }
		
		    return year + '-' + month + '-' + day;
		  }
		
		  var birthdateInput = document.getElementById('birthdate');
		  var maxDate = getCurrentDate();
		  var year = parseInt(maxDate.substr(0, 4)) - 19;
		  maxDate = year + maxDate.substr(4);
		
		  birthdateInput.max = maxDate;
		  </script>
          
          <div>
            <label for="phone">전화번호</label>
            <input
              type="tel"
              id="phone"
              name="phone"
              placeholder="예)01012345678"
              required
              oninput="fn_phoneCheck()"
            />
            <div id="resultMsgPhone" class="alert resultMsg" style=display:none></div>
          </div>
          <div class="email">
            <label for="email">이메일주소</label>
            <input type="text" id="emailId" name="emailId" oninput="fn_emailCheck()" />
            <span>@</span>
            <input type="text" id="emailDomain" name="emailDomain" oninput="fn_emailCheck()" />
            <select name="domainList" id="domainList" onchange="fn_emailSelect(); fn_emailCheck()">
              <option value="self">직접입력</option>
              <option value="gmail.com">gmail.com</option>
              <option value="naver.com">naver.com</option>
              <option value="daum.net">daum.net</option>
              <option value="hanmail.net">hanmail.net</option>
              <option value="yahoo.com">yahoo.com</option>
              <option value="nate.com">nate.com</option>
            </select>
            
            <div class="emailCheck">
           		<div id="resultMsgEmail" class="alert resultMsg" style=display:none></div>                         
            </div>
          </div>
          
          <div class="submitCancel">
            <a href="javascript:void(0)" type="submit" class="button submitBtn" onclick="fnJoin()">가입하기</a>
            <!-- <a href="/views/interest.jsp" role="button" class="button" onclick="fnJoin();"
              >가입하기</a
            > -->
            <!-- type을 버튼으로 바꾸고 action과 submit을 jsp 처리하기 -->
            <a href="${contextPath}/index" role="button" class="buttonCancle">가입취소</a>
          </div>
        </form>
      </div>
    </div>
    <!-- 회원가입 영역 종료 -->
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
      
</body>
</html>