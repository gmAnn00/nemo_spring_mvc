<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="boardList" value="${boardMap.boardList}"/>
<c:set var="totBoard" value="${boardMap.totBoard}" />
<c:set var="section" value="${boardMap.section}" />
<c:set var="pagenum" value="${boardMap.pagenum}" />
<c:set var="filter" value="${boardMap.filter}" />
<c:set var="keyword" value="${boardMap.keyword}" />
<c:set var="noticeList" value="${boardMap.noticeList}"/>


<c:choose>
	<c:when test="${section >totBoard/100 }">
		<c:set var="endValue" value="${(totBoard mod 100)%10==0 ? (totBoard mod 100)/10 : (totBoard mod 100)/10 +1}"/>
	</c:when>
	
	<c:otherwise>
		<c:set var="endValue" value="10" />
	</c:otherwise>
</c:choose>

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>네모: 게시판</title>
    <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/board.css" />
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/resources/js/header.js"></script>
    <script src="${contextPath}/resources/js/board.js"></script>
  </head>
  <body>
    <!-- header 시작 -->
	<jsp:include page="./../../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->

	<!-- section1 -->
	<jsp:include page="./../groupheader.jsp" flush="true"></jsp:include>
	<!-- section1종료 -->

    <!-- 콘텐츠 영역 -->
    <div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">게시판</h2>
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
	                              <div class="menu_submenu_name submenu_select"><span>게시판</span></div>
	                              <i class="fa-solid fa-minus submenu_select"></i>
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
		                              <div class="menu_submenu_name submenu_select"><span>게시판</span></div>
		                              <i class="fa-solid fa-minus submenu_select"></i>
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

        <!-- 게시판 영역 시작 -->
        <div class="board sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">게시판</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>나의 모임</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>게시판</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

		  
		  <div class="boardArea">
		  	 <!-- <form action="${contextPath}/group/board/search?group_id=${param.group_id}" id="search" name="search" method="get"> -->
		     <form action="${contextPath}/group/board/search?group_id=${param.group_id}" id="search" name="search" method="get">
	          	<div class="search">
	            <!-- 검색 부분 필요할 듯 , input 태그에 name, id 넣어야 함 -->
		            <input type="hidden" name="group_id" value="${param.group_id}"/>
		            <div class="selectArea">
			            <select name="filter" id="searchOption">
			              <option value="title">제목</option>
			              <option value="content">내용</option>
			              <option value="writer">작성자</option>
			            </select>
		            </div><div class="keywordArea">
		            	<input type="text" name="keyword" id="keyword" onkeyup="if(window.event.keyCode==13){submitSearchForm()}"/>
		            	<a href="#" role="button" class="button searchBtn" type="submit" onclick="submitSearchForm()">검색</a>
	         		</div>
	          	</div>
	          </form>
	          <div class="category">
	          	<a href="${contextPath}/group/board?group_id=${param.group_id}" class="categoryLink">
					<span class="title">전체</span>
				</a>
				<a href="${contextPath}/group/board/search?group_id=${param.group_id}&filter=brackets&keyword=공지" class="categoryLink">
					<span class="title brackets">공지</span>
					<div class="notice bracketFilter"></div>
				</a>
		        <a href="${contextPath}/group/board/search?group_id=${param.group_id}&filter=brackets&keyword=자유" class="categoryLink">
					<span class="title brackets">자유	</span>
					<div class="free bracketFilter"></div>
				</a>
		        <a href="${contextPath}/group/board/search?group_id=${param.group_id}&filter=brackets&keyword=후기" class="categoryLink">
					<span class="title brackets">후기	</span>
					<div class="review bracketFilter"></div>
				</a>
	          </div>
	          <div class="boardListArea">
	            <table class="boardList">
	              <tr class="boardListHead">
	                <th>번호</th>
	                <th>말머리</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	                <th>조회</th>
	              </tr>
	              <tbody>
	              	<c:choose>
	              		<c:when test="${empty boardList}">
	              			<tr>
	              				<td colspan="6" class="emptyList">등록된 글이 없습니다.</td>
	              			</tr>
	              		</c:when>
	              		<c:when test="${!empty boardList}">
	              			<c:if test="${not empty noticeList}">
	              				<c:forEach var="notice" items="${noticeList}" varStatus="noticeNum">
	              					<tr class="noticeTr">
	              						<!-- <td>${fn:length(noticeList)-noticeNum.index}</td> -->
	              						<td>${noticeNum.count}</td>
	              						<td>${notice.brackets}</td>
						                <td class="tdArticle">
					                    	<div class="titleArea">
						                    	<div class="titleInner">
									            	<a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&article_no=${notice.article_no}" class="titleLink">
									                	<span class="title">${notice.title}</span>
									                 </a>
										             <div class="commentArea">
										             	<a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&article_no=${notice.article_no}" class="cmtLink">
										                	<span class="cmt">${notice.com_cnt}</span>
										                </a>
										             </div>
								                 </div>
						                    </div>
						                  </td>
						                  <td>${notice.nickname}</td>
				                  		  <td>${notice.create_date}</td>
				                  		  <td>${notice.view_cnt}</td>
	              					</tr>
	              				</c:forEach>
	              			</c:if>
	              			<c:forEach var="board" items="${boardList}" varStatus="boardNum">
				                <tr>
				                	<!--  <td>${totArticles-(section-1)*10-articleNum.index}</td>-->
				                    <td>${(totBoard-(pagenum-1)*10-boardNum.index)-((section-1)*100)}</td>
				                  <td>${board.brackets}</td>
				                  <td class="tdArticle">
				                    <div class="titleArea">
				                    	<div class="titleInner">
				                    		<c:choose>
				                    			<c:when test="${not empty filter}">
							                      <a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&filter=${filter}&keyword=${keyword}&article_no=${board.article_no}" class="titleLink">
							                        <span class="title">${board.title}</span>
							                      </a>
								                  <div class="commentArea">
								                     <a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&filter=${filter}&keyword=${keyword}&article_no=${board.article_no}" class="cmtLink">
								                      <span class="cmt">${board.comment_cnt}</span>
								                     </a>
								                  </div>
						                     	</c:when>
						                    	<c:otherwise>
						                     	 <a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&article_no=${board.article_no}" class="titleLink">
						                        	<span class="title">${board.title}</span>
						                      	 </a>
							                  	 <div class="commentArea">
							                     	<a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&article_no=${board.article_no}" class="cmtLink">
							                      		<span class="cmt">${board.comment_cnt}</span>
							                     	</a>
							                     </div>
						                     	</c:otherwise>
						                     </c:choose>
						                   </div>
					                    </div>
				                  </td>
				                  <td>${board.nickname}</td>
				                  <td>${board.create_date}</td>
				                  <td>${board.view_cnt}</td>
				                </tr>
			            	</c:forEach>
		                </c:when>
					</c:choose>
	              </tbody>
	            </table>
	            
	            <div class="bottomBtn">
	              <a href="${contextPath}/group/board/boardform?group_id=${param.group_id}" role="button" class="button">글쓰기</a>
	            </div>
	            
	            <div class="pagenation">
	            	<c:choose>
	            		<c:when test="${(totBoard != 0) and (!empty filter)}">
							<c:forEach var="page" begin="1" end="${endValue}" step="1">
								<c:if test="${section>1 && page==1}">
									<span class="paging prev"><a href="${contextPath}/group/board/search?group_id=${param.group_id}&filter=${filter}&keyword=${keyword}&section=${section-1}&pagenum=10">&lt</a></span>
								</c:if>
									
								<c:choose>
									<c:when test="${page==pagenum}">
										<span class="paging currentPage"><a href="${contextPath}/group/board/search?group_id=${param.group_id}&filter=${filter}&keyword=${keyword}&section=${section}&pagenum=${page}">${(section-1)*10+page}</a></span>
									</c:when>
									
									<c:otherwise>
										<span class="paging notCurrent"><a href="${contextPath}/group/board/search?group_id=${param.group_id}&filter=${filter}&keyword=${keyword}&section=${section}&pagenum=${page}">${(section-1)*10+page}</a></span>
									</c:otherwise>
								</c:choose>
									
								<c:if test="${page==10 and totBoard/100>section}">
									<span class="paing next"><a href="${contextPath}/group/board/search?group_id=${param.group_id}&filter=${filter}&keyword=${keyword}&section=${section+1}&pagenum=1">&gt</a></span>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="page" begin="1" end="${endValue}" step="1">
								<c:if test="${section>1 && page==1}">
									<span class="paging prev"><a href="${contextPath}/group/board?group_id=${param.group_id}&section=${section-1}&pagenum=10">&lt</a></span>
								</c:if>
									
								<c:choose>
									<c:when test="${page==pagenum}">
										<span class="paging currentPage"><a href="${contextPath}/group/board?group_id=${param.group_id}&section=${section}&pagenum=${page}">${(section-1)*10+page}</a></span>
									</c:when>
									
									<c:otherwise>
										<span class="paging notCurrent"><a href="${contextPath}/group/board?group_id=${param.group_id}&section=${section}&pagenum=${page}">${(section-1)*10+page}</a></span>
									</c:otherwise>
								</c:choose>
									
								<c:if test="${page==10 and totBoard/100>section}">
									<span class="paing next"><a href="${contextPath}/group/board?group_id=${param.group_id}&section=${section+1}&pagenum=1">&gt</a></span>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
	            </div>
	            <!-- 페이징 영역 끝 -->
            </div>
          </div>
        </div>
        <!-- 게시판 영역 끝 -->
      </div>
    </div>

	<jsp:include page="./../../footer.jsp" flush="true"></jsp:include>
  </body>
</html>
