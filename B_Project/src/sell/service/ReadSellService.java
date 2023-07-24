package sell.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import sell.dao.SellContentDAO;
import sell.dao.SellDAO;
import sell.model.SellDTO;

public class ReadSellService {

	private SellDAO sellDAO = new SellDAO();
	private SellContentDAO sellConetentDAO = new SellContentDAO();
	
	//상세 조회 정보 취합
	public SellDTO getSell(int no) throws SellNotFoundException {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			
			sellDAO.plusReadCnt(conn, no);
			
			SellDTO sellDTO = sellDAO.getSellDTO(conn, no);
			if(sellDTO == null) {
				throw new SellNotFoundException();
			}
			return sellDTO;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	
	
	
}