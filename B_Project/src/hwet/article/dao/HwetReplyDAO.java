package hwet.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hwet.article.model.HwetReplyDTO;
import jdbc.JDBCUtil;

public class HwetReplyDAO {

	// 댓글 작성
	public int writeReply(Connection conn, int board_id, String writer,String nick_name, String content) {
		String sql = "INSERT INTO hwet_board_reply (board_id, writer, writer_nick, content) VALUES " + 
				"(?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int rowCnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setInt(1, board_id);
			pstmt.setString(2, writer);
			pstmt.setString(3, nick_name);
			pstmt.setString(4, content);
			
			rowCnt = pstmt.executeUpdate();
			
			if(rowCnt>0) { 
				return rowCnt;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return -1;
	}
	
	// 페이징, 댓글 정보 조회
    public List<HwetReplyDTO> getReplyList(Connection conn, int board_id) {
        List<HwetReplyDTO> reply_list = new ArrayList<>();
        // 페이지 번호에 따라 가져올 데이터의 시작 인덱스 계산
        
        String sql = null;
        PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    
        try {
        	sql = "SELECT * FROM hwet_board_reply WHERE board_id = ? ORDER BY created_at DESC";
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, board_id);
        	rs = pstmt.executeQuery();
        	
            while (rs.next()) {
            	HwetReplyDTO reply = new HwetReplyDTO(
					            	rs.getInt("reply_id"),
					            	rs.getInt("board_id"),
					            	rs.getString("writer"),
					            	rs.getString("writer_nick"),
					            	rs.getDate("created_at"),
					            	rs.getDate("updated_at"),
					            	rs.getString("content"),
					            	rs.getInt("positive_count"),
					            	rs.getInt("negative_count"));
            	
            	reply_list.add(reply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}

        return reply_list;
    }
    
    // 추천수 증가 (좋아요, 싫어요)
 	public boolean increaseRecommand(Connection conn, int board_id, int reply_id, String type) {
 		String sql = null;
 		int result = 0;
 		if (type.equals("negative")) {
 			sql = "UPDATE hwet_board_reply SET negative_count=negative_count+1 WHERE board_id = ? AND reply_id = ?";
 		} else if (type.equals("positive")) {
 			sql = "UPDATE hwet_board_reply SET positive_count=positive_count+1 WHERE board_id = ? AND reply_id = ?";
 		} else {
 			return false;	// type이 비정상적으로 들어왔으면 어떤 작업도 하지않는다.
 		}
 		PreparedStatement pstmt = null;
 		try {
 			pstmt = conn.prepareStatement(sql);
 				
 			pstmt.setInt(1, board_id);
 			pstmt.setInt(2, reply_id);
 			result = pstmt.executeUpdate();
 			if (result > 0) {
 				return true;
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			JDBCUtil.close(pstmt);
 		}
 		return false;
 	}

	
}
