package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDTO;

public class MemberDAO {
	
	// user_info 테이블에 정보 넣기 (회원가입) - > 관리자도 함께 사용가능할듯
	public int joinInsert(Connection conn, MemberDTO mem) throws SQLException {
		// 객체준비
		String sql = "INSERT INTO user_info "
				+ "(user_id, user_pw, user_name, user_birth, user_nickname, user_gender, user_tlno, user_joindate) " + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, CURRENT_DATE)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,mem.getUserId());
		pstmt.setString(2,mem.getUserPw());
		pstmt.setString(3,mem.getUserName());
		pstmt.setTimestamp(4, new Timestamp(mem.getUserBirth().getTime()));
		pstmt.setString(5,mem.getUserNickname());
		pstmt.setString(6,mem.getUserGender());
		pstmt.setString(7,mem.getUserTlno());
		
		// 쿼리실행
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	// userid가 존재하는지 확인
	public boolean idDuplicateCheck(String userId) throws SQLException {
		// 객체준비
		Connection conn = ConnectionProvider.getConnection();
		boolean result = false;
		int cnt = 0;
		String sql = "SELECT COUNT(*) FROM user_info WHERE user_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,userId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) { 
			cnt = rs.getInt(1);
		}
		if (cnt == 0) {
			result = false;
		} else {
			result = true;
		}
		
		return result;
	}
	
	// nickname이 존재하는지 확인
	public boolean nicknameDuplicateCheck(String user_nickname) throws SQLException {
		// 객체준비
		Connection conn = ConnectionProvider.getConnection();
		boolean result = false;
		int cnt = 0;
		String sql = "SELECT COUNT(*) FROM user_info WHERE user_nickname = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,user_nickname);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) { 
			cnt = rs.getInt(1);
		}
		if (cnt == 0) {
			result = false;
		} else {
			result = true;
		}
		
		return result;
	}
	
	// 전화번호가 존재하는지 확인
	public boolean tlnoDuplicateCheck(String user_tlno) throws SQLException {
		// 객체준비
		Connection conn = ConnectionProvider.getConnection();
		boolean result = false;
		int cnt = 0;
		String sql = "SELECT COUNT(*) FROM user_info WHERE user_tlno = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,user_tlno);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) { 
			cnt = rs.getInt(1);
		}
		if (cnt == 0) {
			result = false;
		} else {
			result = true;
		}
		
		return result;
	}

}
