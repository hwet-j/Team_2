<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 목록</title>
</head>
<body>
    <h2>글 목록</h2>
    <table border = "1">
    <caption>리스트</caption>
	    <colgroup>
		    <col style="width:10%;">
		    <col style="width:10%;">
		    <col style="width:10%;">
		    <col style="width:30%;">
		    <col style="width:20%;">
		    <col style="width:20%;">
		</colgroup>
        <thead>
            <tr>
                <th>번호 board_id</th>
                <th>제목 title</th>
                <th>내용 content</th>
                <th>카테고리 category</th>
                <th>작성자 writer</th>
                <th>조회수 hit</th>
                <th>작성일 regdate</th>
            </tr>
        </thead>
        <tbody>
        	<tr>
        		<td>1</td>
        		<td>1</td>
        		<td>1</td>
        		<td>1</td>
        		<td>1</td>
        		<td>1</td>
                <td>
	                <form action="show.jsp" method="post">
		                <input type="hidden" name="b_no" value="">
		                <input type="submit" value="자세히보기">
	            	</form>
            	</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
 <%-- <% for (MyBoardDTO board : boardList) { %>
            <tr>
	            <% String content = board.getContent();
	               int maxLength = 10; // 제목의 최대 길이 지정
	               if (content.length() > maxLength) {
	            	   content = content.substring(0, maxLength) + "..."; // 일부분만 출력하고 생략 부호 추가
	               }
	            %>
                <td><%= board.getBoardId() %></td>
                <td><%= board.getTitle() %></td>
                <td><%= content %></td>
                <td><%= board.getWriter() %></td>
                <td><%= board.getRegDate() %></td> 
            <% } %>--%>
