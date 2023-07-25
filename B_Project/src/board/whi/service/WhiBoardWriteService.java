package board.whi.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.whi.dao.WhiBoardDAO;
import board.whi.model.WhiBoardArticle;
import jdbc.connection.ConnectionProvider;

public class WhiBoardWriteService {

	public int writeArticle(WhiBoardArticle whiBoardArticle) {
		Connection conn= null;
		
		try {
			conn = ConnectionProvider.getConnection();
			WhiBoardDAO dao = new WhiBoardDAO();
			int writeRow = dao.writeArticle(conn, whiBoardArticle );
			return writeRow;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
