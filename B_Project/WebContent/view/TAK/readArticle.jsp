<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u"   tagdir="/WEB-INF/tags"%>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>

<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>
 <!-- Bootstrap 4 CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
 
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 $(function(){

 });
 </script>
</head>
<body>

<%--//리턴 OurArticleData ora : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
	OurArticleData ora = readArticleService.getDetail(no);  
	request.setAttribute("ora", ora); --%>
 ora : ${ora} 

 <div class="container">
    <!-- page title -->
	<h2  class="mt-5 mb-4 text-center">상세보기(readArticle/p662)</h2>
	
	<table class="table table-bordered mt-3">
	 <tbody>
	  <tr>
	   <th scope="row" style="width:15%;">글번호</th>
	   <td>${ora.number}</td>
	  </tr>
	  <tr>
	   <th scope="row">작성자id</th>
	   <td>${ora.writer_id}</td>
	  </tr>
	  <tr>
	   <th scope="row">작성자명</th>
	   <td>${ora.writer_name}</td>
	  </tr>
	  <tr>
	   <th scope="row">제목</th>
	   <td>${ora.title}</td>
	  </tr>
	  <tr>
	   <th scope="row">작성일</th>
	   <td><fmt:formatDate value="${ora.regDate}"   pattern="yyyy년 M월 d일"/></td>
	  </tr>
	  <tr>
	   <th scope="row">수정일</th>
	   <td><fmt:formatDate value="${ora.modifiedDate}"   pattern="yyyy.MM.dd HH:mm:ss"/> </td>
	  </tr>
	  <tr>
	   <th scope="row">조회수</th>
	   <td>${ora.readCount}</td>
	  </tr>
	  <tr>
	   <th scope="row">좋아요</th>
	   <td>${ora.good}
	  <%--  <form name="good "action="/article/good.do" method="post">
        <input type="submit" name="${ora.number}" value="좋아요">
      </form> --%>
      <a href="/tak/article/good.do?no=${ora.number}">좋아요</a>
	   </td>
	  </tr>
	  <tr>
	   <th scope="row">내용</th>
	   <td><u:pre value="${ora.content}"/></td>
	  </tr>
	 </tbody>	
	</table>
	
	
	<!-- button --> 
	<!-- d-flex:한개의 row를 block레벨로 차지 
	   flex-start:왼쪽정렬(기본)/ flex-end:오른쪽정렬 / flex-center:가운데정렬
	   justify-content-end : 오른쪽정렬-->
	<div class="d-flex justify-content-between">
	    <!-- 왼쪽에 바로가기 버튼을 추가 -->
		<c:set var="content" value="${ora.content}" />
		<c:choose>
			<c:when test="${content.contains('<') && content.contains('>')}">
				<c:set var="startIndex" value="${content.indexOf('<')}" />
				<c:set var="endIndex" value="${content.indexOf('>')}" />
				<c:set var="extractedText" value="${content.substring(startIndex + 1, endIndex)}" />
				<a href="${extractedText}" class="btn btn-outline-dark">신고글 바로가기</a>
			</c:when>
			<c:otherwise>
				<!-- <a href="#" class="btn btn-outline-dark" disabled>바로가기</a> -->
			</c:otherwise>
		</c:choose>
	<%--  ${extractedText} --%>
	    <c:set var="pageNo" value="${empty param.pageNo ? 1 : param.pageNo}" /> 
	    <a href="list.do?pageNo=${pageNo}" class="btn btn-outline-dark ml-auto">목록보기</a>
	    
	    <c:if test="${AUTH_USER.user_id == ora.writer_id}">
	        <a href="modify.do?no=${ora.number}" class="btn btn-outline-dark">게시글수정</a>
	    </c:if>
	    
	    <c:if test="${(AUTH_USER.user_id == ora.writer_id) || (AUTH_USER.user_id == 'adminid')}">
	        <a href="delete.do?no=${ora.number}" class="btn btn-outline-dark">게시글삭제(up)</a>
	    </c:if> 
	</div>
 </div>
 

<%-- String pageNo = "1";
 <c:set var="변수명" value="값">
 param.pageNo       파라미터명이 pageNo를 의미
 empty param.pageNo 파라미터 pageNo가 비어있니? 비어있으면 true, 그렇지않으면 false리턴
 ${empty param.pageNo?1:param.pageNo} 
 파라미터 pageNo가 비어있으면  1을,
 파라미터 pageNo가 비어있지않으면   파라미터 pageNo에 저장된 값을 출력하라
 
 value="${empty param.pageNo?1:param.pageNo}  1또는  파라미터 pageNo에 저장된 값을
 var="pageNo"라고 정의한 변수pageNo에 값으로 저장해라
 --%>

 
 	
 	
 	
 	
 	
 	
 	
<!-- Bootstrap 4 JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXakfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz4fnFO9gybBvRLFyyN+kW/BQro3T8j6XI4lK7T7rM46_tC6Y1Bf/Dbdbp"
        crossorigin="anonymous"></script>
<script="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
        crossorigin="anonymous"></script>
        	
</body>
</html>













