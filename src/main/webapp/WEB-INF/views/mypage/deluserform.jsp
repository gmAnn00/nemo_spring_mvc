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
<title>네모: 마이페이지</title>
<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/delUser.css" />
<script
  src="https://kit.fontawesome.com/bc604c01cc.js"
  crossorigin="anonymous"
></script>
<script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/js/header.js"></script>

</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
	 <!--1. 전체 부분-->
    <div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">프로필</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="${contextPath}/mypage/myprofile">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select">
                      <span>프로필</span>
                    </div>
                    <i class="fa-solid fa-minus submenu_select"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myschedule">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>내 일정</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/mygroup">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name menu_angle">
                      <span>내 소모임</span>
                    </div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myboard">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name">
                      <span>내가 쓴 글·댓글</span>
                    </div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 메뉴바 종료 -->

        <!--3. 컨텐츠부분-->
        <div class="sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">회원 탈퇴하기</h2>
            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>마이페이지</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>회원탈퇴</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

          <!-- 탈퇴 -->
          <div class="withdrawal">
            <h3>정말로 네모를 <span>탈퇴</span>하시겠습니까?</h3>
            <c:if test="${sns eq 0}">
	            <h4>탈퇴하시려면 비밀번호를 입력하고 탈퇴하기를 눌러주세요</h4>
	            <form action="${contextPath}/mypage/deluser" method="post">
	              비밀번호 확인 <input type="password" name="password" />
	              <input type="submit" class="buttonCancle" value="탈퇴하기" />
	            </form>
            </c:if>
            <c:if test="${sns eq 1}">
            	<form action="${contextPath}/mypage/delkakaouser" method="post">
	              <input type="submit" class="buttonCancle" value="예" />
	            </form>
            </c:if>
          </div>
        </div>
      </div>
    </div>

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>