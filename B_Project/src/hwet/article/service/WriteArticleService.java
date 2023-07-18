package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import hwet.article.dao.HwetArticleDAO;
import hwet.article.model.HwetArticleDTO;
import jdbc.connection.ConnectionProvider;

public class WriteArticleService {
	
	HwetArticleDAO articleDAO = new HwetArticleDAO();

	// 글작성
    public int writeArticle(String writer, String category,
    		String title, String link, String content) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return articleDAO.writeArticle(conn, writer, category, title, link, content);
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
        return -1;
    }
    
	
	
}






