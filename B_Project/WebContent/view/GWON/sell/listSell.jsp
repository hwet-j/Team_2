<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.table {
	padding : 50px;
}
</style>

</head>
<body>
<%@ include file = "../../../navi.jsp" %>

<form method="post" action="/gwon/sell/sellSerach.do">
<div class="container">
	<div class="row">
			<table class="" style = "float : right;">
				<tr>
					<td>
					  <select class="form-control" name="searchColumn">
							<!-- <option selected>선택하세요</option> 가능하면 구현하기-->
							<option value="sell_title">제목</option>
							<option value="user_id">작성자</option>
							<option value="sell_category">카테고리</option>
							<option value="sell_price">희망가격</option>
							<option value="sell_loc">지역</option>
					  </select>
					</td>
					<td><input type="text" class="form-control" placeholder="검색어 입력" name="searchText" maxlength="100"></td>
					<td><button type="submit" class="btn btn-secondary">검색</button></td>
				</tr>
			</table>
	</div>
</div>

<div class = "container">
	<table class = "table" >
		<thead>
		  <tr>
		  	<th scope = "col">번호</th>
		  	<th scope = "col">카테고리</th>
		  	<th scope = "col">제목</th>
		  	<th scope = "col">작성자</th>
		  	<th scope = "col">희망가격</th>
		  	<th scope = "col">지역</th>
		  	<th scope = "col">조회수</th>
		  </tr>
		 </thead>
		 
		 <tbody>
		  	  <c:if test="${sellPage.hasNoSells()}"> 
 	  			<tr>
 	  			<td colspan="4"> 게시글이 없습니다 </td>
 	 		    </tr>
 	 		 </c:if>
			 <c:forEach var = "sell" items = "${sellPage.content}">
			  <tr>
			    <td>${sell.sell_no}</td>
			    <td>${sell.sell_category}</td>
			    <td><a href="/gwon/sell/sellRead.do?no=${sell.sell_no}&pageNo=${nowPage}">${sell.sell_title}</a></td>
			    <td>${sell.writer.user_id}</td>
			    <td>${sell.sell_price}원</td>
			    <td>${sell.sell_loc}</td>
			    <td>${sell.sell_read_cnt}</td>
			   </tr>
			 </c:forEach>
		 </tbody>
		 </table>
		 
		 <c:if test="${AUTH_USER.user_id != sellDTO.user_id}">
	 	 <a href="/gwon/sell/sellWrite.do" class="btn btn-outline-primary" style = "float: right; "role="button">작성</a>
	 	 </c:if>
	 	 
         <div>
			 <nav aria-label="Page navigation example" style = "claar : both">
			  <ul class="pagination justify-content-center"> 
			  <c:if test="${sellPage.startPage>10}">
			   <li class="page-item"><a class="page-link" href="/gwon/sell/sellList.dopageNo=${sellPage.startPage-10}">&lt;&lt;이전</a></li>
			  </c:if> 
			  
			  <c:forEach var="pNo"  begin="${sellPage.startPage}"    end="${sellPage.endPage}"  step="1">
			   <li class="page-item active"><a class="page-link" href="/gwon/sell/sellList.do?pageNo=${pNo}">${pNo}</a></li>
			  </c:forEach>
			  
			  <c:if test="${sellPage.endPage<sellPage.totalPages}"> 
			   <li class="page-item"><a class="page-link" href="/gwon/sell/sellList.do?pageNo=${sellPage.startPage+10}">다음&gt;&gt;</a></li>
			  </c:if>
			  </ul> 
			 </nav> 
	    </div>
</div>

</form>
</body>

<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>