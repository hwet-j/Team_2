<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title></title>
    
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 $(function(){

 });
 </script>
</head>
<body>

polArticlePage : ${polArticlePage}
<p><hr/></p>
<h2  class="mt-5 mb-4 text-center">polListArticle(p653)</h2>
<div class = "#">
<table class = "#" border = "1" >
    <thead> 
   <div>총 게시글수 : ${polArticlePage.total}건 / 현재페이지 : ${nowPage}</div>
 	  <tr>
 	  	<th scope="col">번호no</th>
 	  	<th scope="col">제목title</th>
 	  	<th scope="col">작성자writer_name</th>
 	  	<th scope="col">조회수read_cnt</th>
 	  </tr>
 	 </thead>
 	 <tbody> <%-- 총게시글(수가 0이면)없으면  true리턴, 그렇지 않으면 false --%>
 	  <c:if test="${polArticlePage.hasNoPolArticle()}"> 
 	  <tr>
 	  	<td colspan="4">게시글이 존재하지 않습니다. 첫번째 작성자가 되세요~</td>
 	  </tr>
 	  </c:if>
 	 
 	  <c:forEach var="polArticle"  items="${polArticlePage.content}" >
 	  <tr>
 	  	<td>${polArticle.number}</td>
 	  	<%-- read.do?no=상세하게보고싶은글번호&pageNo=현재페이지"  --%>
 	  	<td><a href="read.do?no=${polArticle.number}&pageNo=${nowPage}">${polArticle.title}</a></td>
 	  	<td>${polArticle.writer.name}/${polArticle.writer.id}</td>
 	  	<td>${polArticle.readCount}</td>
 	  </tr>
 	  </c:forEach> <%-- 내용출력 끝 --%>
 	 </tbody>
 	</table>
 	<div>
 	  <a href="write.do" class="btn btn-outline-dark btn-sm">글 쓰기(p653 12라인)</a>
 	</div>
  	 <br/>          
</body>
</html>
