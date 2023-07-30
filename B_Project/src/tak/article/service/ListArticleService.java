package tak.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import tak.article.model.Article;
import jdbc.connection.ConnectionProvider;
import tak.article.dao.ArticleDAO;

//P650
//ListArticleHandler<->Service<->DAO<->DB
public class ListArticleService {
	
	ArticleDAO articleDAO = new ArticleDAO();
	int size = 5;//1페이지당 출력할 게시글수-p651 14라인

	//총게시글수+목록조회 
	//파라미터  int pageNum : 보고싶은 페이지
	public ArticlePage getArticlePage(int pageNum) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			
			//p651 18라인
			int total = articleDAO.selectCount(conn); //총게시글수
			/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
						int size    -1페이지당 출력할 게시글 수  */
			List<Article> content = articleDAO.select(conn,(pageNum-1)*size,size);//목록조회-p651 19라인
			
			//p651 21라인
			/*파라미터  int	total				//총게시글수
			 			int pageNum 			//보고싶은 페이지
			 			int size    			//1페이지당 출력할 게시글 수
						List<Article> content;  //article목록 */
			ArticlePage ap = new ArticlePage(total, pageNum, size, content);
			System.out.println("ListArticleService- getArticlePage()의 결과 ap="+ap);
			return ap;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public ArticlePage getSearchArticlePage(int pageNum,String search) {
		Connection conn;
		
		System.out.println("getSearchArticlePage"+"진입");
		try {
			conn = ConnectionProvider.getConnection();
			
			//p651 18라인
			int total = articleDAO.selectSearchCount(conn,search); //총게시글수
			/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
						int size    -1페이지당 출력할 게시글 수  */
			List<Article> content = articleDAO.selectSearch(conn,(pageNum-1)*size,size,search);//목록조회-p651 19라인
			
			//p651 21라인
			/*파라미터  int	total				//총게시글수
			 			int pageNum 			//보고싶은 페이지
			 			int size    			//1페이지당 출력할 게시글 수
						List<Article> content;  //article목록 */
			ArticlePage ap = new ArticlePage(total, pageNum, size, content);
			System.out.println("ListArticleService- getArticlePage()의 결과 ap="+ap);
			return ap;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}






