<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 $(function(){

 });
 </script>
</head>
<body>
<%-- ModifyArticleHandler에의해서 아래와 같이 Model
//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 제목과 내용
ModifyRequest modReq=new ModifyRequest(user.getId(), no, oad.getTitle(), oad.getContent());
request.setAttribute("modReq", modReq); --%>	

 <!-- Navigation -->
 <%@ include file="/navi.jsp" %> 	

  <!-- 내용 -->
 <div class="container">
    <!-- page title -->
	<h2  class="mt-5 mb-4 text-center">수정(polmodifyForm/p674)</h2>
	
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
     <a href="polList.do"   class="btn btn-primary">목록보기</a> 
     <button type="submit" class="btn btn-primary">수정하기</button> 
     <a href="poldelete.do?no=${modReq.polarticleNumber}" class="btn btn-primary">삭제하기</a> 
	</div>
   </form>
 </div>
 	
<%@ include file="/navi.jsp" %> 	
 	       	
</body>
</html>













