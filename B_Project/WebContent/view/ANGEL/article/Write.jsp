<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@ include file = "../../../navi.jsp" %>
<form action="/Angel/write.do" method="post">
<div class="container-fluid" style="margin: 20px;">
<input type="hidden" name="articleNo" value="${articleNo}"/>
<p>아이디 : <br><input type="text" name="memberid" size="20"></input></p>
<p>제목 : <br><input type="text" name="title" size="20"></input></p>
<p>이름 : <br><input type="text" name="name" size="20"></input></p>
<p>내용 : <br><textarea name="content" rows="20" cols="50"></textarea></p>
<input type="submit" class="btn-primary" value="글쓰기"></input>
</div>
</form>
</body>
<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>