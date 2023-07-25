package board.whi.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import board.whi.dao.WhiBoardDAO;
import board.whi.model.WhiBoardArticle;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class WhiBoardArticleService {
	Connection conn = null;
	
	public List<WhiBoardArticle> showAllArticle(){
		List<WhiBoardArticle> articleList = new LinkedList<>();
		WhiBoardDAO dao = new WhiBoardDAO();
		try {
			conn = ConnectionProvider.getConnection();
			articleList = dao.showAllArticle(conn); 
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
		return articleList;
	}
	
	public List<WhiBoardArticle> showPagedArticle(int pageNo){
		List<WhiBoardArticle> articleList = new LinkedList<>();
		WhiBoardDAO dao = new WhiBoardDAO();
		try {
			conn = ConnectionProvider.getConnection();
			articleList = dao.showPagedArticle(conn, pageNo); 
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return articleList;
	}
}
