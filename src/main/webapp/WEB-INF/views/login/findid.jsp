<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모: 아이디찾기</title>
<link rel="shortcut icon"
	href="${contextPath}/resources/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/findId.css" />
<link rel="stylesheet"
	href="${contextPath}/resources/css/footerheight.css">
<script src="https://kit.fontawesome.com/f9a2702e84.js"
	crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/js/header.js"></script>
</head>
<body>

	<jsp:include page="../header.jsp" flush="true"></jsp:include>

	<!-- 아이디/비밀번호 찾기 영역 -->
	<div class="login_container">
		<ul class="tabs">
			<li class="tabId" data-tab="idTab"><a
				href="${contextPath}/login/findidform">아이디 찾기</a></li>
			<li class="tabPass" data-tab="passTab"><a
				href="${contextPath}/login/resetpasswordcheckform">비밀번호 찾기</a></li>
		</ul>

		<div id="idTab" class="tabContent current">
			<p>
				회원가입 시 사용하신 아이디는 <strong>${userIdFind}</strong> 입니다.
			</p>

			<button type="button" class="button">
				<a href="${contextPath}/login/loginform">로그인 화면으로 돌아가기</a>
			</button>

		</div>


	</div>

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>

</body>
</html>