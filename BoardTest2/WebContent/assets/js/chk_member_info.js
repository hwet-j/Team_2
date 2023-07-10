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

/* Nick CHECK */
function chkUserNinkName(name){
	var obj = {
			flag : false,
			msg : ''
	};
	
	if(!name){
		obj.msg = '닉네임을 입력해주세요.';
	}else if(/^[a-z0-9]*$/.test(name) && name.length < 4){
		obj.msg = '영문,숫자는 4자 이상 입력해 주세요.';
	}else if(name.length < 2 || name.length > 20){
		obj.msg = '2~20자를 입력해주세요.';
	}else if(name.indexOf(' ') > -1){
		obj.msg = '공백은 입력할 수 없습니다.';
	}else if(name.search(/[^~!\-_+@\=\u3131-\u314E|\u314F-\u3163|\uAC00-\uD7A3|a-z|A-Z|0-9]/gi) != -1 ){
		obj.msg = '특수문자는 _-@!~+= 만 사용 가능합니다.';
    }else if( name.search(/1=1/) > -1 ){
		obj.msg = '\'1=1\'은 사용할 수 없습니다.';
	}else{
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
	}else{
		obj.flag = true;
	}

	return obj;
}

/* E-mail CHECK */
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
