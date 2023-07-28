package angel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import angel.dao.ArticleDAO;
import angel.model.Article;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class ArticleService {
	Connection conn = null;
	List<Article> article = new LinkedList<>();
	ArticleDAO articleDAO = new ArticleDAO();
	List<Article> pagePerArticle = new LinkedList<>();
	
	public List<Article> selectAll() {
		try {
			conn = ConnectionProvider.getConnection();
			article = articleDAO.selectAll(conn);
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	public Article selectContent(int articleNo) {
		try {
			conn = ConnectionProvider.getConnection();
			Article article = articleDAO.selectContent(conn, articleNo);
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

	public Boolean delete(int articleNo) {
		try {
			conn = ConnectionProvider.getConnection();
			Boolean isDelete = articleDAO.delete(conn, articleNo);
			System.out.println("service 되니?"+isDelete);

			return isDelete;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int modify(Article article) {
		int row = 0;
		try {
			System.out.println("서비스시작");
			conn = ConnectionProvider.getConnection();
			row = articleDAO.modify(conn, article);
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int write(Article article) {
		int row = 0;
		try {
			conn = ConnectionProvider.getConnection();
			row = articleDAO.write(conn, article);
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
	

