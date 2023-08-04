package tak.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import tak.article.dao.ArticleContentDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import tak.article.dao.ArticleDAO;


//교재의 내용과 다른 방식으로 진행
public class ReadArticleService {

	//필드
	private ArticleDAO articleDAO = new ArticleDAO();
	private ArticleContentDAO articleContentDAO = new ArticleContentDAO();
	
	//상세조회
	//파라미터 int no : 상세조회할 글 번호
	//리턴 OurArticleData:OurArticleData : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
	public OurArticleData getDetail(int no) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			//조회수 증가 
			articleDAO.increaseReadCount(conn, no);
			
			OurArticleData ourArticleData = articleDAO.getDetail(conn, no);
			if(ourArticleData==null) {
				throw new ArticleNotFoundException();
			}
			
			return ourArticleData;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}	finally {
			JDBCUtil.close(conn);
		}			
	}
	
}









