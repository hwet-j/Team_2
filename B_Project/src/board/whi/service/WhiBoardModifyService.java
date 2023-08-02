package board.whi.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.whi.dao.WhiBoardDAO;
import board.whi.model.WhiBoardArticle;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class WhiBoardModifyService {

	public int modifyArticle(WhiBoardArticle whiBoardArticle) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			WhiBoardDAO dao = new WhiBoardDAO();
			int modifyRow = dao.modifyArticle(conn, whiBoardArticle);
			return modifyRow;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return 0;
	}

}
