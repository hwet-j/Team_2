<<<<<<< HEAD
<%@ tag body-content="empty" pageEncoding="utf-8"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%
value = value.replace("\n", "\n<br>");
value = value.replace(" ", "&nbsp;");
%>
=======
<%@ tag body-content="empty" pageEncoding="utf-8"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%
value = value.replace("\n", "\n<br>");
value = value.replace(" ", "&nbsp;");
%>
>>>>>>> branch 'pj4' of https://github.com/choongangJinho/Team_2.git
<%=value %>