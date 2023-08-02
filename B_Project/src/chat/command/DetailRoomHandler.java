package chat.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat.model.ChatMessageDTO;
import chat.service.ListRoomService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

public class DetailRoomHandler implements CommandHandler{
	
	ListRoomService listRoomService = new ListRoomService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER"); 
		int room_id = Integer.parseInt(request.getParameter("room_id"));
		List<String> participants = listRoomService.roomParticipant(room_id);
		System.out.println(participants);
		
		boolean containsUser = false;
		
		if (user == null) {
			return "/view/errorPage.jsp";
		} else {
			// 세션에 저장된 유저 정보가 해당 방의 참가자인지 확인 (참가자면 true)
			containsUser = participants.stream().anyMatch(data -> data.contains(user.getUser_id()));
			if (!containsUser) {
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
			                + "            icon: 'warning',"
			                + "            title: '해당 채팅방에 초대받지 못했습니다.',"
			                + "            text: '채팅방 목록으로 돌아갑니다.',"
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
			}
		}
		
		// 채팅방 참여자
		// 채팅방 대화목록 
		List<ChatMessageDTO> messages = listRoomService.chatMessage(room_id);
		
		request.setAttribute("room_id", room_id);
		request.setAttribute("participants", participants);
		request.setAttribute("messages", messages);
		
		return "/view/HWET/chat/chat_room.jsp";
	}

}
