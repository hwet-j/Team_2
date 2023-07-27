<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>
 
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 $(function(){

 });
 </script>
</head>
<body>


 pad : ${pad}
 <!-- 내용 -->	
    <!-- page title -->
	<h2  class="mt-4 mb-3 text-center">상세보기(readPolArticle/p662)</h2>
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
	   <td><fmt:formatDate value="${pad.regDate}"   pattern="yyyy년 M월 d일"/></td>
	  </tr>
	  <tr>
	   <th scope="row">수정일</th>
	   <td><fmt:formatDate value="${pad.modifiedDate}"   pattern="yyyy.MM.dd HH:mm:ss"/> </td>
	  </tr>
	  <tr>
	   <th scope="row">조회수</th>
	   <td>${pad.readCount}</td>
	  </tr>
	  <tr>
	   <th scope="row">내용</th>
	   <td><u:pre value="${pad.content}"/></td>
	  </tr>
	 </tbody>	
	</table>
	
	<!-- button --> 
	<!-- d-flex:한개의 row를 block레벨로 차지 
	   flex-start:왼쪽정렬(기본)/ flex-end:오른쪽정렬 / flex-center:가운데정렬
	   justify-content-end : 오른쪽정렬-->
	<div class="d-flex justify-content-end">
	 <c:set var="pageNo" value="${empty param.pageNo?1:param.pageNo}" /> 
     <a href="list.do?pageNo=${pageNo}" class="btn btn-outline-dark">목록보기</a>
     
     <c:if test="${AUTH_USER.id==ora.writer_id}">
	  <a href="modify.do?no=${pad.number}" class="btn btn-outline-dark">게시글수정</a>
     </c:if>
		 


	  <c:if test="${AUTH_USER.id eq pad.writer_id}">
	   <a href="delete.do?no=${pad.number}" class="btn btn-outline-dark">게시글삭제(up)</a>
	 </c:if> 
	</div>
 </div>
</body>
</html>













