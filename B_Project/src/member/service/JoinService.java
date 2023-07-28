package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDAO;
import member.model.MemberDTO;

public class JoinService {
	private MemberDAO  memberDAO = new MemberDAO();
	
	/* 회원가입처리 (일반) */
	public int join(MemberDTO member) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			// autoCommit false 설정 
			conn.setAutoCommit(false); 
			
			// 리턴값 0 : 회원가입 실패, 리턴값 1 : 회원가입 성공
			result = memberDAO.joinInsert(conn, member);
						
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
	
	/* 회원가입처리 (관리자) */
	public int joinAdmin(String user_id,String password) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			// autoCommit false 설정 
			conn.setAutoCommit(false); 
			
			// 리턴값 0 : 회원가입 실패, 리턴값 1 : 회원가입 성공
			result = memberDAO.joinAdminInsert(conn, user_id, password);
						
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
	
	
	/* 회원아이디 중복확인 (관리자) */
	public boolean idDuplicateCheck(String user_id) {
		boolean result = false;
		try {
			result = memberDAO.idDuplicateCheck(user_id);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}











