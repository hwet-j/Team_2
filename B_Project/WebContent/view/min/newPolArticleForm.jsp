<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
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

</head>
<body>
<%@ include file = "../../../navi.jsp" %>
	     
 <div class="container">
     <!-- page title -->
	 <h2  class="mt-5 mb-4 text-center">등록(newPolArticleForm)</h2>
	
	 <form action="polwrite.do" method="post">
	  <div  class="mb-4">	
	  	<label for="user_nickname" class="form-label">작성자(여기에서는 세션의 회원id)</label>
	  	<input type="text" name="user_nickname" id="user_nickname" class="form-control" value="${AUTH_USER.user_nickname}(${AUTH_USER.user_id})"/><br/>
		<%--  <c:if test="${errors.}">작성자를 입력하세요</c:if>  --%>
	  </div>
	  <div  class="mb-4">	
	  	<label for="title"  class="form-label">제목</label>
	  	<input type="text" name="title" id="title"  class="form-control" /><br/>
	  	 <c:if test="${errors.title}"><span class="error">제목을 입력하세요</span></c:if>
	  </div>
	  <div  class="mb-4">	
	  	<label for="content"  class="form-label">내용</label>
	  	<textarea name="content" id="content" class="form-control"  rows="5" cols="30"></textarea><br/>
	  	<c:if test="${errors.content}"><span class="error">내용을 입력하세요</span></c:if>
	  </div>
	  <div>
	 	<button  type="submit" class="btn">글쓰기(java용)</button>
	 	<button  type="reset"  class="btn">취소</button>
	  </div> 
	 </form>
 </div>



</body>
<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>



















