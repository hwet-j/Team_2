<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS 라이브러리 링크 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/navi.jsp" %>

    <div class="container">
        <h2 class="my-4">게시물 수정 </h2>
        <form action="/hwet/article/modify.do" method="post">
            <input type="hidden" name="board_id" value="${data.board_id}">
            <input type="hidden" name="page_no" value="${page_no}">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title" value="${data.title}" required>
            </div>
            <div class="form-group">
                <label for="category">카테고리</label>
                <select class="form-control" id="category" name="category">
                    <option value="JAVA" ${data.category == 'JAVA' ? 'selected' : ''}>JAVA</option>
                    <option value="DB" ${data.category == 'DB' ? 'selected' : ''}>DB</option>
                    <option value="HTML/CSS" ${data.category == 'HTML/CSS' ? 'selected' : ''}>HTML/CSS</option>
                    <option value="기타" ${data.category == '기타' ? 'selected' : ''}>기타</option>
                </select>
            </div>
            <div class="form-group">
                <label for="link">링크</label>
                <input type="url" class="form-control" id="link" name="link" value="${data.link}">
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea class="form-control" id="content" name="content" rows="5">${data.content}</textarea>
            </div>
            <div class="d-flex justify-content-between">
                <a href="javascript:history.back()" class="btn btn-secondary">이전페이지</a>
                <button type="submit" class="btn btn-primary">수정 완료</button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS 라이브러리 링크 (jQuery와 Popper.js를 포함) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
