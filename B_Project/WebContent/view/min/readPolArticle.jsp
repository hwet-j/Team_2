<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>
   <!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

</head>
<body>

<%@ include file = "/header.html" %>>
 <!-- 내용 -->	
    <!-- page title -->
	<h2  class="mt-4 mb-3 text-center">게시글 상세보기</h2>
	<div class ="#">
	
	<table class="table table-bordered mt-3">
	 <tbody>
	  <tr>
	   <th scope="row" style="width:15%;">글번호</th>
	   <td>${pad.number}</td>
	  </tr>
	  <tr>
	   <th scope="row">작성자id</th>
	   <td>${pad.writer_id}</td>
	  </tr>
	  <tr>
	   <th scope="row">작성자명</th>
	   <td>${pad.writer_name}</td>
	  </tr>
	  <tr>
	   <th scope="row">제목</th>
	   <td>${pad.title}</td>
	  </tr>
	  <tr>
	   <th scope="row">작성일</th>
	   <td>${pad.regDate}</td>
	  </tr>
	  <tr>
	   <th scope="row">수정일</th>
	   <td>${pad.modifiedDate}</td>
	  </tr>
	  <tr>
	   <th scope="row">조회수</th>
	   <td>${pad.readCount}</td>
	  </tr>
	  <tr>
	   <th scope="row">내용</th>
	   <td>${pad.content}</td>
	  </tr>
	 </tbody>	
	</table>
	
	<!-- button --> 
	<!-- d-flex:한개의 row를 block레벨로 차지 
	   flex-start:왼쪽정렬(기본)/ flex-end:오른쪽정렬 / flex-center:가운데정렬
	   justify-content-end : 오른쪽정렬-->
	<div class="d-flex justify-content-end">
	 <c:set var="pageNo" value="${empty param.pageNo?1:param.pageNo}" /> 
     <a href="polList.do?pageNo=${pageNo}" class="btn btn-outline-dark">목록보기</a>
     
     <c:if test="${AUTH_USER.user_id==pad.writer_id}">
	  <a href="polmodify.do?no=${pad.number}" class="btn btn-outline-dark">게시글수정</a>
     </c:if>
		 

  <c:if test="${AUTH_USER.user_id eq pad.writer_id}">
	   <a href="poldelete.do?no=${pad.number}" class="btn btn-outline-dark">게시글삭제(del)</a>
	 </c:if>
	</div>
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













