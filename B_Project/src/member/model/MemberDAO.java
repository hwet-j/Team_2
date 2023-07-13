package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JDBCUtil;
import member.model.MemberDTO;

public class MemberDAO {
	
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

}
