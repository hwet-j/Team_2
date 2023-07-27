package gwon.sell.service;

import java.sql.Connection;
import java.sql.SQLException;

import gwon.sell.dao.SellContentDAO;
import gwon.sell.dao.SellDAO;
import gwon.sell.model.SellDTO;
import jdbc.connection.ConnectionProvider;

public class ReadSellService {

	private SellDAO sellDAO = new SellDAO();
	private SellContentDAO sellContentDAO = new SellContentDAO();
	
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
	
	//좋아요 적용된 상세 조회
	public int getLikeSell(int no) throws SellNotFoundException {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			
			int likeCheck = sellContentDAO.plusLike(conn, no);
			
			if(likeCheck != 1) {
				throw new SellNotFoundException();
			}else {
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	
	
}