<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<%
	request.setCharacterEncoding("utf-8");
%>   
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>네모: 소모임 관리</title>
        <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/submenu.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/sectionTitle.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/groupSetting.css" />
        <script src="https://kit.fontawesome.com/bc604c01cc.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
        <script src="${contextPath}/resources/js/header.js"></script>
        
    </head>
   
    <body>
        <jsp:include page="./../../header.jsp" flush="true"></jsp:include>
		
        	<!-- section1 -->
			<jsp:include page="./../groupheader.jsp" flush="true"></jsp:include>
			<!-- section1종료 -->

	        <!-- section2 시작 -->
	        <div class="section2">
	            <div class="sc2_contents">
	                <!-- 메뉴바 시작 -->
	                <div class="sc2_menu_contents">
	                    <div class="sc2_menu">
	                        <h2 class="sc2_menu_title">나의 모임</h2>
	                        <ul class="sc2_menu_list">
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
	                                        <div class="menu_submenu_name submenu_select"><span>소모임관리</span></div>
	                                        <i class="fa-solid fa-minus submenu_select"></i>
	                                    </div>
	                                </a>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                <!-- 메뉴바 종료 -->
	
	                <!-- 컨텐츠 시작 -->
	                <div class="sc2_subsection">
	                    <div class="sc2_subsection_title">
	                        <span class="sc2_subsection_title_name">소모임관리</span>
	                        <!-- nav 바 시작 -->
	                        <div class="nav_bar">
	                            <a href="../index.html">
	                                <i class="fa-solid fa-house nav_icon"></i>
	                            </a>
	                            <i class="fa-solid fa-angle-right nav_icon"></i>
	                            <span>나의 모임</span>
	                            <i class="fa-solid fa-angle-right nav_icon"></i>
	                            <span>소모임관리</span>
	                        </div>
	                        <!-- nav 바 종료 -->
	                    </div>
	                
	                    
	

	                    <!-- 소모임 생성 정보 입력 영역 -->
	                    <div class="formArea">
	                        <form
	                            action="${contextPath}/group/leader/modgroupsetting"
	                            method="post"
	                            id="modGroup"
	                            name="modGroup"
	                            enctype="multipart/form-data"
	                        >
	                        	<input type="hidden" name="group_id" value="${group.group_id}" />
	                            <!-- 프로필 사진 등록 영역-->
	                            <div class="uploadArea">
	                                <c:choose>
						                <c:when test="${empty group.group_img}">
						                	<div class="imgCrop">
						                		<img id="previewImage" src="" alt=" 프로필 사진" />
						                	</div>
						                </c:when>
						                <c:when test="${!empty group.group_img}">  
						                    <div class="imgCrop">
						                    	<img id="previewImage" class="grpImg"
						                    	src="${contextPath}/groupimagedownload?group_id=${group.group_id}&group_img=${group.group_img}" alt=" 프로필 사진" />
						                    </div>                      
						                </c:when>
					                </c:choose>
	                                <div class="profile_btn">
	                                    <label for="file" class="mod_btn">
	                                        <div class="button btnComm">수정</div>
	                                    </label>
	                                    <input
	                                        type="file"
	                                        name="group_img"
	                                        id="file"
	                                        class="hidden"
	                                        accept="image/gif, image/jpeg, image/png"
	                                        onchange="readImage(this);"
	                                    />
	                                    <input type="hidden" id="isDeleteImg" name="isDeleteImg"  value="false"/>
	                                    <div class="buttonCancle btnComm" onClick="popupImgFileRm();">취소</div>
	                                    <input type="hidden" name="originalFileName" value="${group.group_img}" />
	                                </div>
	                            </div>
	                            <fieldset class="formTbl">
	                                <legend class="hidden">소모임 생성 정보 입력</legend>
	                                    
	
	                                <div class="form_div">
	                                    <label class="profile_label" for="groupName">소모임 이름</label>
	                                    <input type="text" name="group_name"  minlength="2"
                  						maxlength="10"placeholder="2~10글자 이내" value="${group.group_name}" required/>

	                                </div>
	                                <div class="groupNum form_div">
	                                    <label for="groupNum" class="profile_label">소모임 인원</label>
	                                    <input
	                                    type="text"
	                                    name="max_memno"
	                                    id="groupNum"
	                                    maxlength="3"
	                                    placeholder="2~50"
	                                    value="${group.max_memno}"
	                                    required
	                                    /><span>명</span>
	                                </div>
	                                <div class="innerTblArea form_div">
	                                	<input type="hidden" id="main_cate_hidden" name="main_cate_hidden" value="${group.main_cate}">
	                                	<input type="hidden" id="sub_cate_hidden" name="sub_cate_hidden" value="${group.sub_cate}">
	                                    <label class="cateTbl profile_label">카테고리</label>
	                                    <table>
	                                    <tr>
	                                        <th>대분류</th>
	                                        <th>소분류</th>
	                                    </tr>
	                                    <tr>
	                                        <td>
						                      <div class="selectBox box1">
						                        <select
						                          name="main_cate"
						                          id="bigCate"
						                          onchange="fn_category_selected(this);"
						                          class="commonSelect input"
						                        >
						                        <!--  
						                        <option value="" class="selectOption">선택하기</option>   -->
						                        </select>
						                     
						                      </div>
						                    </td>
						                    <td>
						                      <div class="selectBox box2">
						                        <select
						                          name="sub_cate"
						                          id="smallCate"
						                          class="commonSelect select"
						                          onchange="fn_category_selected(this);"
						                        >
						                        <!--
						                        <option value="">선택하기</option>  -->
						                        </select>
						                      </div>
						                    </td>
	                                    </tr>
	                                    </table>
	                                    <p>※대분류/소분류 모두 선택하셔야 합니다.</p>
	                                </div>
	                                <div class="form_div">
	                                    <label for="findZipcode" class="profile_label">모임 장소</label>
	
	                                    <input
	                                    type="text"
	                                    id="zipcode"
	                                    name="group_zipcode"
	                                    maxlength="5"
	                                    size="5"
	                                    placeholder=""
	                                    value="${group.group_zipcode}"
	                                    readonly
	                                    required
	                                    />
	                                    <button
	                                    type="button"
	                                    class="findZipcode button"
	                                    id="findZipcode"
	                                    onclick="execDaumPostcode()"
	                                    >
	                                    우편번호 찾기
	                                    </button>
	                                    <input
	                                    type="text"
	                                    id="grp_addr1"
	                                    name="group_addr1"
	                                    placeholder=""
	                                    value="${group.group_addr1}"
	                                    readonly
	                                    />
	                                    <input
	                                    type="text"
	                                    id="grp_addr2"
	                                    name="group_addr2"
	                                    placeholder=""
	                                    value="${group.group_addr2}"
	                                    />
	                                </div>
	                                <div class="textAreaTd form_div">
	                                    <label for="grpDescription" class="profile_label">소모임 소개</label>
	                                    <textarea
	                                    name="group_desc"
	                                    id="grpDescription"
	                                    placeholder=""
	                                    rows="10"
	                                    maxlength="1000"
	                                    >${group.group_desc}</textarea>
	                                    <p>(<span><c:out value="${fn:length(group.group_desc)}"/>/1000</span>)</p>
	                                    
	                                </div>
	                                <div class="creatGrp form_div">
	                                    <a href="javascript:void(0)"
						                  type="button"
						                  name="createGrpBtn"
						                  id="createGrpBtn"
						                  class="button"
						                  onclick="Check_test()"
						                  value="수정"
						                > 수정 </a>
	                                    <input
	                                    type="reset"
	                                    name="createCancleBtn"
	                                    id="createCancleBtn"
	                                    class="buttonCancle"
	                                    value="취소"
	                                    />
	                                </div>
	                            </fieldset>
	                        </form>
	                    </div>
	                    
	                </div>
	                <!-- 컨텐츠 종료 -->
	                
	            </div>
	
	        </div>
	        <!-- section2 종료 -->
	        <script>
			  //var curMain = "${group.main_cate}";
			  //var curSub = "${group.sub_cate}";
			  function Check_test() {
		        	var arrSelect=document.getElementsByName("terms");
		        	let inputLength = $('#grpDescription').val().length;
		        	console.log(inputLength);
		        	if(inputLength<100) {
		        		alert("100자 이상 입력해주세요");
		        		$('#grpDescription').focus();
		        		return false;
		        	} else {
		        		$("#modGroup").submit();
		        	}
		        }
			</script>
	        <script src="${contextPath}/resources/js/modGroup.js"></script>

        <jsp:include page="./../../footer.jsp" flush="true"></jsp:include>
    </body>
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>
