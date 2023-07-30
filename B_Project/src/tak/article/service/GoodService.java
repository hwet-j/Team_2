package tak.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import tak.article.dao.ArticleDAO;
import jdbc.connection.ConnectionProvider;

public class GoodService {
	private ArticleDAO articleDAO = new ArticleDAO();
	public OurArticleData getDetail(int no) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			//조회수 증가-p659 27라인 => 향후 if문추가예정~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			articleDAO.goodCount(conn, no);
			
			OurArticleData ourArticleData = articleDAO.getDetail(conn, no);
			if(ourArticleData==null) {
				throw new ArticleNotFoundException();
			}
			
			return ourArticleData;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}		
	}
}
