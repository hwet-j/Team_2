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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>
<%@ include file="/navi.jsp" %>

<%-- 현재 페이지 번호를 가져옵니다. --%>
<c:set var="current_page" value="${empty param.page_no ? 1 : param.page_no}" />
<%-- 현재 페이지 카테고리를 가져옵니다. --%>
<c:set var="category_info" value="${empty param.category_info ? '전체' : param.category_info}" />

<div class="container">
  <h2>게시물 목록 </h2>
  <%-- 카테고리 분류를 위한 버튼들 생성 --%>
  <div class="btn-group mb-2 mt-4">
  	<%-- 카테고리의 종류를 jsp파일에서 직접 설정하여 사용, 카테고리가 수정되면 여기서도 수정해야함 (에러표시가 있지만 정삭작동함) --%>
  	<c:forEach var="category" items="${['전체', 'DB', 'HTML/CSS', 'JAVA', '기타']}">
      <a href="${pageContext.request.contextPath}/hwet/article/list.do?category_info=${category}&page_no=${current_page}&search_type=${search_type}&keyword=${keyword}" class="btn btn-outline-primary <c:if test='${category_info eq category}'>active</c:if>">${category}</a>
    </c:forEach>
  </div>
  
  <table class="table table-striped table-bordered">
    <thead>
      <tr>
      	<%-- 텍스트가 줄바꿈 되지 않도록 설정 (white-space: nowrap;) 
      		class명에 col-1 과같이 설정해줘서 각 컬럼의 크기 비율을 설정가능하다고 하여 설정했지만 정상적으로 적용이 안됨 
      		col의 범위는 12로 알고있음 (col뒤에 붙는 숫자를 전부 합쳐 12가 되도록 설정)
      	--%>
        <th class="col-1 text-center" style="white-space: nowrap;">번호</th>
        <th class="col-1 text-center" style="white-space: nowrap;">작성자</th>
        <th class="col-1 text-center" style="white-space: nowrap;" data-toggle="tooltip" title="카테고리 설명">카테고리</th>
        <th class="col-7 text-center" style="white-space: nowrap;">제목</th>
        <th class="col-1 text-center" style="white-space: nowrap;">작성일</th>
        <th class="col-1 text-center" style="white-space: nowrap;">조회수</th>
      </tr>
    </thead>
    <tbody>
    
    <%-- 게시글 표시, 받아온 데이터가 없다면 없다고 표시 --%>
    <c:if test="${empty board_list}">
	  <tr>
	    <td colspan="6" class="text-center">게시글이 없습니다.</td>
	  </tr>
	</c:if>
    <%-- forEach문의 status를 활용하여 board_id가 아닌 페이지 자체에서 게시판 번호를 매김 --%>
      <c:forEach var="board" items="${board_list}" varStatus="status">
        <tr>
          <td class="text-center" style="white-space: nowrap;">${status.index + 1 + ((current_page-1) * list_size)}</td>
          <td class="text-center" style="white-space: nowrap;">${board.writer}</td>
          <td class="text-center" style="white-space: nowrap;">${board.category}</td>
          <td class="text-center" style="white-space: nowrap;"><a href="read.do?no=${board.boardId}&page_no=${current_page}">${board.title}</a></td>
          <td class="text-center" style="white-space: nowrap;">${board.regDate}</td>
          <td class="text-center" style="white-space: nowrap;">${board.hit}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  
	<div class="row mt-4 mb-5" style="font-size: 12px;"> 
    	<div class="col-md-6">
		  <%-- 검색 기능 입력 폼 --%>
		  <div class="input-group">
		    <select name="search_type" class="form-select mr-1">
		      <option value="title">제목</option>
		      <option value="content">제목+내용</option>
		      <option value="writer">작성자</option>
		    </select>
		    <input type="text" name="keyword" id="keyword" class="form-control" placeholder="검색어 입력" required />
		    <div class="input-group-append">
		      <a href="javascript:void(0)" class="btn btn-primary" id="searchButton">검색</a>
		    </div>
		  </div>
		</div>
    
		<%-- 글쓰기 버튼 (우측 정렬) --%>
		<div class="col-md-6 text-right">
			<a href="${pageContext.request.contextPath}/hwet/article/write.do" class="btn btn-primary">글쓰기</a>
	    </div>
	</div>
	
	<%-- 페이징 처리 부분 --%>
	<ul class="pagination justify-content-center my-6">
	    <%-- 이전 페이지로 이동하는 링크 --%>
	    <c:if test="${current_page > 1}">
	        <li class="page-item">
	            <a class="page-link" href="${pageContext.request.contextPath}/hwet/article/list.do?category_info=${category_info}&page_no=${current_page - 1}&search_type=${search_type}&keyword=${keyword}">이전</a>
	        </li>
	    </c:if>
		
		<%-- 페이지 이동에 따라 브라우저에 표현되는 페이지 번호의 범위를 설정함 --%>
	    <c:forEach begin="${current_page < 5 ? 1 : current_page-4}" end="${total_pages < current_page + 4 ? total_pages : current_page + 4}" var="page">
	    	<%-- 표시되는 페이지와 현재페이지가 동일하면 class명에 active를 추가하여 표현 --%>
	        <li class="page-item <c:if test='${page == current_page}'>active</c:if>">
	            <a class="page-link" href="${pageContext.request.contextPath}/hwet/article/list.do?category_info=${category_info}&page_no=${page}&search_type=${search_type}&keyword=${keyword}">${page}</a>
	        </li>
	    </c:forEach>
	
	    <%-- 다음 페이지로 이동하는 링크 --%>
	    <c:if test="${current_page < total_pages}">
	        <li class="page-item">
	            <a class="page-link" href="${pageContext.request.contextPath}/hwet/article/list.do?category_info=${category_info}&page_no=${current_page + 1}&search_type=${search_type}&keyword=${keyword}">다음</a>
	        </li>
	    </c:if>
	</ul>
  
</div>


</body>

<script>
<%-- 검색 버튼 클릭시 get방식으로 데이터를 전송 --%>
$(document).ready(function() {
  $("#searchButton").click(function() {
    var keywordInput = $("#keyword");
    var keyword = keywordInput.val();
  
    var search_type = $('[name="search_type"]').val();
    var url = "${pageContext.request.contextPath}/hwet/article/list.do?category_info=${category_info}&page_no=${current_page}" + "&search_type=" + search_type + "&keyword=" + keyword;
    window.location.href = url;
  });
});
</script>

<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>

