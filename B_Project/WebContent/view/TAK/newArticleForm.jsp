<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>
 <!-- Bootstrap 4 CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/myCss.css">
 <script>
 $(function(){

 });
 </script>
</head>
<body>
<%@ include file = "/header.html" %>	
 <%-- WriteArticleHandler에의해 아래와 같이 모델받았다
 		User user = new User("hongid","1234");
		session.setAttribute("AUTH_USER", user); --%>	     
 <div class="container">
     <!-- page title -->
	 <h2  class="mt-5 mb-4 text-center">등록</h2>
	 <form action="write1.do" method="post">
	  <div  class="mb-4">	
	  	<label for="memberNick" class="form-label">작성자</label>
	  	<input type="text" name="memberNick" id="memberNick" class="form-control" value="${AUTH_USER.user_id}"/><br/>
		<%--  <c:if test="${errors.}">작성자를 입력하세요</c:if>  --%>
	  </div>
	  <div  class="mb-4">	
	  	<label for="title"  class="form-label">제목</label>
	  	<input type="text" name="title" id="title"  class="form-control" /><br/>
	  	 <c:if test="${errors.title}"><span class="error">제목을 입력하세요</span></c:if>
	  </div>
	  <div  class="mb-4">	
	  	<label for="content"  class="form-label">내용</label>
	  	<textarea name="content" id="content" class="form-control"  rows="5" cols="30" placeholder="신고할 글주소를 복사해서 < >사이에 넣어주세요 
	  	 "></textarea><br/>
	  	<c:if test="${errors.content}"><span class="error">내용을 입력하세요</span></c:if>
	  </div>
	  <div>
	 	<button  type="submit" class="btn btn-primary">글쓰기</button>
	 	<button  type="reset"  class="btn">취소</button>
	  </div> 
	 </form>
 </div>


<!-- Bootstrap 4 JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXakfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz4fnFO9gybBvRLFyyN+kW/BQro3T8j6XI4lK7T7rM46_tC6Y1Bf/Dbdbp"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
        crossorigin="anonymous"></script>
  <%@ include file = "/footer.html" %>	       
</body>
</html>

























