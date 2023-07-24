package board.whi.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.whi.dao.WhiBoardDAO;
import board.whi.model.WhiBoardArticle;
import jdbc.connection.ConnectionProvider;

public class WhiBoardContentService {

	public WhiBoardArticle whiSelectContent(int articleNo) {
		Connection conn = null;
		WhiBoardArticle article = null;
		try {
			conn = ConnectionProvider.getConnection();
			WhiBoardDAO dao = new WhiBoardDAO();
			article = dao.selectContent(conn, articleNo);
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
