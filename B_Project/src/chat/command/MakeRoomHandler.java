package chat.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat.model.ChatInviteDTO;
import chat.model.ChatRoomDTO;
import chat.service.MakeRoomService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


/* chat/makeRoom.do */ 
public class MakeRoomHandler implements CommandHandler {
	
	MakeRoomService makeRoomService = new MakeRoomService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
		
	// 상세보기
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER"); 
		String title = request.getParameter("title");
		if (user == null) {
			return "/view/errorPage.jsp";
		}
		// 채팅방 생성
		int make_result = makeRoomService.makeChatRoom(user.getUser_id(), title);
		if (make_result > 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				String redirectURL = request.getContextPath() + "/login.do";
				String script = "<!DOCTYPE html>"
		                + "<html>"
		                + "<head>"
		                + "    <meta charset=\"UTF-8\">"
		                + "    <title>Warning</title>"
		                + "    <script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@10\"></script>"
		                + "</head>"
		                + "<body>"
		                + "    <script>"
		                + "        Swal.fire({"
		                + "            position: 'center',"
		                + "            icon: 'success',"
		                + "            title: '채팅방에 생성이 완료되었습니다.',"
		                + "            text: '채팅방 목록으로 돌아갑니다.',"
		                + "            showConfirmButton: true"
		                + "        }).then(function() {"
		                + "            window.location.href = '/chat/showRoom.do';"
		                + "        });"
		                + "    </script>"
		                + "</body>"
		                + "</html>";
				response.getWriter().write(script);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return "/view/errorPage.jsp";
		}
		
	}
	
	// 전체 리스트
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
		

}
