<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="/navi.jsp" %>
<!-- 여기아래로 -->

<h1>상세글입니다ㅏㅏㅏㅏ</h1>
글번호 : ${SELCTED_ARTICLE.articleNo } 
작성자아이디 : ${SELCTED_ARTICLE.userId } <br>
술종류 : ${SELCTED_ARTICLE.category }<br>
제목 : ${SELCTED_ARTICLE.title }<br>
작성일시 : ${SELCTED_ARTICLE.regDate } / 조회수 : ${SELCTED_ARTICLE.readCnt }
<hr>
${SELCTED_ARTICLE.content }
<hr>

<a href="/CJH/whi_board/delete.do?articleNo=${SELCTED_ARTICLE.articleNo }"><button type="button">글 삭제</button></a>
<a href="/view/CJH/whi_board/whi_board_content_modifyform.jsp?articleNo=${SELCTED_ARTICLE.articleNo }&title=${SELCTED_ARTICLE.title }&content=${SELCTED_ARTICLE.content }"><button type="button">글 수정</button></a>


<br>
<br>
<br>
<br>

<!-- 여기위로 -->
<%@ include file="/footer.jsp" %>
</body>
</html>