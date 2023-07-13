<%@page import="member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
String userid = request.getParameter("userid");
String username = request.getParameter("username");
String usertlno = request.getParameter("tlno");
String message = "";

MemberDAO memDAO = new MemberDAO();
 
if (memDAO.idDuplicateCheck(userid)) {
    message = "id_duplicate";
} else {
	if (memDAO.nicknameDuplicateCheck(username)){
		message = "nickname_duplicate";
	} else {
		if (memDAO.tlnoDuplicateCheck(usertlno)){
			message = "tlno_duplicate";
		} else {
			message = "not_duplicate";
		}
	}
}
 
// 클라이언트에게 응답 전송
response.setContentType("text/plain");
response.setCharacterEncoding("UTF-8");
// 개행 (줄바꿈) 데이터가 함께 전송되는 문제가 발생함 -> ajax에서 해결함
response.getWriter().write(message);
%> 