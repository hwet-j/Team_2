package gwon.sell.service;

import java.sql.Connection;
import java.sql.SQLException;

import gwon.sell.dao.SellDAO;
import gwon.sell.model.SellDTO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteSellService {
	
	SellDAO sellDAO = new SellDAO();
	
	public void delete(int no) throws SellNotFoundException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			SellDTO sellDTO = sellDAO.getSellDTO(conn, no);
			if(sellDTO == null) {
				throw new SellNotFoundException();
			}
			
			sellDAO.delete(conn, no);
			
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
