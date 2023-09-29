<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="qnaList" value="${qnaMap.qnaList}" />
<c:set var="totQna" value="${qnaMap.totQna}" />
<c:set var="section" value="${qnaMap.section }" />
<c:set var="pagenum" value="${qnaMap.pagenum }" />
<c:set var="qna" value="${qnaMap.qna }" />

<c:choose>
	<c:when test="${section >totQna/100 }">
		<c:set var="endValue" value="${(totQna mod 100)%10==0 ? (totQna mod 100)/10 : (totQna mod 100)/10 +1}"/>
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
    <title>네모: 고객센터</title>
    <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/helpQnA.css" />
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/resources/js/header.js"></script>
    <script src="${contextPath}/resources/js/qna.js"></script>
  </head>
  <body>
    <!-- header 시작 -->
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->

    <!-- section1 시작 -->
    
   	
    <!-- 콘텐츠 영역 -->
    <div class="section2">
    
      <div class="sc2_contents">
	      <c:choose>
	      	<c:when test="${admin eq 1}">
		        <!-- 메뉴바 시작 -->
		        <div class="sc2_menu_contents">
		          <div class="sc2_menu">
		            <h2 class="sc2_menu_title">관리자</h2>
		            <ul class="sc2_menu_list">
                        	<li>
                                <a href="${contextPath}/admin/adminuser">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>회원 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/admin/admingroup">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>소모임 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>                            
                            <li>
                                <a href="${contextPath}/qna">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name submenu_select"><span>고객센터 Q&A</span></div>
                                        <i class="fa-solid fa-minus submenu_select"></i>
                                    </div>
                                </a>
                            </li>
                        </ul>
		          </div>
		        </div>
	        	<!-- 메뉴바 종료 -->
	        </c:when>
	        <c:otherwise>
	        <!-- 메뉴바 시작 -->
	        <div class="sc2_menu_contents">
	          <div class="sc2_menu">
	            <h2 class="sc2_menu_title">고객센터</h2>
	            <ul class="sc2_menu_list">
	              <li>
	                <a href="${contextPath}/qna">
	                  <div class="sc2_icon_menu">
	                    <div class="menu_submenu_name submenu_select"><span>Q&A</span></div>
	                    <i class="fa-solid fa-minus submenu_select"></i>
	                  </div>
	                </a>
	              </li>
	            </ul>
	          </div>
	        </div>
	        <!-- 메뉴바 종료 -->
	        </c:otherwise>
	       </c:choose>
       
       
        <!-- 게시판 영역 시작 -->
        <div class="board sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">고객센터 Q&A</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>고객센터</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>Q&A</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

		  
		  <div class="boardArea">
			  <form action="${contextPath}/qna/qnasearch" id="search" name="search" method="get">
		          <div class="search">
		            <!-- 검색 부분 필요할 듯 , input 태그에 name, id 넣어야 함 -->
		            <select name="filter" id="searchSelect">
		              <option value="title">제목</option>
		              <option value="content">내용</option>
		              <option value="writer">작성자</option>
		            </select>
		            <input type="text" name="keyword" id="keyword" onkeyup="if(window.event.keyCode==13){submitSearchForm()}"/>
		            <button class="button searchBtn" type="submit" >검색</button>
		          </div>
		       </form>
	          
	          <div class="boardListArea">
	            <table class="boardList">
	              <tr class="boardListHead">
	                <th>번호</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	              </tr>
	              <tbody>
	              	<c:choose>
	              		<c:when test="${empty qnaList}">
	              			<tr>
	              				<td colspan="6" class="emptyList">등록된 글이 없습니다.</td>
	              			</tr>
	              		</c:when>
	              		<c:when test="${!empty qnaList}">
	              			<c:forEach var="article" items="${qnaList}" varStatus="articleNum">
				                <tr>
				                	<!-- <td>${totQna-(pagenum-1)*10+articleNum.count}</td> -->
				                	<!-- <td>${totQna-(section-1)*10-articleNum.index}</td>  -->
				                  <td>${(totQna-(pagenum-1)*10-articleNum.index)-((section-1)*100)}</td>
				                  <td>
				                    <div class="titleArea">
				                    	<div class="titleInner">
					                        <c:choose>
					                        	<c:when test="${article.LVL>1}">
					                        		<c:forEach begin="1" end="${article.LVL-1}" step="1">
									               		<span style="padding-left: 10px"></span>
					                        		</c:forEach>
					                        		└ Re : <a href="${contextPath}/qna/viewqna?qna_no=${article.qna_no}">${article.title}</a>
					                        	</c:when>
					                        	<c:otherwise>
					                        		<a href="${contextPath}/qna/viewqna?qna_no=${article.qna_no}">${article.title}</a>
					                        	</c:otherwise>
					                        </c:choose>
					                      </a>
						                  
					                    </div>
				                    </div>
				                  </td>
				                  <td>${article.nickname}</td>
				                  <td><fmt:formatDate value="${article.create_date}" pattern="yyyy-MM-dd" /></td>
				                  
				                </tr>
			            	</c:forEach>
		                </c:when>
					</c:choose>
	              </tbody>
	            </table>
	            <c:if test="${admin eq 0}">
		            <div class="bottomBtn">
		              <a href="${contextPath}/qna/qnaform" role="button" class="button">글쓰기</a>
		            </div>
	            </c:if>
	            
	            <div class="pagenation">
					<c:if test="${totQna != 0}">
						<c:forEach var="page" begin="1" end="${endValue}" step="1">
							<c:if test="${section>1 && page==1}">
								<span class="paging prev"><a href="${contextPath}/qna?section=${section-1}&pagenum=10">&lt</a></span>
							</c:if>
								
							<c:choose>
								<c:when test="${page==pagenum}">
									<span class="paging currentPage"><a href="${contextPath}/qna?section=${section}&pagenum=${page}">${(section-1)*10+page}</a></span>
								</c:when>
								
								<c:otherwise>
									<span class="paging notCurrent"><a href="${contextPath}/qna?section=${section}&pagenum=${page}">${(section-1)*10+page}</a></span>
								</c:otherwise>
							</c:choose>
								
							<c:if test="${page==10 and totQna/100>section}">
								<span class="paing next"><a href="${contextPath}/qna?section=${section+1}&pagenum=1">&gt</a></span>
							</c:if>
						</c:forEach>
					</c:if>

	            </div>
	            <!-- 페이징 영역 끝 -->
            </div>
          </div>
        </div>
        <!-- 게시판 영역 끝 -->
      </div>
    </div>

<jsp:include page="../footer.jsp" flush="true"></jsp:include>
  </body>
</html>
