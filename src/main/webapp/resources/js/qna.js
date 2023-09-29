$(document).ready(function() {
// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
	let urlStr=window.location.href;
	let url=new URL(urlStr);
	let urlParams = url.searchParams;
	let filter = urlParams.get('filter');
	let keyword=urlParams.get('keyword');
	
	$("#searchOption").val('title');
	
	if(keyword != null && keyword != ''){

		$("#searchSelect").val(filter);
		$("#keyword").val(keyword);
	}

});
