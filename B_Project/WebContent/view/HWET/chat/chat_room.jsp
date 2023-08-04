<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>채팅방</title>
<!-- 부트스트랩 CSS 링크 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 부트스트랩 JS 스크립트 링크 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<%-- sweetAlert2 : 알림창 관련 디자인 --%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<style>
/* 스크롤 영역 */
.scroll-area {
	max-height: 400px;
	overflow-y: auto;
}
</style>

<script>
    $(document).ready(function() {
    	var roomId = ${room_id};
    	
    	$("#content").keydown(function (event) {
            // Shift 키와 Enter 키가 함께 눌렸을 때 줄바꿈 기능 수행
            if (event.shiftKey && event.keyCode === 13) {
                event.preventDefault(); // 기본 동작인 줄바꿈을 방지
                var content = $('#content');
                content.val(content.val() + "\n");
            }
            // Enter 키만 눌렸을 때 메시지 전송
            else if (event.keyCode === 13) {
                event.preventDefault(); // 기본 동작인 줄바꿈을 방지
                sendMessage(); // 메시지를 전송하는 함수 호출
            }
        });
    	
        // 채팅방 대화목록 스크롤 최하단으로 이동
        function scrollToBottom() {
            var scrollArea = $(".scroll-frame");
            scrollArea.scrollTop(scrollArea.prop("scrollHeight"));
        }

        // 페이지 로드시 최하단으로 스크롤 이동
        scrollToBottom();
        
        
        $("#send_button").click(function (event) {
            sendMessage(); // 메시지를 전송하는 함수 호출
        });
        
        function sendMessage() {
        	var room_id = $('#room_id').val();
            var writer = $('#writer').val();
            var content = $('#content').val();
            if (content.trim() === "") {
            	Swal.fire({
      			  icon: 'warning',
      			  title: '문자가 입력되지 않았습니다.',
      			  text: '문자 입력 후 재시도 해주세요',
      			})
      			return false;
            }
            
            $.ajax({
                type: "POST",
                url: "/chat/sendMessage.do",
                data: {
                	room_id: room_id,
                	writer: writer,
                	content: content
    	        },
                success: function(response) {
                	sendMessage_socket(writer, content, response.output_data);
                	
                    $("#postList_"+roomId).append(response.input_data);
                    
                    scrollToBottom();	
                },
                error: function() {
                	Swal.fire({
            			  icon: 'warning',
            			  title: '글 작성에 실패했습니다.',
            			  text: '문제가 지속되면 관리자에게 문의하세요',
            			})
            			return false;
                }
            });
            $('#content').val('');
        };
        
        
        <%-- 웹소켓 관련 스크립트 --%>
		// 「broadsocket」는 호스트 명 (servlet에서 맵핑한 이름)
		// WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
		var webSocket = new WebSocket("ws://172.30.1.13:80/broadsocket");
		
		// WebSocket 서버와 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			 var user_id = "${AUTH_USER.user_id}";
             webSocket.send(JSON.stringify({ type: "join_room", user: user_id, room_id: roomId }));
		};
		// WebSocket 서버와 접속이 끊기면 호출되는 함수
		webSocket.onclose = function(event) {
		};
		// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
		webSocket.onerror = function(message) {
		  alert("error...");
		};
		
		// 페이지를 떠날 때 send 메서드를 호출하여 서버에 데이터를 전송 -> onclose로는 구현되지 않음
		window.onbeforeunload = function() {
		    webSocket.send(JSON.stringify({ type: "disconnect_room", user: "${AUTH_USER.user_id}", room_id: roomId }));
		};
		
		/// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
		webSocket.onmessage = function(event) {
		  var messageData = event.data;
		  if (typeof messageData === "string") {
		    var jsonData = JSON.parse(messageData);
		    var content = jsonData.content;
		    var room_info = jsonData.room_id;	
		    // 데이터가 문자열인 경우 바로 출력
		    $("#postList_"+room_info).append(content);
		  } else if (typeof messageData === "object") {
		    // 데이터가 객체인 경우 JSON 형식으로 변환하여 출력
		    var jsonString = JSON.stringify(messageData);
		    $("#postList_"+roomId).append(jsonString);
		  } 
		  // scrollToBottom 함수를 직접 호출하여 스크롤 이동
		  scrollToBottom();	
		};
		// Send 버튼을 누르면 호출되는 함수
		function sendMessage_socket(user, message, output_message) {
		  //webSocket.send("{{" + user + "}}" + output_message);
		  webSocket.send(JSON.stringify({ type: "chat_message", content: output_message, user: user , room_id:roomId}));
		}
		
		// Disconnect 버튼을 누르면 호출되는 함수
		function disconnect() {
		  // WebSocket 접속 해제
		  webSocket.close();
		}
            
    });
		
	</script>

