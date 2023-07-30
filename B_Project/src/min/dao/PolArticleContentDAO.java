package min.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBCUtil;

//article_content 관련 db작업 클래스
public class PolArticleContentDAO {

	
	//수정 p665
	public int update(Connection conn, int no, String content)  throws SQLException {
		String sql = "update min_pol_content " + 
						"set content=? " + 
						"where pol_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,content);
			stmt.setInt(2,no);
			return stmt.executeUpdate();
			//update가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JDBCUtil.close(stmt);
		}
	}
	
	//글등록 -p637 12라인
	/*파라미터
	savedArticle.getNumber() - 입력된 글번호
	req.getContent() - 내용   
	리턴  int - article_cotnent테이블에 입력성공시 1, 실패시 0 */
	public int insert(Connection conn, Integer number, String content) throws SQLException {
		System.out.println("PolArticleContentDAO-insert()진입");
		
		
		//3.객체준비
		String sql = "insert into min_pol_content(pol_no,content) " + 
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
			JDBCUtil.close(stmt);
		}
	}
	
}








