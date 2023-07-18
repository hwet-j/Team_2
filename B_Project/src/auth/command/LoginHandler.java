package auth.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;



// login.do
public class LoginHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler의 process()진입");
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("user_id"); 
		String password = request.getParameter("password");  
		
		
		System.out.println(id);
		System.out.println(password);
		LoginService service = new LoginService();
		
		MemberDTO user_data = service.login(id, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("AUTH_USER", user_data);
		
		return "/view/HWET/logintest1.jsp";
		
		
		
	}
	

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		// Get방식으로 요청받으면 로그인 창을 띄우는 핸들러 실행
		return "/loginForm.do";
	}
	
}
