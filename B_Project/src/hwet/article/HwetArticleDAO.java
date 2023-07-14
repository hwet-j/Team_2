package hwet.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HwetArticleDAO {
	
	public List<HwetArticleDTO> getBoardList(Connection conn) {
	    List<HwetArticleDTO> boardList = new ArrayList<>();
	    String sql = "SELECT * FROM hwet_board";
	    
	    try (
	    	// 넣어줄 매개변수가 존재하지 않아 PreparedStatement를 꼭 사용할 필요는 없음
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        while (rs.next()) {
	        	HwetArticleDTO board = new HwetArticleDTO();
	            board.setBoardId(rs.getInt("board_id"));
	            board.setWriter(rs.getString("writer"));
	            board.setTitle(rs.getString("title"));
	            board.setCategory(rs.getString("category"));
	            board.setLink(rs.getString("link"));
	            board.setContent(rs.getString("content"));
	            board.setRegDate(rs.getDate("regdate"));
	            board.setHit(rs.getInt("hit"));
	            board.setUpdateDate(rs.getDate("updatedate"));
	            
	            boardList.add(board);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return boardList;
	}

	
	public HwetArticleDTO getBoardOne(Connection conn, int board_id ) {
	    String sql = "SELECT * FROM hwet_board WHERE board_id = ?";
	    HwetArticleDTO board = new HwetArticleDTO();
	    try (
    		// 넣어줄 매개변수가 존재하지 않아 PreparedStatement를 꼭 사용할 필요는 없음
    		PreparedStatement stmt = conn.prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery()) {
	        
    		while (rs.next()) {
			    board.setBoardId(rs.getInt("board_id"));
			    board.setWriter(rs.getString("writer"));
			    board.setTitle(rs.getString("title"));
			    board.setCategory(rs.getString("category"));
			    board.setLink(rs.getString("link"));
			    board.setContent(rs.getString("content"));
			    board.setRegDate(rs.getDate("regdate"));
			    board.setHit(rs.getInt("hit"));
			    board.setUpdateDate(rs.getDate("updatedate"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return board;
	}
	
	public void increaseHit(Connection conn, int board_id ) {
		String sql = "UPDATE hwet_board SET hit=hit+1 WHERE board_id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
				
			pstmt.setInt(1, board_id);
			ResultSet rs = pstmt.executeQuery();
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
