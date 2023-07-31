<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
  <div class="container">
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav"
      aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="/index.jsp">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">공지사항</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPolitics" role="button"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            정치
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownPolitics">
            <a class="dropdown-item" href="/min/article/polList.do">메인페이지</a>
            <a class="dropdown-item" href="#">Link 2</a>
            <a class="dropdown-item" href="#">Link 3</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownWhisky" role="button"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            위스키
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownWhisky">
            <a class="dropdown-item" href="/CJH/whi_board/list.do?pageNo=1">테이스팅 노트</a>
            <a class="dropdown-item" href="/CJH/whi_photo/list.do?pageNo=1">보틀 자랑 게시판</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAnimal" role="button"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            동물
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownAnimal">
            <a class="dropdown-item" href="/Angel/article.do?pageNo=1">동물</a>
            <a class="dropdown-item" href="#">강아지</a>
            <a class="dropdown-item" href="#">고양이</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsedItems" role="button"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            중고거래
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownUsedItems">
            <a class="dropdown-item" href="<%=request.getContextPath() %>/gwon/sell/sellList.do">판매</a>
            <a class="dropdown-item" href="#">구매</a>
            <a class="dropdown-item" href="#">후기</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBoard1" role="button"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            회창
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownBoard1">
            <a class="dropdown-item" href="/hwet/article/list.do">게시판</a>
            <a class="dropdown-item" href="/chat/showRoom.do">채팅(구현중)</a>
            <a class="dropdown-item" href="#">Link 3</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMovie" role="button"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            영화
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMovie">
            <a class="dropdown-item" href="#">Link 1</a>
            <a class="dropdown-item" href="#">Link 2</a>
            <a class="dropdown-item" href="#">Link 3</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBoard2" role="button"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            경탁
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownBoard2">
            <a class="dropdown-item" href="/tak/article/list.do">Link 1</a>
            <a class="dropdown-item" href="#">Link 2</a>
            <a class="dropdown-item" href="#">Link 3</a>
          </div>
        </li>
      </ul>
      <ul class="navbar-nav">
      	<c:if test="${empty AUTH_USER}">
	        <li class="nav-item">
	          <a class="nav-link" href="/join.do">회원가입</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="/loginForm.do">로그인</a>
	        </li>
        </c:if>
        <c:if test="${!empty AUTH_USER}">
        	<c:if test="${AUTH_USER.user_id.contains('admin')}">
        	<li class="nav-item">
	          <a class="nav-link" href="/admin/showAll.do">관리자페이지</a>
	        </li>
        	</c:if>
        	<li class="nav-item">
	          <a class="nav-link" href="/myPage.do?user_id=${AUTH_USER.user_id}">마이페이지</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="/logout.do">로그아웃</a>
	        </li>
        </c:if>
      </ul>
      
    </div>
  </div>
</nav>
