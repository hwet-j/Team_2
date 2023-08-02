<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="hwet.article.model.HwetArticleDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    
    // 댓글 등록
    function submit_reply() {
        var reply_content = document.getElementById('reply_content').value;
        // 댓글 내용이 비어있는지 확인
        if (reply_content.trim() === '') {
            Swal.fire({
                icon: 'error',
                title: '댓글 내용을 입력해주세요.',
                confirmButtonText: '확인'
            });
            return;
        }
        // 댓글 등록 폼 전송
        document.getElementById('reply_form').submit();
    }
    
    // Ajax를 이용하여 좋아요/싫어요 기능 추가 (정상적이지 못함 -> jsp 파일로 안하고 java파일로..)
    function recommendReply(type, board_id, reply_id) {
        $.ajax({
            url: '/hwet/article/recommendReply.do',
            type: 'POST',
            data: {
                type: type,
                board_id: board_id,
                reply_id: reply_id
            },
            success: function(response) {
                var positiveCountValue = document.getElementById("positive_cnt_"+reply_id).innerText;
                var negativeCountValue = document.getElementById("negative_cnt_"+reply_id).innerText;
                console.log(positiveCountValue);
                console.log(negativeCountValue);
                // 서버에서 응답받은 JSON 데이터를 확인하여 처리
                if (response.success) {
                    if (type === 'positive') {
                        // 좋아요
                        Swal.fire({
                            icon: 'success',
                            title: '해당 댓글을 "좋아요" 하셨습니다.',
                            showConfirmButton: true
                        }).then(function() {
                        	// 실제로 데이터를 가져와 표시하는 것은 아니지만, 실제 실행이 되었기 때문에 +1 하여 뿌려준다.
                        	var newCount = parseInt(positiveCountValue) + 1; // +1 증가시킴
                        	document.getElementById("positive_cnt_"+reply_id).innerText = newCount; 
                        });
                    } else if (type === 'negative') {
                        // 싫어요
                        Swal.fire({
                            icon: 'success',
                            title: '해당 댓글을  "싫어요" 하셨습니다.',
                            showConfirmButton: true
                        }).then(function() {
                        	var newCount = parseInt(negativeCountValue) + 1; // +1 증가시킴
                        	document.getElementById("negative_cnt_"+reply_id).innerText = newCount; 
                        });
                    }
                } else {
                    // 서버 오류 등의 경우 메시지 출력
                    Swal.fire({
                        icon: 'error',
                        title: '오류 발생',
                        text: '댓글 추천에 실패했습니다.',
                        showConfirmButton: true
                    });
                }
            },
            error: function() {
                // Ajax 요청 실패 시 처리
                Swal.fire({
                    icon: 'error',
                    title: '오류 발생',
                    text: '서버와의 통신에 실패했습니다.',
                    showConfirmButton: true
                });
            }
        });
    }
    
    // 로그인 하지 않은 경우에는 댓글작성 불가 - 로그인 화면 이동 알림창
    function checkLogin() {
    	Swal.fire({
    		  title: '회원만 댓글을 작성할 수 있습니다.',
    		  text: '로그인 화면으로 이동하시겠습니까?',
    		  showDenyButton: true,
    		  showCancelButton: true,
    		  confirmButtonText: '예',
    		  denyButtonText: '아니오',
    		}).then((result) => {
    		  if (result.isConfirmed) {
    			  window.location.href = '/login.do';
    		  } else if (result.isDenied) {
				  return;
    		  }
    		})
    }
</script>

<style>
	/* 유저 아이디, 닉네임의 글씨체를 변경하여 강조? */
    .reply_nick {
        font-weight: bold;
        font-size: 18px;
    }

    .reply_id {
        font-style: italic;
        font-size: 14px;
    }
    
    .comment-section {
        margin-bottom: 20px;
    }

	/* 작성시간과 수정시간의 글씨 크기를 12px로 설정 및 
	 글씨 색상을 회색으로 설정 */
    .reply_info {
        font-size: 12px; 
        color: #888; 
    }
    
	/* 댓글 작성자 정보와 댓글 내용 사이에 여백 추가 */
    .user_info {
        margin-bottom: 10px; 
    }
    
	/* 댓글 내용과 댓글 정보 사이에 여백 추가 */
    .reply_contents_wrap {
        margin-bottom: 15px; 
    }
    
    /* 우측 정렬을 위한 스타일 추가 (좋아요,싫어요 우측 정렬) */
    .recomm_set {
        display: flex;
        justify-content: flex-end; 
    }
    
