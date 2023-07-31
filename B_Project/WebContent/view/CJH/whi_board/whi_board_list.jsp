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


<title>위스키 보틀 사진 게시판</title>

</head>
<body>
<%@ include file="/navi.jsp" %>
<!-- 여기아래로 -->
<!-- 메인 이미지 -->
<div style="text-align: center;">
<img src="/assets/images/whiBoard/whiskey_banner.jpg" style="height: 100px; width: auto;" >
</div>
<!-- 테이블을 포함한 영역 -->
<div class="container mt-2">
<div class="btn-group mb-2 mt-2">
<a href="/CJH/whi_board/list.do?pageNo=1"><button type="button" class="btn btn-secondary">전체글보기</button></a>
<c:forEach var="category" items="${CATEGORY_LIST }">
<a href="/CJH/whi_board/categorize.do?pageNo=1&category=${category}"><button type="button" class="btn btn-secondary">${category}</button></a>
</c:forEach>
</div>
<!-- 테이블시작 -->
<table class="table table-bordered table-hover" border="1" >
	<thead class="thead-dark" style="text-align: center; background-color: black; color: white;">
		<tr>
			<td style="width: 80px;">글번호</td>
			<td style="width: 80px;">분류</td>
			<td style="width: 80px;">사용자</td>			
			<td>글 제목</td>
			<td style="width: 200px;">작성일</td>
			<td style="width: 100px;">조회수</td>
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
		<tr>
		<td colspan="6">
		<c:forEach var="num" begin="1" end="${PAGECNT}"><a href="/CJH/whi_board/list.do?pageNo=${num}" class="btn btn-secondary">${num}</a> </c:forEach>
		</td>
		</tr>
	</tfoot>	
</table>
<!-- 글작성버튼 -->
<div style="float: right;">
<c:if test="${not empty AUTH_USER}">
<div style="font-size: small;"><a href="/view/CJH/whi_board/whi_board_write_form.jsp"><button type="button"class="btn btn-primary">글 작성</button></a></div>
</c:if>
</div>
<!-- 검색창 -->
<form action="/CJH/whi_board/search.do" method="get">
  <input type="hidden" name="pageNo" value="1"> 
  <div class="row g-2 align-items-center">
    <div class="col-auto">
      <select name="search_type" class="form-select">
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="user_id">작성자ID</option>
      </select>
    </div>    
      <input type="text" name="subject" class="form-control mr-4" style="width: 33.33%;" placeholder="검색하기">
      <button type="submit" class="btn btn-primary">검색</button>
  </div>
</form>
<!-- 검색창을 포함한 영역 -->

</div>
<!-- 테이블끝 -->

<div >

</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<!-- 여기위로 -->
<%@ include file="/footer.jsp" %>
</body>
</html>