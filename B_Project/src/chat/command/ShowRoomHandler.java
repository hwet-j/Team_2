package chat.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat.model.ChatInviteDTO;
import chat.model.ChatMessageDTO;
import chat.model.ChatRoomDTO;
import chat.service.ListRoomService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


/* chat/showRoom.do */ 
public class ShowRoomHandler implements CommandHandler {
	
	ListRoomService listRoomService = new ListRoomService();

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
	
	// 전체 리스트
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER"); 
		if (user == null) {
			return "/view/errorPage.jsp";
		}
		// 채팅방 리스트 불러오기
		List<ChatRoomDTO> room_list = listRoomService.ListChatRoom();
		// 채팅방 참여자 목록 불러오기
		List<ChatInviteDTO> participant_list = listRoomService.getRoomUserList();
		
		
		request.setAttribute("chat_rooms", room_list);
		request.setAttribute("chat_participants", participant_list);
		
		return "/view/HWET/chat/chat.jsp";
	}
		

}
