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
<%@ include file = "/header.html" %>
<!-- 여기아래로 -->
<h1>글 작성하기</h1>
<div class="form-group">
<form action="/CJH/whi_board/write.do" method="post">
<p>
작성자 아이디<input type="text" name="id" value="${AUTH_USER.user_id }" readonly="readonly">
</p>
<p>
<div class="mb-3">
<label for="whiskySelect" class="form-label">위스키 분류 :</label>
<select class="form-select" name="category">
	<option value="버번">버번</option>
	<option value="스페이사이드">스페이사이드</option>
	<option value="스카치">스카치</option>
	<option value="아일라">아일라</option>
	<option value="기타">기타</option>
</select> 
</div>
<div>
	글 제목 <input type="text" name="title" class="form-control col-md-6" width="600px;">
</div>
<div>
	본문<textarea class="form-control col-md-8" rows="50" cols="100" name="content"></textarea>
</div>
<div>
	<button type="submit" class="btn-primary md-2">글작성</button>
</div>
</form>
</div>
<br>
<br>
<br>
<br>
<br>
<!-- 여기위로 -->
<%@ include file="/view/contact.jsp" %>
</body>
</html>