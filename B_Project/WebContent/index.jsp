<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 CSS 파일 링크 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<style>

.container {
 display : flex;
 flex-direction: row;
 justify-content: center;
}

.container-items {
 width : 600px;
 height : 200px;
 padding-left : 50px;
 padding-right : 50px;
 margin-bottom : 50px;
}

<style>
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: white;
   color: black;
   text-align: center;
   font-size: 100%
}

.footer_header {
	display : inline;
}
</style>

</head>
<body>
<header>
<%@ include file="/navi.jsp" %>
</header>

<main>
<div class = "container">
  <div class = "container-items" >
	  <h3 style = "display: inline;">공지사항</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		 <!-- 제목이 반복적으로 나올 수 있도록 EL 이용해야 할 거 같아요 -->
		  <tr>
		   <th scope = "row">1</th>
		   <th scope="col">
		   		<a href = "#">제목</a>
		   </th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col">
		   		<a href = "#">제목</a>
		   </th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col">
		   		<a href = "#">제목</a>
		   </th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
  <div class = "container-items" >
	  <h3 style = "display: inline;">정치</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		  <tr>
		   <th scope = "row">1</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
</div>

<div class = "container">
  <div class = "container-items" >
	  <h3 style = "display: inline;">위스키</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		  <tr>
		   <th scope = "row">1</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
  <div class = "container-items" >
	  <h3 style = "display: inline;">동물</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		  <tr>
		   <th scope = "row">1</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
</div>

<div class = "container">
  <div class = "container-items" >
	  <h3 style = "display: inline;">중고거래</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		  <tr>
		   <th scope = "row">1</th>
		   <th scope="col"><a href = "#">제목</a></th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col"><a href = "#">제목</a></th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col"><a href = "#">제목</a></th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
 <div class = "container-items" >
	  <h3 style = "display: inline;">회창</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		  <tr>
		   <th scope = "row">1</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
</div>

<div class = "container">
  <div class = "container-items" >
	  <h3 style = "display: inline;">영화</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		  <tr>
		   <th scope = "row">1</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
<div class = "container-items">
	  <h3 style = "display: inline;">경탁</h3>
	  <input type="button" class = "btn btn-link btn-sm" value = "더보기" style = "float:right;" onclick="location href =''"/>
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">글번호</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </thead>
		 <tbody>
		  <tr>
		   <th scope = "row">1</th> 
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">2</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		  <tr>
		   <th scope = "row">3</th>
		   <th scope="col">제목</th>
		   <th scope="col">작성자id</th>
		  </tr>
		 </tbody>	
	</table>
  </div>
</div>







</main>

<footer class="footer">
  <div class="container">
    <div class = "">
      <a href="#" class="">개인정보처리방침</a>
      <a href="#" class="">사이트 소개</a>
      <a href="#" class="">홈페이지 이용약관</a>
      <a href="#" class="">위치정보 이용약관</a>
      <a href="#" class="">마이페이지</a>
      <a href="#" class="">메일 문의</a>
    </div> 
  </div>
  <div class="container">
    <div class="">
      <span>사업자등록번호 001-01-011</span>
      <span>중앙정보처리학원</span>
      <span>TEL : 02) 123-4567 / FAX : 02) 123-4567</span>
      <span>개인정보 책임자</span>
      <span> Joongang-B Company. All Rights Reseved.</span>
      <br/><br/>
    </div>
  </div>
</footer>


</body>

<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>
