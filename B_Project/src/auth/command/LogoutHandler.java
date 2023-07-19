package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

// logout.do
public class LogoutHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인한 유저의 session값 제거
		HttpSession session = request.getSession();
		session.removeAttribute("AUTH_USER");
		// session.invalidate(); // -> 모든 세션 객체 무효화
		
		return "/index.jsp";
	}
}
