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
	        <c:forEach items="${chat_rooms}" var="room">
	            <li class="list-group-item">
	                <form action="/chat/showRoom.do" method="post">
	                    <input type="hidden" name="room_id" value="${room.room_id}" />
	                    <input type="hidden" name="room_name" value="${room.room_name}" />
	                    <button type="submit" class="btn btn-link ml-2">채팅방 입장</button>
	                </form>
	                <span class="font-weight-bold">채팅방 이름: ${room.room_name}</span>
	                <ul class="list-unstyled mt-2 mb-0">
	                    <span class="font-weight-bold">참여자: </span>
	                    <c:forEach items="${chat_participants}" var="participant" varStatus="status">
	                        <c:if test="${participant.room_id == room.room_id}">
	                            <ol>
	                                <span>${participant.invite_user}</span> 
	                            </ol>
	                        </c:if>
	                    </c:forEach>
	                </ul>
	            </li>
	        </c:forEach>
	    </ul>
	</div>


    <!-- 부트스트랩 JS 스크립트 링크 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
