package tak.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import tak.article.dao.ArticleDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class GoodService {
	private ArticleDAO articleDAO = new ArticleDAO();
	public OurArticleData getDetail(int no) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			
			articleDAO.goodCount(conn, no);
			
			OurArticleData ourArticleData = articleDAO.getDetail(conn, no);
			if(ourArticleData==null) {
				throw new ArticleNotFoundException();
			}
			
			return ourArticleData;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}	finally {
			JDBCUtil.close(conn);
		}			
	}
}
