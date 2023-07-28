package tak.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import tak.article.dao.ArticleContentDAO;
import tak.article.dao.ArticleDAO;

//p667
//수정처리를 위한 서비스클래스이다
public class ModifyArticleService {
	//필드
	private ArticleDAO articleDAO = new ArticleDAO();
	private ArticleContentDAO articleContentDAO = new ArticleContentDAO();

	//수정처리-p667-17라인
	//파라미터 ModifyRequest modReq-수정처리를 위한 세션에서 가져온 회원id, 글번호,제목,내용
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//1.article테이블에 update하기전 해당글번호 가져오기
			OurArticleData article = articleDAO.getDetail(conn, modReq.getArticleNumber());
			if(article==null) {
				throw new ArticleNotFoundException();
			}
			
			//1.수정가능여부체크
			if(!canModify(modReq.getUserId(), article)) { //수정불가하면
				throw new PermissionDeniedException();
			}
			
			//1.article테이블에 update하는 메서드호출-p668 31라인
			articleDAO.update(conn, modReq.getArticleNumber(), modReq.getTitle(),modReq.getContent());
			
			//2.article_content테이블에 update하는 메서드호출-p668 33라인
			//articleContentDAO.update(conn, modReq.getArticleNumber(), modReq.getContent());
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCUtil.rollback(conn);
			throw new RuntimeException();
		}catch(PermissionDeniedException e){ //수정불가시
			JDBCUtil.rollback(conn);
			throw e;
		}finally {
			JDBCUtil.close(conn);
		}
		
	}
	
	//수정가능여부체크-p668 47라인
	// 수정하고자하는 user의 id가 글작성자id와 일치하는 비교하여 동일하면 수정가능     
	//리턴 boolean - 수정할 수 있으면 true반환, 불가하면 false반환
	private boolean canModify(String modifyingUserId, OurArticleData article) {
		return   modifyingUserId.equals(article.getWriter_id());
	}

	
}











