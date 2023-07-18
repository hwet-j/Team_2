<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="hwet.article.model.HwetArticleDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS 라이브러리 링크 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="my-4">게시물 정보 조회</h2>
        <table class="table table-bordered table-hover">
            <tr>
                <th style="width: 20%;">글 번호</th>
                <td>${data.boardId}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${data.writer}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${data.title}</td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td>${data.category}</td>
            </tr>
            <tr>
                <th>링크</th>
                <td><a href="${data.link}">${data.link}</a></td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${data.content}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${data.regDate}</td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${data.hit}</td>
            </tr>
            <tr>
                <th>수정일</th>
                <td>${data.updateDate}</td>
            </tr>
        </table>
    </div>
    
    <div class="container mt-4">
    	<c:set var="pageNo" value="${empty param.pageNo?1:param.pageNo}" />
        <a href="list.do?pageNo=${pageNo}" class="btn btn-secondary">목록보기</a>
        <c:if test="${AUTH_USER.userId == data.writer}">
            <a href="modify.do?no=${data.boardId}" class="btn btn-primary">게시글수정</a>
        </c:if>
        <c:if test="${AUTH_USER.userId eq data.writer}">
            <a href="delete.do?no=${data.boardId}" class="btn btn-danger">게시글삭제</a>
        </c:if>
    </div>

    <!-- Bootstrap JS 라이브러리 링크 (jQuery와 Popper.js를 포함) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
