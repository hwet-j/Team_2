<%-- 
<%@page import="user.MyUserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
String username = request.getParameter("userid");
String message = "";

MyUserDAO userDAO = new MyUserDAO();
boolean isDuplicate = userDAO.isUsernameDuplicate(username);

if (isDuplicate) {
    message = "duplicate";
} else {
    message = "not_duplicate";
}
 
// 클라이언트에게 응답 전송
response.setContentType("text/plain");
response.setCharacterEncoding("UTF-8");
// 개행 (줄바꿈) 데이터가 함께 전송되는 문제가 발생함 -> ajax에서 해결함
response.getWriter().write(message);
%> --%>