</style>
<body>
<%@ include file="/header.html" %>


    <div class="container">
        <h2 class="my-4">게시물 정보 조회</h2>
        <table class="table table-bordered table-hover">
            <tr>
                <th style="width: 20%;">글 번호</th>
                <td>${data.board_id}</td>
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
                <td style="white-space: pre-wrap;">${data.content}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${data.reg_date}</td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${data.hit}</td>
            </tr>
            <tr>
                <th>수정일</th>
                <td>${data.update_date}</td>
            </tr>
        </table>
    </div>
    
    <div class="container mt-4 d-flex justify-content-end">
        <c:set var="page_no" value="${empty param.page_no?1:param.page_no}" />
        
        <a href="list.do?page_no=${page_no}&search_type=${search_type}&keyword=${keyword}&category_info=${category_info}" class="btn btn-secondary">목록보기</a>
        
        <%-- 수정 버튼 / 작성자만 수정권한  --%>
        <c:if test="${AUTH_USER.user_id == data.writer}">
            <form action="/hwet/article/modifyForm.do" method="post" class="ml-2">
                <!-- hidden 타입의 input 요소를 추가하여 기존 데이터를 전송 -->
                <input type="hidden" name="board_id" value="${data.board_id}">
                <input type="hidden" name="page_no" value="${page_no}">
                
                <button type="submit" class="btn btn-info">게시글 수정</button>
            </form>
        </c:if>
        
        <%-- 삭제 버튼 / 작성자와 관리자 삭제권한  --%>
        <c:if test="${fn:contains(AUTH_USER.user_id, 'admin') || AUTH_USER.user_id eq data.writer}">
	        <form id="deleteForm" action="/hwet/article/delete.do" method="post">
	            <input type="hidden" name="no" value="${data.board_id}">
	            <button type="submit" class="btn btn-danger ml-2" onclick="return confirmDelete()">게시글 삭제</button>
	        </form>
        </c:if>
    </div>
    
    <c:if test="${!empty AUTH_USER}">
		<!-- 댓글 작성 폼 -->
		<div class="container mt-4">
		    <h2><label for="reply_content">댓글 작성</label></h2>
		    <form id="reply_form" action="/hwet/article/addReply.do" method="post">
		        <input type="hidden" name="board_id" value="${data.board_id}">
		        <input type="hidden" name="user_id" value="${AUTH_USER.user_id}">
		        <input type="hidden" name="user_nickname" value="${AUTH_USER.user_nickname}">
		        <input type="hidden" name="page_no" value="${page_no}">
		        <input type="hidden" name="search_type" value="${search_type}">
		        <input type="hidden" name="keyword" value="${keyword}">
		        <input type="hidden" name="category_info" value="${category_info}">
		        
		        <div class="form-group">
		            <textarea class="form-control" id="reply_content" name="reply_content" rows="3"></textarea>
		        </div>
		        
		        <button type="button" class="btn btn-primary" onclick="submit_reply()">댓글 등록</button>
		    </form>
		</div>
	</c:if>
	
	<c:if test="${empty AUTH_USER}">
		<%-- 댓글을 사용할 수 없다는 문구와 로그인 버튼.. --%>
		<div class="container mb-4">
	    <h2><label for="reply_content">댓글 작성</label></h2>
	    
	    <form id="reply_form" action="#">
	        <div class="form-group">
	            <textarea class="form-control" id="reply_content" name="reply_content" rows="3" placeholder="댓글 기능은 로그인해야 사용 가능합니다." readonly></textarea>
	        </div>
	        
	        <button type="button" class="btn btn-primary" onclick="checkLogin()">댓글 등록</button>
    	</form>
		</div>
	</c:if>
	
	<c:if test="${!empty reply_data}">
	<c:forEach var="reply" items="${reply_data}">
	    <div class="container my-4">
	        <div class="row">
	            <div class="col-12">
	                <!-- 댓글 영역 -->
	                <div class="card">
	                    <div class="card-body">
	                        <!-- 댓글 작성자 정보 -->
	                        <div class="d-flex justify-content-between align-items-center">
	                            <span class="user_info">
	                                <span class="reply_nick">${reply.writer_nick}</span>
	                                <span class="reply_id">(${reply.writer})</span>
	                            </span>
	                            
	                        </div>
	                        <!-- 댓글 내용 -->
	                        <div class="reply_contents_wrap">
	                            <span class="reply_contents" style="" data-lang="ko">${reply.content}</span>
	                        </div>
	                        <!-- 댓글 정보 -->
	                        <div class="reply_info">
	                            <span class="reply_created">${reply.created_at}</span>
	                            <c:if test="${reply.updated_at != null}">
	                            	<span class="reply_updated">/ ${reply.updated_at}</span>
	                            </c:if>
	                        </div>
	                        <!-- 좋아요/싫어요 버튼 -->
							<div class="recomm_set">
							    <!-- 좋아요 버튼 -->
							    <a href="#" onclick="recommendReply('positive', ${data.board_id}, ${reply.reply_id})" class="btn btn-success btn_positive">
							        <span class="positive_icon">좋아요</span>
							        <em id="positive_cnt_${reply.reply_id}" class="positive_cnt">${reply.positive_count}</em>
							    </a>
							    <span>&nbsp;&nbsp;</span>
							    <!-- 싫어요 버튼 -->
							    <a href="#" onclick="recommendReply('negative', ${data.board_id}, ${reply.reply_id})" class="btn btn-danger btn_negative">
							        <span class="negative_icon">싫어요</span>
							        <em id="negative_cnt_${reply.reply_id}" class="negative_cnt">${reply.negative_count}</em>
							    </a>
							</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</c:forEach>
	</c:if>

<span class="mb-4"></span>
    
    <!-- Bootstrap JS 라이브러리 링크 (jQuery와 Popper.js를 포함) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
     
    
</body>
</html>
