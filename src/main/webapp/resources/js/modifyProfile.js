$(document).ready(function() {
	
});

function delUserImgSubmit(){
  	document.getElementById("userImg").src = "/nemo/resources/images/pingoo.jpg";
  	document.getElementById("isDeleteImg").value="true";
  	console.log("이미지 삭제");
}