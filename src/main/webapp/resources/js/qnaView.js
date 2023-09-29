$(document).ready(function() {
	ctx = getContextPath();
	// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
	$(document).on('click','a[href="#"]',function(ignore){
		ignore.preventDefault();
	});

})

// 목록으로 돌아가기
function backToList() {
		location.href="/nemo/qna";
}
	
// 글 수정	
function fn_modify_article(qna_no) {
	location.href="/nemo/qna/modqnaform?qna_no="+qna_no;
}

// 답글 쓰기
function fn_reply_form(parent_no) {  //답글쓰기 폼 구현
	location.href="/nemo/qna/replyform?parent_no="+parent_no;
}

// 삭제 확인
function fn_deleteChk(qna_no) {
	if(confirm("글을 삭제하시겠습니까?")){
		location.href="/nemo/qna/delqna?qna_no="+qna_no;
		
	}
} 
    
 //url 클립보드 복사
 function clip(){
	var origUurl = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	origUrl = window.document.location.href;
	let index=origUrl.lastIndexOf("#");
	let url;
	if(index!=-1) {
		url=origUrl.substring(0,index);
	} else {
		url=origUrl;
	}
	console.log(url);
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert("URL이 복사되었습니다.")
}






