package gwon.sell.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gwon.sell.model.SellContent;
import jdbc.JDBCUtil;

public class SellContentDAO {

	//2. 상세조회
	public SellContent selectUsingID(Connection conn, int sell_no) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from gwon_sell " + "where sell_no = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, sell_no);
			rs = stmt.executeQuery();
			SellContent sellContent = null;
			if(rs.next()) {
				sellContent = changeSellContent(rs);
			}
			return sellContent;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}
	}
	
	private SellContent changeSellContent(ResultSet rs) throws SQLException {
		return new SellContent(rs.getInt("sell_no"), rs.getString("sell_content"), rs.getString("sell_file"), rs.getInt("sell_fav"));
	}
	

	//3. 글 작성
	public SellContent insert(Connection conn, SellContent sell_content) throws SQLException  {
		
		String sql = "insert into gwon_sell_content(sell_no, sell_content, sell_file, sell_fav) " +
					"values(?,?,?,0)";
		
		PreparedStatement stmt = null;
		PreparedStatement insertedstmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,sell_content.getSell_no());
			stmt.setString(2,sell_content.getSell_content());
			stmt.setString(3,sell_content.getSell_file());
			
			int insertedQuery = stmt.executeUpdate();
			
			if(insertedQuery > 0) {
				return sell_content;
			} else {
				return null;
			} 
		}finally {
			JDBCUtil.close(stmt);
			JDBCUtil.close(insertedstmt);
		}
	
	}
	
	
	//4. 글 삭제
	public void Delete(Connection conn, int no) {
		
		String sql = "delete from gwon_sell_content where sell_no = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
		} catch (SQLException e) {
		} finally {
			JDBCUtil.close(stmt);
		}
	}
	
	//5. 글 수정
	public int update(Connection conn, String sell_content, String sell_file, int sell_no)  throws SQLException {
		String sql = "update gwon_sell_content " + 
					 "set sell_content = ?, sell_file = ? " + 
					 "where sell_no = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,sell_content);
			stmt.setString(2,sell_file);
			stmt.setInt(3,sell_no);
			return stmt.executeUpdate();
		}finally {
			JDBCUtil.close(stmt);
		}
	}
	
	
	
	// 추가 기능 2. 좋아요 기능
	
	public int plusLike(Connection conn, int no) throws SQLException {
		
		String sql = "update gwon_sell_content " +
					 "set sell_fav = sell_fav + 1 " +
					 "where sell_no = ?";
		
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			stmt.executeUpdate();
			return 1;
		} finally {
			JDBCUtil.close(stmt);
		}
	}
	
	
	
	
	
	
	
	
}