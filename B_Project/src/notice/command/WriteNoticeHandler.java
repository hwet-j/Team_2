package notice.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.model.Writer;
import notice.service.NoticeService;
import notice.service.WriteRequest;



//요청주소 notice/write.do
//쓰기요청을 담당하는 컨트롤러


public class WriteNoticeHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/notice/newNoticeForm.jsp";	
	//private WriteArticleService writeService = new WriteArticleService(); 
		private NoticeService noticeService = new NoticeService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면?
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	}//process
	
	//글등록폼 보여주기
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			MemberDTO user = (MemberDTO)session.getAttribute("AUTH_USER");
			session.setAttribute("AUTH_USER", user);
			return request.getContextPath()+FORM_VIEW;
		}
	
	
		//글등록처리
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//에러정보가 담기는 Map
			Map<String, Boolean> errors = new HashMap<String, Boolean>();
			request.setAttribute("errors",errors);
			
			//1.파라미터받기
			//원래는 로그인을 한 후 글등록작업해야한다.
			
			MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER");  //안돼면 주석처리
			
			//리턴  WriteRequest - 작성정보(여기에서는 session에  담긴 회원id),제목,내용을 WriteRequest객체로 생성*/
			WriteRequest writeRequest = createWriteRequest(user,request); //안돼면 member 지우고 하기
			writeRequest.validate(errors); //필수입력검사
			
			if(!errors.isEmpty()) { //에러가 있으면  FORM_VIEW로 이동
				return request.getContextPath()+FORM_VIEW;
			}
			
			//2.비즈니스로직<->Service<->DAO<->DB 
			//파라미터 WriteRequest - 작성정보(여기에서는 session에  담긴 회원id),제목,내용을 WriteRequest객체로 생성*/
			//리턴 int - 입력된 글번호->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용	
	
			int notice_no = noticeService.write(writeRequest);
			
			//3.Model- request또는session.setAttribute("속성명",Object 값);
			request.setAttribute("notice_no", notice_no);
			
			
			//4.View - 성공시:목록페이지이동 
			//실패시 FORM_view 이동
			
			response.sendRedirect( request.getContextPath()+"/notice/list.do");
			return null;
			
			
			
		}
		
		//p641 53라인
		/*파라미터
		 * User user-작성자정보(여기에서는 session에 담긴 회원id, 회원name)
		 * request - 글등록폼에서 넘어온 parameter를 받기 위함
		 * 리턴
		 * WriteRequest - 작성정보(여기에서는 session에 담긴 회원id, 회원name), 제목,내용을  WriteRequest객체로 생성*/
		private WriteRequest createWriteRequest(MemberDTO user, HttpServletRequest request) {
			//작성자정보
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");		
			
			return  new WriteRequest(
					new Writer(user.getUser_id()),
					title,
					content
				);
		}		
			
} 
