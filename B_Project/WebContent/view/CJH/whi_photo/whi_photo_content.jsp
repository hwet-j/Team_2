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
<title>글 상세보기</title>
 <script>
$(document).ready(function(){
	$("#likebtn").click(function(){
		location.href='/CJH/whi_photo/like.do?articleNo=${CONTENT.article_no}';
	});
	
	$("#dislikebtn").click(function(){
		location.replace('/CJH/whi_photo/dislike.do?articleNo=${CONTENT.article_no}');
	});
});
 </script>
</head>
<body>

<%@ include file = "/header.html" %>
<!-- 여기아래로 -->
  <div class="container mt-3">
  		<!-- 배너 -->
  		<div class="d-flex justify-content-center mb-3">
        	<img src="/assets/images/whiBoard/whiskey_photo_banner.png" style="height: 100px; width: auto;">
    	</div>
        <!-- 게시글 제목 -->
        <h2 class="mb-4">${CONTENT.title}</h2>
        <!-- 게시글 정보 -->
        <div class="row">
            <div class="col-md-4 mb-3">
                <strong>글번호:</strong> ${CONTENT.article_no}
            </div>
            <div class="col-md-4 mb-3">
                <strong>작성자:</strong> ${CONTENT.user_id}
            </div>
            <div class="col-md-4 mb-3">
                <strong>등록일:</strong> ${CONTENT.reg_date}
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <strong>조회수:</strong> ${CONTENT.read_cnt}
            </div>
         	<div class="col-md-4 mb-3">
                <strong>좋아요:</strong>
                <button type="button" class="btn btn-success" name="likebtn" id="likebtn">${CONTENT.like_cnt}</button>
            </div>
            <div class="col-md-4 mb-3">
                <strong>싫어요:</strong>
                <button type="button" class="btn btn-danger" name="dislikebtn" id="dislikebtn">${CONTENT.dislike_cnt}</button>
            </div>
        </div>
        <hr>
        <!-- 게시글 내용 -->
        <div class="mb-4">${CONTENT.content}</div>
        <!-- 게시글 이미지 -->
<div>
    <img src="/CJH/whi_photo/download.do?articleNo=${CONTENT.article_no}&imageFileName=${CONTENT.img_src}" 
         class="img-fluid" alt="게시글 이미지"
         onError="this.onerror=null; this.src='/assets/images/whiBoard/image_not_load.jpeg';">
</div>
        
           <c:if test="${AUTH_USER.user_id == CONTENT.user_id or AUTH_USER.user_id eq 'admin'}">
        <div class="d-flex justify-content-end">
                <a href="/view/CJH/whi_photo/whi_photo_update.jsp?articleNo=${CONTENT.article_no}&title=${CONTENT.title}&content=${CONTENT.content}&imageFileName=${CONTENT.img_src}" class="btn btn-primary m-3">수정</a>
                <a href="/CJH/whi_photo/delete.do?articleNo=${CONTENT.article_no}" class="btn btn-primary m-3">삭제</a>
        </div>
		    </c:if>
    </div>



<!-- 여기위로 -->
<%@ include file="/footer.jsp" %>
</body>
</html>