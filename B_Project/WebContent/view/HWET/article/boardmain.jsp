<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%
    HttpServletRequest req = (HttpServletRequest) request;
    String contextPath = req.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리 페이지</title>

<script>
    function showAlert() {
        alert("로그인 후에 사용할 수 있습니다.");
        window.location.href = "<%= request.getContextPath() %>/login.do";
    }
</script>

<%
    String contextPa = request.getContextPath();
    pageContext.setAttribute("contextPath", contextPa);
%>

</head>
<body>

	<h2>게시판 관리 메인</h2>
		<ul>
			<% if (session.getAttribute("login_user") == null) { %>
		    <li><a href="#" onclick="showAlert();">등록하기</a></li>
			<% } else { %>
		    <li><a href="<%= request.getContextPath() %>/view/HWET/article/registForm.jsp">등록하기</a></li>
			<% } %>
			<li><a href="list.jsp">목록보기</a></li>
		</ul>
		
</body>
</html>