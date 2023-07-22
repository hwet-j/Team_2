<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<script>
</script>
<title>공지사항 상세보기</title>
</head>
<body>
<h1>공지글 불러오기</h1>
<h2>제목 : ${NoticeContent.title}</h2>
<p>${NoticeContent.read_cnt}</p>
<p>조회수 : ${NoticeContent.read_cnt}</p>
<hr>
${NoticeContent.content}
<hr>
<a href='javascript:history.go(-1)'><button>이전페이지</button></a>
<c:if test="${AUTH_USER.id eq ArticleContent.writer_id or AUTH_USER.id eq 'admin'}">
<a href="/notice/deleteNotice.do?notice_no=${NoticeContent.noticeNo}"><button>글 삭제</button></a>
<form action="/notice/modifyNoticeContent.do" method="post">

<input type="hidden" name="" value="">
<input type="hidden" name="" value="">
<input type="hidden" name="" value="">
<input type="hidden" name="" value="">
<button>글 수정</button></form>
</c:if>
</body>
</html> --%>