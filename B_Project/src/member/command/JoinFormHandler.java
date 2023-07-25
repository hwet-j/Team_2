package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

/* 회원가입 창과 연결해주는 Handler */

// joinForm.do
public class JoinFormHandler implements CommandHandler{
	          
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/view/member/joinForm.jsp";
	}
} 







