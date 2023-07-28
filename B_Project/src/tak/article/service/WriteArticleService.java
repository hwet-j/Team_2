package tak.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import tak.article.dao.ArticleContentDAO;
import tak.article.model.Article;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import tak.article.dao.ArticleDAO;

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
public class WriteArticleService {
	
	private ArticleDAO articleDAO = new ArticleDAO();
	private ArticleContentDAO articleContentDAO = new ArticleContentDAO();
	
	//글등록처리
	//파라미터 WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
	//리턴 Integer - 입력된 글번호->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//p639 25라인
			//파라미터 WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
			//리턴 Article - WriteRequest+작성일,수정일,조회수
			Article article = toArticle(req);
			
			//파라미터 Article - 회원id, 회원name,제목,내용,작성일,수정일,조회수
			//리턴     Article - inserted된 정보 글번호!!!,회원id, 회원name,제목,작성일,수정일,조회수
			Article savedArticle = articleDAO.insert(conn,article);
			if(savedArticle==null) {
				throw new RuntimeException("Fail to insert article");
			}
			
			//p639 30라인 //교재에서는 입력된글번호와 내용을 ArticleContent로 묶었다(=>객체생성)
			//여기에서는 다르게 작업			
			//p639 33라인
			/*파라미터
			savedArticle.getNumber() - 입력된 글번호
			req.getContent() - 내용   
			리턴  int - article_cotnent테이블에 입력성공시 1, 실패시 0 */
//			int savedContentRow = articleContentDAO.insert(conn,savedArticle.getNumber(),req.getContent());
//			if(savedContentRow==0) {
//				throw new RuntimeException("Fail to insert article_content");
//			}
			
			conn.commit(); //p639 39라인
			
			//입력된 글번호->교재에서는 글등록성공시  글등록축하.jsp에서  글상세보기  기능 제공용으로 사용
			return savedArticle.getNumber();//p639 40라인
			
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
	
	//p639 52라인
	//파라미터 WriteRequest - 작성정보(여기에서는 session에  담긴 회원id, 회원name),제목,내용을 WriteRequest객체로 생성*/
	//리턴 Article - WriteRequest+작성일,수정일,조회수
	private Article toArticle(WriteRequest req) {
		//아직 글번호는 db에서 auto_increment되기에 현재는 몰라 그래서 null 제시
		Date now = new Date(); //글작성일, 글수정일 용도
		return new Article(null, req.getWriter(), req.getTitle(), now, now, 0,0,req.getContent(),null);
		//writer_id,writer_name,title,regdate,modifiedDate,readCount,good,content,isshow
	}
	
}










