<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지검색</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
function dataCheck(){
	if(document.getElementById("search").value =="")
	
		{
		alert("검색할 단어를 입력하세요");
		document.getElementById("search").focus();
		return;
		}
	document.form.submit();
	}
</script>
</head>
<body>
	<h2></h2>
	
	<div>총 게시글수: ${noticePage.total}건 /현재페이지: ${nowPage}</div>
	<table class="table table-hover">
	 <thead class="thead-light">
	  <tr>
	  <th scope="col">번호</th>
	  <th scope="col">제목</th>
	  <th scope="col">작성자</th>
	  <th scope="col">작성일</th>
	  <th scope="col">조회수</th>
	  </tr>
	 
	  
	  
	  
	  </table>
</body>
</html> --%>