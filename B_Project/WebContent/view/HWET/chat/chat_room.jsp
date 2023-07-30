<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>${roomName} 채팅방</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">${roomName} 채팅방</h1>

        <!-- 메시지 출력 -->
        <div id="chat-box" class="border p-2 mt-4">
            <c:forEach items="${messages}" var="message">
                <p>${message.sender_name}: ${message.content}</p>
            </c:forEach>
        </div>

        <!-- 메시지 작성 폼 -->
        <form action="send_message.jsp" method="post" class="mt-4">
            <input type="hidden" name="room_id" value="${param.room_id}" />
            <input type="hidden" name="sender_id" value="${currentUserId}" />
            <textarea class="form-control mb-2" name="content" rows="3"></textarea>
            <input type="submit" class="btn btn-primary" value="보내기" />
        </form>
    </div>

    <!-- 부트스트랩 JS 스크립트 링크 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
