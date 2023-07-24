package sell.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import sell.dao.SellContentDAO;
import sell.dao.SellDAO;
import sell.model.Sell;

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
			sellContentDAO.update(conn, modRequest.getSell_content(), modRequest.getSell_file(), modRequest.getSell_fav(), modRequest.getSell_no() );
			
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
		//sell.getWriter().getUser_id()는 받아온 내용 (select)
	}

	
}
