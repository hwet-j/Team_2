<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/navi.jsp" %>
<!-- 여기아래로 -->
<h1>글 작성하기</h1>
<form action="/CJH/whi_board/write.do" method="post">
<p>
작성자 아이디<input type="text" name="id">
</p>
<p>
위스키 종류 
<input type="radio" name="category" value="버번">버번
<input type="radio" name="category" value="싱글몰트">싱글몰트
<input type="radio" name="category" value="블렌디드">블렌디드
<input type="radio" name="category" value="기타">기타
</p>
<p>
글 제목 <input type="text" name="title" width="500px">
</p>
<p>
본문 <br>
<textarea rows="50" cols="100" name="content"></textarea>
</p>
<div>
	<button type="submit">글작성</button>
</div>
</form>

<br>
<br>
<br>
<br>
<br>
<!-- 여기위로 -->
</body>
</html>