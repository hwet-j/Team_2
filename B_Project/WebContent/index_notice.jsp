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
 <h2>index_notice.jsp(메인화면)</h2>
 <pre>프로토콜://ip주소:포트번호/컨패/경로
     http://localhost/
     http://localhost/index_notice.jsp
 </pre>	
<%@ include file="/navi.jsp" %>
<%= request.getContextPath()  %>
	<h2>메인</h2>
	<a href="/notice/list.do">공지보기</a>
	<c:if test= "${empty AUTH_USER}">
	<a href= "login.do">로그인</a>
	<br/><br/><br/>
	</c:if>
	<%-- LoginHandler.java를 거쳐오게되면 아래와 같이 Model넘어온다
    //User - 로그인에 성공한 회원정보(회원id,회원명)=>세션에 저장될 정보
	User user = loginService.login(memberid, password);
	session.setAttribute("AUTH_USER", user); 
  --%>
 <%-- (user)로그인을 했을 경우 보여지는 부분 시작 --%>
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
	<%-- 로그인을 했을 경우 보여지는 부분 끝 --%>

<%-- (관리자)로그인을 했을 경우 보여지는 부분 시작 --%>
	 <c:if test="${!empty AUTH_USER  and  AUTH_USER.id=='adminid'}"> 
    <span id="i1">${AUTH_USER.name} 님</span> 
 	<button type="button" id="LogoutBtn">로그아웃</button>
 	<a href="/notice/list.do">공지사항관리</a><br/>
 	<a href="/notice/write.do">공지사항등록</a><br/>
 	<%@ include file="/footer.jsp" %>
 	<br/><br/><br/>
</c:if>
	
</body>
</html> 