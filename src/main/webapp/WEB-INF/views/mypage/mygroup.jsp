<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>네모: 마이페이지</title>
    <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/myGroupList.css" />
    <link
      href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
      rel="stylesheet"
    />
    <link
      href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css"
      rel="stylesheet"
    />
    <script
      src="https://kit.fontawesome.com/bc604c01cc.js"
      crossorigin="anonymous"
    ></script>
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/resources/js/header.js"></script>
    <script src="${contextPath}/resources/js/myGroupList.js"></script>
  </head>
  <body>
    <jsp:include page="../header.jsp" flush="true"></jsp:include>

    <!-- 컨텐츠 시작 -->
    <div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">마이페이지</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="${contextPath}/mypage/myprofile">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>프로필</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
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
                    <div class="menu_submenu_name submenu_select">
                      <span>내 소모임</span>
                    </div>
                    <i class="fa-solid fa-minus submenu_select"></i>
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

        <!-- 메인 컨텐츠 시작 -->
        <div class="sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">내 소모임</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>마이페이지</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>내 소모임</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

          <div class="sc2_subcontents">
            <!-- 리더 영역 시작 -->
            <div class="sc2_subcontent">
              <div class="sc2_contents_title">
                <div class="sc2_contents_title_name">
                  <span>리더</span>
                </div>               
                <div class="cards">
                  <c:choose>
                    <c:when test="${empty leaderGroupList}">
                      <p>만든 소모임이 없습니다.</p>
                    </c:when>
                    <c:when test="${!empty leaderGroupList}">
                      <c:forEach var="leaderGroup" items="${leaderGroupList}" varStatus="loop">
                      <c:set var="idx" value="${leaderGroup.group_addr1.indexOf(' ', leaderGroup.group_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationM" value="${leaderGroup.group_addr1.substring(0, idx)}"/>
                        <div class="card card--1">
                          <div class="card__info-hover">                            
                            <span></span>
                            <span>바로가기 <i class="fa-solid fa-arrow-right"></i></span>                            
                          </div>
                          <div class="card__img"></div>
                          <a href="${contextPath}/group/groupmain?group_id=${leaderGroup.group_id}" class="card_link">
                          	<img class="card__img--hover" alt="소모임 사진"
                          	src="${contextPath}/groupimagedownload?group_id=${leaderGroup.group_id}&group_img=${leaderGroup.group_img}">
                          	<%-- 
                            <div class="card__img--hover"
                            style="background-image:url('${contextPath}/WEB-INF/views/groupImages/${leaderGroup.group_id}/${leaderGroup.group_img}')"></div>
                            --%>
                          </a>
                          <div class="card__info">
                            <span class="card__category">${leaderGroup.main_cate}</span>
                            <span class="card__category">${leaderGroup.sub_cate}</span>
                            <h3 class="card__title">${leaderGroup.group_name}</h3>
                            
                            <span class="card__by">
                              <i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author">${locationM}</a>
                            </span>
                          </div>                        
                        </div>
                      </c:forEach>
                    </c:when>
                    </c:choose>
                  </div>
                
              </div>
            </div>
            <!-- 리더 영역 종료 -->

            <!-- 회원 영역 시작 -->
            <div class="sc2_subcontent">
              <div class="sc2_contents_title">
                <div class="sc2_contents_title_name">
                  <span>회원</span>
                </div>

                <div class="cards">
                  <c:choose>
                    <c:when test="${empty groupList && empty waitGroupList}">
                      <p>가입한 소모임이 없습니다.</p>
                    </c:when>
                  
                    <c:when test="${!empty groupList}">
                      <c:forEach var="userGroup" items="${groupList}" varStatus="loop">
                      <c:set var="idx" value="${userGroup.group_addr1.indexOf(' ', userGroup.group_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationU" value="${userGroup.group_addr1.substring(0, idx)}"/>
                        <div class="card card--1">
                          <div class="card__info-hover">
                            <a href="${contextPath}/group/member/cancel?group_id=${userGroup.group_id}"><span class="leave">탈퇴하기</span></a>
                            <a href="${contextPath}/group/groupmain?group_id=${userGroup.group_id}" class="card_link">
                            <span>바로가기</span>	<i class="fa-solid fa-arrow-right"></i></a>
                          </div>
                          <div class="card__img"></div>
                          <div class="card__img--hover">
                         	<img class="card__img--hover" alt="소모임 사진"
                          	src="${contextPath}/groupimagedownload?group_id=${userGroup.group_id}&group_img=${userGroup.group_img}">
                          	<div class="link">
	                          	<a href="${contextPath}/group/member/cancel?group_id=${userGroup.group_id}"><span class="leave"> &nbsp; &nbsp; &nbsp; &nbsp; </span></a>
	                            <a href="${contextPath}/group/groupmain?group_id=${userGroup.group_id}" class="card_link">
	                              <span> &nbsp; &nbsp; &nbsp; &nbsp; </span>
	                          	</a>
                          	</div>
                          </div>
                          
                          <div class="card__info">
	                        <a href="${contextPath}/group/groupmain?group_id=${userGroup.group_id}" class="card_link">
	                          <span class="card__category">${userGroup.main_cate}</span>
	                          <span class="card__category">${userGroup.sub_cate}</span>
	                          <h3 class="card__title">${userGroup.group_name}</h3>
	                        </a>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author">${locationU}</a>
                            </span>
                          </div>
                         
                        </div>
                      </c:forEach>
                    </c:when>
                    </c:choose>
                    
                    <!-- 가입 대기중인 소모임 -->
                    <c:choose>
                    <c:when test="${!empty waitGroupList}">
                      <c:forEach var="waitGroup" items="${waitGroupList}" varStatus="loop">
                      <c:set var="idx" value="${waitGroup.group_addr1.indexOf(' ', waitGroup.group_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationW" value="${waitGroup.group_addr1.substring(0, idx)}"/>                      	
                        <div class="card card--${waitGroup.group_id}">
                          <div class="card__info-hover">
                            <a href="${contextPath}/group/member/cancelwait?group_id=${waitGroup.group_id}"><span class="leave">취소하기</span></a>
                            <a href="${contextPath}/group/groupinfo?group_id=${waitGroup.group_id}" class="card_link">
                            <span>바로가기</span>	<i class="fa-solid fa-arrow-right"></i></a>
                          </div>
                          <div class="card__img"></div>
                         
                          <div class="card__img--hover">
                          	 <img class="card__img--hover" alt="소모임 사진"
                          	src="${contextPath}/groupimagedownload?group_id=${waitGroup.group_id}&group_img=${waitGroup.group_img}">
                        	<div class="link">
                        		<div class="wait_grp">가입 대기중</div>
                         		<a href="${contextPath}/group/member/cancelwait?group_id=${waitGroup.group_id}"><span class="leave"> &nbsp; &nbsp; &nbsp; &nbsp; </span></a>
	                            <a href="${contextPath}/group/groupinfo?group_id=${waitGroup.group_id}" class="card_link">
	                              <span> &nbsp; &nbsp; &nbsp; &nbsp; </span>
	                         	</a>
                        	</div>
                          </div>                          
                         
                          <div class="card__info">
                             <a href="${contextPath}/group/info?group_id=${waitGroup.group_id}" class="card_link">
	                          <span class="card__category">${waitGroup.main_cate}</span>
	                          <span class="card__category">${waitGroup.sub_cate}</span>
	                          <h3 class="card__title">${waitGroup.group_name}</h3>
	                        </a>
                            <span class="card__by">
                              <i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author">${locationW}</a>
                            </span>
                          </div>
                        </div>
                      </c:forEach>
                    </c:when>
                    </c:choose>
                  </div>               
              </div>
            </div>
            <!-- 회원 영역 종료 -->

            <!-- 찜 영역 시작 -->
            <div class="sc2_subcontent">
              <div class="sc2_contents_title">
                <div class="sc2_contents_title_name">
                  <span>찜</span>
                </div>
                <div class="cards">
                
                  <c:choose>
                    <c:when test="${empty bookmarkGroupList}">
                      <p>찜한 소모임이 없습니다.</p>
                    </c:when>
                  
                    <c:when test="${!empty bookmarkGroupList}">
                      <c:forEach var="bookmarkGroup" items="${bookmarkGroupList}" varStatus="loop">
                      <c:set var="idx" value="${bookmarkGroup.group_addr1.indexOf(' ', bookmarkGroup.group_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationB" value="${bookmarkGroup.group_addr1.substring(0, idx)}"/>                      
		                  <div class="card card--1">		                    
		                    <div class="card__info-hover">
		                      <span></span>
		                      <span>바로가기 <i class="fa-solid fa-arrow-right"></i></span>		                    
		                    </div>
		                    <div class="card__img"></div>
		                    <a href="${contextPath}/group/groupinfo?group_id=${bookmarkGroup.group_id}" class="card_link">
		                      <img class="card__img--hover" alt="소모임 사진"
                          	    src="${contextPath}/groupimagedownload?group_id=${bookmarkGroup.group_id}&group_img=${bookmarkGroup.group_img}">
                          	    <%-- 
		                      <div class="card__img--hover"
		                      style="background-image:url('/WEB-INF/views/groupImages/${bookmarkGroup.group_id}/${bookmarkGroup.group_img}')"></div>
		                      --%>
		                    </a>
		                    <div class="card__info">
		                      <span class="card__category">${bookmarkGroup.main_cate}</span>
		                      <span class="card__category">${bookmarkGroup.sub_cate}</span>
		                      <h3 class="card__title">${bookmarkGroup.group_name}</h3>
		                      <span class="card__by"
		                        ><i class="fa-solid fa-location-dot"></i>
		                        <a href="#" class="card__author">${locationB}</a>
		                          <button type="button" class="grpLikeBtn" title="네모찜하기">
		                          <span class="grpLike grpLike${bookmarkGroup.group_id} on" onclick="bookmarkClick('${user_id}', '${bookmarkGroup.group_id}')"> <svg viewBox="0 0 24 24">
				                                <use xlink:href="#heart" />
				                                <!-- filled heart -->
				                                <use xlink:href="#heart" />
				                                <!-- outline heart -->
				                            </svg> <!-- reference path for both outline, and filled, hearts -->
											<svg class="hide" viewBox="0 0 24 24">
				                                <defs>
				                                    <path id="heart"
													d="M12 4.435c-1.989-5.399-12-4.597-12 3.568 0 4.068 3.06 9.481 12 14.997 8.94-5.516 12-10.929 12-14.997 0-8.118-10-8.999-12-3.568z" />
				                                </defs>
				                            </svg>
										</span>
										<span class="hidden">찜하기</span>
			                      </button>
		                      </span>
		                    </div>
		                  </div>
						</c:forEach>
                    </c:when>
                  </c:choose>
                   
                </div>
              </div>
            </div>
            <!-- 찜 영역 종료 -->
          </div>
        </div>
        <!-- 메인 컨텐츠 종료 -->
      </div>
    </div>
    <!-- 컨텐츠 종료 -->

    <jsp:include page="../footer.jsp" flush="true"></jsp:include>
  </body>
</html>