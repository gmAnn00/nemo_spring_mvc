<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모: 모임 메인페이지</title>
<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/sectionTitle.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/groupMain.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.min.css" />
<script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/js/header.js"></script>
<script src="${contextPath}/resources/js/groupMain.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	<!-- section1 -->
	<jsp:include page="./groupheader.jsp" flush="true"></jsp:include>
	<!-- section1종료 -->
	
	<!-- main content 시작-->
        <div class="section2">
            <div class="sc2_contents">
                <!-- 메뉴바 시작 -->
                <div class="sc2_menu_contents">
                    <div class="sc2_menu">
                        <h2 class="sc2_menu_title">나의 모임</h2>
                        <ul class="sc2_menu_list">
   							<c:choose>
   								<c:when test="${isLeader == true }">
   									<li>
		                                <a href="${contextPath}/group/schedule?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>일정</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
		                            <li>
		                                <a href="${contextPath}/group/board?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>게시판</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
		                            <li>
		                                <a href="${contextPath}/group/leader/memberinfo?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>멤버</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
		                            <li>
		                                <a href="${contextPath}/group/leader/settingform?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>소모임관리</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
					     			<li>
		                                <a href="${contextPath}/group/leader/delgroupform?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu reportArea">
		                                        <div class="menu_submenu_name reportTitle"><span>소모임삭제</span></div>
		                                        <i class="fa-solid fa-arrow-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
   								</c:when>
   								
   								<c:otherwise>
   									<li>
		                                <a href="${contextPath}/group/schedule?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>일정</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
		                            <li>
		                                <a href="${contextPath}/group/board?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>게시판</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
		                            <li>
		                                <a href="${contextPath}/group/member?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>멤버</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
		                            
		                            <!-- 네모 관리자(admin)는 신고하기 안보여줌 -->
		                            <c:if test="${admin == 0}">
		                            <li>
		                                <a href="${contextPath}/report/group?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu reportArea">
		                                        <div class="menu_submenu_name reportTitle"><span>신고하기</span></div>
		                                        <i class="fa-solid fa-arrow-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
		                            </c:if>
   								</c:otherwise>
   							
                            </c:choose>
                        </ul>
                    </div>
                </div>
                <!-- 메뉴바 종료 -->

                <div class="sc2_subsection">
                    <div class="sc2_subsection_title">
                        <h2 class="sc2_subsection_title_name"></h2>
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="${contextPath}/index">
                                <i class="fa-solid fa-house"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>나의 모임</span>
                        </div>
                        <!-- nav 바 종료 -->
                    </div>
                    <!-- 최근 일정 영역 시작-->
                    <div class="recentSchedule recentDiv sc2_subsection">
                        <div class="mainTitle">
                            <h3>최근 일정</h3>
                            <a href="${contextPath}/group/schedule?group_id=${param.group_id}"
                                ><span class="more"><i class="fa-solid fa-plus"></i>더보기</span></a
                            >
                        </div>
                        <table class="recentScheduledTbl recentTbl">
	                        <colgroup>
	                        	<col width="45%">
	                        	<col width="25%">
	                        	<col width="30%">
	                       	</colgroup>
                            <tr>                                
                                <th>모임제목</th>                                
                                <th>모임장소</th>
                                <th>모임날짜</th>
                            </tr>
                            <c:choose>
                        		<c:when test="${schedulesList.isEmpty()}">
                        			<tr>
                        				<td colspan="3" align="center">등록된 일정이 없습니다.</td>
                        			</tr>
                        		</c:when>
                        		<c:when test="${!schedulesList.isEmpty()}">
                        			<c:forEach var="schedule" items="${schedulesList}">
                        				<tr>
                        					<td>${schedule.schedule_title}</td>                        				                           					
                        					<td>${schedule.location}</td>
                        					 <td><fmt:formatDate value="${schedule.schedule_date}" pattern="yyyy-MM-dd HH:mm" /></td>
                        				</tr>
                        			</c:forEach>
                        		</c:when>
                        	</c:choose>
                        </table>
                    </div>

                    <!-- 최신 글 영역 시작-->
                    <div class="recentBoard recentDiv sc2_subsection">
                        <div class="mainTitle">
                            <h3>최신 글</h3>
                            <a href="${contextPath}/group/board?group_id=${param.group_id}"
                                ><span class="more"><i class="fa-solid fa-plus"></i>더보기</span></a
                            >
                        </div>
                        <table class="recentBordTbl recentTbl">
                          <colgroup>
	                        	<col width="50%">
	                        	<col width="12%">
	                        	<col width="30%">
	                       	</colgroup>
                        	<tr>
                                <th>글제목</th>
                                <th>작성자</th>
                                <th>날짜</th>
                            </tr>
                           <c:choose>
                        		<c:when test="${boardsList.isEmpty()}">
                        			<tr>
                        				<td colspan="3" align="center">등록된 글이 없습니다.</td>
                        			</tr>
                        		</c:when>
                        		<c:when test="${!boardsList.isEmpty()}">
                        			<c:forEach var="board" items="${boardsList}">
                        				<tr>
                        					<td>
                        						<a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&article_no=${board.article_no}">
                        							<p>${board.title}</p>
                        						</a>
                        					</td>
                        					<td>${board.user_id}</td>
                        					<td>${board.create_date}</td>
                        				</tr>
                        			</c:forEach>
                        		</c:when>
                        	</c:choose>
                        </table>
                    </div>

                    <!-- 멤버 영역 시작-->
                    <div class="memberArea sc2_subsection">
                        <div class="mainTitle">
                            <h3 class="nemoMemTitle">소모임 멤버</h3>
                            <span>(<span class="currentNum">${groupHeader.current_memno}</span>/<span class="maxNum">${groupHeader.max_memno}</span>)</span>
                        </div>
                        <span class="btnEventPrev" title="이전보기"><i class="fa-solid fa-chevron fa-chevron-left"></i></span>
                        <div class="animationSlide">
                            <div class="sliderPanel">
                                <!-- <p><center>이미지슬라이드영역~</center></p> -->
                                <c:forEach var="member" items="${membersList}">
                                <c:set var="idx" value="${member.user_addr1.indexOf(' ', member.user_addr1.indexOf(' ') + 1)}" />
								<c:set var="locationU" value="${member.user_addr1.substring(0, idx)}"/>
                                	<div class="slideContent">
                                		<div class="memImg">
                                			<img src="${contextPath}/userimagedownload?user_id=${member.user_id}&user_img=${member.user_img}" />
                                		</div>
                                		<br/>
                                		<div class="memName">
                                			<span>${member.nickname}</span>
                                		</div>
                                	</div>
                                </c:forEach>
                            </div>
                            <!-- slide panel 종료-->
                        </div>
                        <!--animation-slide종료-->
                        <span class="btnEventNext" title="다음보기"><i class="fa-solid fa-chevron fa-chevron-right"></i></span>
                    </div>
                    <!-- 멤버 영역 끝-->
                </div>
            </div>

            <!-- main content 종료-->
        </div>

        <input type="hidden" id="user_id_hidden" name="user_id_hidden" value="${user_id}" />
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>
