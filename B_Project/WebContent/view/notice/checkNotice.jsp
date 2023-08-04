 <%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u"   tagdir="/WEB-INF/tags"%>

<%-- <c:set var="변수명" value="변수값" /> --%>
<c:set var="cPath" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title></title>
<style>
textarea#content {
resize:none;
width: 300px;
height: 150px;
}
</style>
<!-- Bootstrap 4 CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>


</script>
</head>
<body>
<%@ include file = "/header.html" %>
<%--	//리턴 Notice notice: 글번호,작성자id,제목,내용,작성일,조회수,
			Notice notice =NoticeService.getDetail(no); 
			//request.setAttribute("notice",notice); 
			request.setAttribute("nod", nod);--%>
<%@ include file = "/header.html" %>
	

<div class="container">
		<!-- page title -->
		<h2 class="mt-5 mb-4 text-center">공지내용</h2>
		
		<form id="noticeForm" action="${cPath}/notice/modify.do" method="post"   enctype="application/x-www-form-urlcencoded" >
		<input type="hidden" name="noticeno"   id="noticeno" value="${nod.number}"/>
		<input type="hidden" name="writer_id"  id="writer_id" value="${nod.writer_id}"/>
		<table class="table table-bordered mt-3">
		
			<tbody>
			<tr>
			 <th scope= "row" style="width:15%;">글번호</th>
			 <td>${nod.number}</td>
			 </tr>
			<tr>
			 <th scope= "row">작성자id</th>
			 <td>${nod.writer_id}</td>
			 </tr>
			<tr>
			 <th scope= "row"><label for="title">제목</label></th>
			 <td><input type="text" name="title" id="title" value="${nod.title}" ></td>
			 </tr>
			<tr>
			 <th scope= "row"><label for="content">내용</label></th>
			 <td><textarea name="content" id="content">${nod.content}</textarea></td>
			 </tr>
			<tr>
			 <th scope= "row">작성일</th>
			 <td><fmt:formatDate value="${nod.writeDate}" pattern="yyyy년  M월  d일" /></td>
			 </tr>
			<tr>
			 <th scope= "row">조회수</th>
			 <td>${nod.views}</td>
			 </tr>
			</tbody>
		</table>
		
		
		<!-- button --> 
		<!-- d-flex:한개의 row를 block레벨로 차지 
		   flex-start:왼쪽정렬(기본)/ flex-end:오른쪽정렬 / flex-center:가운데정렬
		   justify-content-end : 오른쪽정렬-->
		<div class="d-flex justify-content-end">
		<c:set var="pageno" value="${empty param.pageNo?1:param.pageNo}"  />
	<a href="<%=request.getContextPath()%>/notice/list.do?pageNo=${pageno}" class="btn btn-outline-dark">목록보기</a>
	<c:if test="${AUTH_USER.user_id==nod.writer_id}">
	   <a href="modify.do?no=${nod.number}" class="btn btn-outline-dark">공지수정</a>
     </c:if>
      <c:if test="${AUTH_USER.user_id eq nod.writer_id}">
	   <a href="delete.do?no=${nod.number}" class="btn btn-outline-dark">공지삭제</a>
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