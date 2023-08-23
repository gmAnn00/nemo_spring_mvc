<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
</head>
<body>
	<!-- 배경색 조정 -->
        <div class="menu_bg"></div>
        <!-- header 시작 -->
        <header>
            <h1 class="logo">
                <a href="${contextPath}/index"><img src="${contextPath}/resources/images/logo.png" alt="logo" /></a>
            </h1>
        </header>
        <button class="burger">
            <span></span>
        </button>
        <div class="sidemenu">
            <ul class="main_menu">
            	<c:choose>
            		<c:when test="${user_id != null && admin == 0}">
	            		<li>
	                    	<a href="${contextPath}/mypage">
	                       	 <div class="profile"><img class="fa-solid" src="${contextPath}/userImageDownload?user_id=${user_id}&user_img=${user_img}" alt="프로필사진"><span class="profile_name">${nickname}</span></div>
	                    	</a>
	               		 </li>
	              		 <li><a href="${contextPath}/group/createGroup/form">소모임 만들기</a></li>
	               		 <li><a href="${contextPath}/groupSearch">소모임 검색</a></li>
	               		 <li><a href="${contextPath}/mypage">프로필</a></li>
	               		 <li><a href="${contextPath}/mypage/mySchedule">내 일정</a></li>
	               		 <li><a href="${contextPath}/mypage/myGroupList">내 소모임</a></li>
	                	 <li><a href="${contextPath}/viewQna">고객센터</a></li>
	                	 <li><a href="${contextPath}/login/logout">로그아웃</a></li>
            		</c:when>
            		<c:when test="${user_id != null && admin == 1 }">
            			<li></li>
            			<li><a href="${contextPath}/adminUser">회원 관리</a></li>
            			<li><a href="${contextPath}/adminGroup">소모임 관리</a></li>
            			<li><a href="${contextPath}/adminReport">신고 관리</a></li>
            			<li><a href="${contextPath}/viewQna">문의사항 관리</a></li>
            			<li><a href="${contextPath}/login/logout">로그아웃</a></li>
            		</c:when>
            		<c:otherwise>
            			<li></li>
            			<li><a href="${contextPath}/groupSearch">소모임 검색</a></li>
            			<li><a href="${contextPath}/login/loginForm">로그인</a></li>
            			<li><a href="${contextPath}/signup/agree">회원가입</a></li>
            		</c:otherwise>
            	</c:choose>
            </ul>
            
            <div class="sidemenu_footer">
                <div class="header_section2_content">
                    <div class="header_logo">
                        <a href="${contextPath}/index"><img src="${contextPath}/resources/images/logo_black.png" class="img-fluid" alt="logo" /></a>
                    </div>
                    <div class="header_text">
                    	<p>© 2023 NEMO</p>
                    </div>
                    <div class="header_social_icon">
                        <div class="header_social_icon_box">
                            <a href="#"><i class="fab fa-facebook-f header-facebook-bg"></i></a>
                            <a href="#"><i class="fab fa-twitter header-twitter-bg"></i></a>
                            <a href="#"><i class="fab fa-google-plus-g header-google-bg"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        <!-- header 종료 -->
</body>
</html>