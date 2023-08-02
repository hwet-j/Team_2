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
		Connection conn = null;
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
			System.out.println("NoticeService- getNoticePage()의 결과 np="+np);
			return np;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}finally {
			JDBCUtil.close(conn);
		}
			
			
	}
	
	public NoticePage getSearchNoticePage(int pageNum, String field, String search) {
		Connection conn= null;
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = noticeDAO.selectSearchCount(conn ,field,search );//총 게시글 수
			
			/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
			int size    -1페이지당 출력할 게시글 수  */
			List<Notice> content = noticeDAO.selectSearch(conn,(pageNum-1)*size,size,field,search);//목록조회 p651-19
			
			/*파라미터  int	total				//총게시글수
 			int pageNum 			//보고싶은 페이지
 			int size    			//1페이지당 출력할 게시글 수
			List<Notice> content;  //notice목록 */
			
			NoticePage np = new NoticePage(total, pageNum, size, content);
			System.out.println("NoticeService- getNoticePage()의 결과 np="+np);
			return np;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}finally {
			JDBCUtil.close(conn);
		}		
			
			
	}
	
	//상세조회
	//파라미터 int no : 상세조회할 공지글 번호
	//리턴 Notice notice: 글번호, 작성자id,제목,내용,작성일,조회수
	public NoticeData getDetail(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//조회수증가
			noticeDAO.increaseViews(conn, no);
			
			NoticeData nod = noticeDAO.getDetail(conn, no);
			System.out.println("서비스 noticeData="+nod);
			if(nod==null) {
				throw new NoticeNotFoundException();
			}
			return nod;
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException();
	}	finally {
		JDBCUtil.close(conn);
	}	
	
  }
	
	//공지글등록하기
			//파라미터 notice: 세션의 회원id,글제목,내용
			//리턴     noticeno : 방금전 insert된 글번호
			public Integer write(WriteRequest req) {
				
				Connection conn = null;
				try {
					conn = ConnectionProvider.getConnection();
					conn.setAutoCommit(false);
					
					//파라미터 WriteRequest(notice) - 작성정보(여기에서는 session에  담긴 회원id),제목,내용을 WriteRequest(notice)객체로 생성*/
					//리턴notice - WriteRequest+작성일,수정일,조회수
					Notice notice =toNotice(req);
					//파라미터 board   - 회원id,제목,내용,작성일,조회수
					//리턴     int - inserted된 정보 글번호!!!
					Notice savedNoticeno = noticeDAO.insert(conn,notice);
					if(savedNoticeno==null) {
						throw new RuntimeException("Fail to insert notice");
					}
					
					conn.commit();
					return savedNoticeno.getNumber();
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
			//파라미터 WriteRequest -작성정보(여기에서는 session에 담긴 회원id),제목
			//리턴 Notice - WriteRequest+작성일,조회수
			private Notice toNotice(WriteRequest req) {
				//아직 글번호는 db에서 auto_increment되기에 현재는 모르므로 null 제시
				Date now =new Date();
				return new Notice(null, req.getWriter(), req.getTitle(), req.getContent(), now, 0);
			}
			
			//글삭제
			//파라미터 no -삭제할 글번호
			//리턴 int 삭제성공시 삭제된글번호리턴, 실패시 0
			public int deleteNotice(int no) {
				Connection conn = null;
				int deletedNo = 0;
				try {
					conn = ConnectionProvider.getConnection();
					conn.setAutoCommit(false);
					
					//파라미터 no - 삭제할  글번호
					//리턴 int 삭제성공시 삭제된글번호리턴, 실패시 0
					deletedNo = noticeDAO.delete(conn,no);
								
					conn.commit();
					return deletedNo;
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
			
			//수정하기
			//파라미터 notice:글번호,회원id,글수정제목,수정내용
			public void modify(ModifyRequest modReq) {
				
				Connection conn = null;
				System.out.println("modReq="+modReq);
				try {
					conn = ConnectionProvider.getConnection();
					conn.setAutoCommit(false);
					
					//파라미터 notice - notice:글번호,회원id,글수정제목,수정내용
					NoticeData notice = noticeDAO.getDetail(conn, modReq.getNumber());
					if(notice==null) {
						throw new NoticeNotFoundException();
					}
					//수정가능여부체크
					if(!canModify(modReq.getWriter_id(), notice)) {//수정불가하면
						throw new PermissionDeniedException();
					}	
					//1.notice테이블에 update하는 메서드호출
					noticeDAO.update(conn, modReq.getNumber(),modReq.getTitle(),modReq.getContent());
					
					
					conn.commit();
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
			//수정가능여부체크
			// 수정하고자하는 user의 id가 글작성자id와 일치하는지 비교하여 동일하면 수정가능
			//리턴 boolean - 수정할 수 있으면 true반환, 불가하면 false반환
	
	  private boolean canModify(String modifyingWriter_id, NoticeData notice) { return
			  modifyingWriter_id.equals(notice.getWriter_id()); }
	  
	  
	  
	  
}
