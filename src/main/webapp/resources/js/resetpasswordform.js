let pwdCheck = false;
let allPwdCheck = false;

$(document).ready(function () {
    $("#alertSuccess").hide();
    $("#alertDanger").hide();

});

//비밀번호 유효성 체크
function fn_pwdCheck() {
    allPwdCheck = false;
    pwdCheck = false;
    let password = $("#password").val();
    let num = password.search(/[0-9]/g);
    let eng = password.search(/[a-z]/gi);
    let special = password.search(/[`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]/gi);
    let pwdRegExp = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,20}$/;
    
   
    let passwordCheck = $("#passwordCheck").val();
    if (passwordCheck != "" || !(passwordCheck.length <= 0)) {
        if (password == passwordCheck) {
            allPwdCheck = true;
            $("#alertSuccess").show();
            $("#alertDanger").hide();
        } else {
            $("#alertSuccess").hide();
            $("#alertDanger").show();
        }
        
        buttonCheck(pwdCheck, allPwdCheck);
    }
    if (password.length < 8 || password.length > 20) {
        $("#resultMsgPwd").show();
        $("#resultMsgPwd").html("비밀번호를 8~20자리 이내로 입력해주세요.");
        $("#resultMsgPwd").css("color", "#f43965");
        buttonCheck(pwdCheck, allPwdCheck);
        return;
    } else if (password.search(/\s/) != -1) {
        $("#resultMsgPwd").show();
        $("#resultMsgPwd").html("비밀번호를 공백 없이 입력해주세요.");
        $("#resultMsgPwd").css("color", "#f43965");
        buttonCheck(pwdCheck, allPwdCheck);
        return;
    } else if (!pwdRegExp.test(password)) {
        $("#resultMsgPwd").show();
        $("#resultMsgPwd").html("비밀번호를 문자, 숫자 포함 8~20자로 입력해주세요.");
        $("#resultMsgPwd").css("color", "#f43965");
        buttonCheck(pwdCheck, allPwdCheck);
        return;
    } else {
        pwdCheck = true;
        $("#resultMsgPwd").hide();
        buttonCheck(pwdCheck, allPwdCheck);
        return;
    }
    //
}

function fn_pwdDupCheck() {
    allPwdCheck = false;

    $("#passwordCheck").keyup(function () {
        let password = $("#password").val();
        let passwordCheck = $("#passwordCheck").val();
        if (password != "" || passwordCheck != "") {
            if (password == passwordCheck) {
                allPwdCheck = true;
                pwdCheck = true;
                $("#alertSuccess").show();
                $("#alertDanger").hide();
            } else {
                $("#alertSuccess").hide();
                $("#alertDanger").show();

            }
        }
        buttonCheck(pwdCheck, allPwdCheck);
    });
    
}

function buttonCheck(check1, check2){
	if(check1 && check2){
		$("#buttonSubmit").removeAttr("disabled");
	} else {
		$("#buttonSubmit").attr("disabled", "disabled");
	}
}