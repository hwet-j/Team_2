<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@ include file = "../../../header.html" %>
<%
int articleNo = Integer.parseInt(request.getParameter("articleNo"));
String commentNo = request.getParameter("commentNo");
String name = request.getParameter("name");
%>
<div class="container">
  <div class="row justify-content-center">
    <div class="col" style="margin: 20px;">
	<h2 class="text-center">댓글 수정</h2><br>
      <form action="/Angel/commentMod.do" method="post">
      	<input type="hidden" name="articleNo" value="<%= articleNo%>">
        <input type="hidden" name="commentNo" value="<%= commentNo%>">
        <p><input type="text" name="name" value="<%= name %>" readonly class="form-control"></p>
        <p><textarea rows="4" cols="50" name="comment" placeholder="댓글 수정" class="form-control"></textarea></p>
        <div class="d-flex justify-content-center">
        	<input type="submit" value="수정" class="btn btn-primary">
      	</div>
      </form>
    </div>
  </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>