<style>
/* 스크롤 영역 */
.scroll-frame {
	max-height: 400px; 		/* 최대 높이를 지정해줍니다. */
	overflow-y: auto; 		/* 내용이 넘칠 경우 스크롤이 생기도록 설정합니다. */
	border: 1px solid #ccc; /* 프레임에 테두리를 추가합니다. */
	border-radius: 5px; 	/* 프레임의 모서리를 둥글게 만듭니다. */
	padding: 10px; 			/* 내용과 프레임 사이에 여백을 줍니다. */
}

/* 채팅 말풍선들이 가로로 배열되도록 설정 */
.chat-list {
	display: flex;
	flex-direction: column;
}

.chat-bubble {
	max-width: 70%;
	padding: 8px 12px;
	border-radius: 20px;
	margin: 4px;
}

.right-bubble {
	background-color: #83ff6c;
	color: #000;
	align-self: flex-end; /* 오른쪽 정렬 */
}

.left-bubble {
	background-color: #c0c0c0;
	color: #000;
	align-self: flex-start; /* 왼쪽 정렬 */
}

.center-bubble {
    background-color: #c5ffff;
    color: #000;
    align-self: center; /* 가운데 정렬 */
}
</style>

<style>
    .form-group {
      display: inline-block;
      margin-right: 10px;
    }

    .btn {
      display: inline-block;
    }
  </style>

</head>
<body>
<%@ include file = "/header.html" %>
	<div class="container">
		<h1 class="mt-4">채팅방</h1>
		
		<div class="container mt-5">
		    <h2>채팅방 초대</h2>
		    <form action="/chat/inviteRoom.do" method="post">
		      <div class="form-group">
		        <input type="hidden" class="form-control" id="room_id" name="room_id" value="${room_id}">
		        <select class="form-control" id="invite_user" name="invite_user">
		          <%-- member_list 변수의 각 항목을 출력하기 위한 foreach문 --%>
		          <c:forEach items="${member_list}" var="member">
		            <option value="${member}">${member}</option>
		          </c:forEach>
		        </select>
		      </div>
		      <button type="submit" class="btn btn-primary">생성</button>
		    </form>
		  </div>

		<!-- 채팅방 참여자 목록 출력 -->
		<h2>채팅방 참여자 목록</h2>
		<ul class="list-group">
			<ol>
				<c:forEach items="${participants}" var="participant" varStatus="status">
                ${participant}
				<c:if test="${!status.last}">
						<span>, </span>
				</c:if>
				</c:forEach>
			</ol>
		</ul>

		<div class="mt-4 scroll-frame">
			<h2>채팅방 대화목록</h2>
			<div id="postList_${room_id}" class="list-group chat-list">
				<c:forEach items="${messages}" var="message">
					<span class="chat-bubble ${AUTH_USER.user_id eq message.sender_id ? 'right-bubble' : 'left-bubble'}">
						<c:if test="${AUTH_USER.user_id ne message.sender_id}">
							<span>[${message.sender_id}]</span>
						</c:if> <span style="white-space: pre-wrap;" style="font-weight: bold;">${message.content}</span>
						<span style="font-size: small;">(<fmt:formatDate value="${message.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />)
					</span>
					</span>
				</c:forEach>
			</div>
		</div>

	</div>

	<div class="mt-4 text-center">
		<!-- 가운데 정렬을 위해 text-center 클래스 추가 -->
		<h3>채팅작성</h3>
		<form id="send_message_form" class="d-inline-block">
			<!-- 가운데 정렬을 위해 d-inline-block 클래스 추가 -->
			<input type="hidden" id="room_id" name="room_id" value="${room_id}">
			
			<input type="hidden" id="writer"  name="writer" value="${AUTH_USER.user_id}">

			<textarea id="content" name="content" rows="3" cols="50" class="form-control mb-2"></textarea>
			
			<!-- 부트스트랩의 form-control 클래스와 mb-2 클래스로 스타일 적용 -->
			<button type="button" class="btn btn-primary" id="send_button">보내기</button>
		</form>
	</div>
<%@ include file = "/footer.html" %>
</body>
</html>
