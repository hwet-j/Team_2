<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동물 게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@ include file = "/header.html" %>
<h1 style="text-align: center;">동물 게시판</h1><br>
<div class="container">
<a href="/Angel/article.do?pageNo=1"><Button type="button" class="btn btn-secondary">전체글보기</Button></a>
<!-- get 방식으로 카테고리 파라미터 전송 -->
<a href="/Angel/category.do?category=전재권&pageNo=1"><Button type="button" class="btn btn-secondary">전재권</Button></a>
<a href="/Angel/category.do?category=김재정&pageNo=1"><Button type="button" class="btn btn-secondary">김재정</Button></a>
<a href="/Angel/category.do?category=홍진호&pageNo=1"><Button type="button" class="btn btn-secondary">홍진호</Button></a>
<a href="/Angel/category.do?category=조중현&pageNo=1"><Button type="button" class="btn btn-secondary">조중현</Button></a>
<a href="/Angel/category.do?category=백경탁&pageNo=1"><Button type="button" class="btn btn-secondary">백경탁</Button></a>
<a href="/Angel/category.do?category=김현민&pageNo=1"><Button type="button" class="btn btn-secondary">김현민</Button></a>
<a href="/Angel/category.do?category=정회창&pageNo=1"><Button type="button" class="btn btn-secondary">정회창</Button></a>
<br><br>



<table border="1" style="text-align: center;" class="table table-hover table-borderless">
<thead>
	<tr class="table-primary">
		<td>글번호</td>
		<td>아이디</td>
		<td>이름</td>
		<td>제목</td>
		<td>작성일</td>
		<td>조회수</td>
	</tr>	
</thead>
<tbody>
<!-- ${ARTICLE} 페이지 번호마다 보여질 게시물 목록 -->
<c:forEach var="article" items="${ARTICLE}">
	<tr>
		<td>${article.articleNo}</td>
		<td>${article.memberid}</td>
		<td>${article.name}</td>
		<td><a href="/Angel/detail.do?articleNo=${article.articleNo}">${article.title}</a> </td>
		<td>${article.regdate}</td>
		<td>${article.readCnt}</td>
	</tr>
</c:forEach>
</tbody>
	<tr>
		<td colspan="6">
			<!-- ${PAGECOUNT} 페이징 처리할 때 페이징 개수 6개 -->
			
			<%-- <c:forEach var="num" begin="1" end="${PAGECOUNT}">
				<!-- pageNo는 ArticleHandler의 파라미터로 넘겨진다 -->
				<a href="/Angel/article.do?pageNo=${num}" class="btn btn-primary pagination-button">${num}</a>
			</c:forEach> --%>
			
			<nav aria-label="Page navigation">
  			<ul class="pagination">
		    <!-- 페이지 번호를 동적으로 생성하는 JSTL 코드 -->
		    <c:forEach var="num" begin="1" end="${PAGECOUNT}">
		      <!-- pageNo는 ArticleHandler의 파라미터로 넘겨진다 -->
		      <li class="page-item">
		        <a href="/Angel/article.do?pageNo=${num}" class="page-link pagination-button">${num}</a>
		      </li>
		    </c:forEach>
		  </ul>
		</nav>
		</td>
	</tr>
</table>
	<div>
	<c:forEach var="num" begin="1" end="${PAGE_COUNT}">
		<a href="/Angel/category.do?pageNo=${num}&category=${NAME}" class="btn btn-primary">${num}</a>
	</c:forEach>
  </ul>
</nav>
	</div>
</div>
<br>


<div style="display: flex; flex-direction: column; align-items: center;">
  <p><a href="/view/ANGEL/article/Write.jsp?articleNo=">
  	<input type="submit" class="btn btn-primary" value="글쓰기"/></a></p>
  <p><form method="get" action="/Angel/search.do">
	  	<select id="type" name="type" size="1">
	  		<option value="name">이름</option>
	  		<option value="title">제목</option>
	  		<option value="content">내용</option>
	  	</select>
	  	<input type="text" name="subject" id="subject" placeholder="검색"/>
	  	<input type="submit" value="검색">
	</form></p> 	
  
  <a href='javascript:history.go(-1)'>뒤로 가기</a>
</div>
<%@ include file = "/footer.html" %>
</body>
<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>