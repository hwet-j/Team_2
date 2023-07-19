<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<strong>공지사항이 등록되었습니다</strong>
	<a href="list.do">공지사항목록보기</a>
	<a href="read.do?no=${newNoticeNo}">공지사항 상세보기</a>
	
</body>
</html>