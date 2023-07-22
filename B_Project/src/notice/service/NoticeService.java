package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
}
