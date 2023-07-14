package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDAO;
import member.model.MemberDTO;
 
// 컨트롤러 -> Service -> DAO -> DB
public class LoginService {
	
	// 컨트롤러로 부터 전달 받은 회원정보를 DAO에 넘겨줘 회원가입처리 메서드를 호출 
	public int login(String memberid, String password){
		MemberDAO memDao = new MemberDAO();
		Connection conn = null;
		MemberDTO mem = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			
			// SELECT문을 사용할 것이기 때문에 Commit 관련코드는 필요없음
			// Defalut 값이 true인 AutoCommit의 값을 false로 설정한다. -> SQL문이 실행되기 전에 설정
			// conn.setAutoCommit(false);
			
			// AutoCommit이 false로 설정된 연결 객체가 넘어감
			memDao.joinInsert(conn, mem);
			
			// AutoCommit을 막아 놓았기 때문에 사용자가 입력하여 commit
			// conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			// 에러가 발생해도 Rollback 메서드 실행
			JDBCUtil.rollback(conn);
		}
		
		
		return result;
	}
}
