package min.service;

import java.sql.Connection;
import java.sql.SQLException;

import min.dao.PolArticleDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class PolDeleteArticleService {
PolArticleDAO polarticleDAO = new PolArticleDAO();
	
	//update쿼리를 통한 글삭제
	public void deleteUp(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//1.article테이블에 delete하기전 해당글번호 가져오기
			PolArticleData polarticle = polarticleDAO.getDetail(conn, no);
			if(polarticle==null) {
				throw new PolArticleNotFoundException();
			}
			
			//2.article테이블에 update하는 메서드호출
			polarticleDAO.deleteUp(conn, no);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JDBCUtil.close(conn);
		}		
		
	}
	
	
	
	//delete쿼리를 통한 글삭제
	public void delete(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//1.article테이블에 delete하기전 해당글번호 가져오기
			PolArticleData polarticle = polarticleDAO.getDetail(conn, no);
			if(polarticle==null) {
				throw new PolArticleNotFoundException();
			}
			
			//2.article테이블에 delete하는 메서드호출
			polarticleDAO.delete(conn, no);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JDBCUtil.close(conn);
		}
		
	}


}