let itemArr=[];
let ctx;

// 게시글 삭제 확인
function delBoard(group_id, article_no){

	if(confirm("글을 삭제하시겠습니까?")){
		location.href="/nemo/group/board/delboard?group_id="+group_id+"&article_no="+article_no;
		
	}

}


// 댓글 삭제 확인
function fn_delComment(group_id, article_no, comment_no){

	if(confirm("댓글을 삭제하시겠습니까?")){
		location.href="/nemo/group/board/delcomment?group_id="+group_id+"&article_no="+article_no + "&comment_no="+comment_no;
		
	}

}



$(document).ready(function() {
	
	ctx = getContextPath();
	// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
	$(document).on('click','a[href="#"]',function(ignore){
		ignore.preventDefault();
	});
    adjustHeight();
    initItemArray();
    

	//답글 달기 눌렀을 떄 
	$(document).on('click','.comReplyBtn', function(){
		console.log($('#isAdmin').val());
		if($('#isAdmin').val()=='true') {
			console.log('여기 나오냐');
			alert('관지자는 댓글을 달 수 없습니다.');
			return;	
		}
	    let ItemNum=$('.commentItem').length;
	    let index=$('.commentItem').index($(this).closest($('.commentItem')));
	    console.log(".commentItem: " + index);
	    if(itemArr[index]==0){
	        let parent=$(this).closest($('.commentItem'));
	        let parentId=$(parent).attr('id');
			removeReplyBox();
	        let addInputBox=
	        	`<li class="replyBox commentLi replyCommentItem">
				    <div class="commentReWriter">
				        <div class="commentReInbox">
				            <textarea
				                placeholder="댓글을 남겨보세요"
				                class="commentInboxText"
				                rows="1"
				                id="textArea${index}"
				                onkeydown="resize(this)"
				                onkeyup="resize(this)"
				            ></textarea>
				        </div>
				        <div class="commentRegister">
				            <a
				                href="javascript:void(0);"
				                role="button"
				                class="buttonCancle btnSubmitRe btnReCom"
				                id="regBtn${index}"
				                onclick="fn_addCommentChild(${index}, ${parentId})"
				            >
				                등록
				            </a>
				            <a href="javascript:void(0);" role="button" class="buttonCancle btnRemoveRe btnReCom">
				                취소
				            </a>
				        </div>
				    </div>
				</li>`
	        	
	        $(parent).after(addInputBox);
	        itemArr[index]++;
	    }
	});

});


	$(document).on('click','.btnRemoveRe', function(){
		let totLi=$('.commentLi').length;
		let replyBox=$('.replyBox').length;
		console.log(itemArr);
		for(let i=0; i<itemArr.length; i++) {
			console.log(totLi+","+replyBox);
			if(itemArr[i]==1 && replyBox>1){
				replyBox--;
				console.log("replyBox : " + replyBox);
			} else if(itemArr[i]==1 && replyBox==1) {
				itemArr[i]--;
				replyBox--;
				console.log("취소후 : "+itemArr+ " item[i]: " + itemArr[i]);
				$(this).closest($('.replyBox')).remove();
				
				break;
			} 
		}
	});
	

    $(document).on('keyup', '.commentInboxText', function(){
        let inputLength = $(this).val().length;
        let parentSiblings=$(this).parent().siblings();
        let btnRecom=parentSiblings.find('.btnSubmitRe');
        if(inputLength>0){
            btnRecom.addClass('active');
        } else {
            btnRecom.removeClass('active');
        }

        
    });
	
	
	
	function removeReplyBox(){
		$('.replyBox').remove();
	    for(let i=0; i<itemArr.length; i++) {
			itemArr[i]=0;
		}
	}
	
	//댓글 등록 함수
	function fn_addComment() {
		if($('#isAdmin').val()=='true') {
			alert('관지자는 댓글을 달 수 없습니다.');
			return;	
		} else {
		let parentSiblings=$(this).parent().siblings();
		let content=$('#textArea').val();
		let article_no=$('#article_no').val();
		let group_id=$('#group_id').val();

        if(!content){
            alert('내용을 입력해주세요');
        } else {
            //ajax로 댓글 등록 하는거 처리하기~
            let ctx =getContextPath();
            url=ctx+"/group/board/addcomment?group_id="+group_id;
            console.log(url);
            $.ajax({
				url: url,
				async: true,
				data: {
					"content": content,
					"article_no": article_no,
					"parent_no": 0
				},
				type: "post",
				success:function(data) {
					let cnt = $("#comment_cnt").text();
					$("#comment_cnt").text(Number(cnt)+1);
					let commentInfo= JSON.parse(data);
					
					let date = new Date(Date.parse(commentInfo.create_date));
					let month = date.getMonth() + 1;
					let day = date.getDate();
					let hour = date.getHours();
					let minute = date.getMinutes();
					let second = date.getSeconds();
					month = month >= 10 ? month : "0" + month;
					day = day >= 10 ? day : "0" + day;
					hour = hour >= 10 ? hour : "0" + hour;
					minute = minute >= 10 ? minute : "0" + minute;
					let create_date = date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minute;
					
					let appendItem =
					`<li id="${commentInfo.comment_no}" class="commentItem commentLi">
					    <div class="commentbox">
					        <div class="commentTool">
					            <span class="comMod comToolBtn">
					                <a href="#" role="button" onclick="fn_enable(this, ${commentInfo.comment_no})" id="comModBtn${commentInfo.comment_no}">
					                    수정
					                </a>
					            </span>
					            <span class="comDel comToolBtn">
					                <a
					                    href="javascript:void(0)"
					                    role="button"
					                    id="comDelBtn${commentInfo.comment_no}"
					                    onclick="fn_delComment(${group_id}, ${article_no}, ${commentInfo.comment_no})"
					                >
					                    삭제
					                </a>
					            </span>
					        </div>
					        <a href="#" class="commentThumb">
					            <img src="${ctx}/userimagedownload?user_id=${commentInfo.user_id}&user_img=${commentInfo.user_img}" alt="프로필사진" />
					        </a>
					        <div class="commentNick">
					            <span class="commentNickInfo">
					                <a href="#" role="button">
					                    ${commentInfo.nickname}
					                </a>
					            </span>
					        </div>
					        <div class="commentText">
					            <p>
					                <textarea
					                    class="viewTextArea"
					                    rows="1"
					                    id="viewTextArea${commentInfo.comment_no}"
					                    onkeydown="resize(this)"
					                    onkeyup="resize(this)"
					                    disabled
					                >${commentInfo.content}</textarea>
					            </p>
					        </div>
					        <div class="commentInfo">
					            <span class="commentDate comDate">${create_date}</span>
					            <span class="replyCom">
					                <a href="#" role="button" class="comReplyBtn" id="comReplyBtn${commentInfo.comment_no}">
					                    답글쓰기
					                </a>
					            </span>
					            <span class="comMod comToolBtn modReply" id="modReply${commentInfo.comment_no}">
					                <a
					                    href="#"
					                    role="button"
					                    class="modReplyBtn"
					                    id="modReplyBtn${commentInfo.comment_no}"
					                    onclick="fn_cancelMod(this, ${commentInfo.comment_no})"
					                >
					                    취소
					                </a>
					            </span>
					        </div>
					    </div>
					</li>`;
					
					
					$('.commentList').append(appendItem);

					alert("댓글이 등록 되었습니다.");
					$('.commentInboxText').val('');
					itemArr=[];
					initItemArray();
					
				},
				error: {
					
				}
			});
        }	
      }
	}
	
	//수정버튼 누를때 textarea 활성화
	function fn_enable(obj,cnt) {
		//alert(cnt+'클릭');
		let textAreaId="#viewTextArea"+cnt;
		let comModId="#comModBtn"+cnt;
		let comDelID="#comDelBtn"+cnt;
		let comReplyId="#comReplyBtn"+cnt;
		let modReplyId="#modReply"+cnt;
		document.getElementById('viewTextArea'+cnt).disabled=false;
		document.getElementById('viewTextArea'+cnt).style.border='1px solid #ccc';

		$(modReplyId).css({
			visibility:'visible'
		});
		$(comModId).css({
			visibility:'hidden'
		});
		$(comDelID).css({
			visibility:'hidden'
		});
		$(comReplyId).css({
			visibility:'hidden'
		});
		console.log(textAreaId);
		console.log(document.getElementById(textAreaId));
	}
	
	//댓글 수정 완료하는 부분
	function fn_modComment(obj, cnt) {
		let textAreaId="#viewTextArea"+cnt;
		let comModId="#comModBtn"+cnt;
		let comDelID="#comDelBtn"+cnt;
		let comReplyId="#comReplyBtn"+cnt;
		let modReplyId="#modReply"+cnt;
		let group_id=$('#group_id').val();
		let content=$(textAreaId).val();
		
        if(!content){
			alert('내용을 입력해주세요');
        } else {
		    document.getElementById('viewTextArea'+cnt).disabled=true;
			document.getElementById('viewTextArea'+cnt).style.border='none';
			$(modReplyId).css({
				visibility:'hidden'
			});
			$(comModId).css({
				visibility:'visible'
			});
			$(comDelID).css({
				visibility:'visible'
			});
			$(comReplyId).css({
				visibility:'visible'
			});

            let ctx =getContextPath();
            url=ctx+"/group/board/modcomment?group_id="+group_id;
            
            $.ajax({
				url: url,
				async: true,
				data: {
					"content": content,
					"comment_no": cnt
				},
				type: "post",
				success:function(result) {
					if(result=="success") {
						alert("댓글이 수정 되었습니다.");
					}else if(result == "fail"){
						alert("작성자만 댓글을 수정할 수 있습니다.");
					}				
				},
				error: {
					
				}
			});
 
        }	
	} //end of fn_modComment
	
	//댓글 수정 취소하기 
	function fn_cancelMod(obj, cnt) {
		let textAreaId="#viewTextArea"+cnt;
		let comModId="#comModBtn"+cnt;
		let comDelID="#comDelBtn"+cnt;
		let comReplyId="#comReplyBtn"+cnt;
		let modReplyId="#modReply"+cnt;
		let group_id=$('#group_id').val();
		
		let ctx =getContextPath();
            url=ctx+"/group/board/cancelmod?group_id="+group_id;
            console.log(url);
            
            $.ajax({
				url: url,
				async: true,
				data: {
					"comment_no": cnt
				},
				type: "post",
				success:function(content) {
					$(textAreaId).val(content);	
					adjustHeight();
				},
				error: {
					
				}
			});
		
		document.getElementById('viewTextArea'+cnt).disabled=true;
		document.getElementById('viewTextArea'+cnt).style.border='none';
		
		$(modReplyId).css({
				visibility:'hidden'
		});
		$(comModId).css({
			visibility:'visible'
		});
		$(comDelID).css({
			visibility:'visible'
		});
		$(comReplyId).css({
			visibility:'visible'
		});
		
	}
	
	function initItemArray() {
		let ItemNum=$('.commentItem').length;
		for(let i=0; i<ItemNum; i++) {
			itemArr.push(0);
		}
	}
	
	//대댓 등록 함수
	function fn_addCommentChild(index, siblingId) {
		let content=$('#textArea'+index).val();
		let article_no=$('#article_no').val();
		let group_id=$('#group_id').val();
		
        if(!content){
            alert('내용을 입력해주세요');
        } else {
            //ajax로 댓글 등록 하는거 처리하기~
            let ctx =getContextPath();
            url=ctx+"/group/board/addreply?group_id="+group_id;

            $.ajax({
				url: url,
				async: true,
				data: {
					"content": content,
					"article_no": article_no,
					"parent_no": siblingId
				},
				type: "post",
				success:function(data) {
					let commentInfo=JSON.parse(data);
					
					let date = new Date(Date.parse(commentInfo.create_date));
					let month = date.getMonth() + 1;
					let day = date.getDate();
					let hour = date.getHours();
					let minute = date.getMinutes();
					let second = date.getSeconds();
					month = month >= 10 ? month : "0" + month;
					day = day >= 10 ? day : "0" + day;
					hour = hour >= 10 ? hour : "0" + hour;
					minute = minute >= 10 ? minute : "0" + minute;
					let create_date = date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minute;
					
					let cnt = $("#comment_cnt").text();
					$("#comment_cnt").text(Number(cnt)+1);

					removeReplyBox();
					
					let appendItem=
						`<li id="${commentInfo.comment_no}" class="commentItem replyCommentItem commentLi">
						    <div class="commentbox">
						        <div class="commentTool">
						            <span class="comMod comToolBtn">
						                <a href="#" role="button" onclick="fn_enable(this, ${commentInfo.comment_no})" id="comModBtn${commentInfo.comment_no}">
						                    수정
						                </a>
						            </span>
						            <span class="comDel comToolBtn">
						                <a
						                    href="javascript:void(0)"
						                    role="button"
						                    id="comDelBtn${commentInfo.comment_no}"
						                    onclick="fn_delComment(${group_id}, ${article_no}, ${commentInfo.comment_no})"
						                >
						                    삭제
						                </a>
						            </span>
						        </div>
						        <a href="#" class="commentThumb">
						            <img src="${ctx}/userimagedownload?user_id=${commentInfo.user_id}&user_img=${commentInfo.user_img}" alt="프로필사진" />
						        </a>
						        <div class="commentNick">
						            <span class="commentNickInfo">
						                <a href="#" role="button">
						                    ${commentInfo.nickname}
						                </a>
						            </span>
						        </div>
						        <div class="commentText">
						            <p>
						                <textarea
						                    class="viewTextArea"
						                    rows="1"
						                    id="viewTextArea${commentInfo.comment_no}"
						                    onkeydown="resize(this)"
						                    onkeyup="resize(this)"
						                    disabled
						                >${commentInfo.content}</textarea>
						            </p>
						        </div>
						        <div class="commentInfo">
						            <span class="commentDate comDate">${create_date}</span>
						            <span class="replyCom">
						                <a href="#" role="button" class="comReplyBtn" id="comReplyBtn${commentInfo.comment_no}">
						                    답글쓰기
						                </a>
						            </span>
						            <span class="comMod comToolBtn modReply" id="modReply${commentInfo.comment_no}">
						                <a
						                    href="#"
						                    role="button"
						                    class="modReplyBtn"
						                    id="modReplyBtn${commentInfo.comment_no}"
						                    onclick="fn_cancleMod(this, ${commentInfo.comment_no}"
						                >
						                    취소
						                </a>
						            </span>
						        </div>
						    </div>
						</li>`;
						
					
					
					$('#'+commentInfo.parent_no).after(appendItem);

					alert("댓글이 등록 되었습니다.");
					itemArr=[];
					initItemArray();
					
				},
				error: {
					
				}
			});
            

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

function backToList() {
	let referrer = document.referrer;
	location.href=referrer;
	console.log(referrer);
}


//textarea 자동크기조절
function resize(obj) {
  	obj.style.height = "auto";
  	obj.style.height = obj.scrollHeight+"px";
}

//댓글 높이 초기화
function adjustHeight() {
  	let textarea=document.querySelectorAll('.viewTextArea');
	for(let i=0; i<textarea.length; i++) {
		textarea[i].style.height="auto";   
		textarea[i].style.height=textarea[i].scrollHeight+"px";
	}
}


function getContextPath() {
    return sessionStorage.getItem("contextpath");
}