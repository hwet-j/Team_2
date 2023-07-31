<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 위의 fmt는 날짜시간포맷을 적용하기 위해서 선언 --%>
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
	<%--//리턴 OurArticleData ora : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
			OurArticleData ora = readArticleService.getDetail(no);  
			request.setAttribute("ora", ora); --%>
 <h2>ourPolReadArticle.jsp</h2>
 pad : ${pad}
 <p><hr/></p>
 *작성일:<fmt:formatDate value="${pad.regDate}"   pattern="yyyy년 M월 d일"/><br/>
 *수정일:<fmt:formatDate value="${pad.modifiedDate}"   pattern="yyyy.MM.dd HH:mm:ss"/>     

 <p><hr/></p>
 
 <%-- String pageNo = "1";
 <c:set var="변수명" value="값">
 param.pageNo       파라미터명이 pageNo를 의미
 empty param.pageNo 파라미터 pageNo가 비어있니? 비어있으면 true, 그렇지않으면 false리턴
 ${empty param.pageNo?1:param.pageNo} 
 파라미터 pageNo가 비어있으면  1을,
 파라미터 pageNo가 비어있지않으면   파라미터 pageNo에 저장된 값을 출력하라
 
 value="${empty param.pageNo?1:param.pageNo}  1또는  파라미터 pageNo에 저장된 값을
 var="pageNo"라고 정의한 변수pageNo에 값으로 저장해라
 --%>
 <c:set var="pageNo" value="${empty param.pageNo?1:param.pageNo}" /> 
 <a href="polList.do?pageNo=${pageNo}">목록보기</a>
 
 <c:if test="${AUTH_USER.id==pad.writer_id}">
 <a href="polmodify.do?no=${pad.number}">게시글수정</a>
 </c:if>
 
 <c:if test="${AUTH_USER.id eq pad.writer_id}">
 <a href="poldelete.do?no=${pad.number}">게시글삭제해야지</a>
 </c:if>
</body>
</html>










