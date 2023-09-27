<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html lang="ko">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>네모: 소모임 관리</title>
        <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
        <link href="${contextPath}/resources/DataTables/datatables.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/resources/css/adminGroup.css" />
        <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    	<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
    	<script src="${contextPath}/resources/js/header.js"></script>
        <script src="${contextPath}/resources/DataTables/datatables.min.js"></script>
        <script src="${contextPath}/resources/js/adminGroup.js"></script>
    </head>
    
    <body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>

        <!-- 콘텐츠 영역 시작 -->
        <div class="section2">
            <div class="sc2_contents">
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
                                        <div class="menu_submenu_name submenu_select"><span>소모임 관리</span></div>
                                        <i class="fa-solid fa-minus submenu_select"></i>
                                    </div>
                                </a>
                            </li>                            
                            <li>
                                <a href="${contextPath}/qna">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>고객센터 Q&A</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 메뉴바 종료 -->

                <div class="sc2_subsection">
                    <div class="sc2_subsection_title">
                        <h2 class="sc2_subsection_title_name">소모임 관리</h2>
                        
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="${contextPath}/index">
                                <i class="fa-solid fa-house nav_icon"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>관리자</span>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>소모임 관리</span>
                        </div>
                        <!-- nav 바 종료 -->

                    </div>

                    <div class="sc2_subcontents">
                        <div class="groupListArea">
                            <div class="groupList">
                                <table class="adminGroupTbl">
                                    <thead>
                                        <tr>
                                        	<th>No</th>
                                            <th>소모임 ID</th>
                                            <th>소모임장</th>
                                            <th>소모임 이름</th>
                                            <th>현재 인원</th>
                                            <th>최대 인원</th>
                                            <th>생성일</th>
                                            <th>신고횟수</th>
                                            <th>그룹삭제</th>
                                        </tr>
                                    </thead>
                                    
                                    <c:choose>
                                    	<c:when test="${empty groupList}">
                                    		<tr>
                                    			<td colspan="9" align="center">
                                    				등록된 소모임이 없습니다.
                                    			</td>
                                    		</tr>
                                    	</c:when>
                                    	
                                    	<c:when test="${!empty groupList}">
                                    	 	<c:forEach var="group" items="${groupList}" varStatus="status">
                                    	 		<tr align="center">
                                    	 			<td>${status.count}</td>
                                    	 			<td><a href="${contextPath}/group/groupmain?group_id=${group.group_id}">${group.group_id}</a></td>
                                    	 			<td>${group.group_leader}</td>
                                    	 			<td><a href="${contextPath}/group/groupmain?group_id=${group.group_id}">${group.group_name}</a></td>
                                    	 			<td>${group.current_memno}</td>
                                    	 			<td>${group.max_memno}</td>
                                    	 			<c:set var="formatDate" value="${group.create_date}" />
                                    	 			<td><fmt:formatDate value="${formatDate}" pattern="yyyy-MM-dd" type="date"/></td>
                                    	 			<td>${group.report_cnt}</td>
                                    	 			<td><button role="button" class="button" onclick="fn_Grpdelete(${group.group_id})">삭제</button></td>
                                    	 		</tr>
                                    	 	</c:forEach>
                                    	</c:when>
                                    </c:choose>
                                    
                                </table>
                            </div>
                            <!--<div class="groupTablePage"></div>  -->
                        </div>
                    </div>    
                </div>
            </div>
        </div>
        <!-- 콘텐츠 영역 종료-->

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>

    </body>
</html>
