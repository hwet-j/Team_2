/* 1. 회원가입 비밀번호 체크 */
function chkPassword(password, user_id)
{
	return _chkPwd(password, user_id, 3);
}

/* 2. 회원 비밀번호 변경 체크 */
function chkPassword_change(password, user_id, old_password, birth_day)
{
	return _chkPwdChg(password, user_id, 3, old_password, "", birth_day);
}

/* 3. 운영자 비밀번호 체크 */
function chkPasswordAdmin(password, user_id)
{
	return _chkPwd(password, user_id, 3);
}

/* 4. 운영자 비밀번호 변경 체크 */
function chkPasswordAdmin_change(password, user_id, old_password)
{
	return _chkPwdChg(password, user_id, 3, old_password, "");
}

/* 5. 장터등록 비밀번호 체크 */
function chkPasswordMarket(password)
{
	return _chkPwd(password, undefined, 3);
}

/* 6. 장터 비밀번호 변경 체크 */
function chkPasswordMarket_change(password, old_password)
{
	return _chkPwdChg(password, undefined, 3, old_password, "장터");
}

/* 비밀번호 재입력 체크 */
function chkPassword_repeat(password, repeat_password, pwdChkResult)
{
	var chkResult = [];
	chkResult['flagR'] = false;
	
	if (!password) {
		if (!repeat_password) {
			chkResult['flagR'] = true;
			chkResult['msg'] = "";
		} else {
			chkResult['msg'] = "비밀번호를 먼저 입력해주세요";
		}
	} else if(!pwdChkResult) {
		chkResult['msg'] = "유효한 비밀번호를 입력해주세요.";
	} else if (repeat_password != password) {
		chkResult['msg'] = "비밀번호가 일치하지 않습니다.";
	} else {
		chkResult['msg'] = "정확하게 입력하셨습니다.";
		chkResult['flagR'] = true;
	}

	return chkResult;
}

/*********************************/
/*   below are inner functions   */
/*********************************/
function _chkPwd(password, user_id, mixNum)
{
	var chkResult = [];
	chkResult['flag'] = false;
	
	if (!password) {
		chkResult['msg'] = "비밀번호를 입력해주세요.";
	} else if (password.length < 8 || password.length > 16) {
		chkResult['msg'] = "8~16자를 입력해주세요.";
	} else if (user_id && password.indexOf(user_id) > -1) {
		chkResult['msg'] = "아이디를 포함한 비밀번호는 사용할 수 없습니다.";
	} else if (password.indexOf(' ') > -1) {
		chkResult['msg'] = "공백은 입력할 수 없습니다.";
	} else if (!_chkContinu(password)) {
		chkResult['msg'] = "4자 이상 연속된 문자를 사용할 수 없습니다.";
	} else if (!_chkComplex(password, mixNum)) {
		chkResult['msg'] = "영대소문자, 숫자, 특문 중 "+mixNum+"개 이상을 조합해주세요.";
	} else {
		chkResult['msg'] = "유효한 비밀번호입니다.";
		chkResult['flag'] = true;
	}

	return chkResult;
}

function _chkPwdChg(password, user_id, mixNum, old_password, name, birth_day)
{
	var chkResult = [];
	chkResult['flag'] = false;
	chkResult['flagR'] = false;
	chkResult['msg'] = "";

	if (!password) {
		chkResult['flag'] = true;
		chkResult['flagR'] = true;
	} else if (!old_password) {
		chkResult['msg'] = "기존 "+name+"비밀번호를 먼저 입력해주세요";
	} else if (password == old_password) {
		chkResult['msg'] = "기존 "+name+"비밀번호와 동일합니다. 다른 비밀번호로 설정해주세요.";
	} else if (password.length < 8 || password.length > 16) {
		chkResult['msg'] = "8~16자를 입력해주세요.";
	} else if (user_id && password.indexOf(user_id) > -1) {
		chkResult['msg'] = "아이디를 포함한 비밀번호는 사용할 수 없습니다.";
	} else if (password.indexOf(' ') > -1) {
		chkResult['msg'] = "공백은 입력할 수 없습니다.";
	} else if (!_chkContinu(password)) {
		chkResult['msg'] = "4자 이상 연속된 문자를 사용할 수 없습니다.";
	} else if (!_chkComplex(password, mixNum)) {
		chkResult['msg'] = "영대소문자, 숫자, 특문 중 "+mixNum+"개 이상을 조합해주세요.";
	} else if (birth_day && password.indexOf(birth_day) != -1) {
		chkResult['msg'] = "생년월일은 비밀번호로 사용하실 수 없습니다.";
	} else {
		chkResult['msg'] = "유효한 비밀번호입니다.";
		chkResult['flag'] = true;
	}

	return chkResult;
}

function _chkComplex(keyword, mixNum)
{
	var chk = 0;
	if (keyword.search(/[0-9]/g) != -1) chk ++;
	if (keyword.search(/[a-z]/ig) != -1) chk ++;
	if (keyword.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi) != -1) chk ++;
	if (chk >= mixNum) { 
		return true;
	} else {
		return false;
	}
}

function _chkContinu(keyword)
{
	var patt_4num1 = /(\w)\1\1\1/;
	var patt_4num2 = /([\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"])\1\1\1/;
	var patt_cont = /(0123)|(1234)|(2345)|(3456)|(4567)|(5678)|(6789)|(7890)/;
 
	if (keyword.search(patt_4num1) != -1 ||
		keyword.search(patt_4num2) != -1 ||
		keyword.search(patt_cont) != -1) {
		return false;
	} else {
		return true;
	}
}
