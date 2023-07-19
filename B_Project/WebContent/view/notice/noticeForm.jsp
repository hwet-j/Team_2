<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
<title></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myCss.css">
<script>
$(function(){
	
});
</script>
</head>
<body>
<form action="write.do" method="post">
<div class="container">

		<h2  class="mt-5 mb-4 text-center">등록</h2>
			<div class="mb-4">
			<label for="memberNick" class="form-label">회원id</label>
			<input type="text" name="memberNick" id="memberNick" class="form-control" value= "${AUTH_USER_id}" /><br/>
			<c:if test="${errors.memberNick}"><span class="error">회원id를 입력하세요</span></c:if>
	
	</div>
	<div class="mb-4">
			<label for="title" class="form-label">제목</label>
			<input type="text" name="title" id="title" class="form-control" ><br/>
			<c:if test="${errors.title}"><span class="error">제목을 입력하세요</span></c:if>
		</div>
	<div class="mb-4">
			<label for="content" class="form-label">내용</label>
			<textarea name="content" id="content" class="form-control" rows="5" cols="30"></textarea><br/>
			<c:if test="${errors.content}"><span class="error">내용을 입력하세요</span></c:if>
		</div>
		<div>
			<button  type="submit" class="btn btn-success">글쓰기</button> 
		 	<button  type="reset"  class="btn btn-secondary">취소</button>
		</div>
	</div>
		</form>
	
	<!-- Bootstrap 4 JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXakfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz4fnFO9gybBvRLFyyN+kW/BQro3T8j6XI4lK7T7rM46_tC6Y1Bf/Dbdbp"
        crossorigin="anonymous"></script>
<script="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
        crossorigin="anonymous"></script>
	
	
	
</body>
</html>