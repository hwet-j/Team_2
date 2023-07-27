<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<!-- Bootstrap CDN -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<title>관리자 페이지</title>

<%-- 현재 페이지 번호를 가져옵니다. --%>
<c:set var="current_page" value="${empty param.page_no ? 1 : param.page_no}" />

   <script>
 	/* 서브창이 완료되면 호출될 함수  */
    function onSubFormComplete() {
        // 메인 창을 새로고침하여 변경된 데이터를 업데이트
        window.location.reload();
    }
       
    /* 유저 생성 시 서브창을 띄워서 진행 */
    function openSignUpForm() {
        // 창의 크기를 설정
        var width = 450;
        var height = 350;
        // 창의 위치를 설정 (화면의 중앙에 표시되도록)
        var left = (screen.availWidth - width) / 2;
        var top = (screen.availHeight - height) / 2;
        var signUpForm = window.open('/view/Admin/signup.jsp', 'SignUp Form', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);
    }
    
    /* 유저 제거 함수  */
	function delete_user(user_id) {
	    Swal.fire({
	        title: '제거하시겠습니까?',
	        text: "제거하면 데이터를 복구할 수 없습니다",
	        icon: 'warning',
	        showCancelButton: true,
	        confirmButtonColor: '#3085d6',
	        cancelButtonColor: '#d33',
	        confirmButtonText: '확인',
	        cancelButtonText: '아니오'
	    }).then((result) => {
	        if (result.isConfirmed) {
	            window.location.href = "/admin/deleteUser.do?user_id=" + user_id;
	        } else {
	            return false;
	        }
	    }); 
	}
    
   /* 수정 버튼 클릭 시 입력 폼으로 전환하는 함수 */
   function edit_user(user_id) {
		/* 수정 가능한 input태그를 보여주고 일반 span태그 숨김 */
		// 이름 
		$('#user_name-' + user_id).hide();
		$('#user_name_input-' + user_id).show();
		
		// 생일
		$('#user_birth-' + user_id).hide();
		$('#user_birth_input-' + user_id).show();
		
		// 닉네임
		$('#user_nickname-' + user_id).hide();
		$('#user_nickname_input-' + user_id).show();
		
		// 성별 
		$('#user_gender-' + user_id).hide();
		$('#user_gender_select-' + user_id).show();
		
		// 전화번호
		$('#user_tlno-' + user_id).hide();
		$('#user_tlno_input-' + user_id).show();
		
		// 가입날짜
		$('#user_join_date-' + user_id).hide();
		$('#user_join_date_input-' + user_id).show();
		  
		/* 수정 버튼을 숨기고 저장 버튼을 보이도록 변경 */
		$('#save_btn-' + user_id).show();
		$('#edit_btn-' + user_id).hide();
   }
   
   /* 저장 버튼 클릭 시 수정 내용을 서버로 전송하는 함수 */
   function save_edit(user_id) {
	    // 수정한 데이터를 가져오기 (비밀번호 정보도 필요하면 추가)
	    var user_name = $('#user_name_input-' + user_id).val();
	    var user_birth = $('#user_birth_input-' + user_id).val();
	    var user_nickname = $('#user_nickname_input-' + user_id).val();
	    var user_gender = $('#user_gender_select-' + user_id).val();
	    var user_tlno = $('#user_tlno_input-' + user_id).val();
	    var user_join_date = $('#user_join_date_input-' + user_id).val();
	    
	    // 알림창을 통해서 확인
	    Swal.fire({
            title: '수정하시겠습니까?',
            text: "확인 버튼을 누르면 수정작업이 진행됩니다.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '아니오'
        }).then((result) => {
            if (result.isConfirmed) {
            	// Ajax를 통해 서버로 데이터 전송
        	    $.ajax({
        	        type: 'POST',
        	        url: '/admin/editUser.do', // 서버로 전송할 URL
        	        data: {
        	            user_id: user_id,
        	            user_name: user_name,
        	            user_birth: user_birth,
        	            user_gender: user_gender,
        	            user_nickname: user_nickname,
        	            user_tlno: user_tlno,
        	            user_join_date: user_join_date
        	        },
        	        success: function (response) {
        	            $('#user_name-' + user_id).text(user_name).show();
        	            $('#user_birth-' + user_id).text(user_birth).show();
        	            $('#user_nickname-' + user_id).text(user_nickname).show();
        	    	    $('#user_gender-' + user_id).text(user_gender).show();
        	    	    $('#user_tlno-' + user_id).text(user_tlno).show();
        	    	    $('#user_join_date-' + user_id).text(user_join_date).show();

        	            // 수정 가능한 input 태그를 숨기고, 일반 span 태그를 보여줌
        	            $('#user_name_input-' + user_id).hide();
        	            $('#user_birth_input-' + user_id).hide();
        	            $('#user_nickname_input-' + user_id).hide();
        	    	    $('#user_gender_select-' + user_id).hide();
        	    	    $('#user_tlno_input-' + user_id).hide();
        	    	    $('#user_join_date_input-' + user_id).hide();

        	            /* 저장버튼을 숨기고 수정버튼을 보이도록 변경 */
        	            $('#save_btn-' + user_id).hide();
        	            $('#edit_btn-' + user_id).show();
        	        },
        	        error: function (xhr, status, error) {
        	            console.log('Error:', error);
        	        }
        	    });
            } else {
            	Swal.fire({
            		  icon: 'error',
            		  title: '취소되었습니다.',
            		  text: '수정 취소하셨습니다.',
            		  confirmButtonText: '확인'
            		})
            		
        		$('#user_name-' + user_id).show();
				$('#user_name_input-' + user_id).hide();
				
				// 생일
				$('#user_birth-' + user_id).show();
				$('#user_birth_input-' + user_id).hide();
				
				// 닉네임
				$('#user_nickname-' + user_id).show();
				$('#user_nickname_input-' + user_id).hide();
				
				// 성별 
				$('#user_gender-' + user_id).show();
				$('#user_gender_select-' + user_id).hide();
				
				// 전화번호
				$('#user_tlno-' + user_id).show();
				$('#user_tlno_input-' + user_id).hide();
				
				// 가입날짜
				$('#user_join_date-' + user_id).show();
				$('#user_join_date_input-' + user_id).hide();
				  
				/* 수정 버튼을 숨기고 저장 버튼을 보이도록 변경 */
				$('#save_btn-' + user_id).hide();
				$('#edit_btn-' + user_id).show();
                return false;
            }
        });
	    
        return false; // 폼 전송 취소 
	}

   
