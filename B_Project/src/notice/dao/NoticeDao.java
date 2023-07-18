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

public class NoticeDao {
		
	public int getDetail(Connection conn) throws SQLException {
		String sql = "select notice_no, user_id, notice_title, notice_content, notice_views " +
				"from notice " +
				"where notice_no=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int notice_no = 0;
			if(rs.next()) {
				notice_no=rs.getInt("notice_no");
			}
			return notice_no;			
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}	
	}	
	//
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
    				new User(rs.getString("user_id")),			
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
    
		
	}
	

