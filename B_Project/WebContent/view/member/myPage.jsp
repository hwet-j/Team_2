<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<title>Insert title here</title>
 <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</head>
<body>
<%@ include file="/header.html" %>
<!-- 여기아래로 -->
<script>
/* 수정 버튼 클릭 시 입력 폼으로 전환하는 함수 */
function edit_user(user_id) {
		/* 수정 가능한 input태그를 보여주고 일반 span태그 숨김 */
		// 암호
		$('#user_pw-' + user_id).hide();
		$('#user_pw_input-' + user_id).show();
		
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
		  
		/* 수정 버튼을 숨기고 저장 버튼을 보이도록 변경 */
		$('#save_btn-' + user_id).show();
		$('#edit_btn-' + user_id).hide();
}
/* 저장 버튼 클릭 시 수정 내용을 서버로 전송하는 함수 */
function save_edit(user_id) {
	    // 수정한 데이터를 가져오기 (비밀번호 정보도 필요하면 추가)
	    var user_pw = $('#user_pw_input-' + user_id).val();
	    var user_name = $('#user_name_input-' + user_id).val();
	    var user_birth = $('#user_birth_input-' + user_id).val();
	    var user_nickname = $('#user_nickname_input-' + user_id).val();
	    var user_tlno = $('#user_tlno_input-' + user_id).val();
	    var user_gender = $('#user_gender_select-' + user_id).val();
	    
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
     	        url: '/admin/editSelfUser.do', // 서버로 전송할 URL
     	        data: {
     	            user_id: user_id,
     	        	user_pw: user_pw,
     	            user_name: user_name,
     	            user_birth: user_birth,
     	            user_gender: user_gender,
     	            user_nickname: user_nickname,
     	            user_tlno: user_tlno
     	        },
     	        success: function (response) {
     	        	if (response === "nicknameDuplicate") {	
     	        		Swal.fire({
     	        			  icon: 'error',
     	        			  title: '수정에 실패했습니다.',
     	        			  text: '닉네임 ' + user_nickname + '은 이미 사용중입니다.',
     	        			})
     	        	} else if (response === "tlnoDuplicate") {	
     	        		Swal.fire({
   	        			  	icon: 'error',
   	        				title: '수정에 실패했습니다.',
   	        				text: '전화번호 ' + user_tlno + '은 이미 사용중입니다.',
   	        			})
     	        	} else {
     	        		$('#user_pw-' + user_id).text(user_pw).show();
     	        		$('#user_name-' + user_id).text(user_name).show();
          	            $('#user_birth-' + user_id).text(user_birth).show();
          	            $('#user_nickname-' + user_id).text(user_nickname).show();
          	    	    $('#user_gender-' + user_id).text(user_gender).show();
          	    	    $('#user_tlno-' + user_id).text(user_tlno).show();

          	            // 수정 가능한 input 태그를 숨기고, 일반 span 태그를 보여줌
          	            $('#user_pw_input-' + user_id).hide();
          	            $('#user_name_input-' + user_id).hide();
          	            $('#user_birth_input-' + user_id).hide();
          	            $('#user_nickname_input-' + user_id).hide();
          	    	    $('#user_gender_select-' + user_id).hide();
          	    	    $('#user_tlno_input-' + user_id).hide();

          	            /* 저장버튼을 숨기고 수정버튼을 보이도록 변경 */
          	            $('#save_btn-' + user_id).hide();
          	            $('#edit_btn-' + user_id).show();
     	        	}
     	           
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
         		
		// 암호
		$('#user_pw-' + user_id).show();
		$('#user_pw_input-' + user_id).hide();
		
		// 이름 
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
		  
		/* 수정 버튼을 숨기고 저장 버튼을 보이도록 변경 */
		$('#save_btn-' + user_id).show();
		$('#edit_btn-' + user_id).hide();
             return false;
         }
     });
	    
     return false; // 폼 전송 취소 
	}

</script>

<div>
<h1 style="text-align: center;">마이페이지</h1>
</div>

<div class="container">
<table class="table table-hover">
<thead>
		<tr style="text-align: center;">
			<td>데이터 타입</td>
			<td>유저정보</td>
		</tr>
</thead>
<tbody style="text-align: center;">
		<tr>
			<td>아이디</td>
			<td>${USER_INFO.user_id}</td>
		</tr>
		<tr>	
			<td>비밀번호</td>
			<td>
			<span id="user_pw-${AUTH_USER.user_id}">${AUTH_USER.user_pw}</span>
			<input type="password" id="user_pw_input-${AUTH_USER.user_id}" style="display: none;" value="${AUTH_USER.user_pw}"> 
			</td>
		</tr>
		<tr>	
			<td>이름</td>
			<td>
			<span id="user_name-${AUTH_USER.user_id}">${AUTH_USER.user_name}</span>
			<input type="text" id="user_name_input-${AUTH_USER.user_id}" style="display: none;" value="${AUTH_USER.user_name}"> 
			</td>
		</tr>
		<tr>	
			<td>닉네임</td>
			<td>
			<span id="user_nickname-${AUTH_USER.user_id}">${AUTH_USER.user_nickname}</span>
			<input type="text" id="user_nickname_input-${AUTH_USER.user_id}" style="display: none;" value="${AUTH_USER.user_nickname}"> 
			</td>
		</tr>
		<tr>	
			<td>생일</td>
			<td>
			<span id="user_birth-${AUTH_USER.user_id}">${AUTH_USER.user_birth}</span>
			<input type="date" id="user_birth_input-${AUTH_USER.user_id}" style="display: none;" value="${AUTH_USER.user_birth}"> 
			</td>
		</tr>
		<tr>	
			<td>핸드폰번호</td>
			<td>
			<span id="user_tlno-${AUTH_USER.user_id}">${AUTH_USER.user_tlno}</span>
			<input type="text" id="user_tlno_input-${AUTH_USER.user_id}" style="display: none;" value="${AUTH_USER.user_tlno}"> 
			</td>
		</tr>
		<tr>	
			<td>성별</td>
			<td>
			<span id="user_gender-${AUTH_USER.user_id}">${AUTH_USER.user_gender}</span>
			<select id="user_gender_select-${AUTH_USER.user_id}" style="display: none;">
		        <option value="">미지정</option>
		        <option value="남성" ${AUTH_USER.user_gender == '남성' ? 'selected' : ''}>남성</option>
		        <option value="여성" ${AUTH_USER.user_gender == '여성' ? 'selected' : ''}>여성</option>
		    </select> 
			</td>
		</tr>
		<tr>		
			<td>가입일</td><td>${AUTH_USER.user_join_date }</td>
		</tr>
</tbody>
</table>
<a href="#" id="edit_btn-${AUTH_USER.user_id}" onclick="edit_user('${AUTH_USER.user_id}')"><button class="btn-primary">수정</button></a>
<a href="#" style="display: none;" id="save_btn-${AUTH_USER.user_id}" onclick="save_edit('${AUTH_USER.user_id}')"><button class="btn-primary">저장</button></a> 
</div>

</body>
</html>