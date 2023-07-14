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
		// 가입일을 제외하고 전부 PrepareStatement의 매개변수로 받아 사용
		String sql = "INSERT INTO user_info "
				+ "(user_id, user_pw, user_name, user_birth, user_nickname, user_gender, user_tlno, user_joindate) " + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, CURRENT_DATE)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,mem.getUserId());
		pstmt.setString(2,mem.getUserPw());
		pstmt.setString(3,mem.getUserName());
		// 시간의 경우는 여러 방식이 가능함
		pstmt.setTimestamp(4, new Timestamp(mem.getUserBirth().getTime()));
		pstmt.setString(5,mem.getUserNickname());
		pstmt.setString(6,mem.getUserGender());
		pstmt.setString(7,mem.getUserTlno());
		
		// 쿼리실행 (executeUpdate는 실행된 Row수를 반환한다 -> insert문 하나를 실행했으므로 성공하면 1, 실패하면 0을 반환함
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	/* userid 중복 확인을 위한 메서드  boolean형태로 작성하여 중복이면 true 그렇지 않으면 false 반환  - 유효성 검사에 사용  */ 
	public boolean idDuplicateCheck(String userId) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		boolean result = false;
		int cnt = 0;
		
		// 쿼리문에 user_id를 매개변수로 받아와 ROW개수로 결과값 받아오기
		String sql = "SELECT COUNT(*) FROM user_info WHERE user_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,userId);
		
		ResultSet rs = pstmt.executeQuery();
		// cnt라는 변수에 결과값 저장 -> COUNT(*)의 결과 저장
		while(rs.next()) { 
			cnt = rs.getInt(1);
		}
		
		// 검색된 정보가 없으면 0, 그렇지않으면 1 (1이면 중복된다는 의미로 true설정)
		if (cnt == 0) {
			result = false;
		} else {
			result = true;
		}
		
		return result;
	}
	
	/* nickname이 존재하는지 확인 (userid와 동일) - 유효성 검사에 사용 */
	public boolean nicknameDuplicateCheck(String user_nickname) throws SQLException {
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
	
	/* 전화번호가 존재하는지 확인 (userid와 동일) - 유효성 검사에 사용 */
	public boolean tlnoDuplicateCheck(String user_tlno) throws SQLException {
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
