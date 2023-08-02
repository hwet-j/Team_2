<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<meta charset="UTF-8">
<!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>보틀 자랑하기 글 목록</title>
 <script>

 </script>
</head>
<body>

<%@ include file = "/header.html" %>
<!-- 여기아래로 -->

    <div class="container">
        <!-- 배너 -->
        <div class="d-flex justify-content-center mb-3">
        	<img src="/assets/images/whiBoard/whiskey_photo_banner.png" style="height: 100px; width: auto;">
    	</div>
    	<hr>
        <!-- 테이블 출력 -->
        <table class="table table-hover mt-3 text-center">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th>글작성자</th>
                    <th>글제목</th>
                    <th>조회수</th>
                    <th>등록일</th>
                    <th>좋아요</th>
                    <th>싫어요</th>
                </tr>
            </thead>
            <tbody>
            	<tr class="table-primary">
            		    <td>${POP_ARTICLE.article_no}</td>
                        <td>${POP_ARTICLE.user_id}</td>
                        <td>
	                        <a href="/CJH/whi_photo/content.do?articleNo=${POP_ARTICLE.article_no}">
	                        ${POP_ARTICLE.title}</a>
                        </td>
                        <td>${POP_ARTICLE.read_cnt}</td>
                        <td>${POP_ARTICLE.reg_date}</td>
                        <td>${POP_ARTICLE.like_cnt}</td>
                        <td>${POP_ARTICLE.dislike_cnt}</td>
            	</tr>
                <c:forEach items="${ARTICLE}" var="article">
                    <tr>
                        <td>${article.article_no}</td>
                        <td>${article.user_id}</td>
                        <td>
	                        <a href="/CJH/whi_photo/content.do?articleNo=${article.article_no}">
	                        ${article.title}</a>
                        </td>
                        <td>${article.read_cnt}</td>
                        <td>${article.reg_date}</td>
                        <td>${article.like_cnt}</td>
                        <td>${article.dislike_cnt}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- 페이징 출력 -->
         <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:forEach var="pageNo" begin="1" end="${PAGE_CNT}">
                    <li class="page-item">
                        <a class="page-link" href="/CJH/whi_photo/list.do?pageNo=${pageNo}">${pageNo}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
        <!-- 기능버튼 -->
        <c:if test="${not empty AUTH_USER}">
        	<div class="d-flex justify-content-end">
	       		<a href="/view/CJH/whi_photo/whi_photo_write.jsp">
	            	<button type="button" class="btn btn-primary">글작성하기</button>
	        	</a>
   			</div>
		</c:if>
    </div>
<br>
<br><br><br><br><br>

<!-- 여기위로 -->
<%@ include file="/view/contact.jsp" %>
</body>
</html>