<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>

   <!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 $(function(){

 });
 </script>
</head>
<body>

 <!-- Navigation -->
<%@ include file = "../../../navi.jsp" %>

  <!-- 내용 -->
 <div class="container">
    <!-- page title -->
	<h2  class="mt-5 mb-4 text-center">게시글 수정</h2>
	
	<form id="polmodifyForm" action="polmodify.do" method="post">
	 <div class="form-group">
	   <label for="no">작성자</label>                                              ${modReq.userId}
	   <input type="text" name="writerId" id="writerId" class="form-control" value="${modReq.userId}" readonly="readonly"/>
	  </div>
	 <div class="form-group">
	   <label for="no">글번호</label>
	   <input type="text" name="no" id="no" class="form-control" value="${modReq.polarticleNumber}" readonly="readonly"/>
	  </div>
	 <div class="form-group">
	 	<label for="title">제목</label>
	 	<input type="text" name="title" id="title" class="form-control" value="${modReq.title}"/>
	 </div>
	 <div class="form-group">
	 	<label for="content">내용</label>
	 	<textarea rows="5" name="content" id="content" class="form-control">${modReq.content}</textarea>
	 </div>

	<!-- button -->
	<div class="d-flex justify-content-end">
     <a href="polList.do"   class="btn">목록보기</a> 
     <button type="submit" class="btn btn-primary">수정하기</button> 
     <a href="poldelete.do?no=${modReq.polarticleNumber}" class="btn">삭제하기</a> 
	</div>
   </form>
 </div>
 		     	
</body>
<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>













