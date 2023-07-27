package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


/* login.do 로그인폼(GET)과 로그인 기능(POST)을 구현 */
public class LoginHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
	
	// 로그인 기능
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("user_id"); 
		String password = request.getParameter("password");  
		
		LoginService service = new LoginService();
		
		// 로그인에 성공하면 로그인에 성공한 유저의 정보를 가져와 저장
		MemberDTO user_data = service.login(id, password);
		
		// 로그인한 유저의 정보를 session값으로 저장
		HttpSession session = request.getSession();
		session.setAttribute("AUTH_USER", user_data);
		
		return "/index.jsp";
	}
	

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		// Get방식으로 요청받으면 로그인 창을 띄우는 핸들러 실행
		return "/loginForm.do";
	}
	
}
