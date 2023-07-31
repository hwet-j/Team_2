<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 찾기</title>
<!-- 부트스트랩 CSS 파일 추가 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<%-- sweetAlert2 : 알림창 관련 디자인 --%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>


<c:set var="findType" value="${param.find_type}" />


<style>
/* 숫자 입력 input 태그의 숫자 스핀 버튼 숨기기 */
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input[type="number"] {
  -moz-appearance: textfield;
}
</style>


<script>
		$('#timerDisplay').hide();
		var tlno = "";
		
		function startTimer(remainingTime) {
	        var timerInterval = setInterval(function() {
	            remainingTime--;
	            if (remainingTime >= 0) {
	                updateTimerDisplay(remainingTime);
	            } else {
	                clearInterval(timerInterval);
	                $('#verification_code').prop('disabled', true); // 시간 초과 시 인증번호 입력 비활성화
	                $('#timerDisplay').text('시간 초과').css('color', 'black'); // 타이머 종료 시 색상을 검정색으로 변경
	                $('#find_button').prop('disabled', true);	// 버튼 비활성화
	            }
	        }, 1000);
	    }
		
		function updateTimerDisplay(seconds) {
		    var minutes = Math.floor(seconds / 60);
		    var remainingSeconds = seconds % 60;
		    var formattedTime = padZero(minutes) + ":" + padZero(remainingSeconds);
		
		    $('#timerDisplay').text('남은 시간: ' + formattedTime);
		}
		
		function padZero(number) {
		    return (number < 10) ? "0" + number : number;
		}
		
		
        function makeVerification() {
        	// 각 input 요소에 입력된 데이터를 가져옵니다.
            var phone_first = $('#phone_first').val();
            var phone_second = $('#phone_second').val();
            var phone_third = $('#phone_third').val();
            
         	// phone_first가 3글자인지 확인
            if (phone_first.length !== 3) {
            	Swal.fire({
        			  icon: 'error',
        			  title: '전화번호 첫 번째 부분은 3글자여야 합니다.',
        			  text: '입력 상태를 확인해주세요',
        			});
            	$('#phone_first').focus();
                return false;
            }

            // phone_second가 3~4글자인지 확인
            if (phone_second.length < 3 || phone_second.length > 4) {
            	Swal.fire({
        			  icon: 'error',
        			  title: '전화번호 두 번째 부분은 3~4글자여야 합니다.',
        			  text: '입력 상태를 확인해주세요',
        			});
            	$('#phone_second').focus();
                return false;
            }

            // phone_third가 3~4글자인지 확인
            if (phone_third.length < 3 || phone_third.length > 4) {
            	Swal.fire({
        			  icon: 'error',
        			  title: '전화번호 세 번째 부분은 3~4글자여야 합니다.',
        			  text: '입력 상태를 확인해주세요',
        			});
            	$('#phone_third').focus();
                return false;
            }
            
        	tlno = phone_first + "-" +phone_second + "-" + phone_third;
        	$.ajax({
    	        type: 'POST',
    	        url: '/findUser.do', 
    	        data: {
    	        	type: "code",
    	        	tlno: tlno
    	        },
    	        success: function (response) {
    	        	if (response.result === "exist") {	
    	        		$('#code_number').val(response.code);
    	        		Swal.fire({
    	        			  icon: 'success',
    	        			  title: tlno + '로 인증번호를 전송했습니다.',
    	        			  text: '인증번호 입력하여 진행해주세요.',
    	        			}).then((result) => {
    	        		        if (result.isConfirmed) {
    	        		        	Swal.fire({
    	      	        			  icon: 'info',
    	      	        			  title: '인증 번호 : ' + response.code,
    	      	        			  text: '3분내로 입력해주세요',
    	      	        			})
    	        		        	$('#find_button').prop('disabled', false);
    	        		        	var remainingTime = 180;
    	            	        	$('#timerDisplay').css('display', 'block');
    	            	        	$('#timerDisplay').css('color', 'red');
    	            	        	startTimer(remainingTime);
    	            	        	
    	        		        } else {
    	        		            return false;
    	        		        }
    	        		    }); 
    	        		
    	        	} else if (response.result === "not_exist") {	
    	        		Swal.fire({
  	        			  	icon: 'error',
  	        				title: '회원기록이 없는 전화번호입니다.',
  	        				text: '전화번호 ' + tlno + '를 다시 확인해주세요',
  	        			});
    	        	} else {
    	        		Swal.fire({
  	        			  	icon: 'error',
  	        				title: '서버와 연결을 확인해주세요',
  	        				text: '지속적으로 문제가 발생하면 관리자에게 문의하세요',
  	        			});
    	        	}
    	           
    	        },
    	        error: function (xhr, status, error) {
    	            console.log('Error:', error);
    	        }
    	    });
        }
        
        function sendVerification() {
            var code_number = $('#code_number').val();
            var verification_code = $('#verification_code').val();
            var find_info = $('#find_info').val();
            
            if (tlno === undefined){
            	Swal.fire({
      			  	icon: 'warning',
      				title: '전화번호 인증을 진행하세요.',
      				text: '인증 번호가 존재하지 않습니다.',
      			});
            	return;
            }
            
        	$.ajax({
    	        type: 'POST',
    	        url: '/findUser.do', 
    	        data: {
    	        	type: "verification",
    	        	code_number: code_number,
    	        	verification_code: verification_code,
    	        	tlno:tlno
    	        },
    	        success: function (response) {
    	        	if (response.massage === "success") {
    	        		if (find_info === "user_id"){	// 아이디 찾기
    	        			Swal.fire({
      	        			  	icon: 'success',
      	        				title: '회원 아이디 : ' + response.user_id,
      	        				text: 'OK 버튼을 누르면 창이 종료됩니다.',
      	        			}).then((result) => {
    	        		        if (result.isConfirmed) {
    	        		        	window.close();
    	        		        }
    	        		    });
    	        		} else {	// 비밀번호 찾기
    	        			Swal.fire({
      	        			  	icon: 'success',
      	        				title: '회원 비밀번호 : ' + response.user_pw,
      	        				text: 'OK 버튼을 누르면 창이 종료됩니다.',
      	        			}).then((result) => {
    	        		        if (result.isConfirmed) {
    	        		        	window.close();
    	        		        }
    	        		    });
    	        			
    	        		}
    	        	} else if (response.massage === "failed") {	
    	        		Swal.fire({
  	        			  	icon: 'error',
  	        				title: '인증번호가 올바르게 입력되지 않았습니다.',
  	        				text: '인증번호를 다시 확인해주세요',
  	        			});
    	        	} else {
    	        		Swal.fire({
  	        			  	icon: 'error',
  	        				title: '서버와 연결을 확인해주세요',
  	        				text: '지속적으로 문제가 발생하면 관리자에게 문의하세요',
  	        			});
    	        	}
    	           
    	        },
    	        error: function (xhr, status, error) {
    	            console.log('Error:', error);
    	        }
    	    });
        }
    </script>
    
