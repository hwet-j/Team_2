package chat.command;

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
		
		if (user == null) {
			return "/view/errorPage.jsp";
		}
		
		int room_id = Integer.parseInt(request.getParameter("room_id"));
		// 채팅방 참여자
		List<String> participants = listRoomService.roomParticipant(room_id);
		// 채팅방 대화목록 
		List<ChatMessageDTO> messages = listRoomService.chatMessage(room_id);
		
		request.setAttribute("room_id", room_id);
		request.setAttribute("participants", participants);
		request.setAttribute("messages", messages);
		
		return "/view/HWET/chat/chat_room.jsp";
	}

}
