package board.whi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import board.whi.model.WhiBoardArticle;
import jdbc.JDBCUtil;

public class WhiBoardDAO {
	Statement stmt = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	
	public List<WhiBoardArticle> showAllArticle(Connection conn){
		List<WhiBoardArticle> articleList = new LinkedList<>();
		String sql = "SELECT * FROM whi_board ORDER BY ARTICLE_NO DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int articleNo = rs.getInt("article_no");
				String userId = rs.getString("USER_ID");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				String category = rs.getString("CATEGORY");
				int readCnt = rs.getInt("READ_CNT");
				String imgSrc = rs.getString("img_src");
				WhiBoardArticle whiBoardArticle = new WhiBoardArticle(articleNo, userId, title, content, regDate, category, readCnt, imgSrc);
				whiBoardArticle.toString();
				articleList.add(whiBoardArticle);
			}
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		
		return null;
	}

	public List<WhiBoardArticle> showPagedArticle(Connection conn, int pageNo){
		List<WhiBoardArticle> articleList = new LinkedList<>();
		String sql = "SELECT * FROM whi_board ORDER BY ARTICLE_NO DESC LIMIT ?,?";
		int totalArticleCnt = countTotalArticle(conn);
		int pagingSize = 10;
		if(pagingSize*(pageNo-1)>totalArticleCnt) {pagingSize = totalArticleCnt%pagingSize;}
		int pageCnt = totalArticleCnt/pagingSize + 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0+pagingSize*(pageNo-1));
			pstmt.setInt(2, pagingSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int articleNo = rs.getInt("article_no");
				String userId = rs.getString("USER_ID");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				String category = rs.getString("CATEGORY");
				int readCnt = rs.getInt("READ_CNT");
				String imgSrc = rs.getString("img_src");
				WhiBoardArticle whiBoardArticle = new WhiBoardArticle(articleNo, userId, title, content, regDate, category, readCnt, imgSrc);
				whiBoardArticle.toString();
				articleList.add(whiBoardArticle);
			}
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		
		return null;
	}

	public int countTotalArticle(Connection conn) {
		String sql="SELECT COUNT(*) FROM whi_board";
		int totalArticleCnt =0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalArticleCnt = rs.getInt(1);
				return totalArticleCnt;
			}
			else {return 0;}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalArticleCnt; 
	}

	public WhiBoardArticle selectContent(Connection conn, int articleNo) {
		String sql1 = "SELECT * FROM whi_board "
				+ "WHERE article_no=? ";
		String sql2 = "UPDATE WHI_BOARD SET READ_CNT = READ_CNT+1 WHERE article_no=?";
		try {
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			
			pstmt2=conn.prepareStatement(sql2);
			pstmt2.setInt(1, articleNo);
			int uptCnt = pstmt2.executeUpdate();
			
			if(rs.next()) {
				String userId = rs.getString("USER_ID");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				String category = rs.getString("CATEGORY");
				int readCnt = rs.getInt("READ_CNT")+1;
				String imgSrc = rs.getString("img_src");
				WhiBoardArticle whiBoardArticle = new WhiBoardArticle(articleNo, userId, title, content, regDate, category, readCnt, imgSrc);
				if(uptCnt==1) {return whiBoardArticle;}
				else { 
					conn.rollback();
					return null;}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
	}

	public Boolean deleteArticle(Connection conn, int articleNo) {
		String sql = "delete FROM whi_board where article_no = ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			Boolean deleteChk = pstmt.execute();
			return deleteChk;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt);
		}
		return null;
	}

	public int modifyArticle(Connection conn, WhiBoardArticle whiBoardArticle) {
		String sql= "UPDATE whi_board "
				+ "SET TITLE = ?, CONTENT=? "
				+ "WHERE ARTICLE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, whiBoardArticle.getTitle());
			pstmt.setString(2, whiBoardArticle.getContent());
			pstmt.setInt(3, whiBoardArticle.getArticleNo());
			int modifyRow = pstmt.executeUpdate();
			System.out.println("dao에서 modifyRow"+modifyRow);
			return modifyRow;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return 0;
	}

	public int writeArticle(Connection conn, WhiBoardArticle whiBoardArticle) {
		String sql = "INSERT INTO whi_board (user_id,title,content,reg_date,category,read_cnt) "
				+ "values(?,?,?,now(),?,0)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, whiBoardArticle.getUserId());
			pstmt.setString(2, whiBoardArticle.getTitle());
			pstmt.setString(3, whiBoardArticle.getContent());
			pstmt.setString(4, whiBoardArticle.getCategory());
			int writeRow = pstmt.executeUpdate();
			return writeRow;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return 0;
	}

		
}
