<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<script>

</script>
<title>공지사항작성페이지</title>
</head>
<body>
<h1>글을 입력하세요</h1>
<c:if test="${AUTH_USER.id eq null}">
잘못된 경로의 접근입니다. 로그인하세요.
</c:if>
<c:if test="${AUTH_USER.id ne null}">
<form action="/notice/writeNotice.do" method="post">
<p> 작성자: ${AUTH_USER.id}
<input type="hidden" name="id" value="${AUTH_USER.id }">
<input type="hidden" name="pageNo" value="1">
</p>
<table>
	<tr>
	 <td> 제목 </td>
	 <td><input type="text" name="title" style="width: 600px;"></td>
	 </tr>
	 <tr>
	  <td>내용</td>
	  <td><input type="text" name="content" style="width: 600px; height: 600px;"></td>
	  </tr>
</table>
<p> <button type="submit">글 작성</button> </p>
<p> <button type="reset">글 다시쓰기</button> </p>
</form>
</c:if>
</body>
</html>