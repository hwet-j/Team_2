<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<title>위스키 게시판</title>

</head>
<body>
<%@ include file="/navi.jsp" %>
<!-- 여기아래로 -->
<h2 style="text-align: center; margin-top: 40px;">제목제목</h2>

<div class="container mt-5">
<table class="table table-bordered table-hover" border="1" >
	<thead class="thead-dark" style="text-align: center; background-color: black; color: white;">
		<tr>
			<td style="width: 80px;">글번호</td>
			<td style="width: 80px;">분류</td>
			<td style="width: 80px;">사용자</td>			
			<td>글 제목</td>
			<td style="width: 200px;">작성일</td>
			<td style="width: 80px;">조회수</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="arti" items="${WHI_ARTICLE}">
		<tr>
			<td style="text-align: center;">${arti.articleNo }</td>
			<td style="text-align: center;">${arti.category }</td>			
			<td>${arti.userId }</td>
			<td><a href="/CJH/whi_board/content.do?articleNo=${arti.articleNo}">${arti.title }</a></td>
			<td>${arti.regDate }</td>
			<td>${arti.readCnt }</td>
		</tr>
	</c:forEach>
	</tbody>
	<tfoot>
		<tr class="">
		<td colspan="6">
		<c:forEach var="num" begin="1" end="${PAGECNT}"><a href="/CJH/whi_board/list.do?pageNo=${num}">${num}</a> </c:forEach>
		</td>
		</tr>
	</tfoot>
</table>
</div>
<br>
<a href="/view/CJH/whi_board/whi_board_write_form.jsp"><button type="button">글 작성</button></a> 




<br>
<br>
<br>
<br>
<br>
<!-- 여기위로 -->
<%@ include file="/footer.jsp" %>
</body>
</html>