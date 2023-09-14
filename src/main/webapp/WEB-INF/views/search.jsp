<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="totGroup" value="${resultLength}" />
<c:set var="section" value="${searchMap.section}"/>
<c:set var="pagenum" value="${searchMap.pagenum}"/>

<c:choose>
	<c:when test="${section > totGroup/100}">
		<c:set var="endValue" value="${(totGroup%100)%10 == 0 ? (totGroup % 100)/10 : (totGroup % 100)/10 + 1}"/>
	</c:when>
	<c:otherwise>
		<c:set var="endValue" value="10" />
	</c:otherwise>
</c:choose>

<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>네모: 동네모임</title>
<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/search.css" />
<script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ea6bda86230b8415e663eb00385b3b43&libraries=services"></script>
<script src="${contextPath}/resources/js/header.js"></script>
<script src="${contextPath}/resources/js/search.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	<!--카테고리 영역-->
	<div id="contentsArea">
		<div class="formArea">
			<form action="${contextPath}/search" method="get"
				class="searchBtn" id="searchForm">
				<input type="hidden" id="userLat" name="user_lat" />
				<input type="hidden" id="userLng" name="user_lng" />
				<div class="searchText1">
					<h2>소모임 이름 검색</h2>
				</div>
				<div class="categoriesArea">
					<!--카테고리-->
					<div class="categories01">
						<div class="bar">
							<!--대분류-->
							<div class="bar01">
								<select name="main_cate" class="barSel" ></select>
							</div>
							<!--소분류-->
							<div class="bar02">
								<select name="sub_cate" class="barSel" ></select>
							</div>
							<!--지역-->
							<div class="bar03">
								<select name="area" class="barSel">
									<option value="-1">무제한</option>
									<option value="5">5km 이내</option>
									<option value="10">10km 이내</option>
									<option value="20">20km 이내</option>
								</select>
							</div>
						</div>
					</div>
				
					<div class="nameSearch">
						

						<div class="nameBtn">
							<input type="text" class="searchText" name="keyword"
								value="${searchMap.searchText}" />

							<button type="submit" class="button">검색</button>
						</div>
					</div>

					
				</div>

				<div class="result">
					<div class="resultText">
						<h3>검색결과(${totGroup} 개)</h3>
					</div>
					<div class="resultBtn">
						<input type="checkbox" class="hidden" name="joinable" id="joinAble"/>
						<label for="joinAble" id="joinAbleLabel" class="joinAbleLabel" onclick="resultJoinAble('joinAble')">가입가능한 소모임만 표시</label>
						
						<input type="radio" class="hidden" name="sort" id="sortByName" value="name" />
						<label id="sortByNameLabel" for="sortByName" class="buttonSort" onclick="sortSubmit('sortByName')">이름순정렬</label>
						
						<input type="radio" class="hidden" name="sort" id="sortByBookmark" value="bookmark" />
						<label id="sortByBookmarkLabel" for="sortByBookmark" class="buttonSort" onclick="sortSubmit('sortByBookmark')">찜순정렬</label>
						
						<input type="radio" class="hidden" name="sort" id="sortByNumber" value="number" />
						<label id="sortByNumberLabel" for="sortByNumber" class="buttonSort" onclick="sortSubmit('sortByNumber')">사람많은순</label>
					</div>
				</div>
			</form>
		</div>
		<div class="searchResult">
			<div class="resultGroup">
				<c:if test="${empty resultList}">
					<div id="noResultArea">
						<div>
							<h4>검색 결과가 없습니다.</h4>
						</div>
						<div id="gotoCreateDiv">
							<a id="gotoCreate" class="button"
								href="${contextPath}/group/creategroupform">소모임 만들기</a>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty resultList}">
					<c:forEach var="result" items="${resultList}">
						<div class="group">
							<div class="groupImg">
									<img alt="소모임 이미지"
									src="${contextPath}/groupimagedownload?group_id=${result.group_id}&group_img=${result.group_img}" />
							</div>
							<div class="SteamedImg">
								<button type="button" class="grpLikeBtn" title="소모임 찜하기">
									<c:if test="${result.isBookmark}">
										<span class="grpLike grpLike${result.group_id} on" onclick="bookmarkClick('${user_id}', '${result.group_id}')">
										<svg viewBox="0 0 24 24">
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
									</span> <span class="hidden">찜하기</span>
									</c:if>
									<c:if test="${!result.isBookmark}">
										<span class="grpLike grpLike${result.group_id}" onclick="bookmarkClick('${user_id}', '${result.group_id}')"> <svg viewBox="0 0 24 24">
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
									</span> <span class="hidden">찜하기</span>
									</c:if>
								</button>
							</div>
							<a
								href="${contextPath}/group/groupinfo?group_id=${result.group_id}">
								<div class="groupText">
									<div class="groupText01 gt">
										<span>${result.main_cate}</span> | <span>${result.sub_cate}</span>
									</div>
									<div class="groupText02 gt">
										<span>${result.group_name}</span>
									</div>
									<div class="groupText03 gt">
										<i class="fas fa-map-marker-alt"></i> <span>
											${result.group_addr1}</span>
									</div>
									<div class="groupText04 gt">
										<i class="fa-solid fa-comment-dots"></i> <span>
											${result.group_desc}</span>
									</div>
									<div class="groupText05 gt">
										<i class="fa-solid fa-user"></i> <span>${result.current_memno}명</span>
									</div>
									<div class="groupText06 gt">
										<i class="fa-solid fa-heart"></i> <span>찜
											${result.bookmark_no}</span>
									</div>
								</div>
							</a>
						</div>

					</c:forEach>

				</c:if>


			</div>

			<div id="groupMap" class="GroupMap"></div>
		</div>
		<div class="PageBtn">
			<c:if test="${totGroup != 0}"> <!-- 게시글이 있을 경우 -->
				<c:forEach var="page" begin="1" end="${endValue}" step="1">
					<c:if test="${section > 1 && page == 1}">
						<a href="${contextPath}/search?main_cate=${param.main_cate}&sub_cate=${param.sub_cate}&area=${param.area}&keyword=${param.keyword}&section=${section-1}&pagenum=10&sort=${param.sort}">prev</a>
					</c:if>
					
					<c:choose>
						<c:when test="${page==pagenum}">
							<a style='color:var(--main-color)' href="${contextPath}/search?main_cate=${param.main_cate}&sub_cate=${param.sub_cate}&area=${param.area}&keyword=${param.keyword}&section=${section}&pagenum=${page}&sort=${param.sort}">${(section-1)*10 + page}</a>
						</c:when>
						<c:otherwise>
							<a href="${contextPath}/search?main_cate=${param.main_cate}&sub_cate=${param.sub_cate}&area=${param.area}&keyword=${param.keyword}&section=${section}&pagenum=${page}&sort=${param.sort}">${(section-1)*10 + page}</a>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${page == 10 and totGroup/100 > section}">
						<a href="${contextPath}/search?main_cate=${param.main_cate}&sub_cate=${param.sub_cate}&area=${param.area}&keyword=${param.keyword}&section=${section+1}&pagenum=1&sort=${param.sort}">next</a>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<input type="hidden" id="joinAble_hidden" value="${searchMap.joinable}" />
	<input type="hidden" id="sort_hidden" value="${searchMap.sort}" />
	<input type="hidden" id="main_name_hidden"
		value="${searchMap.main_cate}" />
	<input type="hidden" id="sub_name_hidden" value="${searchMap.sub_cate}" />
	<input type="hidden" id="areaBar_hidden" value="${searchMap.area}" />

	<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>
