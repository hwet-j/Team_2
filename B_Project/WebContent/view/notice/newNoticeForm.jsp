<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title></title>
<!-- Bootstrap 4 CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 <!-- Flatly 테마 CSS -->
 <!-- <link rel="stylesheet" href="https://bootswatch.com/4/flatly/bootstrap.min.css"> -->
 <!--<link rel="stylesheet" href="https://bootswatch.com/4/Cosmo/bootstrap.min.css"> --> 
 <link rel="stylesheet" href="https://bootswatch.com/4/cerulean/bootstrap.min.css">  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

</script>
</head>
<body>
<%@ include file = "/header.html" %>
<!-- 내용 -->		    
 <div class="container">
     <!-- page title -->
<h2  class="mt-4 mb-3 text-center">등록</h2>

	<!--  ★업로드기능있는 form은 반드시 method="post" enctype="multipart/form-data"★
	 *파일업로드 라이브러리 필요(commons-fileupload-x.x.x.jar, commons-io-x.x.jar) -->
	 <form action="write.do" method="post"  enctype="application/x-www-form-urlcencoded">
	  <div  class="mb-1">	
	  	<label for="writer_id" class="form-label">작성자</label>
	  	<input type="text" name="writer_id" id="writer_id" class="form-control" value="${AUTH_USER_id}"/>
		  <c:if test="${errors.writer_id}"><br/><span class="error">작성자를 입력하세요</span></c:if>  
	  </div>
	  <div  class="mb-1">	
	  	<label for="title"  class="form-label">제목</label>
	  	<input type="text" name="title" id="title"  class="form-control" />
	  	  <c:if test="${errors.title}"><br/><span class="error">제목을 입력하세요</span></c:if> 
	  </div>
	  <div  class="mb-1">	
	  	<label for="content"  class="form-label">내용</label>
	  	<textarea name="content" id="content" class="form-control"  rows="5" cols="30"></textarea>
	  	 <c:if test="${errors.content}"><br/><span class="error">내용을 입력하세요</span></c:if> 
	  </div>	   	    
	  <div>
	  <c:if test="${!empty AUTH_USER and AUTH_USER.user_id.contains('admin')}">
	 	<button  type="submit" class="btn btn-primary">글쓰기</button>
	 	<button  type="reset"  class="btn">취소</button>		
	  </c:if>
	 
	  
	  <c:if test="${empty AUTH_USER}">
	   <a href="/login.do">로그인</a>
	  </c:if>	  
	  </div> 
	 </form>
 </div>
<%@ include file="../bootstrap4js.jsp" %> 
<%@ include file = "/footer.html" %>
</body>
</html>