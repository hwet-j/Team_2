<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%-- sweetAlert2 : 알림창 관련 디자인 --%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<head>
    <title>로그인 화면</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/popup_login.css">
    <script type="text/javascript" src="/assets/js/jquery-3.7.0.min.js"></script>
    <script type="text/javascript" src="/js/jquery-migrate-1.2.1.min.js"></script>
    
    <script type="text/javascript">
    
	$(document).ready(function(){
		$("#user_id").keypress(function (e) {;
    	    if (e.which == 13){
    	    	login_check_submit();
    	    }
    	});
		$("#password").keypress(function (e) {;
    	    if (e.which == 13){
    	    	login_check_submit();
    	    } 
    	});
		$('#user_id').focus();
	});
	
	// 어떤 정보도 입력하지 않으면 입력하도록 알림창
    function login_check_submit() {
        if ($('#user_id').val() === "") {
        	// alert와 똑같은 기능 -> sweetAlert2의 기능을 사용
        	Swal.fire({
        		  icon: 'error',
        		  title: 'ID Not Entered...',
        		  text: '아이디를 입력해주세요!',
       		})
            $('#user_id').focus();
            return false;
        }
        if ($('#password').val() === "") {
        	Swal.fire({
      		  icon: 'error',
      		  title: 'Password Not Entered...',
      		  text: '비밀번호를 입력해주세요!',
     		})
            $('#password').focus();
            return false;
        }
        // 모든 정보가 입력되었을 때, ajax 함수 실행 (로그인 가능한지 확인)
        checkLogin();
    }
    
  	// 로그인 성공 여부
    function checkLogin() {
        let user_id = $("input[name='user_id']");
        let password = $("input[name='password']");
        
        // AJAX 요청
        $.ajax({
            url: "/view/loginError.jsp",
            type: "POST",
            data: { user_id: user_id.val() ,
            	password: password.val() },
            success: function(response) {
            	var message = response.replace(/\s/g, ""); // 공백 제거(줄바꿈로 해결되지 않아 공백제거로 변경...)
            	// 입력 가능한지 불가능한지 체크하여 가능하면 Success 반환
                if (message === "Success") {	
                	$('#login').submit();	// 로그인 가능하므로 submit 실행
                } else {
                	Swal.fire({
                        icon: 'error',
                        title: '로그인 실패',
                        text: '아이디 또는 비밀번호가 일치하지 않습니다.',
                    });
                	// 로그인 불가능하면 비밀번호는 초기화
                	password.val('');
                	return;
                }
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    }
	</script>
    
    
    </head>

<body>
    <div class="popup_login">
    	<a href="/index.jsp"><p class="log_top"><img src="/assets/images/logo.png"></p></a>
    	
        <form name="login" id="login" method="post" action="/login.do">
	        <ul class="login_box">
	            <li class="log_id">
	            	<input placeholder="아이디 입력" type="text" name="user_id" id="user_id" maxlength="20" />
	            	<input placeholder="비밀번호 입력" type="password" name="password" id="password" maxlength="20" />
	            </li>
	            
	            <a href="#none" onclick="login_check_submit();">
	            	<li class="log_btn"><span class="logtext jBtnLogin">로그인</span></li>
	            </a>
	            
	            <li class="auto_log"><label><input type="checkbox" id="aLogin" name="auto_login" value=1 /><span class="autol">자동 로그인</span></label></li>
	            <li class="log_btn_02">
	            	<a href="/join.do">회원가입</a> |
                    <a href="#">아이디 찾기</a> |
                    <a href="#">비밀번호 찾기</a> |
                    <a href="#">소셜회원탈퇴</a>
                    
                    <!-- 이후에 가능하면 기능 구현 -->
	            	<div class="sns_login">
	            		<a href="https://kauth.kakao.com/oauth/authorize?client_id=770b249e43c567d7c518187be78e9147&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&response_type=code&state=kakao%7C%7CW3n2LcVSR%252BBF4O%252FmEDGNCzS0Hb%252BItbvo7yBcL3o9hj7L5JR8%252F15mO5NAK41Xy1a52p3Qq6d63jhvcy1bgzd4NlbZT%252BXpFUGciJ7GIG%252FQ0gE%253D"><img src="/assets/images/auth/Login_kakao_ico.png"><span>카카오 로그인</span></a>
						<a href="https://www.facebook.com/v12.0/dialog/oauth?client_id=179876179410019&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&state=facebook%7C%7CDFXhU14w5EWK0Pzi0tuTshmnwo4tUYf6qcwLWvp4vuJ6FL7KGgNxuMy1rqP%252BtZ5azTyhJ%252B2GdB6REbXMHZAAaBuTgFQz70lcqMvG2IzSpBs%253D"><img src="/assets/images/auth/Login_facebook_ico.png"><span>페이스북 로그인</span></a>
                        <a href="https://nid.naver.com/oauth2.0/authorize?client_id=xDGYSg2QR6mVXgzCutar&response_type=code&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&state=naver%7C%7Czk1upFRjfUCkpP3yEsRt%252FFnVnA0rm1THYFIxvVbkmejKDdYSE06jKDXsNjpgOMBv2zIBEKPY0fQP9VN%252FiKuQx6ZZkSV9Gj6%252BI9sylfuNEwM%253D"><img src="/assets/images/auth/Login_naver_ico.png"><span>네이버 로그인</span></a>
                        <a href="https://accounts.google.com/o/oauth2/auth?response_type=code&access_type=online&client_id=774606124414-qrjkg1h32rv1ftjab687p1mvcou8kvim.apps.googleusercontent.com&redirect_uri=https%3A%2F%2Fwww.ppomppu.co.kr%2Fopenapi%2Fsocial%2Flogin.php&state=google%7C%7CSirAcY4yAEV8sQCL2fAs8OjayzGzGLj%252Fg0R8LQudeuj6wX2alaCGmtGpIMdAOo0VOqn3T1oX9UIk9fNRoC0hbiEy3FiU2UAxuBK62p3QC2A%253D&scope=email%20profile&approval_prompt=auto"><img src="/assets/images/auth/Login_google_ico.png"><span>구글 로그인</span></a>
	            	</div>

	            </li>
                <li class="lg_banner"></li>
	            <p class="backs">
                 	<a href="/index.jsp">
                 	  <span class="back">메인페이지</span>
                 	</a>
                 </p>
	        </ul>
        </form>
    </div>
</body>
</html>


