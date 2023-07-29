 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@page import="notice.dao.NoticeDTO" %>
 <%@page import="java.util.ArrayList" %>
 <%@page import="notice.dao.NoticeDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
 <!-- Bootstrap 4 CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- Flatly 테마 CSS -->
 <!-- <link rel="stylesheet" href="https://bootswatch.com/4/flatly/bootstrap.min.css"> -->
 <!--<link rel="stylesheet" href="https://bootswatch.com/4/Cosmo/bootstrap.min.css"> --> 
 <link rel="stylesheet" href="https://bootswatch.com/4/cerulean/bootstrap.min.css">  
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
function dataCheck()
{
	if(document.getElementById("search").value == "")
	{
		alert("검색할 단어를 입력하세요");
		document.getElementById("search").focus();
		return;
	}
	document.form.submit();
}
</script>
</head>
<body>

 
 <%-- ListNoticeHandler로 부터 
 	 request.setAttribute("nowPage", pageNo); //현재페이지
 	 //request.setAttribute("listNotice", listNotice); //notice목록
     request.setAttribute("noticePage", noticePage); //notice목록,총페이지수 등
 
	 NoticePage noticePage에는  
	      총 게시글수포함(getTotal()호출)
		  notice목록포함(getContent()호출) 
		  int  totalPages;	//총페이지수   
		  int  startPage;	//시작페이지  
		  int  endPage;	//끝페이지--%> 
 noticePage : ${noticePage}

	 <!-- 내용 -->	
	<div class="container"><!-- page title -->
	<h2  class="mt-4 mb-3 text-center">공지사항</h2>
	
	<!-- 검색 -->
	<form name=form action="/notice/list.do" method=post>
	<!-- 변수 field,value 받은 후 현재 페이지에서 바로 DAO를 호출한다음 list에 뿌리기  -->
		<div class="form-group">
		
		<select name=field><!-- 검색키  -->
		<option value=title>제목</option>
		<option value=content>내용</option>
		<option value=write_name>작성자</option>
		</select>
		<!-- title, content, write_name sql 작성시 넣은 컬럼이름. (select*from notice where title like "%?%";-->
		<input onmouseover="this.focus()" type="text" id="search" name="search" placeholder="검색할 공지를 입력하세요"><!--  search는 like 안의 ?에 들어갈 부분 -->
		<button type="button" onclick=dataCheck() class="btn btn-default">검색</button> <!-- 검색버튼 클릭시 submit 후 현재 페이지 갱신하고 field,value 변수를 받은 후 넣기 -->
		</div>
		</form>
		<%
			NoticeDAO noticeDAO = new NoticeDAO();
			String field = request.getParameter("field"); //select의 option value가 들어간다(title,content,write_name)
			String value = request.getParameter("value");//검색버튼을 누르면 field, value에 값을 넣어준다
			ArrayList<NoticeDTO> list = noticeDAO.selectList(field,value);
			%>
			
		<%for(int i=0; i<list.size(); i++) {
			NoticeDTO dto = list.get(i);
			%>
			<%= dto.getNotice_no() %>
			<%= dto.getWriter_id() %>
			<%= dto.getTitle() %>
			<%= dto.getContent() %>
			<%= dto.getWritedate() %>
			<%= dto.getViews() %>
			<%} %>
		
	<!-- Board List table -->
	<div>총 게시글수: ${noticePage.total}건 /현재페이지: ${nowPage}</div>
	<table class="table table-hover">
	 <thead class="thead-light">
	  <tr>
	  <th scope="col">번호</th>
	  <th scope="col">제목</th>
	  <th scope="col">작성자</th>
	  <th scope="col">작성일</th>
	  <th scope="col">조회수</th>
	  </tr>
	 </thead>
	 <tbody> <%-- 총공지글(수가 0이면)없으면  true리턴, 그렇지 않으면 false --%>
	  <c:if test="${noticePage.hasNoNotice()}">
	  <tr>
		  <td colspan="4">공지글이 존재하지 않습니다. 공지를 입력해주세요</td>
	  </tr>
	 </c:if> 
	 
	  <%-- <c:forEach>반복문이용 1페이지당 출력할 게시글수 만큼 반복 출력 시작 
 	  <c:forEach var="변수명"  items="배열명 또는 컬렉션" >--%>
	  <c:forEach var="notice"   items="${noticePage.content}">
	  <tr>
		  <td>${notice.number}</td>
		  <%-- check.do?no=상세하게보고싶은글번호&pageNo=현재페이지"  --%>
		  <td><a href="check.do?no=${notice.number}&pageNo=${nowPage}">${notice.title}</a></td>
		  <td>${notice.writer.id}</td>
		  <td>${notice.writeDate}</td>
		  <td>${notice.views}</td>
	  </tr>
	  </c:forEach> <!-- 내용출력 끝  -->
	 </tbody>
	</table>
	<div>
 	  <a href="<%=request.getContextPath()%>/notice/write.do" class="btn btn-outline-dark btn-sm">공지글 쓰기</a>
 	</div>
	<br/>
	
	<%--pagenation 출력부분 --%>
	<!-- nav요소를 이용하여 pagination을 감싼다 -->
	<nav area-label="Page navigation">  <!--  justify-content-center클래스는 가운데정렬  -->
	<ul class="pagination justify-content-center" style="margin:0 0">
	<%--<c:if>이용하여 노출여부가 달라진다 --%>

	<c:if test="${noticePage.startPage>3}">
	<li class="page-item"><a class="page-link" href="list.do?pageNo=${noticePage.startPage-3}">&lt;&lt;
	prev</a></li>
	</c:if>
	
	
	 <%--p653 43라인 <c:forEach></c:forEach>반복문이용 --%>
	<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}" step="1">
	<li class="page-item"><a class="page-link" href="list.do?pageNo=${pNo}">${pNo}</a></li>
	</c:forEach>

	<%--<c:if>이용하여 노출여부가 달라진다 --%>
	<c:if test="${noticePage.endPage<noticePage.totalPages}">
	<li class="page-item"><a class="page-link" href="list.do?pageNo=${noticePage.startPage+3}">next&gt;&gt;</a></li>
	</c:if>	
	</ul>
	</nav>
	</div>
	<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html> 