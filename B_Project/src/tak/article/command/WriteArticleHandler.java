package tak.article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tak.article.model.Writer;
import tak.article.service.WriteArticleService;
import tak.article.service.WriteRequest;
import auth.service.LoginFailException;
import auth.service.User;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

//p640
//요청주소 http://localhost/article/write.do이면   호출되는  핸들러(담당컨트롤러)이다. 
//모든 핸들러(담당컨트롤러)는 반드시 interface CommandHandler를 구현해야 한다

public class WriteArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/article/newArticleForm.jsp";
	private WriteArticleService writeService = new WriteArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteArticleHandler의 process()진입");
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
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
		System.out.println("processForm()");
		return request.getContextPath()+FORM_VIEW;
	}
	
	//글등록처리-p641 35라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//에러정보가 담기는 Map- p641 36라인
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);

		//1.파라미터받기
		
		//원래는 로그인을 한 후 글등록작업해야한다.p641 39라인
		MemberDTO user = (MemberDTO)request.getSession(false).getAttribute("AUTH_USER");
		
	   //리턴  WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
		WriteRequest writeRequest = createWriteRequst(user, request);//p641 40라인
		writeRequest.validate(errors); //필수입력검사

		//p641 43라인
		if(!errors.isEmpty()) { //에러가 있으면  로그인폼페이지로 이동
			return request.getContextPath()+FORM_VIEW;
		}

		//p641 47라인	
		//2.비즈니스로직<->Service<->DAO<->DB 
		//파라미터 WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
		//리턴 int - 입력된 글번호->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용	
		int newArticleNo = writeService.write(writeRequest);
		
		//3.Model- request또는session.setAttribute("속성명",Object 값);
		request.setAttribute("newArticleNo", newArticleNo);
		
		//4.View - 성공시(교재):/view/article/newArticleSuccess.jsp이동
		//실패시 FORM_VIEW 이동
		return request.getContextPath()+"/view/article/newArticleSuccess.jsp";
		
	}

	//p641 53라인
	/*파라미터 
	   User user - 작성자정보(여기에서는 session에  담긴 회원id, 회원name)
	   request	 - 글등록폼에서 넘어온 parameter를 받기 위함	
	 * 리턴
	   WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
	private WriteRequest createWriteRequst(User user, HttpServletRequest request) {
		//작성자정보(여기에서는 session에  담긴 회원id, 회원name)를 Writer객체로 생성

		String title = request.getParameter("title"); //제목
		String content = request.getParameter("content"); //내용
		//String memberNick = request.getParameter("memberNick");//작성자nick
		return  new WriteRequest(
					new Writer( user.getId(), user.getName() ),
					request.getParameter("title"),
					request.getParameter("content")
				);
		
	
	}
}










