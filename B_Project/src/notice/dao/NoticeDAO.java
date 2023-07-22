package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;
import notice.model.Notice;


//notice테이블관련 DB작업용 클래스이다
public class NoticeDAO {
	
	public int update(Connection conn, int no, String title)throws SQLException {
		String sql="update notice" +
				"set notice_title=?" +
				"where notice_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,title);
			stmt.setInt(2,no);
			return stmt.executeUpdate();
		}finally {
			JDBCUtil.close(stmt);
		}
	}
	
	//상세보기
	//파라미터 int no : 상세조회할 공지글 번호
	//리턴 Notice notice: 글번호, 작성자id,제목,내용,작성일,조회수
	public Notice getDetail(Connection conn, int no) throws SQLException {
		String sql = "select notice_no, writer_id, title, content, writedate, views " +
				"from notice " +
				"where notice_no=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			Notice notice = null;
			if(rs.next()) {
				notice = new Notice();
				//조회된 각 컬럼의 값을 가져와 Notice클래스 객체로 생성한다
				notice.setNumber(rs.getInt("notice_no"));
				notice.setWriterId(rs.getString("writer_id"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setWriteDate(rs.getDate("writedate"));
				notice.setViews(rs.getInt("views"));
				
				System.out.println("NoticeDao에서 getDetail() Notice notice ="+notice);
			}
			return notice;			
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}	
	}//상세조회	
	
	//조회수증가
	public void increaseViews(Connection conn, int no)throws SQLException {
		String sql = "update notice " +
					 "set views=views+1 " +
					 "where notice_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			stmt.executeUpdate();
		}finally {
			JDBCUtil.close(stmt);
		}
	}
	
	//notice테이블 목록조회
	/*0,13; -- 1page페이지의 경우
	-- 1페이지당 게시글수가 3인 경우(여기에서는 총게시물수가 13건) 
	-- =>1page이면 limit 0,3   13 12 11
	-- =>2page이면 limit 3,3   10 9 8
	-- =>3page이면 limit 6,3   7 6 5 
	-- =>4page이면 limit 9,3   4 3 2 
	-- =>5page이면 limit 12,3  1 */
	/*파라미터  int startRow-페이지에 따른 row시작번호
	int size    -1페이지당 출력할 게시글수  */
    public  List<Notice> select(Connection conn,int startRow,int size )  throws SQLException{
    	String sql= "select notice_no, writer_id, title, content, writedate, views " + 
    				"from notice " +
    				"order by notice_no desc  limit ?,?";
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
			 stmt =conn.prepareStatement(sql);
			 	stmt.setInt(1,startRow);
			 	stmt.setInt(2,size);
				rs = stmt.executeQuery();
			
				List<Notice> result = new ArrayList<Notice>();
				//List참조변수.add(값); list에   값을 추가
				//List참조변수.get(int 인덱스번호); list에서 값을 가져오기
				while(rs.next()) {
					//데이터타입 변수명=rs.get데이터타입("컬럼명");
					//데이터타입 변수명=rs.get데이터타입(int 컬럼순서);
					result.add( convertNotice(rs) ); 
				}//while
				
				return result;
			}finally {
				JDBCUtil.close(rs);
				JDBCUtil.close(stmt);
			}
		}
    
  //select쿼리를 실행한 결과집합(ResultSet)을 이용하여 Notice클래스의 객체를 생성
    private Notice convertNotice(ResultSet rs) throws SQLException {
		return new Notice(rs.getInt("notice_no"),
    				rs.getString("writer_id"),			
    				rs.getString("title"),
    				rs.getString("content"),
    				rs.getDate("writedate"),
    				rs.getInt("views")
    				); 
    }
    
    
    
    
  //총 게시물수 조회
	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from notice";
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			 stmt =conn.prepareStatement(sql);
			 rs =stmt.executeQuery();
			int totalCNT= 0; //총 게시물수를 저장하기 위한 변수 선언 및 초기화
			if(rs.next()) {
				totalCNT=rs.getInt(1);
			}
			return totalCNT;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}
		
	}// 목록 조회
    
	public Notice insert(Connection conn, Notice notice) throws SQLException {
		System.out.println("NoticeDao-insert()진입");
		
	String sql="insert into notice(user_id,notice_title,notice_content,notice_views) "+
	 "values(?,?,?,0)";
	PreparedStatement stmt = null;
	PreparedStatement stmt2 = null;
	ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setObject(1,notice.getWriterId());
			stmt.setString(2,notice.getTitle());
			stmt.setString(3,notice.getContent());
			int insertedCount = stmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt2 = conn.prepareStatement("select last_insert_user_id()from notice");
				rs =stmt2.executeQuery();
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					//return new Notice(newNum,notice.getWriterId(),notice.getTitle(),notice.getContent(),0); 수정예정
				}
			}
			return null;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
			JDBCUtil.close(stmt2);
		}
	}
	
	
}
	

