<%@page import="hwet.article.model.HwetArticleDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리스트 목록</title>

<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body>
<%@ include file="/navi.jsp" %>

<%-- 현재 페이지 번호를 가져옵니다. --%>
<c:set var="currentPage" value="${empty param.pageNo ? 1 : param.pageNo}" />

<div class="container">
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
				<%-- value="${age >= 18 ? true : false}" --%>
    <c:forEach begin="${currentPage < 5 ? 1 : currentPage-4}" end="${totalPages < currentPage + 4 ? totalPages : currentPage + 4}" var="page">
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




</body>

<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>

