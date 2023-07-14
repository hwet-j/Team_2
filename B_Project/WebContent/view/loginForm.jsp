<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>로그인 화면</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/assets/css/popup_login.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-3.7.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-migrate-1.2.1.min.js"></script>
    
    <script type="text/javascript">
	$(document).ready(function(){
		$("#user_id").keypress(function (e) {;
    	    if (e.which == 13){
    	    	zb_login_check_submit();
    	    }
    	});
		$("#password").keypress(function (e) {;
    	    if (e.which == 13){
    	    	zb_login_check_submit();
    	    } 
    	});
		$('#user_id').focus();
	});

    function zb_login_check_submit() {
        var $user_id = document.getElementById('user_id');
        var $password = document.getElementById('password');

        if ($('#user_id').val() === "") {
            alert("아이디를 입력해주십시요");
            $('#user_id').focus();
            return false;
        }
        if ($('#password').val() === "") {
            alert("비밀번호를 입력해주십시요");
            $('#password').focus();
            return false;
        }
        $('#zb_login').submit();
    }
	</script>
    
    
    </head>

<body>
    <div class="popup_login">
    	<a href="<%=request.getContextPath() %>/index.jsp"><p class="log_top"><img src="<%= request.getContextPath() %>/assets/images/logo.png"></p></a>
    	
        <form name="zb_login" id="zb_login" method="post" action="/login_check.php">
        <input type="hidden" name="s_url" value="%2F" />
	        <ul class="login_box">
	            <li class="log_id">
	            	<input placeholder="아이디 입력" type="text" name="user_id" id="user_id" maxlength="20" />
	            	<input placeholder="비밀번호 입력" type="password" name="password" id="password" maxlength="20" />
	            </li>
	            <a href="#none" onclick="zb_login_check_submit();">
	            <li class="log_btn"><span class="logtext jBtnLogin">로그인</span></li></a>
	            
	            <li class="auto_log"><label><input type="checkbox" id="aLogin" name="auto_login" value=1 /><span class="autol">자동 로그인</span></label></li>
	            <li class="log_btn_02">
	            	<a href="<%=request.getContextPath() %>/join.do">회원가입</a> |
                    <a href="#">아이디 찾기</a> |
                    <a href="#">비밀번호 찾기</a> |
                    <a href="#">소셜회원탈퇴</a>
                    
	            	<div class="sns_login">
	            		<a href="https://kauth.kakao.com/oauth/authorize?client_id=770b249e43c567d7c518187be78e9147&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&response_type=code&state=kakao%7C%7CW3n2LcVSR%252BBF4O%252FmEDGNCzS0Hb%252BItbvo7yBcL3o9hj7L5JR8%252F15mO5NAK41Xy1a52p3Qq6d63jhvcy1bgzd4NlbZT%252BXpFUGciJ7GIG%252FQ0gE%253D"><img src="<%=request.getContextPath() %>/assets/images/auth/Login_kakao_ico.png"><span>카카오 로그인</span></a>
						<a href="https://www.facebook.com/v12.0/dialog/oauth?client_id=179876179410019&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&state=facebook%7C%7CDFXhU14w5EWK0Pzi0tuTshmnwo4tUYf6qcwLWvp4vuJ6FL7KGgNxuMy1rqP%252BtZ5azTyhJ%252B2GdB6REbXMHZAAaBuTgFQz70lcqMvG2IzSpBs%253D"><img src="<%=request.getContextPath() %>/assets/images/auth/Login_facebook_ico.png"><span>페이스북 로그인</span></a>
                        <a href="https://nid.naver.com/oauth2.0/authorize?client_id=xDGYSg2QR6mVXgzCutar&response_type=code&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&state=naver%7C%7Czk1upFRjfUCkpP3yEsRt%252FFnVnA0rm1THYFIxvVbkmejKDdYSE06jKDXsNjpgOMBv2zIBEKPY0fQP9VN%252FiKuQx6ZZkSV9Gj6%252BI9sylfuNEwM%253D"><img src="<%=request.getContextPath() %>/assets/images/auth/Login_naver_ico.png"><span>네이버 로그인</span></a>
                        <a href="https://accounts.google.com/o/oauth2/auth?response_type=code&access_type=online&client_id=774606124414-qrjkg1h32rv1ftjab687p1mvcou8kvim.apps.googleusercontent.com&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&state=google%7C%7CSirAcY4yAEV8sQCL2fAs8OjayzGzGLj%252Fg0R8LQudeuj6wX2alaCGmtGpIMdAOo0VOqn3T1oX9UIk9fNRoC0hbiEy3FiU2UAxuBK62p3QC2A%253D&scope=email%20profile&approval_prompt=auto"><img src="<%=request.getContextPath() %>/assets/images/auth/Login_google_ico.png"><span>구글 로그인</span></a>
	            	</div>

	            </li>
                <li class="lg_banner"></li>
	           <p class="backs">
                 	<a href="<%=request.getContextPath() %>/index.jsp">
                 	  <span class="back">되돌아가기</span>
                 	</a>
                 </p>
	        </ul>
        </form>
        <p class="log_b"><a href='/'></a></p>
    </div>
</body>
</html>


