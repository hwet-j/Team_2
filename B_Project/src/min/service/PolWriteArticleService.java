package min.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import min.dao.PolArticleContentDAO;
import min.dao.PolArticleDAO;
import min.model.PolArticle;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

//p638
//글등록처리 서비스클래스이다
/*WriteArticleHandler<->서비스<->ArticleDAO<->DB
		insert into article(writer_id,writer_name,title,regdate,moddate,read_cnt)
		values(세션작성자id,세션작성자명,제목,작성일,수정일,0);
		
		방금 직전에 입력된 글번호를 DB에서 가져온다
		->article_content 테이블에 insert시 글번호로 사용
								 ArticleContentDAO<->DB
	  	insert into article_content(article_no,content)
		values(글번호,내용);

		->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용
*/   //p639
public class PolWriteArticleService {
	
	private PolArticleDAO polArticleDAO= new PolArticleDAO();
	private PolArticleContentDAO polArticleContentDAO=new PolArticleContentDAO();
	
	//글등록처리
	//파라미터 WriteRequest - 작성정보(여기에서는 session에 담긴 회원 id, 회원name) 제목,내용을 WriteRequest 객체로 생성
	//리턴 Integer - 입력된 글번호 ->교재에서는 글등록성공시 글등록축하.jsp에서 글 상세보기
	
	public Integer write(PolWriteRequest req) {
		Connection conn =null;
		try {
			
			//p639 25라인 
			//Article - WriteRequest + 작성일,수정일,조회수
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			PolArticle polArticle = toPolArticle(req);
			
			//아티클테이블에 데이터입력스
			PolArticle savedPolArticle = polArticleDAO.insert(conn, polArticle);
			if (savedPolArticle==null) {
				throw new RuntimeException("Fail to insert polArticle");
			}
			
			//컨텐츠 테이블에 데이터입력시 1
			int savedContentRow=polArticleContentDAO.insert(conn, savedPolArticle.getNumber(), req.getContent());
			if(savedContentRow==0) {
				throw new RuntimeException("Fail to insert article_content");
			}
			
			conn.commit();
			
			return savedPolArticle.getNumber();
			
			
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

	private PolArticle toPolArticle(PolWriteRequest req) {
		
		Date now= new Date();
		return new PolArticle(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
	

}










