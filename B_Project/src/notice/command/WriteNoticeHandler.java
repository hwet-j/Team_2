//package notice.command;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import mvc.command.CommandHandler;
//
//
////요청주소 notice/writeNotice
////쓰기요청을 담당하는 컨트롤러
//=notice.command.WriteNoticeHandler
//
//public class WriteNoticeHandler implements CommandHandler {
//
//	private static final String FORM_VIEW = "/view/article/newArticleForm.jsp";	
//	//private WriteArticleService writeService = new WriteArticleService(); 
//		private 	WriteNoticeService writeNoticeService = new WriteNoticeService();
//	@Override
//	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("WriteArticleHandler의 process()진입");
//		System.out.println("request.getMethod()="+request.getMethod());
//		
//		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
//			return processForm(request,response);
//		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
//			return processSubmit(request,response); //p607 25라인
//		}else {
//			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
//			return null;
//		}
//	}//process
//	
//	//글등록폼 보여주기-p641 31라인
//		private String processForm(HttpServletRequest request, HttpServletResponse response) {
//			return request.getContextPath()+FORM_VIEW;
//		}
//	
//		//로그인처리-p641 36라인
//		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			//에러정보가 담기는 Map- p641 36라인
//			Map<String, Boolean> errors = new HashMap<String, Boolean>();
//			request.setAttribute("errors",errors);
//			
//			//원래는 로그인을 한 후 글등록작업해야한다.
//			User user = (User)request.getSession(false).getAttribute("AUTH_USER");
//			
//			//1.파라미터받기
//			request.setCharacterEncoding("UTF-8");
//			String id = request.getParameter("id");
//			String name = request.getParameter("name");
//			String title =request.getParameter("title");
//			String regdate = request.getParameter("Regdate");
//			String moddate = request.getParameter("Moddate");
//			String content =request.getParameter("content");
//			Notice notice = new Notice(id, name, title, regdate, moddate, content);
//			System.out.println("write handler="+notice.toString());
//			
//			//2.비즈니스로직<->Service<->DAO<->DB 
//			WriteNoticeService writeNoticeService = new WriteNoticeService();
//			int writeCnt = writeNoticeService.writeNotice(notice);
//			
//			//4.View - 성공시:/view/newindex.jsp이동 
//			//실패시 FORM_view 이동
//			if(writeCnt!=2) {
//				return request.getContextPath()+"/view/notice/writeFail.jsp";
//			}else {
//				response.sendRedirect( request.getContextPath()+"/notice/showNotice.do");
//			}
//			
//			
//			
//			
//			//리턴
//			WriteRequest writeRequest=createWriteRequest(user,request);
//			writeRequest.validate(errors);//필수입력검사
//	
//
//		//p641 43라인
//		if(!errors.isEmpty()) { //에러가 있으면  로그인폼페이지로 이동
//			return request.getContextPath()+FORM_VIEW;
//		}
//		
//		//p641 47라인
//		
//			//2.비즈니스로직<->Service<->DAO<->DB 
//			//파라미터 String memberid, String password-유저가 입력한 id,비번
//			//리턴  Int - 입력된 글번호->교재에서는 글등록성공시 글등록축하.jsp에서 글상세보기
//			int newArticleNo=writeService.write(writeRequest);
//						
//			//3.Model- request또는session.setAttribute("속성명",Object 값);
//			request.setAttribute("newArticleNo", newArticleNo);
//			//4.View - 성공시:/view/newindex.jsp이동 
//			//실패시 FORM_view 이동
//			return request.getContextPath()+"/view/article/newArticleSuccess.jsp";
//			
//		}
//		//p641 53라인
//		/*파라미터
//		 * User user-작성자정보(여기에서는 session에 담긴 회원id, 회원name)
//		 * request - 글등록폼에서 넘어온 parameter를 받기 위함
//		 * 리턴
//		 * WriteRequest - 작성정보(여기에서는 session에 담긴 회원id, 회원name), 제목,내용을  WriteRequest객체로 
//	@Override
//	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("UTF-8");
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		String title =request.getParameter("title");
//		String regdate = request.getParameter("Regdate");
//		String moddate = request.getParameter("Moddate");
//		String content =request.getParameter("content");
//		Notice notice = new Notice(id, name, title, regdate, moddate, content);
//		System.out.println("write handler="+notice.toString());
//		WriteNoticeService writeNoticeService = new WriteNoticeService();
//		int writeCnt = writeNoticeService.writeNotice(notice);
//		
//		if(writeCnt!=2) {
//			return request.getContextPath()+"/view/notice/writeFail.jsp";
//		}else {
//			return request.getContextPath()+"/notice/showNotice.do";
//		}
//		
//	}
//
//}