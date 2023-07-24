package hwet.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import hwet.article.dao.HwetReplyDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class AddReplyService {
	
	HwetReplyDAO replyDAO = new HwetReplyDAO();

	// 글작성
    public int writeReply(int board_id, String writer, String nick_name, String content) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return replyDAO.writeReply(conn, board_id, writer, nick_name, content);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			JDBCUtil.close(conn);
		}
        return -1;
    }
    
	
	
}






