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
#floating-button {
	margin : 2px;
}
</style>


</head>
<body>
<%@ include file = "../../../header.html" %>

 <div style = "text-align : center; font-size : 20px"><h3>글 작성이 완료되었습니다.</h3></div>
 <br/><br/><br/>
 <a id = "floating-button" class="btn btn-secondary" href="/gwon/sell/sellList.do" style = "float: right; "role="button">목록</a>
 <a id = "floating-button" class="btn btn-secondary" href="/gwon/sell/sellRead.do?no=${writeSellinfo}" style = "float: right; "role="button">확인</a>
<br>
<br>
<%@ include file = "../../../footer.html" %>
</body>



<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>

