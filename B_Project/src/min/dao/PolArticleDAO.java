package min.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import min.model.PolArticle;
import min.model.PolWriter;
import min.service.PolArticleData;
import jdbc.JDBCUtil;

public class PolArticleDAO {
	

	//필드
	//생성자
	//update쿼리를 통한 글삭제
		public int deleteUp(Connection conn, int no)   throws SQLException {
			String sql = "update min_pol set isshow='N' where pol_no=?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,no);
				return stmt.executeUpdate();
				//update가 성공되면 1리턴, 실패시 0리턴
			}finally {
				JDBCUtil.close(stmt);
			}
		}
		
			
		//삭제-delete쿼리를 통한 글삭제
		//우리는 article삭제시 article_content테이블에서도 해당 글번호가 연쇄적으로 삭제하게끔.
		public int delete(Connection conn, int no)  throws SQLException {
			String sql = "delete from min_pol where pol_no=?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,no);
				return stmt.executeUpdate();
				//delete가 성공되면 1리턴, 실패시 0리턴
			}finally {
				JDBCUtil.close(stmt);
			}
		}
		
	
	//수정 p665
	/*파라미터  int no : update하고자하는 글번호 
	            String title : 수정하고자하는 새 제목 	 
	 *리턴 		int : update가 성공되면 1리턴, 실패시 0리턴
	 */
	public int update(Connection conn, int no, String title) throws SQLException {
		String sql = "update min_pol " + 
					"set title=?, regdate=now() " + 
					"where pol_no=?";
			
		PreparedStatement stmt=null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setInt(2, no);
			return stmt.executeUpdate();
		} finally {
			JDBCUtil.close(stmt);
		}
	}
	
	//교재에서는 P655 selectById()이용하여 article테이블의 내용가져오기
	//			 p656 selectById()이용하여 article_content테이블의 내용가져오기
	//2개의 테이블의 내용을 ArticleData클래스(p657)로 묶어서 처리하였다
	
	//여기에서는 2개의 테이블의 내용을 join하여 처리하는 방식으로 진행
	//(article테이블,article_content테이블)상세조회
	//파라미터 int no : 상세조회할 글 번호
	//리턴     OurArticleData : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
	public PolArticleData getDetail(Connection conn, int no) throws SQLException {
		String sql="select p.pol_no, p.user_id, p.user_nickname, p.title, " + 
				"       p.regdate,    p.moddate,   p.read_cnt, " + 
				"       pc.content " + 
				"from min_pol p,  min_pol_content pc " + 
				"where p.pol_no=pc.pol_no " + 
				"	  and " + 
				"     p.pol_no=? ";
		PreparedStatement stmt = null;
		ResultSet rs= null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			System.out.println(rs);
			PolArticleData pad = null;
			if(rs.next()) {
				pad = new PolArticleData();
				pad.setNumber(rs.getInt("pol_no"));
				pad.setWriter_id(rs.getString("user_id"));
				pad.setWriter_name(rs.getString("user_nickname"));
				pad.setTitle(rs.getString("title"));
				pad.setRegDate(rs.getDate("regdate"));
				pad.setModifiedDate(rs.getDate("moddate"));
				pad.setReadCount(rs.getInt("read_cnt"));
				pad.setContent(rs.getString("content"));
				
				//콘솔출력
				System.out.println("PolArticleDAO에서  getDetail() PolArticleData pad ="+pad);
			}
			
			return pad;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}

	}
	
	//조회수증가-P656 20라인
	public void increaseReadCount(Connection conn, int no) throws SQLException {
			String sql= "update min_pol " + 
						"set read_cnt=read_cnt+1 " + 
						"where pol_no=?";
			PreparedStatement stmt = null;
			try {
			 stmt=conn.prepareStatement(sql);
			 stmt.setInt(1, no);
			 stmt.executeUpdate();
		} finally {
			JDBCUtil.close(stmt);
		}
	}
	
	//polArticle테이블 목록조회 p647 15라인
	/*0,13; --1page페이지의 경우 갯수마다 조회
	 파라미터 int startRow페이지에 따른 row시작번호
	 	   int size -1페이지당 출력할 게시글 수 */
	public List<PolArticle> select(Connection conn, int startRow, int size) throws SQLException{
		String sql="select pol_no, user_id, user_nickname, title, " + 
				"       regdate,    moddate,   read_cnt " + 
				"from min_pol " + 
				"order by pol_no desc limit ?,?";
		
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
		JDBCUtil.close(stmt);
	}
}
	
	//p647 36라인
	//select 쿼리를 실행 결과집합(ResultSet)을 이용하여 polArticle클래스 객체를 생성
	private PolArticle covertPolArticle(ResultSet rs) throws SQLException {
		return new PolArticle(rs.getInt("pol_no"),
				new PolWriter(rs.getString("user_id"), rs.getString("user_nickname")),
				rs.getString("title"),		
				rs.getDate("regdate"),		
				rs.getDate("moddate"),		
				rs.getInt("read_cnt")	);	
	}



	//총게시글수 조회-p646
	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from min_pol";
		
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
	
//p634 16라인
//파라미터 Article - 회원id, 회원name,제목,내용,작성일,수정일,조회수
//리턴     Article - inserted된 정보 글번호!!!,회원id, 회원name,제목,작성일,수정일,조회수
public PolArticle insert(Connection conn, PolArticle polArticle) throws SQLException {
	System.out.println("PolArticleDAO-insert()진입");
	
	//3.객체준비
	String sql=
	"insert into min_pol(user_id,user_nickname,title,regdate,moddate,read_cnt) " + 
	"values(?,?,?,?,?,0)";
	
	PreparedStatement stmt=null;
	PreparedStatement stmt2=null;
	ResultSet rs=null;
	
	try {
		stmt=conn.prepareStatement(sql);
	
		//4.쿼리실행 stmt.set데이터타입(?순서,값);
		stmt.setString(1,polArticle.getWriter().getId());
		stmt.setString(2,polArticle.getWriter().getName());
		stmt.setString(3,polArticle.getTitle());
		stmt.setTimestamp(4,toTimestamp(polArticle.getRegdate()) );
		stmt.setTimestamp(5,toTimestamp(polArticle.getModifiedDate()) );
		int insertedCount=stmt.executeUpdate(); 
		//입력성공시 1리턴, 실패시 0리턴
		if(insertedCount>0) {
		//방금 직전에 입력된 글번호를 DB에서 가져온다 ->글작성이 완료되면 그 글을 내게보여줘
		//->article_content 테이블에 insert시 글번호로 사용	
		stmt2 = conn.prepareStatement("select last_insert_id() from min_pol");
		rs = stmt2.executeQuery();
		if(rs.next()) {
			Integer newNum=rs.getInt(1);
			return new PolArticle(newNum, polArticle.getWriter(), polArticle.getTitle(), 
					polArticle.getRegdate(), polArticle.getModifiedDate(), 0);
		}
		}
		
		return null;
	} finally {
		JDBCUtil.close(rs);
		JDBCUtil.close(stmt);
		JDBCUtil.close(stmt2);
	}
	

	
	
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

}
