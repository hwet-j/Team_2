<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script>

 </script>
</head>
<body>

<%@ include file="/navi.jsp" %>
<!-- 여기아래로 -->

<h1>temporary main page</h1>

 <form action="/CJH/whi_board/list.do?pageNo=1" method="post"><button type="submit">위스키 게시판으로 이동</button><input type="hidden" name ="pageNo" value="1"> </form>
<br>
<!-- 여기위로 -->
<%@ include file="/footer.jsp" %>
</body>
</html>