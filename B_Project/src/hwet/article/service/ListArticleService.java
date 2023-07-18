package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import hwet.article.dao.HwetArticleDAO;
import hwet.article.model.HwetArticleDTO;
import jdbc.connection.ConnectionProvider;

public class ListArticleService {
	
	HwetArticleDAO articleDAO = new HwetArticleDAO();

	// 페이징 처리를 위한 메서드
    public List<HwetArticleDTO> getBoardListWithPaging(int page, int pageSize) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            // 페이지 번호에 따라 해당 페이지의 데이터를 가져옵니다.
            return articleDAO.getBoardListWithPaging(conn, page, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    // 전체 데이터 개수를 반환하는 메서드
    public int getTotalDataCount() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return articleDAO.getTotalDataCount(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
	
	
}






