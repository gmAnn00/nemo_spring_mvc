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
<title>네모: 비밀번호찾기</title>
<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/findIdPass.css" />
        <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/resources/js/header.js"></script>
        <script src="${contextPath}/resources/js/resetpasswordform.js"></script>
</head>
<body>

<jsp:include page="../header.jsp" flush="true"></jsp:include>

        <!-- 아이디/비밀번호 찾기 영역 -->
        <div class="login_container">
            <ul class="tabs">
                <li class="tabId" data-tab="idTab">
                    <a href="${contextPath}/login/findidform">아이디 찾기</a>
                </li>
                <li class="tabPass" data-tab="passTab">
                    <a href="${contextPath}/login/resetpasswordcheckform">비밀번호 찾기</a>
                </li>
            </ul>

            <div id="passTab" class="tabContent current">
                <h2>비밀번호 재설정</h2>
                <p>비밀번호를 입력하시면 재설정 하실 수 있습니다.</p>
                <form action="${contextPath}/login/resetpassword" method="post">
                    <label for="password"></label>
                    <input type="password" id="password" name="password" placeholder="비밀번호 입력(문자, 숫자 포함 8~20자)" oninput="fn_pwdCheck()" required />
                    <div id="resultMsgPwd" class="alert resultMsg" style=display:none></div>
                    <label for="passwordCheck"></label>
                    <input type="password" id="passwordCheck" placeholder="비밀번호 확인" oninput="fn_pwdDupCheck()" required />
                    <div class="alert alertSuccess" id="alertSuccess">
	            		비밀번호가 일치합니다.
	          		</div>
		          	<div class="alert alertDanger" id="alertDanger">
		            	비밀번호가 일치하지 않습니다.
		          	</div>
                    <button type="submit" class="button" id="buttonSubmit" disabled>비밀번호 재설정 하기</button>
                </form>
            </div>
        </div>
        <!-- 아이디/비밀번호 찾기 영역 종료 -->
        
        <jsp:include page="../footer.jsp" flush="true"></jsp:include>
        
</body>
</html>