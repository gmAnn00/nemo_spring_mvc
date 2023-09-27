<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<link rel="stylesheet" href="${contextPath}/resources/css/myBoardList.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/tabmenu.css">
<script src="https://kit.fontawesome.com/bc604c01cc.js"crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/js/header.js"></script>
<script src="${contextPath}/resources/js/tabmenu.js"></script>
    
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
						<li><a href="${contextPath}/mypage/myprofile">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>프로필</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/mypage/myschedule">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>내 일정</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/mypage/mygroup">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name menu_angle">
										<span>내 소모임</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/mypage/myboard">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name menu_angle submenu_select">
										<span>내가 쓴 글·댓글</span>
									</div>
									<i class="fa-solid fa-minus submenu_select"></i>
								</div>
						</a></li>
					</ul>
				</div>
			</div>
			<!-- 메뉴바 종료 -->

			<!--3. 컨텐츠부분-->
			<div class="sc2_subsection">
				<div class="sc2_subsection_title">
					<h2 class="sc2_subsection_title_name">내가 쓴 글·댓글</h2>
					<!-- nav 바 시작 -->
					<div class="nav_bar">
					  <a href="${contextPath}/index">
					  	<i class="fa-solid fa-house nav_icon"></i>
					  </a>
					  <i class="fa-solid fa-angle-right nav_icon"></i>
		              <span>마이페이지</span>
		              <i class="fa-solid fa-angle-right nav_icon"></i>
		              <span>내가 쓴 글·댓글</span>
		            </div>
					<!-- nav 바 종료 -->
				</div>
				
				<!-- 내가 쓴 글/댓글 영역 -->
				<div class="sc2_subcontents">
	            <!-- tab menu 시작 -->
	            <div class="tab_container">
	              <div class="tab-slider--nav">
	                <ul class="tab-slider--tabs">
	                  <li class="tab-slider--trigger active" rel="tab1"><span>작성글</span></li>
	                  <li class="tab-slider--trigger" rel="tab2"><span>작성댓글</span></li>
	                </ul>
	              </div>
	              <div class="tab-slider--container">
	                <!-- tab1 시작 -->
	                <div id="tab1" class="tab-slider--body">
	                  <div class="tab_box">
	                    <!-- 글 시작 -->
                        <c:choose>
		                  <c:when test="${empty myBoardList}">
		                    <p>작성된 글이 없습니다.</p>
		                  </c:when>
		                  <c:when test="${!empty myBoardList}">
		                    <c:forEach var="myBoard" items="${myBoardList}" varStatus="loop">
	                        	  
		                    <div class="user-comment">
		                      <div class="comments-section">	                                          
	                        	<div class="comment_box">	                         
		                          <div class="comment_title">
		                            <div class="comment_textarea">
		                              <h3 class="comment_group_title">${myBoard.group_name}</h3>
		                              <span class="comment_board_title">${myBoard.title}</span>
		                            </div>
		                          </div>
		
		                          <div class="comment-post">
		                            <div class="comment-img"><img src="${contextPath}/userimagedownload?user_id=${myBoard.user_id}&user_img=${myBoard.user_img}" /></div>
		                            <div class="comment-details">
		                              <div class="comment_details_titlebox">
		                                <div class="comment_details_title">
		                                  <span class="comment-author" id="user"> ${myBoard.nickname}</span>
		                                  <span class="comment-time"><fmt:formatDate value="${myBoard.create_date}" pattern="yyyy-MM-dd" /></span>
		                                   <div class="comment-like-unlike">
			                                  <span><a href="${contextPath}/group/board/viewboard?group_id=${myBoard.group_id}&article_no=${myBoard.article_no}"><i class="fa-solid fa-pen"></i></a></span>
			                                  <span><a href="${contextPath}/group/board/delboard?group_id=${myBoard.group_id}&article_no=${myBoard.article_no}"><i class="fa-solid fa-xmark"></i></a></span>
			                                </div>
		                                </div>
		                               
		                              </div>
		                              <p class="comment-content">
		                               	${myBoard.content}
		                              </p>
		                            </div>
		                          </div>
		                         </div>
	                          </div>
                    		</div>
                    		
                            </c:forEach>
                          </c:when>
                        </c:choose>
	                    <!-- 글 종료 -->
	                  </div>	                  
	                </div>
	                <!-- tab1 종료 -->
	
	                <!-- tab2 시작 -->
	                <div id="tab2" class="tab-slider--body">
	                  <div class="tab_box">
	                    <!-- comment 시작 -->
                        <c:choose>
		                  <c:when test="${empty myCommentList}">
		                    <p>작성된 댓글이 없습니다.</p>
		                  </c:when>
		                  <c:when test="${!empty myCommentList}">
		                    <c:forEach var="myComment" items="${myCommentList}" varStatus="loop">
                        
		                    <div class="user-comment">
		                      <div class="comments-section">	                                          
	                        	<div class="comment_box">	                         
		                          <div class="comment_title">
		                            <div class="comment_textarea">
		                              <h3 class="comment_group_title">${myComment.group_name}</h3>
		                              <span class="comment_board_title">${myComment.title}</span>
		                            </div>
		                          </div>
		
		                          <div class="comment-post">
		                            <div class="comment-img"><img src="${contextPath}/userimagedownload?user_id=${myComment.user_id}&user_img=${myComment.user_img}" /></div>
		                            <div class="comment-details">
		                              <div class="comment_details_titlebox">
		                                <div class="comment_details_title">
		                                  <span class="comment-author" id="user"> ${myComment.nickname}</span>
		                                  <span class="comment-time"><fmt:formatDate value="${myComment.create_date}" pattern="yyyy-MM-dd HH:mm" /></span>
		                                   <div class="comment-like-unlike">
			                                  <span><a href="${contextPath}/group/board/viewboard?group_id=${myComment.group_id}&article_no=${myComment.article_no}"><i class="fa-solid fa-pen"></i></a></span>
			                                  <span><a href="${contextPath}/group/board/delcomment?group_id=${myComment.group_id}&article_no=${myComment.article_no}&comment_no=${myComment.comment_no}"><i class="fa-solid fa-xmark"></i></a></span>
			                                </div>
		                                </div>
		                               
		                              </div>
		                              <p class="comment-content">
		                              	 ${myComment.content}
		                              </p>
		                            </div>
		                          </div>
		                         </div>
	                          </div>
                    		</div>
                    		
		                    
	                       </c:forEach>
	                      </c:when>
	                    </c:choose>
	                    <!-- comment 종료 -->	                    
	                  </div>
	                </div>
	                <!-- tab2 종료 -->
	              </div>
	            </div>
	          </div>
				
			  <!-- 내가 쓴 글/댓글 영역 끝 -->
				
			</div>
			<!-- 컨텐츠 종료 -->
		</div>
	</div>

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>