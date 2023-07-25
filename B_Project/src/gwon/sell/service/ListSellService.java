package gwon.sell.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import gwon.sell.dao.SellDAO;
import gwon.sell.model.Sell;
import jdbc.connection.ConnectionProvider;

public class ListSellService {
	
	private SellDAO sellDAO = new SellDAO();
	int size = 10;
	
	//페이지 기능 구현
	public SellPage getSellPage(int pageNum) {
		Connection conn;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = sellDAO.selectCount(conn); 
			List<Sell> sellContent = sellDAO.select(conn,(pageNum-1)*size,size);
			
			SellPage sellPage = new SellPage(total, pageNum, size, sellContent);
			return sellPage;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public SellPage searchSellPage(int pageNum, SearchRequest searchRequest) {
		
		Connection conn;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = sellDAO.selectCount(conn); 
			
			List<Sell> sellContent = sellDAO.search(conn,searchRequest,(pageNum-1)*size,size);
			
			SellPage sellPage = new SellPage(total, pageNum, size, sellContent);
			return sellPage;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}






