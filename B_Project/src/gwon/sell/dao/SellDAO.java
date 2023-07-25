package gwon.sell.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gwon.sell.model.Sell;
import gwon.sell.model.SellDTO;
import gwon.sell.model.Writer;
import gwon.sell.service.SearchRequest;
import jdbc.JDBCUtil;

public class SellDAO {
	
	//1. 전체조회
	public int selectCount(Connection conn) throws SQLException {
		
		String sql = "select count(*) from gwon_sell";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int totalCount = 0;
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
			return totalCount;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}
	}
	
	public List<Sell> select(Connection conn,int startRow, int size) throws SQLException {
		String sql="select sell_no, user_id, user_name, sell_title, sell_category, sell_price, sell_loc, " + 
					"sell_regdate, sell_moddate, sell_read_cnt " + 
				    "from gwon_sell " + 
				    "order by sell_no desc  limit ?,?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,startRow);
			stmt.setInt(2,size);
			rs = stmt.executeQuery();
		
			List<Sell> result = new ArrayList<>();
			while(rs.next()) {
				result.add(changeSell(rs)); 
			}
			
			return result;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}
	}

	private Sell changeSell(ResultSet rs) throws SQLException {
		return  new Sell(rs.getInt("sell_no"),
			    		new Writer(rs.getString("user_id"), rs.getString("user_name")),
					    rs.getString("sell_title"),
					    rs.getString("sell_category"),
					    rs.getInt("sell_price"),
					    rs.getString("sell_loc"),
					    toTimestamp(rs.getDate("sell_regdate")),
					    toTimestamp(rs.getDate("sell_moddate")),
					    rs.getInt("sell_read_cnt")  );
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	
	//2. 상세조회
		public Sell selectUsingID(Connection conn, int sell_no) throws SQLException {
			
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql = "select * from gwon_sell where sell_no = ?";
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, sell_no);
				rs = stmt.executeQuery();
				Sell sell = null;
				if(rs.next()) {
					sell = changeSell(rs);
				}
				return sell;
			} finally {
				JDBCUtil.close(rs);
				JDBCUtil.close(stmt);
			}
		}
		
		public void plusReadCnt(Connection conn, int no) throws SQLException {
			String sql = "update gwon_sell " +
						"set sell_read_cnt = sell_read_cnt + 1 " +
						"where sell_no = ?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.executeUpdate();
			} finally {
				JDBCUtil.close(stmt);
			}
			
		}

		public SellDTO getSellDTO(Connection conn,  int no) throws SQLException {
			String sql = "select a.sell_no, a.user_id, a.user_name, a.sell_title, " +
						"a.sell_category, a.sell_price, a.sell_loc, a.sell_regDate, " +
						"a.sell_modDate, a.sell_read_cnt, b.sell_content, b.sell_file, b.sell_fav "+
						"from gwon_sell a, gwon_sell_content b " +
						"where a.sell_no = b.sell_no " +
						"and " + "a.sell_no = ?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			SellDTO dto = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
				if(rs.next()) {
					dto = new SellDTO();
					dto.setSell_no(rs.getInt("sell_no"));
					dto.setUser_id(rs.getString("user_id"));
					dto.setUser_name(rs.getString("user_name"));
					dto.setSell_title(rs.getString("sell_title"));
					dto.setSell_category(rs.getString("sell_category"));
					dto.setSell_price(rs.getInt("sell_price"));
					dto.setSell_loc(rs.getString("sell_loc"));
					dto.setSell_regDate(rs.getDate("sell_regDate"));
					dto.setSell_modDate(rs.getDate("sell_modDate"));
					dto.setSell_read_cnt(rs.getInt("sell_read_cnt"));
					dto.setSell_content(rs.getString("sell_content"));
					dto.setSell_file(rs.getString("sell_file"));
					dto.setSell_fav(rs.getString("sell_fav"));
					
				}
				System.out.println(dto);
				return dto;
			}  finally {
				JDBCUtil.close(rs);
				JDBCUtil.close(stmt);
			}
		}


		
		//3. 글 작성
		public Sell insert(Connection conn, Sell sell) throws SQLException {
			
			
			String sql = "insert into gwon_sell(user_id, user_name, " +
						"sell_title, sell_category, sell_price, sell_loc, " +
						"sell_regdate, sell_moddate, sell_read_cnt) " +
						"values(?,?,?,?,?,?,now(),now(),0)";
			
			PreparedStatement stmt = null;
			PreparedStatement insertedstmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, sell.getWriter().getUser_id());
				stmt.setString(2, sell.getWriter().getUser_name());
				stmt.setString(3, sell.getSell_title());
				stmt.setString(4, sell.getSell_category());
				stmt.setInt(5, sell.getSell_price());
				stmt.setString(6, sell.getSell_loc());
				
				
				int insertedQuery = stmt.executeUpdate();
				
				if(insertedQuery > 0) {
					String insertedsql = "select last_insert_id() from gwon_sell";
					insertedstmt = conn.prepareStatement(insertedsql);
					rs = insertedstmt.executeQuery();
					if(rs.next()) {
						Integer lastSellNo = rs.getInt(1);
						return new Sell(lastSellNo, sell.getWriter(), 
								sell.getSell_title(), sell.getSell_category(), sell.getSell_price(),
								sell.getSell_loc(), sell.getSell_regdate(), sell.getSell_moddate(), 0);
					}
				}return null;
				}finally {
					JDBCUtil.close(rs);
					JDBCUtil.close(stmt);
					
				}
		}
		

		//4. 글 삭제
		public int delete(Connection conn, int no)  throws SQLException {
			String sql = "delete from gwon_sell where sell_no=?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,no);
				return stmt.executeUpdate();
			}finally {
				JDBCUtil.close(stmt);
			}
		}
		
		
		//5. 글 수정
		public int update(Connection conn, int sell_no, String sell_title, String sell_category, int sell_price, String sell_loc)  throws SQLException {
			String sql = "update gwon_sell " + 
						 "set sell_title=?, sell_category=?, sell_price=?, sell_loc=?, sell_moddate=now() " + 
						 "where sell_no=?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,sell_title);
				stmt.setString(2,sell_category);
				stmt.setInt(3,sell_price);
				stmt.setString(4,sell_loc);
				stmt.setInt(5,sell_no);
				return stmt.executeUpdate();
			}finally {
				JDBCUtil.close(stmt);
			}
		}
		
		//추가 기능 1. - 검색 기능
		public List<Sell> search(Connection conn, SearchRequest searchRequest, int startRow, int size) throws SQLException {
			
			String sql = null;
			PreparedStatement stmt = null;
			List<Sell> list = new ArrayList<>();
			ResultSet rs = null;
			String searchColumn	= searchRequest.getSearchColumn();
			String searchText	= searchRequest.getSearchText();
			System.out.println(searchColumn);
			System.out.println(searchText);
			try {
				System.out.println("error");
				sql = "select * from gwon_sell where " + searchColumn + 
						" like '%"+ searchText.trim() +"%' order by sell_no desc limit ?,?";

				System.out.println(sql);
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,startRow);
				stmt.setInt(2,size);
				System.out.println("startRow");
				System.out.println("size");
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					list.add(searchSell(rs));
				}
				return list;
			} finally {
				JDBCUtil.close(rs);
				JDBCUtil.close(stmt);
			}
			
		}
		

		private Sell searchSell(ResultSet rs) throws SQLException {
			return  new Sell(rs.getInt("sell_no"),
				    		new Writer(rs.getString("user_id"), rs.getString("user_name")),
						    rs.getString("sell_title"),
						    rs.getString("sell_category"),
						    rs.getInt("sell_price"),
						    rs.getString("sell_loc"),
						    toTimestamp(rs.getDate("sell_regdate")),
						    toTimestamp(rs.getDate("sell_moddate")),
						    rs.getInt("sell_read_cnt")  );
		}
		
		// 2. 		
		
		

}