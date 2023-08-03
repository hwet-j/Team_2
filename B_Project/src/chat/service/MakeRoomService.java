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

public class MakeRoomService {
	
	ChatDAO chatDAO = new ChatDAO();
	
	/* 채팅방 리스트 가져오기 */
	public int makeChatRoom(String user_id, String title) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = chatDAO.makeChatRoom(conn, user_id, title);
			
			return result;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn); 
		}
		return result;		
	}
	
	/* 채팅방 참가자 초대 */
	public int inviteChatRoom(int room_id, String user_id) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = chatDAO.inviteChatRoom(conn, room_id, user_id);
			
			return result;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn); 
		}
		return result;		
	}
	
	
}











