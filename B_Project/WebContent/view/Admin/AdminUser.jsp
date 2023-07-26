<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Insert title here</title>
 <script>
 $()
 </script>
</head>
<body>
<% String message = (String) request.getAttribute("MSG"); %>
<% if (message != null && !message.isEmpty()) { %>
    <script>
        var message = '<%= message %>';
        alert(message);
    </script>
<% } %>
<%@ include file="/navi.jsp" %>
<!-- 여기아래로 -->
<h2 style="text-align: center;">관리자 페이지</h2>

<div class="container mt-4">
<table class="table table-hover">
<thead>
	<tr>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>생년월일</td>
		<td>닉네임</td>
		<td>성별</td>
		<td>연락처</td>
		<td>가입일</td>
		<td>회원 삭제</td>
	</tr>
</thead>
<tbody>
<c:forEach var="user" items="${USERS}">
	<tr>
		<td>${user.user_id }</td>
		<td>${user.user_pw }</td>
		<td>${user.user_name }</td>
		<td>${user.user_birth }</td>
		<td>${user.user_nickname }</td>
		<td>${user.user_gender }</td>
		<td>${user.user_tlno }</td>
		<td>${user.user_join_date }</td>
		<td><a href="/admin/deleteUser.do?user_id=${user.user_id }">
		<button class="btn-primary">회원 삭제</button></a> 
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>

</body>
</html>