package chat.service;

import java.sql.Connection;
import java.sql.SQLException;

import chat.model.ChatDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class SendMessageService {
	
	ChatDAO chatDAO = new ChatDAO();
	
	
	/* 채팅 작성 하기 */
	public int sendMessage(int room_id, String sender_id, String content) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			result = chatDAO.sendMessage(conn, room_id, sender_id, content);
			
			return result;			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn); 
		}
		return result;		
	}
	
	
}











