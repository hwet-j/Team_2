<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/view/HWET/logintest1.jsp">1번</a>
<a href="<%=request.getContextPath() %>/view/HWET/logintest2.jsp">2번</a>
<a href="<%=request.getContextPath() %>/view/HWET/logintest3.jsp">3번</a>
<br><hr/>
<c:set var="user_data" value="${sessionScope.AUTH_USER}" />
  사용자 ID: ${sessionScope.AUTH_USER.userId}<br/>
  사용자 이름: ${sessionScope.AUTH_USER.userName}<br/>
  사용자 ID: ${AUTH_USER.userId}<br/>
  사용자 이름: ${AUTH_USER.userName}<br/>
<c:if test="${not empty user_data}">
  사용자 ID: ${user_data.userId}<br/>
  사용자 이름: ${user_data.userName}<br/>
  <!-- 다른 user_data 속성 사용 예시... -->
</c:if>

<!-- 세션에 AUTH_USER가 없을 경우 -->
<c:if test="${empty user_data}">
  세션에 사용자 데이터가 없습니다.
</c:if>

bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
</body>
</html>