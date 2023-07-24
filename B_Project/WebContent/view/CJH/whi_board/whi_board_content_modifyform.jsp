<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int articleNo = Integer.parseInt(request.getParameter("articleNo"));
String title = request.getParameter("title");
String content = request.getParameter("content");
%>

<form action="/CJH/whi_board/modify.do">
글제목<input type="hidden" name ="articleNo" value="<%=articleNo %>">
내용<input type="text" name ="title" width="700px" value="<%=title%>">
<hr>
<textarea name ="content" rows="20" cols="10" ><%=content%></textarea>


<button type="submit">글수정완료</button>
</form>

</body>
</html>