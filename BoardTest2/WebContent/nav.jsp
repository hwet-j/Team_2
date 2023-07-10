<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<!-- Option : Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<!-- Option : Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
-->



<!-- jstl.jar파일이 있어야 함 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  



<!-- class 명에 bg-dark, bg-primary 등을 넣어줘서 색상설정 가능 이외 색상은 style background로 설정 -->
<nav class="navbar navbar-expand-lg bg-body-tertiary" style="background-color: #e3f2fd;">
	<div class="container-fluid">
  
		<a class="navbar-brand" href="#"><img width="180" height="68" src="<%=request.getContextPath() %>/assets/images/logo.png" alt="로고" /></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
    
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<li class="nav-item">
				<a class="nav-link active" aria-current="page" href="#">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Link</a>
			</li>
			
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            	게시판	
				</a> 
				<ul class="dropdown-menu">
					<!-- 각자 페이지를 추가하고 싶다면  이름밑에 하나씩 추가 (class명 dropdown-item으로)-->
					<li><a class="dropdown-item" href="#">김현민</a></li>
					<li><a class="dropdown-item" href="#">김현민2</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="#">백경탁</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="#">전재권</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="#">조중현</a></li>
					<li><a class="dropdown-item" href="#">조중현2</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="#">김재정</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="#">정회창</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="#">홍진호</a></li>
				</ul>
			</li>
			<li class="nav-item">
				<a class="nav-link disabled">Disabled</a>
			</li>
			</ul>
			<!-- 검색 관련 form -->
			<!-- <form class="d-flex" role="search">
				<input class="form-control me-3" type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form> -->
		
		<ul class="nav justify-content-end">
		<c:if test="${sessionScope.username == null}">
			<li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/view/login.jsp">로그인</a>
			</li>
			<li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/view/member/joinform.jsp">회원가입</a>
			</li>
		</c:if>
		<c:if test="${sessionScope.username != null}">
			<li class="nav-item">
				<a class="nav-link" href="#">로그아웃</a>
			</li>
		</c:if>
		 
	  </ul>
    </div>
  </div>
</nav>

