package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import hwet.article.dao.HwetArticleDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteArticleService {
	
	HwetArticleDAO articleDAO = new HwetArticleDAO();
	
	// 삭제 요청 진행 메서드(게시글 번호를 매개변수로 받아 삭제한다)
	public int deleteArticle(int b_no) {
		Connection conn = null;
		int result = 0;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);
            // 삭제 요청 진행
            result = articleDAO.deleteArticle(conn, b_no);
            conn.commit();
        } catch (SQLException e) {
        	JDBCUtil.rollback(conn);
            e.printStackTrace();
        } finally {
			JDBCUtil.close(conn);
		}
        return result;
		
	}
    
	
	
}






