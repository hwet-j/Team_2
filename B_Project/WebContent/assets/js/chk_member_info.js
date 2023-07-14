/* ID CHECK */
function chkUserId(user_id){
	var obj = {
			flag : false,
			msg : ''
	};
	
	if(!user_id){
		obj.msg = '아이디를 입력해주세요.';
	}else if(user_id.length < 4 || user_id.length > 20){
		obj.msg = '4~20자를 입력해주세요.';
	}else if(user_id.indexOf("admin") > -1){
		obj.msg = 'admin이 포함된 아이디는 사용할 수 없습니다.';
	}else{ 
		
		var id_first_regType = /^[a-z0-9]*$/;
		var id_regType = /^[a-z0-9-_]*$/;
		var id_onlynumber_regType = /^[0-9-]*$/;
		
		var id_first_index = user_id.substring(0,1); 
		var id_last_index = user_id.substring( user_id.length - 1, user_id.length);
		
		if(!id_first_regType.test(id_first_index)){
			obj.msg = '첫 글자는 영문 혹은 숫자를 입력해주세요.';
		}else if(!id_regType.test(user_id)){
			obj.msg = '영문,숫자,-,_ 의 조합으로 아이디 생성이 가능합니다.';
		}else if(!id_first_regType.test(id_last_index)){
			obj.msg = '마지막 글자는 영문 혹은 숫자를 입력해주세요.';
		}else if(id_onlynumber_regType.test(user_id)){
			obj.msg = '숫자와 -의 조합으로 아이디 생성이 불가합니다.';
		}else{
			obj.flag = true;
		}
	}
	return obj;
}

/* NickName CHECK */
function chkUserNickName(nickname){
	var obj = {
			flag : false,
			msg : ''
	};
	
	if(!nickname){
		obj.msg = '닉네임을 입력해주세요.';
	}else if(/^[a-z0-9]*$/.test(nickname) && nickname.length < 4){
		obj.msg = '영문,숫자는 4자 이상 입력해 주세요.';
	}else if(nickname.length < 2 || nickname.length > 20){
		obj.msg = '2~20자를 입력해주세요.';
	}else if(nickname.indexOf(' ') > -1){
		obj.msg = '공백은 입력할 수 없습니다.';
	}else if(nickname.search(/[^~!\-_+@\=\u3131-\u314E|\u314F-\u3163|\uAC00-\uD7A3|a-z|A-Z|0-9]/gi) != -1 ){
		obj.msg = '특수문자는 _-@!~+= 만 사용 가능합니다.';
    }else if(nickname.search(/1=1/) > -1 ){
		obj.msg = '\'1=1\'은 사용할 수 없습니다.';
	}else{
		obj.flag = true;
	}

	return obj;
}

/* Name CHECK */
function chkUserName(name){
	var obj = {
			flag : false,
			msg : ''
	};
	
	if (!name || name == '') {
		obj.msg = '이름을 입력해주세요.';
	} else if (name.indexOf(' ') > -1) {
		obj.msg = '공백은 입력할 수 없습니다.';
	} else if (name.search(/[^~!\-_+@\=\u3131-\u314E|\u314F-\u3163|\uAC00-\uD7A3]/gi) !== -1) {
		// ~, !, -, _, +, @, =, 그리고 한글 자모 범위인 \u3131-\u314E, \u314F-\u3163, 그리고 한글 범위인 \uAC00-\uD7A3 까지 문자를 포함하지 않음
		obj.msg = '한글만 사용 가능합니다.';
	} else {
		obj.flag = true;
	}

	return obj;
}

/* Gender CHECK */
function chkUserGender(gender){
	var obj = {
			flag : false,
			msg : ''
	};
	
	if (!gender || gender == '') {
		obj.msg = '성별을 선택해 주세요.';
	} else {
		obj.flag = true;
	}

	return obj;
}


/* Birth CHECK */
function chkUserBirth(birth){
	var obj = {
			flag : false,
			msg : ''
	};
	
	if (!birth || birth == '') {
		obj.msg = '생년월일을 선택해 주세요.';
	} else {
		obj.flag = true;
	}

	return obj;
}


/* Tel CHECK */
function chkUserTelNum(number){
	var obj = {
			flag : false,
			msg : ''
	};
	
	if(!number){
		obj.msg = '전화번호를 입력해주세요';
	}else if(number.indexOf(' ') > -1){
		obj.msg = '공백은 입력할 수 없습니다.';
	}else if(!/^(\d{3})-(\d{4})-(\d{4})$/.test(number)) {
        obj.msg = '전화번호 형식이 올바르지 않습니다. (예: 010-1234-5678)';
    }else{
		obj.flag = true;
	}

	return obj;
}

/* E-mail CHECK -> 현재 회원가입시 이메일 정보 필요없음 ( 이후 필요할 가능성이 있음 ) */
function chkEmail(email){
	var obj = {
			flag : false,
			msg : ''
	};
	email = email.trim();
	
	if (email.length == 0) {
		obj.msg = "이메일 주소를 입력해주세요.";
	}else{
		
		var intput_email = escape(email);
		var sEmailHost = intput_email.split('@')[1];
		
		if (intput_email.match(/^([\.|0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/) == null){
			obj.msg = "이메일 주소를 정확하게 입력해 주세요.";
		}else{
			obj.flag = true;
		}
		
	}

	return obj;
}
