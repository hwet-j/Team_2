package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
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
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		if(id==null || id.isEmpty()) {
			//에러가 있다면
			//Map참조변수 errors에  필드명을 key로, True를 값으로 저장 
			errors.put("memberid", Boolean.TRUE );
		}
		
		if(password==null || password.isEmpty()) {
			errors.put("password", Boolean.TRUE );
		}
		
		if(!errors.isEmpty()) {
			return "/index.jsp";
		}
		
		try {
			
			response.sendRedirect("/index.jsp");
			return "/loginForm.do";	// 계속 테스트하기위해 Form으로
		} catch (LoginFailException e) {
			
		} catch (IOException e) {
			
		}
		
		
		return "/loginForm.do";	// 계속 테스트하기위해 Form으로
	}
	
	// 좌우 공백 제거 -> String클래스에 trim() 메서드를 사용해서 공백을 좌우의 공백을 제거할 수 있다. 
	// 아래 코드는 String클래스의 trim()메서드를 사용하지 않고 정규표현식을 사용해서 공백을 제거해주었다.
	private String trim(String str) {
		str = str.replaceAll("^\\s+|\\s+$", "");
		return str;
	}
	

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/loginForm.do";
	}
}
