package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class LoginFormMYHandler implements CommandHandler {
	private static final String FORM_VIEW = "/view/loginFormMY.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return FORM_VIEW;
	}

}
