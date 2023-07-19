package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import hwet.article.dao.HwetArticleDAO;
import hwet.article.model.HwetArticleDTO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class ReadArticleService {
	
	HwetArticleDAO articleDAO = new HwetArticleDAO();
    
    // 게시판 하나 읽기
    public HwetArticleDTO getDetail(int no) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return articleDAO.getBoardOne(conn, no);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			JDBCUtil.close(conn);
		}
        return null;
    }
	
    // 조회수 증가
    public void increaseHit(int no) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            articleDAO.increaseHit(conn, no);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			JDBCUtil.close(conn);
		}
    }
	
}






