package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

// 회원가입 요청 (joinProc.do)
public class JoinProcHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/member/joinSuccess.jsp";

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		return FORM_VIEW;
	}
	
}
