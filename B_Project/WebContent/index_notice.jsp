<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>MAIN</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
  #i1 {font-weight:900;}
	
  .intro {
   color: white;
   background-color: green;
  }
  .pointer {cursor: pointer;}

</style>
<script>
$(document).ready(function(){
	$("#LogoutBtn").on("click",function(){
		location.href="http://localhost/logout.do";
	});
	$("#spanLogout").on("click",function(){
		location.href="http://localhost/logout.do";
	}).on("mouseover mouseout", function(){
	    $(this).toggleClass("intro pointer");
	});
});
</script>
</head>
<body>
<%= request.getContextPath()  %>
	<h2>메인</h2>
	<a href="/article/list.do">게시판보기</a>
	<c:if test= "${empty AUTH_USER}">
	<a href= "login.do">로그인</a>
	<br/><br/><br/>
	</c:if>
	<%--회원 로그인 --%>
	<c:if test="${!empty AUTH_USER  and  AUTH_USER.id!='adminid'}"> 
    <span id="i1">${AUTH_USER.name} 님</span> 
 	<button type="button" id="LogoutBtn">로그아웃</button>
 	<a href="logout.do">로그아웃</a>
 	<span id="spanLogout">로그아웃</span>
 	<a href="changePwd.do">비번변경</a>
 	<a href="#">내 정보</a>
 	<a href="/notice/write.do">글쓰기</a>
 	<br/><br/><br/>
</c:if>
	<%--관리자 로그인 --%>
	 <c:if test="${!empty AUTH_USER  and  AUTH_USER.id=='adminid'}"> 
    <span id="i1">${AUTH_USER.name} 님</span> 
 	<button type="button" id="LogoutBtn">로그아웃</button>
 	<a href="changePwd.do">비번변경</a>
 	<a href="#">공지사항관리</a><br/>
 	<a href="#">공지사항등록</a><br/>
 	<br/><br/><br/>
</c:if>
	
</body>
</html>