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
	
	//회원가입처리 
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
	
	
}











