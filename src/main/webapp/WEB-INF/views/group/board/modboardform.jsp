<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
    <link rel="stylesheet" href="${contextPath}/resources/css/boardWrite.css" />
    <link rel="stylesheet" href="${contextPath}/resources/resources/summernote/summernote-lite.css"/>
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
	<script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/resources/summernote/summernote-lite.js"></script>
    <script src="${contextPath}/resources/resources/summernote/lang/summernote-ko-KR.js"></script>
    <!-- <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet"> -->
    <script src="${contextPath}/resources/js/header.js"></script>
    <script src="${contextPath}/resources/js/modArticle.js"></script>
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

        <!-- 게시판 글쓰기 영역 시작 -->
        <div class="boardWrite sc2_subsection">

          <!-- 메인 상단 타이틀 출력 부분-->
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
          <!-- 메인 상단 타이틀 출력 부분 종료 -->

          <!-- 글쓰기 영역 -->
          <div class="boardWriteArea">         
              <form action="${contextPath}/group/board/modboard" method="post" name="articleForm" id="articleForm">
              <input type="hidden" name="group_id" value="${param.group_id}"/>
              <input type="hidden" name="article_no" value="${board.article_no}"/>
              <input type="hidden" name="user_id" value="${board.user_id}"/>
              <!-- 제목 영역 -->
              <div class="articleWritingTitle">
                <!-- 말머리 컨텐츠 확인 필요 -->
                <div class="headTitleArea">
	                <select name="brackets" id="headTitle" class="headTitle">
	                  <option value="">말머리</option>
	                  <c:if test="${isLeader == true}">
	                  	<option value="공지" <c:if test="${board.brackets eq '공지'}">selected</c:if>>공지</option>
	                  </c:if>
	                  <option value="자유" <c:if test="${board.brackets eq '자유'}">selected</c:if>>자유</option>
	                  <option value="후기" <c:if test="${board.brackets eq '후기'}">selected</c:if>>후기</option>
	                </select>
                </div>
                <!-- 제목 -->
                <div class="titleArea">
                	<input type="text" name="title" id="writeTitle" class="writeTitle" value="${board.title}"></input>
              	</div>
              </div>
              <!-- 글쓰는 영역 -->
              <div class="editorArea">
                <textarea id="summernote" name="content">${board.content}</textarea>
              </div>
              <!-- 수정 버튼 -->
              <div class="btnRegister">
                <a href="#" role="button" class="button">수정</a>
                <a href="#" role="button" class="buttonCancle" onclick="fn_cancel(${param.group_id}, ${param.article_no})">취소</a>
              </div>

            </form>


            </div>
          </div>
        </div>
        <!-- 게시판 글쓰기 영역 끝 -->
      </div>
    </div>

	<jsp:include page="./../../footer.jsp" flush="true"></jsp:include>

  </body>
</html>