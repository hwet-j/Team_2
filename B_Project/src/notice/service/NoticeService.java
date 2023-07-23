package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.model.Notice;

//ListNoticeHandler<->Service<->DAO<->DB
public class NoticeService {
		
	//필드
	private NoticeDAO noticeDAO = new NoticeDAO();
	int size =3;
	//총게시글수+목록조회 
	//파라미터  int pageNum : 보고싶은 페이지
	public NoticePage getNoticePage(int pageNum) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = noticeDAO.selectCount(conn);//총 게시글 수
			
			/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
			int size    -1페이지당 출력할 게시글 수  */
			List<Notice> content = noticeDAO.select(conn,(pageNum-1)*size,size);//목록조회 p651-19
			
			/*파라미터  int	total				//총게시글수
 			int pageNum 			//보고싶은 페이지
 			int size    			//1페이지당 출력할 게시글 수
			List<Notice> content;  //notice목록 */
			
			NoticePage np = new NoticePage(total, pageNum, size, content);
			System.out.println("ListNoticeService- getNoticePage()의 결과 np="+np);
			return np;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}			
			
			
	}
	
	//상세조회
	//파라미터 int no : 상세조회할 공지글 번호
	//리턴 Notice notice: 글번호, 작성자id,제목,내용,작성일,조회수
	public Notice getDetail(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//조회수증가
			noticeDAO.increaseViews(conn, no);
			
			Notice notice = noticeDAO.getDetail(conn, no);
			System.out.println("서비스 notice="+notice);
			if(notice==null) {
				throw new NoticeNotFoundException();
			}
			return notice;
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException();
	}		
	
  }
	
	//공지글등록하기
			//파라미터 notice: 세션의 회원id,글제목,내용
			//리턴     noticeno : 방금전 insert된 글번호
			public int write(WriteRequest req) {
				
				Connection conn = null;
				try {
					conn = ConnectionProvider.getConnection();
					conn.setAutoCommit(false);
					
					//파라미터 WriteReques(notice) - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest(notice)객체로 생성*/
					//리턴notice - WriteRequest+작성일,수정일,조회수
					
					//파라미터 board   - 회원id,제목,내용,작성일,조회수
					//리턴     int - inserted된 정보 글번호!!!
					Integer savedNoticeno = noticeDAO.insert(conn,notice);
					if(savedNoticeno==null) {
						throw new RuntimeException("Fail to insert notice");
					}
					
					conn.commit();
					return savedNoticeno;
				} catch (SQLException e) {
					e.printStackTrace();
					JDBCUtil.rollback(conn);
					throw new RuntimeException(e);
				} catch (RuntimeException e) {
					e.printStackTrace();
					JDBCUtil.rollback(conn);
					throw e;
				} finally {
					JDBCUtil.close(conn);
				}
				
				
				
			}
}
