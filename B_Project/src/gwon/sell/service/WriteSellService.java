package gwon.sell.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import gwon.sell.dao.SellContentDAO;
import gwon.sell.dao.SellDAO;
import gwon.sell.model.Sell;
import gwon.sell.model.SellContent;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class WriteSellService {

	private SellDAO sellDAO = new SellDAO();
	private SellContentDAO sellContentDAO =  new SellContentDAO();
	
	public Integer write(WriteRequest writeR) throws SQLException {
		
		Connection conn = null;
		
	 try {
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		
		Sell sell = toSell(writeR);
		System.out.println(sell);
		Sell wroteSell = sellDAO.insert(conn, sell);
		System.out.println(wroteSell);
		if(wroteSell == null) {
			throw new RuntimeException();
		}
		
		SellContent sell_content = new SellContent(wroteSell.getSell_no(),writeR.getSell_content(),writeR.getSell_file());
		SellContent wroteSellContent = sellContentDAO.insert(conn, sell_content);
		
		if(wroteSellContent == null) {
			throw new RuntimeException();
		}
		
		conn.commit();
		
		return wroteSellContent.getSell_no();
		
	} catch (SQLException e) {
		JDBCUtil.rollback(conn);
		throw new RuntimeException(e);
	} catch (RuntimeException e) {
		JDBCUtil.rollback(conn);
		throw new RuntimeException(e);
	} finally {
		JDBCUtil.close(conn);
	}
}
	
	
	private Sell toSell(WriteRequest writeR) {
		Date now = new Date();
		return new Sell(null, writeR.getWriter(), writeR.getSell_title(), writeR.getSell_category(),
				writeR.getSell_price(), writeR.getSell_loc(), now, now, 0);
	}
}
