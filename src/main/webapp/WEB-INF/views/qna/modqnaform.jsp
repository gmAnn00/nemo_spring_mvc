<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
        <link rel="stylesheet" href="${contextPath}/resources/css/qnaWrite.css" />
        <link rel="stylesheet" href="${contextPath}/resources/resources/summernote/summernote-lite.css" />
        <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="${contextPath}/resources/resources/summernote/summernote-lite.js"></script>
        <script src="${contextPath}/resources/resources/summernote/lang/summernote-ko-KR.js"></script>
        <script src="${contextPath}/resources/js/header.js"></script>
        <script src="${contextPath}/resources/js/modQnaArticle.js"></script>
    </head>

    <body>
        <!-- header 시작 -->
        <jsp:include page="../header.jsp" flush="true"></jsp:include>
        <!-- header 종료 -->

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

                <!-- 게시판 글쓰기 영역 시작 -->
                <div class="boardWrite sc2_subsection">
                    <!-- 메인 상단 타이틀 출력 부분-->
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
                    <!-- 메인 상단 타이틀 출력 부분 종료 -->

                    <!-- 글쓰기 영역 -->
                    <div class="boardWriteArea">
                        <form action="${contextPath}/qna/modqna" method="post" name="qnaArticleForm" id="qnaArticleForm">
                            <input type="hidden" name="qna_no" value="${qna.qna_no}" />
                            <input type="hidden" name="user_id" value="${qna.user_id}" />
                            
                            <!-- 제목 영역 -->
                            <div class="articleWritingTitle">
                                <!-- 제목 -->
                                <div class="titleArea">
                                    <input type="text" name="title" id="writeTitle" class="writeTitle" value="${qna.title}" />
                                </div>
                            </div>
                            
                            <!-- 글쓰는 영역 -->
                            <div class="editorArea">
                                <textarea id="summernote" name="content">${qna.content}</textarea>
                            </div>

                            <!-- 등록 버튼 -->
                            <div class="btnRegister">
                                <a href="#" role="button" class="button">수정</a>
                                <a href="javascript:void(0)" role="button" class="buttonCancle" onclick="fn_cancel(${qna.qna_no})">취소</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- 게시판 글쓰기 영역 끝 -->
        </div>

        <jsp:include page="../footer.jsp" flush="true"></jsp:include>
    </body>
</html>
