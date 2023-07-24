package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import hwet.article.dao.HwetReplyDAO;
import hwet.article.model.HwetArticleDTO;
import hwet.article.model.HwetReplyDTO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class ReadReplyService {
	
	HwetReplyDAO replyDAO = new HwetReplyDAO();
    
    // 게시판 하나 읽기
    public List<HwetReplyDTO> getReplyList(int board_id) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return replyDAO.getReplyList(conn, board_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			JDBCUtil.close(conn);
		}
        return null;
    }
	
	
}






