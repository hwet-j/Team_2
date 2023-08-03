<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <style>
#floating-button {
	margin : 2px;
}
.table {
	padding : 50px;
}

.searchTab {
	margin-bottom : 5px;
}
</style>
    
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

</head>
<body>
<%@ include file = "/header.html" %>

<h2  class="title" align = "center">정치게시판(beta)</h2>
<div class = "#">
<table class = "#" border = "1" >
    <thead> 
   <div>총 게시글수 : ${polArticlePage.total}건 / 현재페이지 : ${nowPage}</div>
 	  <table class = "table" >
 	  <tr>
 	  	<th scope="col">글번호</th>
 	  	<th scope="col">제목</th>
 	  	<th scope="col">작성자</th>
 	  	<th scope="col">작성일</th>
 	  	<th scope="col">조회수</th>
 	  </tr>
 	 </thead>
 	 <tbody> <%-- 총게시글(수가 0이면)없으면  true리턴, 그렇지 않으면 false --%>
 	 
 	 
 	 <c:if test="${polArticlePage.hasNoPolArticle()}"> 
  <tr>
    <td colspan="4" style="text-align: center;">게시글이 존재하지 않습니다.</td>
  </tr>
  <tr>
    <td colspan="5" style="text-align: center;">
      <button onclick="goBack()" class="btn btn-outline-dark">이전 페이지로 이동</button>
      <a href="polList.do?pageNo=1" class="btn btn-outline-dark">전체글보기</a>
    </td>
  </tr>
  <tr>
    <script>
      function goBack() {
        window.history.back();
      }
    </script>
  </tr>
</c:if>
 	 
 	  <c:forEach var="polArticle"  items="${polArticlePage.content}" >
 	  <tr>
 	  	<td>${polArticle.number}</td>
 	  	<%-- read.do?no=상세하게보고싶은글번호&pageNo=현재페이지"  --%>
 	  	<td><a href="polread.do?no=${polArticle.number}&pageNo=${nowPage}">${polArticle.title}</a></td> <%--제목번호에 행당링크를 압축해놓음 --%>
 	  	<td>${polArticle.writer.name}(${polArticle.writer.id})</td>
 	  	<td>${polArticle.regdate}</td>
 	  	<td>${polArticle.readCount}</td>
 	  </tr>
 	  </c:forEach> <%-- 내용출력 끝 --%>
 	 </tbody>
 	</table>
 	
 	
 	<div>
 	  <a href="polwrite.do" class="btn btn-outline-dark btn-sm">글작성</a>
 	  </div>   
	
 	<!-- 검색기능 -->
	 <form id="search" action="/min/article/polSearch.do" method="post" class="centered-form">
	  <div>
	 	<label for="search"></label>
	 	<input type="text" name="searchcontent" id="searchcontent" placeholder="검색할 제목 입력" />
	 	<input type="submit" value="검색"/>
	  </div>
 	</form>
 	
 	
 	<!-- 페이징처리 -->
	  <div>
	  <nav aria-label="Page navigation example" style = "claar : both">
		 <ul class="pagination justify-content-center">
		   <%--<c:if>이용하여 노출여부가 달라진다 --%>

  			 <%--p653 43라인 <c:forEach></c:forEach>반복문이용 --%>
 			 <c:forEach var="pNo"  begin="${polArticlePage.startPage}" end="${polArticlePage.endPage}"  step="1">
   			<li class="page-item active"><a class="page-link" href="polList.do?pageNo=${pNo}">${pNo}</a></li>
  				</c:forEach>

			  </ul> 
			 </nav> 
	    </div>
 	          
<br>
<br>
<%@ include file = "/footer.html" %>
</body>

<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>