</script>

<script>

$(document).ready(function() {
    // 검색 버튼 클릭 시 search 함수 실행
    $("#searchButton").click(function() {
        search();
    });

    // Enter 키 누를 때 검색 기능 실행
    $("#keyword").keypress(function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            search();
        }
    });
});

<%-- 검색 버튼 클릭시 get방식으로 데이터를 전송 --%>
function search() {
    var keywordInput = $("#keyword");
    var keyword = keywordInput.val();
    var search_type = $('[name="search_type"]').val();
    var url = "/admin/showAll.do?page_no=${current_page}" + "&search_type=" + search_type + "&keyword=" + keyword;
    window.location.href = url;
}
</script>

</head>
<body>
<%@ include file="/navi.jsp" %>




<h2 style="text-align: center;">관리자 페이지</h2>

<div class="container mt-4">
<ul>
    <li><a href="#" onclick="openSignUpForm()">새 사용자 추가</a></li>
</ul>
<table class="table table-hover">
<thead>
	<tr>
		<td style="white-space: nowrap;">아이디</td>
		<td style="white-space: nowrap;">비밀번호</td>
		<td style="white-space: nowrap;">이름</td>
		<td style="white-space: nowrap;">생년월일</td>
		<td style="white-space: nowrap;">닉네임</td>
		<td style="white-space: nowrap;">성별</td>
		<td style="white-space: nowrap;">연락처</td>
		<td style="white-space: nowrap;">가입일</td>
		<td style="white-space: nowrap;">수정</td>
		<td style="white-space: nowrap;">삭제</td>
	</tr>
