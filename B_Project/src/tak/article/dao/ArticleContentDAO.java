package tak.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

//article_content 관련 db작업 클래스
public class ArticleContentDAO {

	
	//수정 p665
	/*파라미터  int no : update하고자하는 글번호 
	            String content : 수정하고자하는 새 내용 	 
	 *리턴 		int : update가 성공되면 1리턴, 실패시 0리턴
	 */
	public int update(Connection conn, int no, String content)  throws SQLException {
		String sql = "update article_content " + 
					 "set content=? " + 
					 "where article_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,content);
			stmt.setInt(2,no);
			return stmt.executeUpdate();
			//update가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	//글등록 -p637 12라인
	/*파라미터
	savedArticle.getNumber() - 입력된 글번호
	req.getContent() - 내용   
	리턴  int - article_cotnent테이블에 입력성공시 1, 실패시 0 */
	public int insert(Connection conn, Integer number, String content) throws SQLException {
		System.out.println("ArticleContentDAO-insert()진입");
		
		//3.객체준비
		String sql = "insert into article_content(article_no,content) " + 
				     "values(?,?)";
		PreparedStatement stmt = null; 
		
		try {
		stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setInt(1,number);
		stmt.setString(2,content);
		
		int insertedCount = stmt.executeUpdate();
		//입력성공시 1리턴, 실패시 0리턴
		return insertedCount;
		
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
}








