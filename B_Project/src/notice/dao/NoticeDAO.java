package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdbc.JDBCUtil;
import notice.Notice;
import notice.model.Notice.NoticeContent;
import notice.model.User_notUse;
import notice.service.NoticeData;

public class NoticeDAO {
	Statement stmt = null;
	PreparedStatement stmt1=null;
	PreparedStatement stmt2=null;
	ResultSet rs=null; //

	
	//전체조회
    public  List<Notice> showAllNotice(Connection conn){ 
    	String sql= "select * " + 
    				"from notice " +
    				"order by notice_no desc";
    List<Notice> NoticeList = new LinkedList<>();
    	try {
			 stmt1 =conn.prepareStatement(sql);
			 rs = stmt1.executeQuery();
				while(rs.next()) {
					int notice_no = rs.getInt("notice_no");
					String writer_id = rs.getString("writer_id");
					String title = rs.getString("title");
					String regdate = rs.getString("regdate");
					int read_cnt = rs.getInt("read_cnt");
					Notice notice = new Notice(notice_no, writer_id, title, regdate, read_cnt);
					NoticeList.add(notice);
					
				}
				
				return NoticeList;
    	} catch (SQLException e) {
			e.printStackTrace();
			}finally {
				JDBCUtil.close(stmt1);
				JDBCUtil.close(rs);
			}
    		return null;
		}
		@SuppressWarnings("finally")//

   public NoticeContent showNoticeContent(Connection conn,int noticeNum) {
			String sql = "SELECT N.notice_no, N.writer_id, N.title, N.regdate, N.read_cnt, N.content " +
						"FROM notice N, notice_content N" ;
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
	

