package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;
import notice.Notice;
import notice.model.User;
import notice.service.NoticeData;

public class NoticeDao {
	
	public int update(Connection conn, int no, String title)throws SQLException {
		String sql="update notice" +
				"set notice_title=?" +
				"where notice_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,title);
			stmt.setInt(2,no);
			return stmt.executeUpdate();
		}finally {
			JDBCUtil.close(stmt);
		}
	}
	
	public NoticeData getDetail(Connection conn, int no) throws SQLException {
		String sql = "select notice_no, user_id, notice_title, notice_content, notice_views " +
				"from notice " +
				"where notice_no=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			NoticeData noc = null;
			if(rs.next()) {
				noc = new NoticeData();
				noc.setNumber(rs.getInt("notice_no"));
				noc.setUser_id(rs.getString("user_id"));
				noc.setNotice_title(rs.getString("notice_title"));
				noc.setNotice_content(rs.getString("notice_content"));
				noc.setNotice_views(rs.getInt("notice_views"));
				
				System.out.println("NoticeDao에서 getDetail() NoticeData noc ="+noc);
			}
			return noc;			
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}	
	}	
	
	public void increaseNotice_Views(Connection conn, int no)throws SQLException {
		String sql = "update notice " +
					 "set notice_views=notice_views+1 " +
					 "where notice_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			stmt.executeUpdate();
		}finally {
			JDBCUtil.close(stmt);
		}
	}
	
	
    public  List<Notice> select(Connection conn,int startRow,int size )  throws SQLException{
    	String sql= "select notice_no, user_id, notice_title, notice_content, notice_views " + 
    				"from notice " +
    				"order by notice_no desc  limit ?,?";
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	try {
			 stmt =conn.prepareStatement(sql);
			 	stmt.setInt(1,startRow);
			 	stmt.setInt(2,size);
				
				rs = stmt.executeQuery();
			
				List<Notice> result = new ArrayList<Notice>();
		
				while(rs.next()) {
				
					result.add( convertNotice(rs) ); 
				}
				
				return result;
			}finally {
				JDBCUtil.close(rs);
				JDBCUtil.close(stmt);
			}
		}
    

    private Notice convertNotice(ResultSet rs) throws SQLException {
		return
		new Notice(rs.getInt("notice_no"),
    				rs.getString("user_id"),			
    				rs.getString("notice_title"),
    				rs.getString("notice_content"),
    				rs.getInt("notice_views")
    				);
    }
    
    
    
    

	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from notice";
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			 stmt =conn.prepareStatement(sql);
			 rs =stmt.executeQuery();
			int totalCNT= 0; 
			if(rs.next()) {
				totalCNT=rs.getInt(1);
			}
			return totalCNT;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}
		
	}
    
	public Notice insert(Connection conn, Notice notice) throws SQLException {
		System.out.println("NoticeDao-insert()진입");
		
	String sql="insert into notice(user_id,notice_title,notice_content,notice_views) "+
	 "values(?,?,?,0)";
	PreparedStatement stmt = null;
	PreparedStatement stmt2 = null;
	ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setObject(1,notice.getUser_id());
			stmt.setString(2,notice.getNotice_title());
			stmt.setString(3,notice.getNotice_content());
			int insertedCount = stmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt2 = conn.prepareStatement("select last_insert_user_id()from notice");
				rs =stmt2.executeQuery();
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Notice(newNum,notice.getUser_id(),notice.getNotice_title(),notice.getNotice_content(),0);
				}
			}
			return null;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
			JDBCUtil.close(stmt2);
		}
	}
	
	
}
	

