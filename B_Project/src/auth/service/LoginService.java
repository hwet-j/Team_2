package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDAO;
import member.model.MemberDTO;
 
public class LoginService {
	
	// 컨트롤러로 부터 전달 받은 회원정보를 DAO에 넘겨줘 회원가입처리 메서드를 호출 
	public MemberDTO login(String memberid, String password){
		MemberDAO memDao = new MemberDAO();
		Connection conn = null;
		MemberDTO user_data = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			user_data = memDao.login(conn, memberid, password);
		} catch (SQLException e) {
			e.printStackTrace();
			// 에러가 발생하면 Rollback 메서드 실행
			JDBCUtil.rollback(conn);
		} finally { // 자원반납
			JDBCUtil.close(conn);
		}
		
		return user_data;
	}
}