</head>
</head>
<body>



    <div class="container mt-5">
    	<c:if test="${findType.equals('id')}">
        	<h2 class="mb-4">아이디 찾기</h2>
        </c:if>
        <c:if test="${findType.equals('pw')}">
        	<h2 class="mb-4">비밀번호 찾기</h2>
        </c:if>
			<div class="mb-3">
			    <div class="d-flex align-items-center">
			        <label for="phone_first" class="form-label col-form-label me-2" style="white-space: nowrap;">전화번호:</label>
			        <div class="input-group mt-3">
			            <input type="number" class="form-control col-3" id="phone_first" name="phone_first" placeholder="번호입력" minlength="3" maxlength="3" required>
			            <div class="col-auto mx-2">-</div>
			            <input type="number" class="form-control col-3" id="phone_second" name="phone_second" placeholder="번호입력" minlength="3" maxlength="4" required>
			            <div class="col-auto mx-2">-</div>
			            <input type="number" class="form-control col-3" id="phone_third" name="phone_third" placeholder="번호입력" minlength="3" maxlength="4" required>
			            <div class="col-auto mx-2"></div>
			            <div class="input-group-append">
			                <button type="button" class="btn btn-primary" onclick="makeVerification()">전화번호 인증</button>
			            </div>
			        </div> 
			    </div>
			</div>
		    <!-- 인증번호 입력 필드 추가 (인증번호를 입력받기 위한 부분) -->
		    <div class="mb-3">
		        <input type="hidden" id="code_number" name="code_number" value="(*&!$!$)">
		        <c:if test="${findType.equals('id')}">
		        	<input type="hidden" id="find_info" name="find_info" value="user_id">
		        </c:if>
		        <c:if test="${findType.equals('pw')}">
		        	<input type="hidden" id="find_info" name="find_info" value="user_pw">
		        </c:if>
		        
		                
               	<div class="mb-2"><span style="display: none;" id="timerDisplay">남은 시간: 03:00</span></div>
		        <div class="mb-3 d-flex align-items-center">
				    <label for="verification_code" class="form-label col-form-label me-2" style="white-space: nowrap;">인증번호:</label>
				    <input type="text" class="form-control" id="verification_code" name="verification_code" placeholder="인증번호를 입력해주세요" required>
				</div>
		    </div>
		    <div class="mb-3 d-flex justify-content-center"> <!-- 가운데 정렬 -->
		    	<button type="button" id="find_button" class="btn btn-primary" onclick="sendVerification()">
		    	<c:if test="${findType.equals('id')}">
		        	아이디 찾기
		        </c:if>
		        <c:if test="${findType.equals('pw')}">
		        	비밀번호 찾기
		        </c:if>
				</button>
			</div>
    </div>

    

    <!-- 부트스트랩 JS 파일 및 jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>