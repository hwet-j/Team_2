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
<title>글 수정하기</title>
 <script>
 function readURL(obj){
	  if (obj.files && obj.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(obj.files[0]);
	  } 
 }
 </script>
 
</head>
<body>
<%@ include file = "/header.html" %>
<!-- 여기아래로 -->
<%
int article_no = Integer.parseInt(request.getParameter("articleNo"));
String title = request.getParameter("title");
String content = request.getParameter("content");
String imageFileName = request.getParameter("imageFileName");
%>
글번호 : <%=article_no%>
    <div class="container">
        <h2>작성 글 수정</h2>
        <form method="post" action="/CJH/whi_photo/update.do" enctype="multipart/form-data">
            <input type="hidden" name="articleNo" value="<%=article_no%>" >
            <div class="form-group">
                <label for="title">글제목</label>
                <input type="text" class="form-control" id="title" name="title" required value="<%=title %>">
            </div>
            <div class="form-group">
                <label for="content">글내용</label>
                <textarea class="form-control" id="content" name="content" rows="4" required value="<%=content%>"></textarea>
            </div>
            <div class="form-group">
                <label for="image_src">이미지</label>
                <input type="file" class="form-control-file" id="image_src" name="image_src" onchange="readURL(this)" value="">
                <img src="" id="preview" style="width:150px;"
                onError="this.onerror=null; this.src='/assets/images/whiBoard/image_not_load.jpeg';"/> 
            </div>
            <button type="submit" class="btn btn-primary">작성 완료</button>
        </form>
    </div>
<script>
    window.onload = function() {
        var imgSrc = "/CJH/whi_photo/download.do?articleNo=<%=article_no%>&imageFileName=<%=imageFileName %>";
        var imgPreview = document.getElementById('preview');
        var imageInput = document.getElementById('image_src');

        // 이미지 파일을 미리 읽고 미리보기로 표시합니다.
        imgPreview.src = imgSrc;

        // input 필드에 이미지 파일 경로를 설정합니다.
        imageInput.value = imgSrc;
    };
</script>


<!-- 여기위로 -->
<%@ include file="/footer.jsp" %>
</body>
</html>