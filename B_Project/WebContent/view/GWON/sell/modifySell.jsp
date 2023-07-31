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
#floating-button {
	margin : 2px;
}
</style>

</head>
<body>
<%@ include file = "../../../header.html" %>

 <div class="container">
	<h2  class="" align = "center">중고거래 글수정하기</h2>
	<form action="/gwon/sell/sellModify.do" method="post" enctype="multipart/form-data">
	 <div class="">
	   <label for="sell_no" class="form-label">글 번호</label>
	   <input type="text" name="sell_no" id="sell_no" class="form-control" value="${modRequest.sell_no}" readonly="readonly" />
	  </div>
	 <div class="">
	   <label for="user_id" class="form-label">작성자</label>
	   <input type="text" name="user_id" id="user_id" class="form-control" value="${modRequest.user_id}" readonly="readonly"/>
	  </div>
	 <div class="">
	 	<label for="sell_title" class="form-label" >글 제목</label>
	 	<input type="text" name="sell_title" id="sell_title" class="form-control" value="${modRequest.sell_title}"  required/>
	 </div>
	 <div class="">
	 	<label for="sell_category"  class="form-label">카테고리</label>
	  	<select name = "sell_category" id = "sell_category" class="form-control"  size = "1"   required>
	  		<option selected >${modRequest.sell_category}</option>
	  		<option value = "생활용품"	  ${data.category == '생활용품' ? 'selected' : ''}>생활용품</option>
	  		<option value = "가구/가전"   ${data.category == '가구/가전' ? 'selected' : ''}>가구/가전</option>
	  		<option value = "의류"	      ${data.category == '의류' ? 'selected' : ''}>의류</option>
	  		<option value = "쿠폰/상품권" ${data.category == '쿠폰/상품권' ? 'selected' : ''}>쿠폰/상품권</option>
	  		<option value = "기타"        ${data.category == '기타' ? 'selected' : ''}>기타</option>
	  	</select>
	 </div>
	 <div class="">
	 	<label for="sell_price" class="form-label">희망가격</label>
	 	<input type="number" name="sell_price" id="sell_price" class="form-control" min = "1" value="${modRequest.sell_price}"  required/>
	 </div>
	 <div class="">
	  	<label for="sell_loc"  class="form-label">판매장소</label>
	    <select size = "1" name = "sell_loc" id = "sell_loc" class="form-control"  required >
	    	<option selected >${modRequest.sell_loc}</option>
	  		<option value = "서울" ${data.category == '서울' ? 'selected' : ''}>서울</option>
	  		<option value = "경기" ${data.category == '경기' ? 'selected' : ''}>경기</option>
	  		<option value = "충북" ${data.category == '충북' ? 'selected' : ''}>충북</option>
	  		<option value = "충남" ${data.category == '충남' ? 'selected' : ''}>충남</option>
	  		<option value = "강원" ${data.category == '강원' ? 'selected' : ''}>강원</option>
	  		<option value = "전북" ${data.category == '전북' ? 'selected' : ''}>전북</option>
	  		<option value = "전남" ${data.category == '전남' ? 'selected' : ''}>전남</option>
	  		<option value = "경북" ${data.category == '경북' ? 'selected' : ''}>경북</option>
	  		<option value = "경남" ${data.category == '경남' ? 'selected' : ''}>경남</option>
	  		<option value = "제주" ${data.category == '제주' ? 'selected' : ''}>제주</option>
	  	</select>
	 </div>
	  
	 <div class="">
	 	<label for="sell_content" class="form-label">내용</label>
	 	<textarea rows="5" name="sell_content" id="sell_content" class="form-control">${modRequest.sell_content}"</textarea>
	 </div>
	<%--  <div class="">
	 	<label for="sell_file" class="form-label">파일 다운로드</label>
	 	<input type="text" name="sell_file" id="sell_file" class="form-control" value="${modRequest.sell_file}"/>
	 </div> --%>
	 <%-- 	 <c:if test="${not empty sellDTO.sell_file && sellDTO.sell_file = null}">
	  <tr> --%>
	  <th class ="col-9">
	   	  <input type="hidden" name="originalSell_file" id="originalSell_file" value="${sellDTO.sell_file}" />
	   	  <img src= "<%=request.getContextPath() %>/gwon/sell/sellDownload.do?fileNo=${sellDTO.sell_no}&sell_file=${sellDTO.sell_file}"  id="preview" style="width:250px;"/>
	    </th>
	  </tr>
	  <tr>
	   <th class="col-3"><label for="sell_file">파일 수정</label></th>
	   <td><input type="file" name="sell_file" id="sell_file" onchange="readURL(this);"/></td>
	  </tr> 
<%-- 	 </c:if> --%>
	 
	<div class="">
     <button type="submit" id = "floating-button" class= "btn btn-success" style = "float : right;">수정하기</button>
     <button type="reset"  id = "floating-button" class= "btn btn-secondary" style = "float : right;">새로고침</button>
     <a id = "floating-button" class="btn btn-secondary" href="/gwon/sell/sellRead.do?no=${modRequest.sell_no}" style = "float: right; "role="button">취소</a>
     <a id = "floating-button" class="btn btn-secondary" href="/gwon/sell/sellList.do" style = "float: right; "role="button">목록</a>
	</div>
	</form>
 </div>
 	
 	       	
</body>
</html>