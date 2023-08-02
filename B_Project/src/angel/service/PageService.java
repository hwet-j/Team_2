package angel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import angel.dao.ArticleDAO;
import angel.model.Article;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class PageService {

	public List<Article> pageNoArticle(int pageNo) {
		ArticleDAO articleDAO = new ArticleDAO();
		List<Article> pageNoArticle = new LinkedList<>();
		Connection conn = null;
		
		try {
			conn=ConnectionProvider.getConnection();
			pageNoArticle = articleDAO.pageNoArticle(conn, pageNo);
			return pageNoArticle;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}
	
}
