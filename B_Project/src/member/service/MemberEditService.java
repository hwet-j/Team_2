package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDAO;
import member.model.MemberDTO;

public class MemberEditService {
	private MemberDAO  memberDAO = new MemberDAO();
	
	/* 회원 정보 가져오기 */
	public MemberDTO getMemberDetail(String user_id) {
		Connection conn = null;
		MemberDTO result = null;
		try {
			conn = ConnectionProvider.getConnection();
			// autoCommit false 설정 
			conn.setAutoCommit(false); 
			
			result = memberDAO.getMemberDetail(conn, user_id);
						
			conn.commit(); 
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCUtil.rollback(conn);//rollback  
			throw new RuntimeException(e);
		}finally {
			JDBCUtil.close(conn); 
		}
	}
	
	/* 회원 정보 수정 */
	public int editMember(MemberDTO member) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			// autoCommit false 설정 
			conn.setAutoCommit(false); 
			
			result = memberDAO.editMember(conn, member);
						
			conn.commit(); 
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCUtil.rollback(conn);  //rollback  
			throw new RuntimeException(e);
		}finally {
			JDBCUtil.close(conn); 
		}
	}
	
	
}











