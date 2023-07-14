package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;


/* 로그인 폼 창으로 연결해주는 Handler */

// loginForm.do
public class LoginFormHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/loginForm.jsp";

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return FORM_VIEW;
	}

}
