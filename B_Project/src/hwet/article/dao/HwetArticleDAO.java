package hwet.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hwet.article.model.HwetArticleDTO;

public class HwetArticleDAO {
	
	// 전체 목록
	public List<HwetArticleDTO> getBoardList(Connection conn) {
	    List<HwetArticleDTO> boardList = new ArrayList<>();
	    String sql = "SELECT * FROM hwet_board";
	    
	    try (
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
	
	// 페이징 처리를 위한 메서드
    public List<HwetArticleDTO> getBoardListWithPaging(Connection conn, int page, int pageSize) {
        List<HwetArticleDTO> boardList = new ArrayList<>();
        // 페이지 번호에 따라 가져올 데이터의 시작 인덱스 계산
        int startIndex = (page - 1) * pageSize;
        String sql = "SELECT * FROM hwet_board LIMIT ?, ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, startIndex);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();

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
    
    // 전체 데이터 개수를 반환하는 메서드 (페이징 처리에 사용)
    public int getTotalDataCount(Connection conn) {
        int totalCount = 0;
        // total 이라는 컬럼으로 Alias 설정
        String sql = "SELECT COUNT(*) AS total FROM hwet_board";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
            	// sql문에서 설정한 컬럼명을 가져다 사용
                totalCount = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCount;
    }
    
	// 목록 하나
	public HwetArticleDTO getBoardOne(Connection conn, int board_id ) {
	    String sql = "SELECT * FROM hwet_board WHERE board_id = ?";
	    HwetArticleDTO board = new HwetArticleDTO();
	    try {
    		PreparedStatement stmt = conn.prepareStatement(sql);
	    	stmt.setInt(1, board_id);
    		ResultSet rs = stmt.executeQuery();
	        
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
	
	// 조회수 증가
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

	public int writeArticle(Connection conn, String writer, String category, String title, String link,
			String content) {
		// 글번호, 글 작성일, 조회수 -> 값을 설정해주지 않아도 자동설정 (updatedate만 null값으로 설정됨)
		String sql = "INSERT INTO hwet_board (writer, title, category, link, content) VALUES " + 
				"(?, ?, ?, ?, ?)";
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		int rowCnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, category);
			pstmt.setString(4, link);
			pstmt.setString(5, content);
			
			rowCnt = pstmt.executeUpdate();
			
			System.out.println("1번");
			if(rowCnt>0) { //p635 31라인
				//방금 직전에 입력된 글번호를 DB에서 가져온다
				//->article_content 테이블에 insert시 글번호로 사용
				stmt2 =	conn.prepareStatement("select last_insert_id()");
				
				System.out.println("2번");
				rs = stmt2.executeQuery();
				System.out.println("3번");
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
}
