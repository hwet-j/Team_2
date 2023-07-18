<%@page import="hwet.article.model.HwetArticleDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 현재 페이지 번호를 가져옵니다. --%>
<c:set var="currentPage" value="${empty param.pageNo ? 1 : param.pageNo}" />

<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap CSS 라이브러리 링크 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%@ include file="/header.jsp" %>
<%@ include file="/navi.jsp" %>


<div class="container my-6">
  <h2>게시물 목록 </h2>
  <table class="table table-striped table-bordered">
    <thead>
      <tr>
      	<!-- 텍스트가 줄바꿈 되지 않도록 설정 (white-space: nowrap;) -->
        <th class="col-1 text-center" style="white-space: nowrap;">번호</th>
        <th class="col-1 text-center" style="white-space: nowrap;">작성자</th>
        <th class="col-1 text-center" style="white-space: nowrap;" data-toggle="tooltip" title="카테고리 설명">카테고리</th>
        <th class="col-4 text-center" style="white-space: nowrap;">제목</th>
        <th class="col-1 text-center" style="white-space: nowrap;">작성일</th>
        <th class="col-1 text-center" style="white-space: nowrap;">조회수</th>
      </tr>
    </thead>
    <tbody>
    <%-- forEach문의 status를 활용하여 board_id가 아닌 페이지 자체에서 게시판 번호를 매김 --%>
      <c:forEach var="board" items="${boardList}" varStatus="status">
        <tr>
          <td class="text-center" style="white-space: nowrap;">${status.index + 1 + ((currentPage-1) * listSize)}</td>
          <td class="text-center" style="white-space: nowrap;">${board.writer}</td>
          <td class="text-center" style="white-space: nowrap;">${board.category}</td>
          <td class="text-center" style="white-space: nowrap;"><a href="read.do?no=${board.boardId}&pageNo=${currentPage}">${board.title}</a></td>
          <td class="text-center" style="white-space: nowrap;">${board.regDate}</td>
          <td class="text-center" style="white-space: nowrap;">${board.hit}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  
  <!-- 페이징 처리 부분 -->
  <ul class="pagination justify-content-center">
    

    <%-- 이전 페이지로 이동하는 링크 --%>
    <c:if test="${currentPage > 1}">
      <li class="page-item">
        <a class="page-link" href="${pageContext.request.contextPath}/hwet/article/list.do?pageNo=${currentPage - 1}">이전</a>
      </li>
    </c:if>

    <%-- 페이지 번호를 출력하는 부분 --%>
    <c:forEach begin="1" end="${totalPages}" var="page">
      <li class="page-item <c:if test='${page == currentPage}'>active</c:if>">
        <a class="page-link" href="${pageContext.request.contextPath}/hwet/article/list.do?pageNo=${page}">${page}</a>
      </li>
    </c:forEach>

    <%-- 다음 페이지로 이동하는 링크 --%>
    <c:if test="${currentPage < totalPages}">
      <li class="page-item">
        <a class="page-link" href="${pageContext.request.contextPath}/hwet/article/list.do?pageNo=${currentPage + 1}">다음</a>
      </li>
    </c:if>
  </ul>
  <!-- 글쓰기 버튼 -->
  <div class="text-right">
    <!-- 글목록 버튼 -->
    <a href="${pageContext.request.contextPath}/hwet/article/write.do" class="btn btn-primary">글쓰기</a>
  </div>
</div>




<%@ include file="/footer.jsp" %>

<!-- Bootstrap JS 라이브러리 링크 (jQuery와 Popper.js를 포함) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

