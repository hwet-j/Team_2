package board.whi.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.whi.dao.WhiBoardDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class WhiBoardDeleteService {

	public Boolean deleteArticle(int articleNo) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			WhiBoardDAO dao = new WhiBoardDAO();
			Boolean deleteChk = dao.deleteArticle(conn, articleNo);
			return deleteChk;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
