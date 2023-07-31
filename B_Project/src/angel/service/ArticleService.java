package angel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import angel.dao.ArticleDAO;
import angel.model.Article;
import angel.model.Comment;
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
		} finally {
			JDBCUtil.close(conn);
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

			return isDelete;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
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
		} finally {
			JDBCUtil.close(conn);
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
		} finally {
			JDBCUtil.close(conn);
		}
		return 0;
	}

	public List<Comment> comment(int articleNo) {
		try {
			conn = ConnectionProvider.getConnection();
			List<Comment> commentText = articleDAO.comment(articleNo);
			return commentText;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

	public int writeComment(int articleNo, Comment writeComment) {
		int row = 0;
		try {
			conn = ConnectionProvider.getConnection();
			row = articleDAO.writeComment(conn, articleNo, writeComment);
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return 0;
	}


	public int modifyComment(int commentNo, String comment) {
		int row = 0;
		try {
			conn = ConnectionProvider.getConnection();
			row = articleDAO.modifyComment(conn, commentNo, comment);
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return 0;
	}
}
	

