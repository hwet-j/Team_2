package chat.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat.model.ChatInviteDTO;
import chat.model.ChatMessageDTO;
import chat.model.ChatRoomDTO;
import chat.service.ListRoomService;
import chat.service.MakeRoomService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


/* chat/inviteRoom.do */ 
public class InviteRoomHandler implements CommandHandler {
	
	ListRoomService listRoomService = new ListRoomService();
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
		
	// 기능
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER"); 
		if (user == null) {
			return "/view/errorPage.jsp";
		}
		String invite_user = request.getParameter("invite_user");
		int room_id = Integer.parseInt(request.getParameter("room_id"));
		// 특정 채팅방 참여자 목록 불러오기
		List<String> participants = listRoomService.roomParticipant(room_id);
		
		boolean containsUser = false;
		
		containsUser = participants.stream().anyMatch(data -> data.contains(invite_user));
		if (containsUser) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
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
		                + "            icon: 'warning',"
		                + "            title: '이미 채팅방에 초대된 유저입니다.',"
		                + "            text: '채팅방으로 돌아갑니다.',"
		                + "            showConfirmButton: true"
		                + "        }).then(function() {"
		                + "            history.back();"
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
			int result = makeRoomService.inviteChatRoom(room_id, invite_user);
			
			if (result > 0) {
				try {
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
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
			                + "            title: '" + invite_user + "님을 채팅방에 초대했습니다.',"
			                + "            text: '채팅방으로 돌아갑니다.',"
			                + "            showConfirmButton: true"
			                + "        }).then(function() {"
			                + "           window.location.href = '/chat/detailRoom.do?room_id=" + room_id  + "';"
			                + "        });"
			                + "    </script>"
			                + "</body>"
			                + "</html>";
					
					response.getWriter().write(script);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		
		return null;
	}
	
	// --
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		return null;
	}
		

}
