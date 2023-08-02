<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
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

.container-items {
  height: 350px; /* 원하는 높이 값으로 설정 */
  overflow-y: auto; /* 내용이 넘칠 경우 스크롤 표시 */
}

th {
  white-space: normal;
}

 th:nth-child(1) {
  width: 10%;
}

/* 두 번째 <th> 셀 (제목)의 너비를 60%로 설정 */
th:nth-child(2) {
  width: 80%;
}

/* 세 번째 <th> 셀 (Id)의 너비를 20%로 설정 */
th:nth-child(3) {
  width: 10%;
}
</style>

</head>
<body>
<%@ include file="/header.html" %>
<div class="position-relative overflow-hidden p-3 m-md-3 text-center bg-light">
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal">모두의 게시판</h1>
    <p class="lead font-weight-normal">중앙정보처리학원 B조의 게시판으로 초대합니다</p>
    <a class="btn btn-outline-secondary" href="/notice/list.do">공지사항</a>
    <a class="btn btn-outline-secondary" href="/chat/showRoom.do">채팅(회원 전용)</a>
  </div>
  <div class="product-device shadow-sm d-none d-md-block"></div>
  <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
</div>





<div class="d-md-flex flex-md-equal justify-content-center w-100 my-md-3 pl-md-3"> 
  <div class="flex-fill mr-md-3 pt-3 px-3 text-center text-white overflow-hidden col-6" style="background-color: #add8e6;">
    <div class="my-3 py-3">
      <h2 class="display-5">정치 게시판</h2>
      <a class="btn btn-outline-secondary" href="/min/article/polList.do">더보기</a>
    </div>
    <div class = "container">
  <div class = "container-items" >
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">No</th>
		   <th scope="col">제목</th>
		   <th scope="col">Id</th>
		  </tr>
		 </thead>
		<tbody>
          <%  
            try {
            	String jdbcUrl="jdbc:mysql://152.67.216.37:3306/board";
            	String dbUser="scott";
            	String dbPass="Qnfl1234.";
            	Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
              
              // SQL 쿼리 실행
              String sql = "SELECT * FROM min_pol order by pol_no desc limit 0,3;";
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery();

              // 결과를 테이블에 출력
              while (rs.next()) {
                int postId = rs.getInt("pol_no");
                String title = rs.getString("title");
                String authorId = rs.getString("user_id");

                // 테이블 행 출력
                out.println("<tr>");
                out.println("<th scope=\"row\">" + postId + "</th>");
                out.println("<th scope=\"col\"><a href=\"/min/article/polread.do?no="+postId+"\">" + title + "</a></th>");
                out.println("<th scope=\"col\">" + authorId + "</th>");
                out.println("</tr>");
              }

              // 연결 종료
              rs.close();
              stmt.close();
              conn.close();

            } catch (Exception e) {
              e.printStackTrace();
            }
          %>
        </tbody>
	</table>
  </div>
</div>
  </div>
  
  
  
  
  
  
  
  <div class="flex-fill bg-light mr-md-3 pt-3 px-3 text-center overflow-hidden col-6">
    <div class="my-3 p-3">
      <h2 class="display-5">위스키 게시판</h2>
      <a class="btn btn-outline-secondary" href="/CJH/whi_board/list.do?pageNo=1">더보기</a>
    </div>
    <div class = "container">
  <div class = "container-items" >
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">No</th>
		   <th scope="col">제목</th>
		   <th scope="col">Id</th>
		  </tr>
		 </thead>
		  <tbody>
          <%  
            try {
            	String jdbcUrl="jdbc:mysql://152.67.216.37:3306/board";
            	String dbUser="scott";
            	String dbPass="Qnfl1234.";
            	Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
              
              // SQL 쿼리 실행
              String sql = "SELECT * FROM whi_board order by article_no desc limit 0,3;";
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery();

              // 결과를 테이블에 출력
              while (rs.next()) {
                int postId = rs.getInt("article_no");
                String title = rs.getString("title");
                String authorId = rs.getString("USER_ID");

                // 테이블 행 출력
                out.println("<tr>");
                out.println("<th scope=\"row\">" + postId + "</th>");
                out.println("<th scope=\"col\"><a href=\"/CJH/whi_board/content.do?articleNo="+postId+"\">" + title + "</a></th>");
                out.println("<th scope=\"col\">" + authorId + "</th>");
                out.println("</tr>");
              }

              // 연결 종료
              rs.close();
              stmt.close();
              conn.close();

            } catch (Exception e) {
              e.printStackTrace();
            }
          %>
        </tbody>
	</table>
  </div>
 </div>
  </div>
  
  
  
  
  
  
  
   <div class=" flex-fill mr-md-3 pt-3 px-3 text-center text-white overflow-hidden col-6" style="background-color: #add8e6;">
    <div class="my-3 py-3">
      <h2 class="display-5">동물   게시판</h2>
      <a class="btn btn-outline-secondary" href="/Angel/article.do?pageNo=1">더보기</a>
    </div>
    <div class = "container">
  <div class = "container-items" >
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">No</th>
		   <th scope="col">제목</th>
		   <th scope="col">Id</th>
		  </tr>
		 </thead>
		 <tbody>
          <%  
            try {
            	String jdbcUrl="jdbc:mysql://152.67.216.37:3306/board";
            	String dbUser="scott";
            	String dbPass="Qnfl1234.";
            	Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
              
              // SQL 쿼리 실행
              String sql = "SELECT * FROM angel_animaltable order by articleNo desc limit 0,3;";
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery();

              // 결과를 테이블에 출력
              while (rs.next()) {
                int postId = rs.getInt("articleNo");
                String title = rs.getString("title");
                String authorId = rs.getString("memberid");

                // 테이블 행 출력
                out.println("<tr>");
                out.println("<th scope=\"row\">" + postId + "</th>");
                out.println("<th scope=\"col\"><a href=\"/Angel/detail.do?articleNo="+postId+"\">" + title + "</a></th>");
                out.println("<th scope=\"col\">" + authorId + "</th>");
                out.println("</tr>");
              }

              // 연결 종료
              rs.close();
              stmt.close();
              conn.close();

            } catch (Exception e) {
              e.printStackTrace();
            }
          %>
        </tbody>
	</table>
  </div>
