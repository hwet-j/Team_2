<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>채팅방</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">채팅방</h1>

        <!-- 채팅방 목록 출력 -->
        <h2>채팅방 목록</h2>
        <ul class="list-group">
            <c:forEach items="${chatRooms}" var="room">
                <li class="list-group-item"><a href="chat_room.jsp?room_id=${room.room_id}">${room.room_name}</a></li>
            </c:forEach>
        </ul>
    </div>

    <!-- 부트스트랩 JS 스크립트 링크 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
