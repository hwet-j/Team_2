<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세조회</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@ include file = "../../../navi.jsp" %>
<%-- ${DETAIL_CONTENT} --%>
<h1>상세글 조회</h1>
글번호 : ${DETAIL_CONTENT.articleNo}
아이디 : ${DETAIL_CONTENT.memberid } 
이름 : ${DETAIL_CONTENT.name } 
제목 : ${DETAIL_CONTENT.title }
조회수 : ${DETAIL_CONTENT.readCnt } <br>
<hr>
내용 : ${DETAIL_CONTENT.content }
<hr>
<form action="/view/ANGEL/article/ArticleUpdate.jsp" method="post">
<input type="hidden" name="articleNo" value=" ${DETAIL_CONTENT.articleNo}">
<input type="hidden" name="title" value=" ${DETAIL_CONTENT.title}">
<input type="hidden" name="content" value=" ${DETAIL_CONTENT.content}">
</form>
<a href="/Angel/delete.do?articleNo=${DETAIL_CONTENT.articleNo}"><Button type="button" class="btn-primary">삭제</Button></a>
<a href="/view/ANGEL/article/ArticleUpdate.jsp?articleNo=${DETAIL_CONTENT.articleNo}&title=${DETAIL_CONTENT.title}&content=${DETAIL_CONTENT.content}">
<Button type="button" class="btn-primary">수정</Button></a>
<a href="/Angel/article.do?pageNo=1"><Button type="button" class="btn-primary">글 목록</Button></a>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>