</div>
    </div>
  </div>







<div class="d-md-flex flex-md-equal justify-content-center w-100 my-md-3 pl-md-3" >
  <div class="flex-fill bg-light mr-md-3 pt-3 px-3 text-center overflow-hidden col-6">
    <div class="my-3 p-3">
      <h2 class="display-5">중고 게시판</h2>
      <a class="btn btn-outline-secondary" href="/gwon/sell/sellList.do">더보기</a>
    </div>
    <div class = "container">
  <div class = "container-items" >
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">No</th>
		   <th scope="col">제목</th>
		   <th scope="col">Id</th>
		  </tr>
		 </thead>
		  <tbody>
          <%  
            try {
            	String jdbcUrl="jdbc:mysql://152.67.216.37:3306/board";
            	String dbUser="scott";
            	String dbPass="Qnfl1234.";
            	Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
              
              // SQL 쿼리 실행
              String sql = "SELECT * FROM gwon_sell order by sell_no desc limit 0,3;";
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery();

              // 결과를 테이블에 출력
              while (rs.next()) {
                int postId = rs.getInt("sell_no");
                String title = rs.getString("sell_title");
                String authorId = rs.getString("user_id");

                // 테이블 행 출력
                out.println("<tr>");
                out.println("<th scope=\"row\">" + postId + "</th>");
                out.println("<th scope=\"col\"><a href=\"/gwon/sell/sellRead.do?no="+postId+"\">" + title + "</a></th>");
                out.println("<th scope=\"col\">" + authorId + "</th>");
                out.println("</tr>");
              }

              // 연결 종료
              rs.close();
              stmt.close();
              conn.close();

            } catch (Exception e) {
              e.printStackTrace();
            }
          %>
        </tbody>
	</table>
  </div>
</div>
    
  </div>
  
  
  
  
  
  
  <div class="flex-fill mr-md-3 pt-3 px-3 text-center text-white overflow-hidden col-6" style="background-color: #add8e6;">
    <div class="my-3 py-3">
      <h2 class="display-5">HWET  게시판</h2>
      <a class="btn btn-outline-secondary" href="/hwet/article/list.do">더보기</a>
    </div>
    <div class = "container">
	<div class = "container-items" >
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">No</th>
		   <th scope="col">제목</th>
		   <th scope="col">Id</th>
		  </tr>
		 </thead>
		 <tbody>
          <%  
            try {
            	String jdbcUrl="jdbc:mysql://152.67.216.37:3306/board";
            	String dbUser="scott";
            	String dbPass="Qnfl1234.";
            	Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
              
              // SQL 쿼리 실행
              String sql = "SELECT * FROM hwet_board order by board_id desc limit 0,3;";
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery();

              // 결과를 테이블에 출력
              while (rs.next()) {
                int postId = rs.getInt("board_id");
                String title = rs.getString("title");
                String authorId = rs.getString("writer");

                // 테이블 행 출력
                out.println("<tr>");
                out.println("<th scope=\"row\">" + postId + "</th>");
                out.println("<th scope=\"col\"><a href=\"/hwet/article/read.do?no=43&page_no="+postId+"\">" + title + "</a></th>");
                out.println("<th scope=\"col\">" + authorId + "</th>");
                out.println("</tr>");
              }

              // 연결 종료
              rs.close();
              stmt.close();
              conn.close();

            } catch (Exception e) {
              e.printStackTrace();
            }
          %>
        </tbody>
	</table>
  </div>
</div>
  </div>
  
  
  
  
  
    <div class="flex-fill bg-light mr-md-3 pt-3 px-3 text-center overflow-hidden col-6">
    <div class="my-3 p-3">
      <h2 class="display-5">신고 게시판</h2>
      <a class="btn btn-outline-secondary" href="/tak/article/list.do">더보기</a>
    </div>
     <div class = "container"> 
	<div class = "container-items">
	  <table class="table table-hover">
		<thead>
		  <tr >
		   <th scope="col">No</th>
		   <th scope="col">제목</th>
		   <th scope="col">Id</th>
		  </tr>
		 </thead>
		 <%  
            try {
            	String jdbcUrl="jdbc:mysql://152.67.216.37:3306/board";
            	String dbUser="scott";
            	String dbPass="Qnfl1234.";
            	Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
              
              // SQL 쿼리 실행
              String sql = "SELECT * FROM tak_board order by article_no desc limit 0,3;";
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery();

              // 결과를 테이블에 출력
              while (rs.next()) {
                int postId = rs.getInt("article_no");
                String title = rs.getString("title");
                String authorId = rs.getString("writer_id");

                // 테이블 행 출력
                out.println("<tr>");
                out.println("<th scope=\"row\">" + postId + "</th>");
                out.println("<th scope=\"col\"><a href=\"/tak/article/read.do?no="+postId+"\">" + title + "</a></th>");
                out.println("<th scope=\"col\">" + authorId + "</th>");
                out.println("</tr>");
              }

              // 연결 종료
              rs.close();
              stmt.close();
              conn.close();

            } catch (Exception e) {
              e.printStackTrace();
            }
          %>
        </tbody>
	</table>
  </div>
</div>
  </div>
</div>
<%@ include file="/footer.html" %>

</body>

<!-- 부트스트랩 JS 파일들 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>
