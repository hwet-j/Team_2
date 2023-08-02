<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="notice.dao.NoticeDTO" %>
 <%@page import="java.util.ArrayList" %>
 <%@page import="notice.dao.NoticeDAO" %>
 <%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "/header.html" %>

<%
			NoticeDAO noticeDAO = new NoticeDAO();
			ArrayList<NoticeDTO> dtos = noticeDAO.noticeSelect();
			
			for(int i=0; i<dtos.size(); i++) {
				NoticeDTO dto = dtos.get(i);
				int notice_no = dto.getNotice_no();
				String writer_id = dto.getWriter_id();
				String title = dto.getTitle();
				String content = dto.getContent();
				Date writedate = dto.getWritedate();
				int views = dto.getViews();
				
				out.println("글번호 : " + notice_no + ", 아이디 : " + writer_id + ", 제목 : " + title + ", 내용 :" + content + ", 작성일 :" + writedate + ", 조회수 : " + views + "<br />" );
			}
			%>
			
			
			
			
<%@ include file = "/footer.html" %>
</body>
</html>