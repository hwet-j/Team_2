<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>공지글</h2>
<table border="1">
	<thead>
	<tr>
		<td>게시글번호</td>
		<td>작성자아이디</td>
		<td style="width:300px;">제목</td>
		<td>작성일</td>
		<td>조회수</td>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="notice" items="${NOTICE}">
	<tr>
		<td>${notice.notice_no}</td>
		<td>${notice.writer_id}</td>
		<td><a href="/notice/showNotice.do?num=${notice.notice_no}">${notice.title}</a></td>
		<td>${notice.regdate}</td>
		<td>${notice.read_cnt}</td>
	</tr>
	</c:forEach>
	</tbody>
	<tfoot>
	<tr>
	<td colspan="5">
	페이지 번호 : 
	<c:forEach var="pNo" begin="1" end="${page_count}" step="1">
	<a class="page-link" href="/notice/showNotice.do?pageNo=${pNo}"></a>
	</c:forEach>
	</td>
	</tr>
	</tfoot>
	</table>
	<br>
	<c:if test="${!empty AUTH_USER}"><a href="/view/board/writeNoticeForm.jsp"><button type="button">공지글 작성</button></a></c:if>
</body>
</html>