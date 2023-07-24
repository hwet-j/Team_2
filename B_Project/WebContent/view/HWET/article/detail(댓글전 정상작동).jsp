<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="hwet.article.model.HwetArticleDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS 라이브러리 링크 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>



<script>
    function confirmDelete() {
        Swal.fire({
            title: '정말 삭제하시겠습니까?',
            text: "삭제하면 게시글 정보가 전부 사라집니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네, 삭제하겠습니다!',
            cancelButtonText: '아니오'
        }).then((result) => {
            if (result.isConfirmed) {
                // 폼 전송 실행
            	document.getElementById('deleteForm').submit();
            } else {
            	Swal.fire({
            		  icon: 'error',
            		  title: '취소되었습니다.',
            		  text: '게시글 삭제를 취소하셨습니다.',
            		  confirmButtonText: '확인'
            		})
                return false;
            }
        });
        return false; // 폼 전송 취소 - 기본적인 이동 동작을 막음
    }
</script>

<body>
    <div class="container">
        <h2 class="my-4">게시물 정보 조회</h2>
        <table class="table table-bordered table-hover">
            <tr>
                <th style="width: 20%;">글 번호</th>
                <td>${data.boardId}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${data.writer}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${data.title}</td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td>${data.category}</td>
            </tr>
            <tr>
                <th>링크</th>
                <td><a href="${data.link}">${data.link}</a></td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${data.content}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${data.regDate}</td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${data.hit}</td>
            </tr>
            <tr>
                <th>수정일</th>
                <td>${data.updateDate}</td>
            </tr>
        </table>
    </div>
    
    <div class="container mt-4 d-flex justify-content-end">
        <c:set var="page_no" value="${empty param.page_no?1:param.page_no}" />
        
        <a href="list.do?page_no=${page_no}&search_type=${search_type}&keyword=${keyword}&category_info=${category_info}" class="btn btn-secondary">목록보기</a>
        
        <c:if test="${AUTH_USER.userId == data.writer}">
            <!-- 수정 버튼을 클릭했을 때 폼 전송 -->
            <form action="${pageContext.request.contextPath}/hwet/article/modifyForm.do" method="post" class="ml-2">
                <!-- hidden 타입의 input 요소를 추가하여 기존 데이터를 전송 -->
                <input type="hidden" name="boardId" value="${data.boardId}">
                <input type="hidden" name="page_no" value="${page_no}">
                <input type="hidden" name="no" value="${no}">
                
                <button type="submit" class="btn btn-info">게시글 수정</button>
            </form>
        </c:if>
        
        <!-- 삭제 버튼 -->
        <c:if test="${AUTH_USER.userId eq data.writer}">
           	<!-- 삭제 버튼 -->
	        <form id="deleteForm" action="${pageContext.request.contextPath}/hwet/article/delete.do" method="post">
	            <input type="hidden" name="no" value="${data.boardId}">
	            <button type="submit" class="btn btn-danger ml-2" onclick="return confirmDelete()">게시글 삭제</button>
	        </form>
        </c:if>
    </div>

    <!-- Bootstrap JS 라이브러리 링크 (jQuery와 Popper.js를 포함) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
     
    
</body>
</html>