</thead>
<tbody>
<c:forEach var="user" items="${USERS}">
	<tr>
		<td>
			<span id="user_id-${user.user_id}">${user.user_id}</span>
		</td>
		<td>
			<span id="user_pw-${user.user_id}">${user.user_pw}</span></td>
		<td>
            <span id="user_name-${user.user_id}">${user.user_name}</span>
            <input type="text" id="user_name_input-${user.user_id}" style="display: none;width: 100px;" value="${user.user_name}">
        </td>
        <td>
            <span id="user_birth-${user.user_id}">${user.user_birth}</span>
            <input type="date" id="user_birth_input-${user.user_id}" style="display: none;width: 120px;" value="${user.user_birth}">
        </td>
        <td>
            <span id="user_nickname-${user.user_id}">${user.user_nickname}</span>
            <input type="text" id="user_nickname_input-${user.user_id}" style="display: none;width: 100px;" value="${user.user_nickname}">
        </td>
		<td>
		    <span id="user_gender-${user.user_id}">${user.user_gender}</span>
		    <select id="user_gender_select-${user.user_id}" style="display: none;">
		        <option value="">미지정</option>
		        <option value="남성" ${user.user_gender == '남성' ? 'selected' : ''}>남성</option>
		        <option value="여성" ${user.user_gender == '여성' ? 'selected' : ''}>여성</option>
		    </select>
		</td>
		<td>
		    <span id="user_tlno-${user.user_id}">${user.user_tlno}</span>
		    <input type="text" id="user_tlno_input-${user.user_id}" style="display: none;width: 130px;" value="${user.user_tlno}">
		</td>
		<td>
		    <span id="user_join_date-${user.user_id}">${user.user_join_date}</span>
		    <input type="date" id="user_join_date_input-${user.user_id}" style="display: none;width: 120px;" value="${user.user_join_date}">
		</td>
		<td>
            <a href="#" id="edit_btn-${user.user_id}" onclick="edit_user('${user.user_id}')"><button class="btn-primary">수정</button></a> 
            <a href="#" style="display: none;" id="save_btn-${user.user_id}" onclick="save_edit('${user.user_id}')"><button class="btn-primary">저장</button></a> 
        </td>
		<td>
			<a href="#" id="delete_btn-${user.user_id}" onclick="delete_user('${user.user_id}')"><button class="btn-primary">삭제</button></a> 

		</td>
	</tr>
</c:forEach>
</tbody>
</table>

<%-- 검색 기능  --%>
<div class="row mt-4 mb-5" style="font-size: 12px;"> 
   	<div class="col-md-6">
	  <div class="input-group">
	    <select name="search_type" class="form-select mr-1">
	      <option value="id">아이디</option>
	      <option value="name">이름</option>
	      <option value="nickname">닉네임</option>
	      <option value="join_date">가입일</option>
	    </select>
	    <input type="text" name="keyword" id="keyword" class="form-control" placeholder="검색어 입력" required />
	    <div class="input-group-append">
	      <a href="javascript:void(0)" class="btn btn-primary" id="searchButton">검색</a>
	    </div>
	  </div>
	</div>
</div>

<%-- 페이징 처리 부분 --%>
<ul class="pagination justify-content-center my-6">
    <%-- 이전 페이지로 이동하는 링크 --%>
    <c:if test="${current_page > 1}">
        <li class="page-item">
            <a class="page-link" href="/admin/showAll.do?page_no=${current_page - 1}">이전</a>
        </li>
    </c:if>
	
	<%-- 페이지 이동에 따라 브라우저에 표현되는 페이지 번호의 범위를 설정함 --%>
    <c:forEach begin="${current_page < 5 ? 1 : current_page-4}" end="${total_pages < current_page + 4 ? total_pages : current_page + 4}" var="page">
    	<%-- 표시되는 페이지와 현재페이지가 동일하면 class명에 active를 추가하여 표현 --%>
        <li class="page-item <c:if test='${page == current_page}'>active</c:if>">
            <a class="page-link" href="/admin/showAll.do?page_no=${page}">${page}</a>
        </li>
    </c:forEach>

    <%-- 다음 페이지로 이동하는 링크 --%>
    <c:if test="${current_page < total_pages}">
        <li class="page-item">
            <a class="page-link" href="/admin/showAll.do?page_no=${current_page + 1}">다음</a>
        </li>
    </c:if>
</ul>

</div>

</body>
</html>