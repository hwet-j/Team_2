package min.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;
import min.model.PolArticle;
import min.model.Writer;

public class PolArticleDAO {
	//필드
	//생성자
	
	//교재에서는 P655 selectById()이용하여 article테이블의 내용가져오기
	//			 p656 selectById()이용하여 article_content테이블의 내용가져오기
	//2개의 테이블의 내용을 ArticleData클래스(p657)로 묶어서 처리하였다
	
	//여기에서는 2개의 테이블의 내용을 join하여 처리하는 방식으로 진행
	//(article테이블,article_content테이블)상세조회
	//파라미터 int no : 상세조회할 글 번호
	//리턴     OurArticleData : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
	
	
	
	//polArticle테이블 목록조회 p647 15라인
	/*0,13; --1page페이지의 경우 갯수마다 조회
	 파라미터 int startRow페이지에 따른 row시작번호
	 	   int size -1페이지당 출력할 게시글 수 */
	public List<PolArticle> select(Connection conn, int startRow, int size) throws SQLException{
		String sql="select polArticle_no, writer_id, writer_name, title, " + 
				"       regdate,    moddate,   read_cnt " + 
				"from polArticle " + 
				"order by polArticle_no desc limit ?,?";
		
	PreparedStatement stmt=null;
	ResultSet rs = null;
	try {
		stmt = conn.prepareStatement(sql);
		//p647 22,23라인
		stmt.setInt(1, startRow);
		stmt.setInt(2, size);
		rs=stmt.executeQuery();
	List<PolArticle> result = new ArrayList<PolArticle>();
	//List참조변수.add(값); list에 값을 추가
	//List참조변수.get(int 인덱스번호); list에서 값을 가져오기
	while(rs.next()) {
		//데이터타입 변수명=rs.get데이터타입("컬럼명")
		//데이터타입 변수명=rs.get데이터타입(int 컬럼순서);
		result.add(covertPolArticle(rs));
	}//while
	return result;
		
	} finally {
	JDBCUtil.close(rs);
	 JDBCUtil.close(stmt); //회창님꺼 이름
	}
}
	
	//p647 36라인
	//select 쿼리를 실행 결과집합(ResultSet)을 이용하여 polArticle클래스 객체를 생성
	private PolArticle covertPolArticle(ResultSet rs) throws SQLException {
		return new PolArticle(rs.getInt("polArticle_no"),
				new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getString("title"),		
				rs.getDate("regdate"),		
				rs.getDate("moddate"),		
				rs.getInt("read_cnt")	);	
	}



	//총게시글수 조회-p646
	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from polArticle";
		
	PreparedStatement stmt=null;
	ResultSet rs=null;
	try {
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		int totalCNT=0;
		if(rs.next()) {
			totalCNT = rs.getInt(1);
		}
		return totalCNT;
		
	} finally  {
		JDBCUtil.close(rs);
		JDBCUtil.close(stmt);
		
	}
	
	
	
	
	}

}
