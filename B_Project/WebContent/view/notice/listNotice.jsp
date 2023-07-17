<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
$(function(){
	
});
</script>
</head>
<body>

	noticePage : ${noticePage}
	<p><hr/></p>
	
	<div class="">
	<h1 class="">공지사항</h1>
	<div>총 게시글수: ${noticePage.total}건 /현재페이지: ${nowPage}</div>
	<table class="" border="1">
	 <thead class="">
	  <tr>
	  <th scope="col">번호no</th>
	  <th scope="col">제목title</th>
	  <th scope="col">작성자user_id</th>
	  <th scope="col">조회수views</th>
	  </tr>
	 </thead>
	 <tbody>
	  <c:if test="${articlePage.hasNoNotice()}">
	  <tr>
		  <td colspan="4">게시글이 존재하지 않습니다. 게시글을 입력해주세요</td>
	  </tr>
	 </c:if> 
	 
	  
	  <c:forEach var="notice"   items="${noticePage.content}">
	  <tr>
		  <td>${notice.number}</td>
		  <td><a href="read.do?no=${notice.number}&pageNo=${nowPage}">${notice.notice_title}</a></td>
		  <td>${notice.user_id}</td>
		  <td>${notice.notice_views}</td>
	  </tr>
	  </c:forEach> 
	  <tr>
	  	<td colspan="4"><a href="write.do">글 쓰기</a></td>
	  </tr>
	 </tbody>
	</table>
	<br/><br/>

	<c:if test="${noticePage.startPage>3}">
		<a href="list.do?pageNo=${noticePage.startPage-3}">&lt;&lt;prev</a>
	</c:if>
	<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}" step="1">
	<a href="list.do?pageNo=${pNo}">${pNo}</a>
	</c:forEach>

	<c:if test="${noticePage.endPage<noticePage.totalPages}">
	<a href="list.do?pageNo=${noticePage.startPage+3}">next&gt;&gt;</a>
	</c:if>	
	
	</div>

</body>
</html>