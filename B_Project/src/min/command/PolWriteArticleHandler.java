package min.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.model.PolWriter;
import min.service.PolWriteArticleService;
import min.service.PolWriteRequest;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

public class PolWriteArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/min/newPolArticleForm.jsp";
	private PolWriteArticleService polWriteArticleService  = new PolWriteArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); //p607 25라인
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}

	}//process

	//글등록폼을 보여주기-p641 31라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}
	
	//글등록처리-p641 35라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		//에러정보가 담기는 Map- p641 36라인
		Map<String, Boolean> errors =new HashMap<String, Boolean>();		
		request.setAttribute("errors", errors);
		
		//1.파라미터받기
	//원래는 로그인을 한 후 글등록작업해야한다.p641 39라인
		MemberDTO user = (MemberDTO)request.getSession(false).getAttribute("AUTH_USER");
		//★★★★★테스용 삭제?   ▲//DTO형변환, 로그인핸들러에서 선언한 것, 오브젝트형식인데 DTO로 변환해줌
		
		
		//리턴 WriteRequest
		//리턴  WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
		PolWriteRequest polWriteRequest = createWriteRequst(user, request); 
		polWriteRequest.validate(errors);
		
		//p641 43라인
		if(!errors.isEmpty()) {
			return request.getContextPath()+FORM_VIEW;
		}
		
		//p641 47라인	
		//2.비즈니스로직<->Service<->DAO<->DB 
		//파라미터 WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
		//리턴 int - 입력된 글번호->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용	
				
		int newPolArticleNo = polWriteArticleService.write(polWriteRequest);
		
		//3.Model- request또는session.setAttribute("속성명",Object 값);
		request.setAttribute("newPolArticleNo", newPolArticleNo);
		
		//4.View - 성공시(교재):/view/article/newArticleSuccess.jsp이동
		//실패시 FORM_VIEW 이동
		return request.getContextPath()+"/view/min/newPolArticleSuccess.jsp";
				
	}
	
	//p641 53라인
	/*파라미터 
	   User user - 작성자정보(여기에서는 session에  담긴 회원id, 회원name)
	   request	 - 글등록폼에서 넘어온 parameter를 받기 위함	
	 * 리턴
	   WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
	private PolWriteRequest createWriteRequst(MemberDTO user, HttpServletRequest request) {
		//작성자정보(여기에서는 session에  담긴 회원id, 회원name)를 Writer객체로 생성
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//String memberNick = request.getParameter("memberNick");//작성자nick
		return new PolWriteRequest(new PolWriter(user.getUser_id(), user.getUser_nickname()), title, content);
		
	}
}
