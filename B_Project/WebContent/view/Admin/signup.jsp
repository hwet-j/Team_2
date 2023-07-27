<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <%-- sweetAlert2 : 알림창 관련 디자인 --%>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <!-- 부트스트랩 CSS 불러오기 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
    
    <script>
        function closeOnSubmit() {
        	var admin = $('#admin');
        	var user_id = $('#user_id');
        	var password = $('#user_password');
        	// AJAX 요청
            $.ajax({
                url: "/join.do",
                type: "POST",
                data: { admin : admin.val(),
                		user_id: user_id.val() ,
                		password: password.val() },
                success: function(response) {
                    if (response === "true") {	
                    	Swal.fire({
                            icon: 'error',
                            title: '회원가입 실패',
                            text: '이미 존재하는 아이디 입니다.',
                        });
                    	user_id.val('');
                    	password.val('');
                    	return false;
                    } else {
                    	// 회원 가입진행 후 창 종료
                    	window.opener.onSubFormComplete(); 
                        window.close();
                        return false;
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
    <div class="container mt-5">
        <h1 class="mb-4">회원가입(관리자용)</h1>
        <form action="#" id="loginForm">
            <input type="hidden" id="admin" name="admin" value="admin">
            
            <div class="form-group">
                <label for="user_id">ID:</label>
                <input type="text" class="form-control" id="user_id" name="user_id" placeholder="아이디" maxlength="20" required>
            </div>
    
            <div class="form-group">
                <label for="user_password">PW:</label>
                <input type="password" class="form-control" id="user_password" name="user_password" placeholder="비밀번호"  maxlength="16" required>
            </div>
            
            <button type="button" onclick="closeOnSubmit()">회원 가입</button>
        </form>
    </div>

    <!-- 부트스트랩 JS 및 jQuery 링크 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery 라이브러리 불러오기 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>
</html>
