<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- import하자. -->
    <%@ page import="com.oreilly.servlet.*" %>
    <%@ page import="com.oreilly.servlet.multipart.*" %>
    <%@ page import="java.util.*" %>
    
<%
request.setCharacterEncoding("UTF-8");	//	한글이 깨질 수 있으므로 추가

//		데이터를 저장할 경로 정보 확인
//	real path를 알아내기 위해 선언
ServletContext context = getServletContext();
String realPath = context.getRealPath("img");
//	String realPath = context.getRealPath("");


//		파일을 업로드 할 때, 필요한 정보
String filename1 = "";			//	파일 이름
String filename2 = "";			//	파일 이름
String filename3 = "";			//	파일 이름

int maxSize = 1024 * 1024 * 5;	//	5MegaByte크기를 최대 사이즈로 설정
String fullfilename = "";		//	전체 경로 포함 파일 이름
MultipartRequest multi;
try{
	multi = new MultipartRequest(request,realPath,
			maxSize,"UTF-8",
			new DefaultFileRenamePolicy()	//	제약 사항(파일의 이름이 중복 시, 이름을 재정의 하거나) 등을 처리할 때, 필요
			);
//	Form에서 전송된 파일의 정보를 가져오는 작업
Enumeration<?> files = multi.getFileNames();	//	파일이 여러 개 넘어올 수 있다.
filename1 = multi.getFilesystemName((String)files.nextElement());
filename2 = multi.getFilesystemName((String)files.nextElement());
filename3 = multi.getFilesystemName((String)files.nextElement());



//	realPath + filename 결합하기
fullfilename = realPath + "\\" +filename1;

}catch(Exception e){}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img src="/img/<%=filename1%>" width=300 height=200></img>
	<img src="/img/<%=filename2%>" width=300 height=200></img>
	<img src="/img/<%=filename3%>" width=300 height=200></img>
</body>
</html>