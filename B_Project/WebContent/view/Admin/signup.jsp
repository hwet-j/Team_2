<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery 라이브러리 불러오기 -->
    <script>
        function closeOnSubmit() {
    		${"#loginForm"}.submit();
            window.close();
            return false;
        }
    </script>
</head>

<body>
    <h1>회원가입(관리자용)</h1>
    <form action="/join.do" method="post" id="loginForm" onsubmit="return closeOnSubmit()" target="_self">
    	<input type="hidden" name="admin" value="admin">
    	
        <label for="user_id" >ID:</label>
        <input type="text" id="user_id" name="user_id" required><br>
        
        <label for="user_password" >PW:</label>
        <input type="password" id="user_password" name="user_password" required><br>
        
        <button type="submit">가입</button>
    </form>
</body>
</html>
