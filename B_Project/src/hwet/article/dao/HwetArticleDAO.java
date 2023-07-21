package hwet.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hwet.article.model.HwetArticleDTO;
import jdbc.JDBCUtil;

public class HwetArticleDAO {
	
	// 전체 목록
	// List로 전체 목록을 반환함
	public List<HwetArticleDTO> getBoardList(Connection conn) {
	    List<HwetArticleDTO> boardList = new ArrayList<>();
	    String sql = "SELECT * FROM hwet_board";
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    try {
	    	pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 	        
			while (rs.next()) {
				HwetArticleDTO board = new HwetArticleDTO();
			    board.setBoardId(rs.getInt("board_id"));
				board.setWriter(rs.getString("writer"));
				board.setTitle(rs.getString("title"));
				board.setCategory(rs.getString("category"));
				board.setLink(rs.getString("link"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("created_at"));
				board.setHit(rs.getInt("hit"));
				board.setUpdateDate(rs.getDate("updated_at"));
			    
			    boardList.add(board);
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	    
	    return boardList;
	}
	
	// 페이징 처리를 위한 메서드
	// List로 목록을 반환함
    public List<HwetArticleDTO> getBoardListWithPaging(Connection conn, int page, int list_size, 
    		String cate_info, String search_type, String keyword) {
        List<HwetArticleDTO> boardList = new ArrayList<>();
        // 페이지 번호에 따라 가져올 데이터의 시작 인덱스 계산
        int startIndex = (page - 1) * list_size;
        
        String sql = null;
        PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    
	    // 전체 검색을 위한 % 설정
    	if (cate_info.equals("전체")) {
    		cate_info = "%";
    	}
    	
        try {
        	if(!search_type.equals("")) {
        		keyword = "%" + keyword + "%";	// sql문에 검색을 위한 코드 변경
            	if (search_type.equals("title")) {	// 제목 검색
            		sql = "SELECT * FROM hwet_board WHERE category LIKE ? AND title LIKE ? LIMIT ?, ?";
            		pstmt = conn.prepareStatement(sql);
            		pstmt.setString(1, cate_info);
            		pstmt.setString(2, keyword);
                	pstmt.setInt(3, startIndex);
                	pstmt.setInt(4, list_size);
            	} else if (search_type.equals("content")) {	// 제목 + 내용 검색
            		sql = "SELECT * FROM hwet_board WHERE category LIKE ? AND (content LIKE ? OR title LIKE ?) LIMIT ?, ?";
            		pstmt = conn.prepareStatement(sql);
            		pstmt.setString(1, cate_info);
            		pstmt.setString(2, keyword);
            		pstmt.setString(3, keyword);
                	pstmt.setInt(4, startIndex);
                	pstmt.setInt(5, list_size);
            	} else if (search_type.equals("writer")) {	// 작성자 검색
            		sql = "SELECT * FROM hwet_board WHERE category LIKE ? AND writer LIKE ? LIMIT ?, ?";
            		pstmt = conn.prepareStatement(sql);
            		pstmt.setString(1, cate_info);
            		pstmt.setString(2, keyword);
                	pstmt.setInt(3, startIndex);
                	pstmt.setInt(4, list_size);
            	}
            } else {	// 전체(카테고리 분류만) 검색
            	sql = "SELECT * FROM hwet_board WHERE category LIKE ? LIMIT ?, ?";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, cate_info);
            	pstmt.setInt(2, startIndex);
            	pstmt.setInt(3, list_size);
            }
        	rs = pstmt.executeQuery();
        	
            while (rs.next()) {
                HwetArticleDTO board = new HwetArticleDTO();
                board.setBoardId(rs.getInt("board_id"));
                board.setWriter(rs.getString("writer"));
                board.setTitle(rs.getString("title"));
                board.setCategory(rs.getString("category"));
                board.setLink(rs.getString("link"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("created_at"));
                board.setHit(rs.getInt("hit"));
                board.setUpdateDate(rs.getDate("updated_at"));

                boardList.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}

        return boardList;
    }
    
    // 전체 데이터 개수를 반환하는 메서드 -> 관리자 페이지? 에서 사용하지 않을까 싶음 
    public int getTotalDataCount(Connection conn) {
        int totalCount = 0;
        // total 이라는 컬럼으로 Alias 설정
        String sql = "SELECT COUNT(*) AS total FROM hwet_board";
        PreparedStatement pstmt = null; 
	    ResultSet rs = null;
        try {
        	pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	// sql문에서 설정한 컬럼명을 가져다 사용
                totalCount = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}

        return totalCount;
    }
    
	// 카테고리 별 데이터 개수를 반환하는 메서드 (페이징 처리에 사용)
	public int getCategoryDataCount(Connection conn, String category, String search_type, String keyword) {
	    int total_count = 0;
	    // total 이라는 컬럼으로 Alias 설정
	    String sql = null;
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null;
	    
	    if (category.equals("전체")) {
	    	category = "%";
    	}
    	
        try {
        	if(!search_type.equals("")) {
        		keyword = "%" + keyword + "%";	// sql문에 검색을 위한 코드 변경
            	if (search_type.equals("title")) {	// 제목 검색
            		sql = "SELECT COUNT(*) AS total FROM hwet_board WHERE category LIKE ? AND title LIKE ?";
            		pstmt = conn.prepareStatement(sql);
            		pstmt.setString(1, category);
            		pstmt.setString(2, keyword);
            	} else if (search_type.equals("content")) {	// 제목 + 내용 검색
            		sql = "SELECT COUNT(*) AS total FROM hwet_board WHERE category LIKE ? AND (content LIKE ? OR title LIKE ?)";
            		pstmt = conn.prepareStatement(sql);
            		pstmt.setString(1, category);
            		pstmt.setString(2, keyword);
            		pstmt.setString(3, keyword);
            	} else if (search_type.equals("writer")) {	// 작성자 검색
            		sql = "SELECT COUNT(*) AS total FROM hwet_board WHERE category LIKE ? AND writer LIKE ?";
            		pstmt = conn.prepareStatement(sql);
            		pstmt.setString(1, category);
            		pstmt.setString(2, keyword);
            	}
            } else {	// 전체(카테고리 분류만) 검색
            	sql = "SELECT COUNT(*) AS total FROM hwet_board WHERE category LIKE ?";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, category);
            }
        	rs = pstmt.executeQuery();
	
	        if (rs.next()) {
	        	// sql문에서 설정한 컬럼명을 가져다 사용
	        	total_count = rs.getInt("total");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	
	    return total_count;
	}
    
	// 목록 하나
    // 목록 하나를 DTO 객체에 저장하여 리턴함
	public HwetArticleDTO getBoardOne(Connection conn, int board_id ) {
	    String sql = "SELECT * FROM hwet_board WHERE board_id = ?";
	    HwetArticleDTO board = new HwetArticleDTO();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, board_id);
    		rs = pstmt.executeQuery();
	        
    		while (rs.next()) {
			    board.setBoardId(rs.getInt("board_id"));
			    board.setWriter(rs.getString("writer"));
			    board.setTitle(rs.getString("title"));
			    board.setCategory(rs.getString("category"));
			    board.setLink(rs.getString("link"));
			    board.setContent(rs.getString("content"));
			    board.setRegDate(rs.getDate("created_at"));
			    board.setHit(rs.getInt("hit"));
			    board.setUpdateDate(rs.getDate("updated_at"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
	    
	    return board;
	}
	
	// 조회수 증가
	// return받아 사용할 곳이 없어보여 void
	public void increaseHit(Connection conn, int board_id ) {
		String sql = "UPDATE hwet_board SET hit=hit+1 WHERE board_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setInt(1, board_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
	}

	// 게시글 작성
	// 성공하면 가장 최근에 사용된 자동증가 숫자 반환, 실패하면 -1 반환 
	public int writeArticle(Connection conn, String writer, String category, String title, String link,
			String content) {
		// 글번호, 글 작성일, 조회수 -> 값을 설정해주지 않아도 자동설정 (updatedate만  null값으로 설정됨)
		String sql = "INSERT INTO hwet_board (writer, title, category, link, content) VALUES " + 
				"(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int rowCnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, category);
			pstmt.setString(4, link);
			pstmt.setString(5, content);
			
			rowCnt = pstmt.executeUpdate();
			
			if(rowCnt>0) { 
				// 방금 직전에 입력된 글번호를 DB에서 가져온다
				// -> last_insert_id()는 가장 최근에 사용된 자동증가 데이터의 값을 가져온다.
				pstmt2 =	conn.prepareStatement("select last_insert_id()");
				
				rs = pstmt2.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt2);
			JDBCUtil.close(pstmt);
		}
		
		return -1;
	}

	// 게시글 수정
	// 실패하면 0 반환 
	public int modifyArticle(Connection conn, HwetArticleDTO data) {
		// 수정날짜는 시간을 제외한 날짜만 입력되도록 설정 (CURRENT_DATE입력함 시간도 입력되도록 하려면 NOW() 입력)
		String sql = "UPDATE hwet_board SET title = ?, category = ?, link = ?, content = ?, updated_at = NOW() WHERE board_id = ?";
		int rowCnt = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getCategory());
			pstmt.setString(3, data.getLink());
			pstmt.setString(4, data.getContent());
			pstmt.setInt(5, data.getBoardId());
			
			rowCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return rowCnt;
	}
	
	// 게시글 삭제
	// 실패하면 0 반환
	public int deleteArticle(Connection conn, int b_no) {
		String sql = "DELETE FROM hwet_board WHERE board_id = ?";
		int rowCnt = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setInt(1, b_no);
			
			rowCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return rowCnt;
	}
	
}
