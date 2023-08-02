<%@page import="hwet.article.model.HwetArticleDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap CSS 라이브러리 링크 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
  <script>
  function resetForm() {
    // 글 작성 폼 초기화
    document.getElementById("writeForm").reset();
  }
</script>
  
  
</head>
<body>
<%@ include file = "/header.html" %>

<div class="container my-6">
	<h2>글 작성</h2>
	
	<form action="/hwet/article/write.do" id="writeForm" method="POST">
	<div class="form-group">
		<label for="writer">작성자</label>
		<input type="text" class="form-control" id="writer" name="writer" value="${sessionScope.AUTH_USER.user_id}" readonly>
	</div>
	
	<div class="form-group">
		<label for="category">카테고리 (하나만 선택 가능)</label><br>
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="java" name="category" value="JAVA" required>
			<label class="form-check-label" for="java">JAVA</label>
		</div>
		
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="db" name="category" value="DB" required>
       		<label class="form-check-label" for="db">DB</label>
     		</div>
     		
     		<div class="form-check form-check-inline">
	        <input type="radio" class="form-check-input" id="htmlcss" name="category" value="HTML/CSS" required>
	        <label class="form-check-label" for="htmlcss">HTML/CSS</label>
     		</div>
     		
     		<div class="form-check form-check-inline">
	        <input type="radio" class="form-check-input" id="etc" name="category" value="기타" required>
	        <label class="form-check-label" for="etc">기타</label>
     		</div>
	</div>
    
	<div class="form-group">
		<label for="title">제목</label>
		<input type="text" class="form-control" id="title" name="title"  minlength="5" placeholder="제목" required>
    </div>
    
    <div class="form-group">
		<label for="link">링크</label>
		<input type="url" class="form-control" id="link" name="link" required>
    </div>
    
    <div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" id="content" name="content" rows="5" required></textarea>
    </div>
    
    <div class="row mb-4">
	    <div class="col-6">
			<a href="/hwet/article/list.do" class="btn btn-secondary">글목록</a>
			<button type="button" class="btn btn-info" onclick="resetForm()">다시 작성하기</button>
	    </div>
	    
	    <div class="col-6 text-right">
			<button type="submit" class="btn btn-primary">글 작성</button>
	    </div>
	</div>
	</form>
  
  
</div>

<!-- Bootstrap JS 라이브러리 링크 (jQuery와 Popper.js를 포함) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
