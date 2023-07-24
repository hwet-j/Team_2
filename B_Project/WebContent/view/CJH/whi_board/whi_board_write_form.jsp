<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
<button type="submit">글작성</button>
</form>
</body>
</html>