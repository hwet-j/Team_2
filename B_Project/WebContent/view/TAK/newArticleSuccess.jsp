<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
 <%--  WriteArticleHandler에서 아래와 같이
       //입력된 글번호->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용	
		int newArticleNo = writeService.write(writeRequest);
		request.setAttribute("newArticleNo", newArticleNo); --%>
 <h2>newArticleSuccess.jsp(p643)</h2>
 http://localhost/article/write.do
 <strong>입력성공</strong>
 <a href="list.do">목록보기</a>
 <a href="read.do?no=${newArticleNo}">방금 입력된 글 상세보기</a>
</body>
</html>









