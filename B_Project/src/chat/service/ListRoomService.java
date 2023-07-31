package chat.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chat.model.ChatDAO;
import chat.model.ChatInviteDTO;
import chat.model.ChatMessageDTO;
import chat.model.ChatRoomDTO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class ListRoomService {
	
	ChatDAO chatDAO = new ChatDAO();
	
	/* 채팅방 리스트 가져오기 */
	public List<ChatRoomDTO> ListChatRoom() {
		Connection conn = null;
		List<ChatRoomDTO> result = new ArrayList<ChatRoomDTO>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = chatDAO.getRoomList(conn);
			
			return result;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn); 
		}
		return result;		
	}
	
	/* 전체 채팅방 참여자 목록 가져오기 */
	public List<ChatInviteDTO> getRoomUserList() {
		Connection conn = null;
		List<ChatInviteDTO> result = new ArrayList<ChatInviteDTO>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = chatDAO.getRoomUserList(conn);
			
			return result;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn); 
		}
		return result;		
	}
	
	/* 채팅방 참여자 가져오기 */
	public List<String> roomParticipant(int room_id) {
		Connection conn = null;
		List<String> result = new ArrayList<String>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = chatDAO.roomParticipant(conn, room_id);
			
			return result;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn); 
		}
		return result;		
	}
	
	/* 채팅방 대화기록 가져오기 */
	public List<ChatMessageDTO> chatMessage(int room_id) {
		Connection conn = null;
		List<ChatMessageDTO> result = new ArrayList<ChatMessageDTO>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = chatDAO.chatMessage(conn, room_id);
			
			return result;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn); 
		}
		return result;		
	}
	
	
}











