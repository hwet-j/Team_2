<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
$(function(){
	
});
</script>
</head>
<body>

	<h2>로그인폼</h2>
	<div class="container">
  <h2>Login</h2>
  <form id="loginForm" action="login.do" method="post"> 
 	<div>
	 	<label for="memberid">ID</label>
	 	<input type="text" name="memberid" id="memberid" class="" />
	 	<c:if test="${errors.id}"><span class="error">ID를 입력하세요</span></c:if>
 	</div>
 	<div>
	 	<label for="password">비밀번호</label>
	 	<input type="password" name="password" id="password" class="" /><br/>
	 	<c:if test="${errors.password}"><span class="error">비밀번호를 입력하세요</span></c:if>
	 	<c:if test="${errors.idOrPwNotMatch}"><span class="error">아이디 혹은 비밀번호가 일치하지 않습니다.</span></c:if>
 	</div>
 	<div>
	 	<input  type="submit" value="로그인(java용)" class=""/>
	 	<input  type="reset"  value="취소" class=""/>
	</div> 	
  </form>		
 </div>
</body>
</html> --%>