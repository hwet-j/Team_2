package angel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import angel.model.Article;
import jdbc.JDBCUtil;
public class ArticleDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;	
	ResultSet rs = null;
	
	public List<Article> selectAll(Connection conn) {
		String sql = "select * from angel_animaltable order by articleNo desc";
		List<Article> article = new LinkedList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int articleNo = rs.getInt("articleNo");
				String memberid = rs.getString("memberid");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String regdate = rs.getString("regdate");
				int readCnt = rs.getInt("readCnt");
				String content = rs.getString("content");
				Article arti = new Article(articleNo, memberid, name, title, regdate, readCnt, content);
//				System.out.println("작동되면 말좀해봐");
	//			System.out.println(arti.toString());
				article.add(arti);
			}
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
			
		}
		return null;
	}

	public int articleTotal(Connection conn) {
		int articleTotal = 0;
		String sql = "select count(*) from angel_animaltable";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleTotal = rs.getInt(1);
				return articleTotal;
			} else {
				return 0; }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleTotal;
	}
	
	public Article selectContent(Connection conn, int articleNo) {
		String sql1 = "select * from angel_animaltable where articleNo = ?";
		String sql2 = "update angel_animaltable set readCnt = readCnt+1 where articleNo = ?";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, articleNo);
			int upCnt = pstmt2.executeUpdate();

			while(rs.next()) {
				String memberid = rs.getString("memberid");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String regdate = rs.getString("regdate");
				int readCnt = rs.getInt("readCnt") + 1;
				String content = rs.getString("content");
				Article arti = new Article(articleNo, memberid, name, title, regdate, readCnt, content);
				
				if(upCnt==1) {
					return arti;
				} else {
					conn.rollback();
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(Connection conn, int articleNo) {
		String sql = "delete from angel_animaltable where articleNo = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			boolean isDelete = pstmt.execute();
			System.out.println("dao 되니?"+isDelete);
			return !isDelete;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
			
		}
		return false;
	}

	public int modify(Connection conn, Article article) {
		int row = 0;
		String sql = "update angel_animaltable set content=?, title=? where articleNo=?";
		
		try {
			System.out.println("쿼리문 작동시작");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getContent());
			pstmt.setString(2, article.getTitle());
			pstmt.setInt(3, article.getArticleNo());
			row = pstmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return row;
	}
	
	public int write(Connection conn, Article article) {
		int row = 0;
		String sql = "insert into angel_animaltable(memberid, title, name, content) values(?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getMemberid());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getName());
			pstmt.setString(4, article.getContent());
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return row;
	}

	public List<Article> pageNoArticle(Connection conn, int pageNo) {
		String sql = "select * from angel_animaltable order by 1 desc limit ?, ?";
		int ten = 10;
		int startNum = 0+(pageNo-1)*ten;
		int totalCnt = articleTotal(conn);
		System.out.println("totalCnt"+totalCnt);
//		if(0+(pageNo-1)*five >totalCnt) {five = totalCnt%five;}
		List<Article> pageNoArticle = new LinkedList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, ten);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int articleNo = rs.getInt("articleNo");
				String memberid = rs.getString("memberid");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String regdate = rs.getString("regdate");
				int readCnt = rs.getInt("readCnt");
				String content = rs.getString("content");
				Article arti = new Article(articleNo, memberid, name, title, regdate, readCnt, content);
//				System.out.println("작동되면 말좀해봐");
	//			System.out.println(arti.toString());
				pageNoArticle.add(arti);
			}
			return pageNoArticle;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Article> select(Connection conn, String type, String subject) { // type은 검색할거 종류 , subject 는 검색 내용
		String sql = "SELECT * FROM angel_animaltable WHERE "+type+" LIKE ?";
		
		List<Article> select = new LinkedList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "%" + subject + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int articleNo = rs.getInt("articleNo");
				String memberid = rs.getString("memberid");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String regdate = rs.getString("regdate");
				int readCnt = rs.getInt("readCnt");
				String content = rs.getString("content");
				Article arti = new Article(articleNo, memberid, name, title, regdate, readCnt, content);
//				
				select.add(arti);
			}
			return select;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public List<Article> category(Connection conn, String category) {
		String sql = "select * from angel_animaltable where name = ?";
		
		List<Article> select = new LinkedList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int articleNo = rs.getInt("articleNo");
				String memberid = rs.getString("memberid");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String regdate = rs.getString("regdate");
				int readCnt = rs.getInt("readCnt");
				String content = rs.getString("content");
				Article arti = new Article(articleNo, memberid, name, title, regdate, readCnt, content);
				arti.toString();
				select.add(arti);
			}
			return select;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}	

