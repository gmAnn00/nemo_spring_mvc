$(document).ready(function() {
	$("table.adminGroupTbl").DataTable();
	$("table.adminMemberTbl").DataTable();
	
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
});


$(function () {
    (function ($, document) {
        // get tallest tab__content element
        let height = -1;

        $(".list").each(function () {
            height = height > $(this).outerHeight() ? height : $(this).outerHeight();
            $(this).css("position", "absolute");
        });

        // set height of tabs + top offset
        $("[data-tabs]").css("min-height", height + 40 + "px");
    })(jQuery, document);
});


function fn_Grpdelete(group_id){
		let result=confirm("삭제 하시겠습니까?");
	if(result) {
		let newForm = $('<form></form>');
		newForm.attr("name","newForm");
		newForm.attr("method","post");
		newForm.attr("action","/nemo/admin/admingroup/delgroup");
		newForm.append($('<input/>', {type: 'hidden', name: 'group_id', value:group_id }));
		newForm.appendTo('body');

	// submit form
	newForm.submit();
	}else {
		
	}
}
