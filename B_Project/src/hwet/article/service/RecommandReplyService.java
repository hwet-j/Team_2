package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import hwet.article.dao.HwetArticleDAO;
import hwet.article.dao.HwetReplyDAO;
import hwet.article.model.HwetArticleDTO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class RecommandReplyService {
	
	HwetReplyDAO replyDAO = new HwetReplyDAO();

	// 추천, 비추천
    public boolean recommandReply(int board_id, int reply_id, String type) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return replyDAO.increaseRecommand(conn, board_id, reply_id, type);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			JDBCUtil.close(conn);
		}
        return false;
    }
    
	
	
}






