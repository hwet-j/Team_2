package chat.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


/* chat/showRoom.do */ 
public class ShowRoomHandler implements CommandHandler {

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
		
		return "/index.jsp";
	}
	

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "/view/HWET/chat/chat.jsp";
	}
		

}
