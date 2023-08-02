package min.service;

import java.sql.Connection;
import java.sql.SQLException;

import min.dao.PolArticleContentDAO;
import min.dao.PolArticleDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class ReadPolArticleService {

	
	private PolArticleDAO polArticleDAO =new PolArticleDAO();
	private PolArticleContentDAO polArticleContentDAO =new PolArticleContentDAO();
	
	public PolArticleData getDetail(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//조회수 증가-p659 27라인 => 향후 if문추가예정~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			polArticleDAO.increaseReadCount(conn, no);
			
			PolArticleData polArticleData = polArticleDAO.getDetail(conn, no);
			if(polArticleData==null) {
				throw new PolArticleNotFoundException();
			}
			
			return polArticleData;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBCUtil.close(conn);
		}
	}
	
	
	
}
