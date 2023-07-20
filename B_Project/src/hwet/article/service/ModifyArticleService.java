package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import hwet.article.dao.HwetArticleDAO;
import hwet.article.model.HwetArticleDTO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyArticleService {
	
	HwetArticleDAO articleDAO = new HwetArticleDAO();
	
	// 게시글을 수정하는 메서드
	public int modifyArticle(HwetArticleDTO data) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return articleDAO.modifyArticle(conn, data);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			JDBCUtil.close(conn);
		}
        return 0;
		
	}
    
	
	
}






