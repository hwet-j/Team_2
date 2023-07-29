package notice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JDBCUtil;
import notice.model.Notice;
import notice.model.Writer;
import notice.service.NoticeData;



//notice테이블관련 DB작업용 클래스이다
public class NoticeDAO {
	private String url = "jdbc:mysql://localhost:3306/cab?serverTimezone=UTC";
	private String uid = "root";
	private String upw = "rootpw";
	
	public NoticeDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NoticeDTO> noticeSelect() {
		
		ArrayList<NoticeDTO> dtos = new ArrayList<NoticeDTO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from notice");
			
			while (rs.next()) {
			int notice_no = rs.getInt("notice_no");
			String writer_id = rs.getString("writer_id");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Date writedate = rs.getDate("writedate");
			int views = rs.getInt("views");
			
			NoticeDTO dto = new NoticeDTO(notice_no,writer_id,title,content,writedate,views);
			dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return dtos;
	}
	public void update(Connection conn,int no, String title, String content)throws SQLException {
		System.out.println("BoardDAO-modify()진입");
		System.out.println("title="+title);
		//3.객체준비
		String sql="update notice " +
				"set title=?, content=? " +
				"where notice_no=?";
		PreparedStatement stmt = null;//insert용
		try {
			stmt = conn.prepareStatement(sql);
			
			//4.쿼리실행
			//stmt.set데이터타입(?순서,값);
			stmt.setString(1,title);
			stmt.setString(2,content);
			stmt.setInt(3,no);
			int updatedCount = stmt.executeUpdate();
			//수정성공시 1리턴, 실패시 0리턴
			System.out.println("dao의 수정레코드수="+updatedCount);
		}finally {
			JDBCUtil.close(stmt);
		}
	}
	
	//상세보기
	//파라미터 int no : 상세조회할 공지글 번호
	//리턴 Notice notice: 글번호, 작성자id,제목,내용,작성일,조회수
	public NoticeData getDetail(Connection conn, int no) throws SQLException {
		String sql = "select notice_no, writer_id, title, content, writedate, views " +
				"from notice " +
				"where notice_no=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			NoticeData nod = null;
			if(rs.next()) {
				nod = new NoticeData();
				//조회된 각 컬럼의 값을 가져와 Notice클래스 객체로 생성한다
				nod.setNumber(rs.getInt("notice_no"));
				nod.setWriter_id(rs.getString("writer_id"));
				nod.setTitle(rs.getString("title"));
				nod.setContent(rs.getString("content"));
				nod.setWriteDate(rs.getDate("writedate"));
				nod.setViews(rs.getInt("views"));
				
				System.out.println("NoticeDao에서 getDetail() NoticeData nod ="+nod);
			}
			return nod;			
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
    				new Writer(rs.getString("writer_id")),			
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
    
	//공지글등록
		//파라미터 board - 회원id,제목,내용
		//리턴     int - inserted된 정보 글번호!!!
	public Notice insert(Connection conn, Notice notice) throws SQLException {
		System.out.println("NoticeDao-insert()진입");
		
	String sql="insert into notice(writer_id,title,content,writedate,views) "+
	 "values(?,?,?,now(),0)";
	PreparedStatement stmt = null; //insert용
	PreparedStatement stmt2 = null; //select용
	ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			//4.쿼리실행
			//stmt.set데이터타입(?순서,값);
			stmt.setString(1,notice.getWriter().getId());
			stmt.setString(2,notice.getTitle());
			stmt.setString(3,notice.getContent());
			
			int insertedCount = stmt.executeUpdate();
			//입력성공시 1리턴, 실패시 0리턴
			if(insertedCount>0) {
				//방금 직전에 입력된 글번호를 DB에서 가져온다
				//->notice테이블에 insert시 글번호로 사용
				stmt2 = conn.prepareStatement("select last_insert_id() from notice");
				rs =stmt2.executeQuery();
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Notice(newNum,notice.getWriter(),notice.getTitle(),notice.getContent(),notice.getWriteDate(),0);
					//return rs.getInt(1);
				}
			}
			return null;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt2);
			JDBCUtil.close(stmt);
		}
	}
	
	//삭제하기
	//파라미터 no - 삭제할  글번호
	//리턴 noticeno - 삭제된 글번호
	
	public int delete(Connection conn, int no) throws SQLException {
		//3.객체준비
		String sql ="delete from notice " +
					"where notice_no=?";
		PreparedStatement stmt = null; //delete용
		try {
			stmt = conn.prepareStatement(sql);
			
			//4.쿼리실행
			//stmt.set데이터타입(?순서,값);
			stmt.setInt(1,no);
		
			int deletedCount = stmt.executeUpdate();
			//삭제성공시 삭제된글번호리턴, 실패시 0리턴
			if(deletedCount>0) {
				return no;				
			}
			
		}finally {
			JDBCUtil.close(stmt);
		}
		return 0; //삭제실패시 0을 리턴
	
	}

	
	//검색하기

	public ArrayList<NoticeDTO> selectList(String field, String value) {
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>(); //리턴해줄부분 초기화
		Connection conn = null; // DB 연결부분 초기화
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select * from notice order by no"; //검색어 없을때 기본값
		String sql2 = "select * from notice where "+field+" like ?"; //검색어 있을때
		try {
			if(value==null || value=="") {
			//키워드(value)가 있는지 체크. 없을시
			pstmt = conn.prepareStatement(sql1);
			}else {
			//키워드 있을시
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%"+value+"%");
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {//한 열만 출력시 if(rs.next()) 값이 여러개면 while(rs.next())
			int notice_no = rs.getInt("notice_no");
			String writer_id = rs.getString("writer_id");
			String title = rs.getString("content");
			String content = rs.getString("content");
			Date writedate = rs.getDate("writedate");
			int views = rs.getInt("views");
			//rs에서 현재 DB에 있는 값을 불러와서 위에 선언한 변수에 넣어준다.
			NoticeDTO dto = new NoticeDTO();
			dto.setNotice_no(notice_no);
			dto.setWriter_id(writer_id);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setWritedate(writedate);
			dto.setViews(views);
			
			list.add(dto);//현재 리스트에 위에서 설정한 값들을 넣어준다.
			}
		}catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				JDBCUtil.close(rs);
				JDBCUtil.close(pstmt);
			}
			return list;
		}

	}
	
	

