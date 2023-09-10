<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@
	page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>네모:일정</title>
<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/sectionTitle.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/schedule.css" />
<!-- <script src="https://kit.fontawesome.com/bc604c01cc.js" crossorigin="anonymous"></script> -->
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/js/header.js"></script>
<script src="${contextPath}/resources/js/schedule.js"></script>

<script>
    var contextPath = "${pageContext.request.contextPath}";
</script>


</head>
<body>
	<!-- header 시작 -->
	<jsp:include page="../../header.jsp" flush="true"></jsp:include>
	<!-- header 종료 -->
	<!-- section1 -->
	<jsp:include page="../groupheader.jsp" flush="true"></jsp:include>
	<!-- section1종료 -->

	<!-- 콘텐츠 영역 -->
	<!-- <div id="contentsArea"> -->
	<div class="section2">
		<div class="sc2_contents">
			<!-- 메뉴바 시작 -->
			<div class="sc2_menu_contents">
				<div class="sc2_menu">
					<h2 class="sc2_menu_title">
						<a href="${contextPath}/group/groupmain?group_id=${param.group_id}">나의 모임</a>
					</h2>
					<ul class="sc2_menu_list">
						<c:choose>
   								<c:when test="${isLeader == true }">
   									<li>
		                                <a href="${contextPath}/group/schedule?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name submenu_select"><span>일정</span></div>
		                                        <i class="fa-solid fa-minus submenu_select"></i>
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
		                                <a href="${contextPath}/group/leader/setting?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name"><span>소모임관리</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
   								</c:when>
   								
   								<c:otherwise>
   									<li>
		                                <a href="${contextPath}/group/schedule?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu">
		                                        <div class="menu_submenu_name submenu_select"><span>일정</span></div>
		                                        <i class="fa-solid fa-minus submenu_select"></i>
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
   								</c:otherwise>
   							
                            </c:choose>
					</ul>
				</div>
			</div>
			<!-- 메뉴바 종료 -->

			<!-- 일정 등록, 확인 영역 시작 -->
			<div class="schedule sc2_subsection">
				<div class="sc2_subsection_title">
					<h2 class="sc2_subsection_title_name">일정</h2>
					<!-- nav 바 시작 -->
					<div class="nav_bar">
						<a href="${contextPath}/index"> <i
							class="fa-solid fa-house nav_icon"></i>
						</a> <i class="fa-solid fa-angle-right nav_icon"></i> <span>나의
							모임</span> <i class="fa-solid fa-angle-right nav_icon"></i> <span>일정</span>
					</div>
					<!-- nav 바 종료 -->
				</div>
				<div class="sc2_subcontents">
            	  <div class="sc2_subcontent">
            	  <div class="myScheduleCalAndList">
	                <div id="myScheduleCalendarArea"></div>
	                <div class="myScheduleListArea">
	                  <h3>다가오는 일정</h3>
	                
	                  <c:choose>
	                    <c:when test="${empty commingScheduleList}">
	                      <p>등록된 일정이 없습니다.</p>
	                    </c:when>
	                    <c:when test="${!empty commingScheduleList}"> 	                                             
	                      <c:forEach var="commmingSchedule" items="${commingScheduleList}">
	                     	      
			                  <div class="mySchedule">		                  	
			                    <p class="myScheduleDate">${commmingSchedule.schedule_date}<span> ${commmingSchedule.schedule_time}</span></p>
			                    <div class="myScheduleImgContent">		                                           
			                      <div class="myScheduleContent">		                        
			                        <p class="contents">${commmingSchedule.schedule_title}</p>
			                        <p class="contents location"><i class="fa-solid fa-location-dot"></i>${commmingSchedule.location}</p>
			                      </div>
			                    </div>		                    
			                  </div>    		                                                 
	                  	     	 
	                  	  </c:forEach>
	                    </c:when>
	                  </c:choose>
	                  
	                </div>
	              </div>
				  </div>
				</div>				
				
				
				<!-- 일정 조회 ,추가, 수정, 삭제 부분 -->
				<div class="scheduleArea clearfixed">
					<!-- 일정 상세보기 -->
					<form name = "frmSchedule"  action="" method="post">
						<div class="scheduleDetailArea clearfixed">
							<div class="scheduleDetail">								
								<div class="contentLocationMap">
														
									<div class="titleContent">
										<div class="detailTop">
											<h3 id="schedule_h3">일정 상세보기</h3>
										</div>
										<div id="schedule_Title">								
											
											<div class="partBtn clearfixed">
												<textarea id="scheduleTitle" name="schedule_title" placeholder="제목을 입력하세요" class="scheduleTitleText" rows="1" maxlength="200" required></textarea>
												<button type="button" id="joinSchedule" class="button btnPart" onclick="fn_join_schedule(${param.group_id})">참석</button>
											</div>
											<div class="dateTime">
												<input type="hidden" id="schedule_id" name="schedule_id" />
												<input type="hidden" id="group_id" name="group_id" value="${param.group_id}" />
												<input type="hidden" id="user_id_hidden" name="user_id_hidden" value="${user_id}"/>						
												<input id="sche_dateTime" type="datetime-local" name="schedule_date" value="">																				
											</div>
										</div>									
										<div class="content">										
											<script>
													var currentDate2 = new Date();
													var currentYear = currentDate2.getFullYear();
													var currentMonth = (currentDate2.getMonth() + 1).toString().padStart(2, '0');
													var currentDay = currentDate2.getDate().toString().padStart(2, '0');
													var currentHour = currentDate2.getHours().toString().padStart(2, '0');
													var currentMinute = currentDate2.getMinutes().toString().padStart(2, '0');
													
													var koreanDateTime = currentYear + '-' + currentMonth + '-' + currentDay + 'T' + currentHour + ':' + currentMinute;
													document.getElementById("sche_dateTime").min = koreanDateTime;
												</script>
											<div class="contentDetail">
												<textarea id="sche_cont" name="schedule_content" class="contentDetailText" maxlength="1000" rows="10"></textarea>
											</div>
										</div>
									</div>									
									
									<!-- 모임 위치 -->
									<input type="hidden" id="location" name="location" value="" required />
									<div class="locationMap" id="newMap">
										<h4>모임 위치</h4>
										<div id="clickLatlng"></div>
										<div style="position: relative">
											<div class="map" id="map"></div>
											<div id="menu_wrap" class="bg_white menu_wrap_class">
												<div class="option">
													<div>
														<form>
															<input type="text" id="keyword" value="서울광역시"
																size="10" placeholder="만남 장소" onkeydown="return handleOuterFormKeyDown(event);" />
															<button type="button" onclick="fn_addr_search(); return false;" style="padding: 3px">검색하기</button>
														</form>
													</div>
												</div>
												<hr>
												<ul id="placesList"></ul>
												<div id="pagination"></div>
											</div>										
										</div>
										<br>										
										<p style="margin-top: 12px">
											<em class="link"> <a href="javascript:void(0);"
												onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
													혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요. </a>
											</em>
										</p>
										<script type="text/javascript"
											src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ea6bda86230b8415e663eb00385b3b43&libraries=services"></script>

									</div>
								</div>
								
								
								<div class="participant clearfixed">							
									
				                    <!-- 멤버 영역 시작-->
				                    <div class="memberArea sc2_subsection">
				                        <div class="mainTitle">
				                            <h3 class="nemoMemTitle">참석자</h3>
				                            <!-- <span>(<span class="currentNum">${groupNum}</span>/<span class="maxNum">${groupVO.mem_no}</span>)</span> -->
				                            <span id="attendCnt">현재 참석인원 : </span>
				                        </div>
				                        <span class="btnEventPrev" title="이전보기"><i class="fa-solid fa-chevron fa-chevron-left"></i></span>
				                        <div class="animationSlide">
				                            <div id="sliderPanel_id" class="sliderPanel">
				                                <!-- <p><center>이미지슬라이드영역~</center></p>
				                                	<div class="slideContent">
				                                		<div class="memImg">
				                                			<img src="${contextPath}/userImageDownload?user_id=${user.user_id}&user_img=${user.user_img}" />
				                                		</div>
				                                		<br/>
				                                		<div class="memName">
				                                			<span>${user.nickname}</span>
				                                		</div>
				                                	</div> -->
				                            </div>
				                            <!-- slide panel 종료-->
				                        </div>
				                        <!--animation-slide종료-->
				                        <span class="btnEventNext" title="다음보기"><i class="fa-solid fa-chevron fa-chevron-right"></i></span>
				                    </div>
				                    <!-- 멤버 영역 끝-->								
	
								</div>
								<div id="submit_modify_button" class="editBtn">
									<button type="button" id="modScheduleBtn" class="button btnEdit" onclick="fn_modify_schedule(this.form, ${param.group_id})">수정반영하기</button>
									<input type="button" value="취소" onclick="toSchedule(frmSchedule, ${param.group_id})" class="button buttonCancle">
								</div>
								
								
								<div id="modify_button" class="editBtn">
									<input type="button" value="수정" onclick="fn_mod_able()" class="button btnEdit">
									<button type="button" id="delScheduleBtn" class="buttonCancle btnDel" onclick="fn_delete_schedule()">삭제</button>									
								</div>
								
								<div id="submit_button" class="editBtn">
									<input type="button" value="등록" onclick="fn_add_schedule(this.form);" class="button btnResi" />
									<input type="button" value="취소" onclick="toSchedule(frmSchedule, ${param.group_id})" class="button buttonCancle" />
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- </div> -->
	

	<!-- 푸터 영역 시작 -->
	<jsp:include page="../../footer.jsp" flush="true"></jsp:include>
	<!-- 푸터 영역 끝 -->

</body>
</html>