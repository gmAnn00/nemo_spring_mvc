<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
    
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
    <link rel="stylesheet" href="${contextPath}/resources/css/qnaView.css" />
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/resources/js/header.js"></script>
    <script src="${contextPath}/resources/js/qnaView.js"></script>
    
    <script type="text/javascript">
	
	</script>


  </head>
  <body>
    <!-- header 시작 -->
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->


    <!-- 콘텐츠 영역 -->
    <div class="section2">
      <!-- 소모임 내부 메뉴(공통) 코딩 필요 -->
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
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 메뉴바 종료 -->
        </c:otherwise>
       </c:choose>
       
        <!-- 게시글 조회 영역 시작 -->
        <div class="boardView sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">고객센터 Q&A</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>고객센터</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>Q&A</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

			<div class="atricleArea">
	          <div class="articleContentBox">
	            <!-- 제목 영역 -->
	            <div class="articleHeader">
	              <div class="articleTitle">
	              	<div class="titleHead">
	              		<span class="brackets">
			             	<c:choose>
			             		<c:when test="${qna.user_id eq 'admin'}">답변</c:when>
			             		<c:otherwise>문의</c:otherwise>
			             	</c:choose>
	                	</span>
	                </div>
	                <div class="titleArea">${qna.title}</div>
	              </div>
	              <div class="writerInfo">
	                <div class="thumbArea">
	                  <img src="${contextPath}/userimagedownload?user_id=${qna.user_id}&user_img=${qna.user_img}" alt="프로필사진"/>
	                </div>
	                <div class="profileArea">
	                  <div class="profileInfo">
	                    <p class="writerNick">${qna.nickname }</p>
	                    <p class="date comDate"><fmt:formatDate value="${qna.create_date}" pattern="yyyy-MM-dd" /></p>
	                  </div>
	                </div>
	              </div>
	              </div>
	            </div>
	            <!-- 내용 영역 -->
	            <div id="contentArea" class="contentArea">
	          		<div class="contentEditTool">
		            <!-- 기능 구현에 따라 코딩 수정요 -->
			            <c:if test="${admin eq 0 && user_id eq qna.user_id}">			          
				            <a href="javascript:void(0)" role="button" class="btnEdit btn" onclick="fn_modify_article(${qna.qna_no})">수정</a>
				            <a href="javascript:void(0)" role="button" class="btnDel btn" onclick="fn_deleteChk(${qna.qna_no})">삭제</a>
			            </c:if>
			            <c:if test="${admin eq 1 and qna.user_id != user_id}">
				            <a href="javascript:void(0)" role="button" class="btnEdit btn" onclick="fn_reply_form(${qna.qna_no}})">답글쓰기</a>
			            </c:if>
				         <c:if test="${admin eq 1 and qna.user_id eq user_id}">
				         	<a href="javascript:void(0)" role="button" class="btnEdit btn" onclick="fn_modify_article(${qna.qna_no})">수정</a>
				            <a href="javascript:void(0)" role="button" class="btnDel btn" onclick="fn_deleteChk(${qna.qna_no})">삭제</a>
			            </c:if>
		       		</div>
	            
	              <div class="content">
	            		${qna.content}
	            	</div>
	            </div>
	            
	          <!-- 글 아래쪽 버튼 영역 -->
	          <div class="articleBottomBtns">
	            <!-- 기능 구현에 따라 코딩 수정요 -->
	            <div class="leftArea">
	              <!-- 본인 글이면 수정 삭제 뜨고 아니면 글쓰기만 뜨도록 -->
		            <c:if test="${admin eq 0 && user_id eq qna.user_id}">		
		            		<a href="${contextPath}/qna/qnaform" class="btn">새글쓰기</a>	          
				            <a href="javascript:void(0)" role="button" class="btnEdit btn" onclick="fn_modify_article(${qna.qna_no})">수정</a>
				            <a href="javascript:void(0)" role="button" class="btnDel btn" onclick="fn_deleteChk(${qna.qna_no})">삭제</a>
			            </c:if>
			            <c:if test="${admin eq 1 and qna.user_id != user_id}">
				            <a href="javascript:void(0)" role="button" class="btnEdit btn" onclick="fn_reply_form(${qna.qna_no}})">답글쓰기</a>
			            </c:if>
				         <c:if test="${admin eq 1 and qna.user_id eq user_id}">
				         	<a href="javascript:void(0)" role="button" class="btnEdit btn" onclick="fn_modify_article(${qna.qna_no})">수정</a>
				            <a href="javascript:void(0)" role="button" class="btnDel btn" onclick="fn_deleteChk(${qna.qna_no})">삭제</a>
			            </c:if>
	            </div>
	            <div class="rightArea">
	              <a href="${contextPath}/qna" role="button" class="btnList btn">목록</a>
	              <a href="#boardView" role="button" class="btn btnTop "><i class="fa-solid fa-caret-up"></i>TOP</a>
	            </div>
	          </div>
          </div>
        </div>
        <!-- 게시글 조회 영역 끝 -->
      </div>
    </div>

<jsp:include page="../footer.jsp" flush="true"></jsp:include>
  </body>
</html>
