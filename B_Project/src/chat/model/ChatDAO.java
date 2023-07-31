package chat.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;

public class ChatDAO {
	
	/* 채팅방 전체 목록을 List로  반환함 (채팅방 생성이 완성되려면 참여 유저가 필요하기 때문에 참여유저 테이블과 JOIN하여 출력 */
	public List<ChatRoomDTO> getRoomList(Connection conn) {
	    List<ChatRoomDTO> room_list = new ArrayList<>();
	    String sql = "SELECT * FROM chat_room";
	    
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    try {
	    	pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 	        
			while (rs.next()) {
				ChatRoomDTO room = new ChatRoomDTO(rs.getInt("room_id"), rs.getString("room_name"));
			    
				room_list.add(room);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	    
	    return room_list;
	}
	
	/* 전체 채팅방의 참여자 목록을 List로  반환함  */
	public List<ChatInviteDTO> getRoomUserList(Connection conn) {
	    List<ChatInviteDTO> participant = new ArrayList<>();
	    String sql = "SELECT invite_id, room_id, invite_user FROM chat_room JOIN chat_invited_user USING(room_id)";
	    
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    try {
	    	pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 	        
			while (rs.next()) {
				ChatInviteDTO data = new ChatInviteDTO(rs.getInt("invite_id"), rs.getInt("room_id"), rs.getString("invite_user"));
			    
				participant.add(data);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	    
	    return participant;
	}
	
	/* 특정 채팅방의 참여자 목록을 List로  반환함  */
	public List<String> roomParticipant(Connection conn, int room_id) {
	    List<String> participant = new ArrayList<>();
	    String sql = "SELECT invite_id, room_id, invite_user FROM chat_room JOIN chat_invited_user USING(room_id) WHERE room_id = ?";
	    
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, room_id);
	    	
			rs = pstmt.executeQuery();
			 	        
			while (rs.next()) {
				participant.add(rs.getString("invite_user"));
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	    
	    return participant;
	}

	/* 특정 채팅방의 대화기록  */
	public List<ChatMessageDTO> chatMessage(Connection conn, int room_id) {
	    List<ChatMessageDTO> message = new ArrayList<>();
	    String sql = "SELECT message_id, room_id, sender_id, content, created_at FROM chat_message WHERE room_id = ? ORDER BY created_at";
	    
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, room_id);
	    	
			rs = pstmt.executeQuery();
			 	        
			while (rs.next()) {
				// 100ms 의 단위 까지 출력되는 문제를 SimpltDateFormat으로 해결가능함 -> String 형식으로 저장되므로 방식만 알아두도록 하자
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // String formattedDateTime = sdf.format(rs.getTimestamp("created_at"));
				
				ChatMessageDTO data = new ChatMessageDTO(rs.getInt("message_id"), 
						rs.getInt("room_id"), rs.getString("sender_id"), rs.getString("content"), rs.getTimestamp("created_at"));
				
				message.add(data);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	    
	    return message;
	}
	
	
	/* 채팅 작성  */
	public int sendMessage(Connection conn, int room_id, String sender_id, String content) {
		String sql = "INSERT INTO chat_message(room_id, sender_id, content) VALUE(?, ?, ?)";
		
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room_id);
			pstmt.setString(2, sender_id);
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		
		return result;
	}
	
	
}
