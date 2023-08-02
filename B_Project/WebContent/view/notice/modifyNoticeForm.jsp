<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

</script>
</head>
<body>
<%@ include file = "/header.html" %>

<div class="container"></div>
	<h2 class="mt-5 mb-4 text-center">수정</h2>
	
	<form id="modifyForm" action="modify.do" method="post">
	<div class="form-group">
	<label for="no">작성자</label>
	<input type="text" name= "writer_id" id="writer_id" class="form-control" value="${modReq.writer_id}" readonly="readonly" />
	</div>
	<div class="form-group">
	<label for="no">글번호</label>
	<input type="text" name= "no" id="no" class= "form-control" value="${modReq.number}" readonly="readonly"/>
	</div>
	<div class="form-group">
	<label for="title">제목</label>
	<input type="text" name="title" id="title" class="form-control" value="${modReq.title}"/>
	</div>
	<div class="form-group">
	<label for="">내용</label>
	<textarea rows="5" name="content" id="content" class="form-control">${modReq.content}</textarea>
	</div>
	
	<div class="d-flex justify-content-end">
 <a href="list.do" class="btn">목록보기</a>
  <c:if test="${!empty AUTH_USER and  AUTH_USER.user_id.contains('admin')}">
 <button type="submit"  class="btn btn-primary">수정하기</a>
 <a href="delete.do?no=글번호" class="btn btn-primary">삭제하기</a>
 </c:if>

  <c:if test="${empty AUTH_USER}">
  <a href="/login.do">로그인</a>
  </c:if>
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
<script="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
        crossorigin="anonymous"></script>
        <%@ include file = "/footer.html" %>
</body>
</html>