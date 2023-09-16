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
<title>네모: 동네모임</title>
<link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/index.css" />
<script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/js/header.js"></script>
<script src="${contextPath}/resources/js/index.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	
	<!--메인 이미지 영역-->
        <div class="intro">
            <div class="intro-slideshow">
                <img src="${contextPath}/resources/images/main_bike2.jpg" />
                <img src="${contextPath}/resources/images/main_book.jpg" />
                <img src="${contextPath}/resources/images/main_music2.jpg" />
                <img src="${contextPath}/resources/images/main_pet.jpg" />
                <img src="${contextPath}/resources/images/main_culture.jpg.jpg" />
            </div>
            <div class="intro-header">
                <h1>새로운 경험과<br> 친구를 만나보세요</h1>
                <p>소모임 멤버들 간의 소통을 위한 온라인 그룹 플랫폼 <br>여러분의 다양한 관심사와 취향을 함께 나눌 수 있는 네모입니다</p>
            </div>
        </div>

        <!-- 카테고리 슬라이드 영역 시작 -->
        <div class="categories">
            <div class="categoriesText">
                <h1 class="categories_title">소모임 카테고리</h1>
            </div>

            <div class="flowBanner1">
                <ul class="list01">
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=문화 · 공연 · 축제&sub_cate=none&area=-1&keyword=&sort=name">
                    		<div class="imgBox sle1">
                           		<span class="box_text">문화,공연</span>
                        	</div>
                    	</a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=음악 · 악기&sub_cate=none&area=-1&keyword=&sort=name">
	                        <div class="imgBox sle2">
	                            <span class="box_text">음악,악기</span>
	                        </div>
	                    </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=사진 · 영상&sub_cate=none&area=-1&keyword=&sort=name">
	                        <div class="imgBox sle3">
	                            <span class="box_text">사진,영상</span>
	                        </div>
                       	</a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=아웃도어 · 여행&sub_cate=none&area=-1&keyword=&sort=name">
	                        <div class="imgBox sle4">
	                            <span class="box_text">아웃도어</span>
	                        </div>
	                    </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=운동 · 스포츠&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle5"><span class="box_text">스포츠</span></div>
                        </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=인문학 · 책 · 글&sub_cate=none&area=-1&keyword=&sort=name">
	                        <div class="imgBox sle6">
	                            <span class="box_text">인문학,책</span>
	                        </div>
                        </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=외국 · 언어&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle7"><span class="box_text">언어</span></div>
                        </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=공예 · 만들기&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle8"><span class="box_text">공예</span></div>
                       	</a>
                    </li>
                </ul>
            </div>

            <div class="flowBanner2">
                <ul class="list02">
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=댄스 · 무용&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle9"><span class="box_text">댄스</span></div>
                        </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=봉사활동&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle10"><span class="box_text">봉사</span></div>
                        </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=사교 · 인맥&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle11"><span class="box_text">사교</span></div>
                        </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=차 · 오토바이&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle12"><span class="box_text">자동차</span></div>
                       	</a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=스포츠 관람&sub_cate=none&area=-1&keyword=&sort=name">
	                        <div class="imgBox sle13">
	                            <span class="box_text">경기관람</span>
	                        </div>
	                    </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=게임 · 오락&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle14"><span class="box_text">게임</span></div>
                       	</a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=요리 · 제조&sub_cate=none&area=-1&keyword=&sort=name">
                        	<div class="imgBox sle15"><span class="box_text">요리</span></div>
                        </a>
                    </li>
                    <li>
                    	<a href="${contextPath}/search?user_lat=none&user_lng=none&main_cate=반려동물&sub_cate=none&area=-1&keyword=&sort=name">
	                        <div class="imgBox sle16">
	                            <span class="box_text">반려동물</span>
	                        </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 카테고리 슬라이드 영역 종료 -->
        
        <!-- 소모임 가입하기, 만들기 영역 시작 -->
        <div class="group_jm">
            <div class="jm_text">
                <h2>여기는 다함께 즐기는 공간 <span>네모</span>입니다</h2>
                <p>좋아하는 활동에 참여하여 새로운 친구들을 만들고 즐거운 시간을 함께 보내세요</p>
            </div>
            
            <div class="group_jm_boxes">
                
                <div class="group_jm_box jm_box1">
                    <div class="group_jm_text">
                        <span>소모임 만들기</span>
                        <p>좋아하는 취미생활을 모두와 함께 시작해보세요</p>
                    </div>
                    <a href="${contextPath}/group/creategroupform">
	                    <div class="group_btn">
	                        <span class="btn_more">더보기</span>
	                        <i class="fa-solid fa-angle-right"></i>
	                    </div>
                    </a>
                    <img class="group_jm_img jm_img1" src="${contextPath}/resources/images/—Pngtree—people make puzzles concept team_5356575.png" alt="bg">
                </div>
                
                <div class="group_jm_box jm_box2">
                    <div class="group_jm_text">
                        <span>소모임 가입하기</span>
                        <p>새로운 아이디어를 공유하고 서로의 경험을 나누어 보세요</p>
                    </div>
                    <a href="${contextPath}/search">
	                    <div class="group_btn">
	                        <span class="btn_more">더보기</span>
	                        <i class="fa-solid fa-angle-right"></i>
	                    </div>
                    </a>
                    <img class="group_jm_img jm_img2" src="${contextPath}/resources/images/—Pngtree—best friends happy taking selfie_5435706.png" alt="bg">
                </div>

            </div>
        </div>
        
        <!-- 소모임 가입하기, 만들기 영역 종료 -->
        
        
        <div class="group">
        	<!-- 로그인 안했을 떄 랜덤 소모임 보여줌 -->
        	<c:if test="${user_id == null}">
        		<div class="smallPage">
	                <div class="smallText">
	                    <h3 class="smallText_title">추천 소모임</h3>
	                </div>
	                <div class="cards">
	               		 <c:forEach var="group" items="${randomGroupList}">
		                    <div class="card card--1">
		                        <div class="card__info-hover">
		                        </div>
		                        <div class="card__img"></div>
		                        <a href="${contextPath}/group/groupinfo?group_id=${group.group_id}" class="card_link">
		                            <div>
		                            	<img alt="소모임 프로필 사진" class="card__img--hover"
		                            	src="${contextPath}/groupimagedownload?group_id=${group.group_id}&group_img=${group.group_img}">
		                            </div>
		                        </a>
		                        <div class="card__info">
		                            <span class="card__category">${group.main_cate}</span>
		                            <span class="card__category">${group.sub_cate}</span>
		                            <h3 class="card__title">${group.group_name}</h3>
		                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="" class="card__author">${group.group_addr1}</a></span>
		                        </div>
		                    </div>
	                	
	              	  </c:forEach>
	                </div>
	            </div>
        	</c:if>
        	
        	<c:if test="${user_id != null}">
        		       <!-- 관심사, 소모임 보여주는 영역 시작 -->
	            <div class="smallPage">
	                <div class="smallText">
	                    <h3 class="smallText_title">관심사 소모임</h3>
	                </div>
	               <div class="cards">
	               		<c:forEach var="group" items="${interestsGroupList}">
		                    <div class="card card--1">
		                        <div class="card__info-hover">
		                        </div>
		                        <div class="card__img"></div>
		                        <a href="${contextPath}/group/groupinfo?group_id=${group.group_id}" class="card_link">
		                            <div>
		                            	<img alt="소모임 프로필 사진" class="card__img--hover"
		                            	src="${contextPath}/groupimagedownload?group_id=${group.group_id}&group_img=${group.group_img}">
		                            </div>
		                        </a>
		                        <div class="card__info">
		                            <span class="card__category">${group.main_cate}</span>
		                            <span class="card__category">${group.sub_cate}</span>
		                            <h3 class="card__title">${group.group_name}</h3>
		                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">${group.group_addr1}</a></span>
		                        </div>
		                    </div>
	               	   </c:forEach> 
	               </div>
	            </div>
	            
	            <div class="smallPage">
	                <div class="smallText">
	                    <h3 class="smallText_title">가까운 소모임</h3>
	                </div>
	                <div class="cards">
	              		 <c:forEach var="group" items="${nearGroupList}">
	               		
		                    <div class="card card--1">
		                        <div class="card__info-hover">
		                        </div>
		                        <div class="card__img"></div>
		                        <a href="${contextPath}/group/groupinfo?group_id=${group.group_id}" class="card_link">
		                            <div>
		                            	<img class="card__img--hover" alt="프로필 이미지 사진"
		                            	src="${contextPath}/groupimagedownload?group_id=${group.group_id}&group_img=${group.group_img}">
		                            </div>
		                        </a>
		                        <div class="card__info">
		                            <span class="card__category">${group.main_cate}</span>
		                            <span class="card__category">${group.sub_cate}</span>
		                            <h3 class="card__title">${group.group_name}</h3>
		                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">${group.group_addr1}</a></span>
		                        </div>
		                    </div>
	            	   </c:forEach>
	               </div>
	            </div>
            <!-- 관심사, 소모임 보여주는 영역 종료 -->
        	</c:if>
        </div>
        <!--가까운 소모임 영역 종료 -->
	
	<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>