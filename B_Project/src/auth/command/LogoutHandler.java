package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

// logout.do
public class LogoutHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/index.jsp";
	}
}
