package min.service;

import java.sql.Connection;
import java.sql.SQLException;

import min.dao.PolArticleContentDAO;
import min.dao.PolArticleDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

//p667
//수정처리를 위한 서비스클래스이다
public class PolModifyArticleService {
	//필드
	
	private PolArticleDAO polArticleDAO =new PolArticleDAO();
	private PolArticleContentDAO polArticleContentDAO = new PolArticleContentDAO();
	
	//수정처리 -p667 17라인
	//파라미터 수정처리를 위한 세션에서 가져온 회원id,글번호,제목,내용
	public void modify(PolModifyRequest modReq) {
		Connection conn= null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//1.polArticle테이블에 update하기전 해당글번호 가져오기
			PolArticleData polArticle = polArticleDAO.getDetail(conn, modReq.getPolarticleNumber());
			if(polArticle==null) {
				throw new PolArticleNotFoundException();
			}
			
			//1.수정가능여부체크
			if(!canPolModify(modReq.getUserId(), polArticle)) {
				throw new PermissionDeniedException();
			}
			
			//1.article 테이블에 update하는 메서드호출 p668 31라인
			polArticleDAO.update(conn, modReq.getPolarticleNumber(), modReq.getTitle());
			
			//2.article_content테이블에 update하는 메서드호출-p668 33라인
			polArticleContentDAO.update(conn, modReq.getPolarticleNumber(), modReq.getContent());
			
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

	
	private boolean canPolModify(String polmodifyingUserId, PolArticleData polArticle) {
		return polmodifyingUserId.equals(polArticle.getWriter_id());
	}
	
	
	
	
}
