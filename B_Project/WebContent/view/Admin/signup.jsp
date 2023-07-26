<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery 라이브러리 불러오기 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> <!-- 부트스트랩 CSS 불러오기 -->
    <script>
        function closeOnSubmit() {
            $("#loginForm").submit();
            window.close();
            return false;
        }
    </script>
</head>

<body>
    <div class="container mt-5">
        <h1 class="mb-4">회원가입(관리자용)</h1>
        <form action="/join.do" method="post" id="loginForm" onsubmit="return closeOnSubmit()" target="_self">
            <input type="hidden" name="admin" value="admin">
            
            <div class="form-group">
                <label for="user_id">ID:</label>
                <input type="text" class="form-control" id="user_id" name="user_id" required>
            </div>
            
            <div class="form-group">
                <label for="user_password">PW:</label>
                <input type="password" class="form-control" id="user_password" name="user_password" required>
            </div>
            
            <button type="submit" class="btn btn-primary">가입</button>
        </form>
    </div>

    <!-- 부트스트랩 JS 및 jQuery 링크 추가 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
