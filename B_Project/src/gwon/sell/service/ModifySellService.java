package gwon.sell.service;

import java.sql.Connection;
import java.sql.SQLException;

import gwon.sell.dao.SellContentDAO;
import gwon.sell.dao.SellDAO;
import gwon.sell.model.Sell;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class ModifySellService {
	
	private SellDAO sellDAO = new SellDAO();
	private SellContentDAO sellContentDAO = new SellContentDAO();

	public void modify(ModifyRequest modRequest) throws Exception {
		Connection conn = null;
		try {                           
			conn = ConnectionProvider.getConnection();                                                                                                                                                                                                                              			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Sell sell = sellDAO.selectUsingID(conn, modRequest.getSell_no());
			
			if(sell==null) {
				throw new SellNotFoundException();
			}
			
			if(!canModify(modRequest.getUser_id(), sell)) {
				throw new PermissionDeniedException();
			}
			
			sellDAO.update(conn, modRequest.getSell_no(), modRequest.getSell_title(), modRequest.getSell_category(), modRequest.getSell_price(), modRequest.getSell_loc());
			sellContentDAO.update(conn, modRequest.getSell_content(), modRequest.getSell_file(), modRequest.getSell_no() );
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCUtil.rollback(conn);
			throw new RuntimeException();
		}catch(PermissionDeniedException e){ 
			JDBCUtil.rollback(conn);
			throw e;
		}finally {
			JDBCUtil.close(conn);
		}
	}
	
	private boolean canModify(String modifiedUser_id, Sell sell) {
		return sell.getWriter().getUser_id().equals(modifiedUser_id);
	}
	
}