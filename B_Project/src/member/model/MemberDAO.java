package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class MemberDAO {
	
	// user_info 테이블에 정보 넣기 (회원가입) - > 관리자도 함께 사용가능할듯
	public int joinInsert(Connection conn, MemberDTO member) throws SQLException {
		// 가입일을 제외하고 전부 PrepareStatement의 매개변수로 받아 사용
		String sql = "INSERT INTO user_info "
				+ "(user_id, user_pw, user_name, user_birth, user_nickname, user_gender, user_tlno, user_joindate) " + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, CURRENT_DATE)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,member.getUser_id());
		pstmt.setString(2,member.getUser_pw());
		pstmt.setString(3,member.getUser_name());
		// 시간의 경우는 여러 방식이 가능함
		pstmt.setTimestamp(4, new Timestamp(member.getUser_birth().getTime()));
		pstmt.setString(5,member.getUser_nickname());
		pstmt.setString(6,member.getUser_gender());
		pstmt.setString(7,member.getUser_tlno());
		
		// 쿼리실행 (executeUpdate는 실행된 Row수를 반환한다 -> insert문 하나를 실행했으므로 성공하면 1, 실패하면 0을 반환함
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	/* AJAX - userid 중복 확인을 위한 메서드  boolean형태로 작성하여 중복이면 true 그렇지 않으면 false 반환  - 유효성 검사에 사용  */ 
	public boolean idDuplicateCheck(String user_id) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		boolean result = false;
		int cnt = 0;
		
		// 쿼리문에 user_id를 매개변수로 받아와 ROW개수로 결과값 받아오기
		String sql = "SELECT COUNT(*) FROM user_info WHERE user_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,user_id);
		
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
	
	/* AJAX- nickname이 존재하는지 확인 (userid와 동일) - 유효성 검사에 사용 */
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
	
	/* AJAX- 전화번호가 존재하는지 확인 (userid와 동일) - 유효성 검사에 사용 */
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

	/* login WHERE절에 아이디, 비밀번호를 입력했을때, 데이터가 존재한다면 로그인 성공 및 데이터를 Session에 저장할 것이므로 return type -> DTO */ 
	public MemberDTO login(Connection conn, String user_id, String password){
		MemberDTO user_data = null;
		String sql = "SELECT user_id, user_pw, user_name, user_birth, user_nickname, user_gender, user_tlno, user_joindate FROM user_info WHERE user_id = ? AND user_pw = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_id);
			pstmt.setString(2,password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				user_data = new MemberDTO(rs.getString("user_id"), rs.getString("user_pw"), 
						rs.getString("user_name"), rs.getDate("user_birth"), rs.getString("user_nickname"), 
						rs.getString("user_gender"), rs.getString("user_tlno"),  rs.getDate("user_joindate"));
			}
			
			return user_data;
		} catch (SQLException e) {
			e.printStackTrace();
			return user_data;
		} finally { // 자원반납
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		
	}
	
	/* AJAX - login WHERE절에 아이디, 비밀번호,아이디가 존재하는지 확인 (로그인 가능한지) */ 
	public boolean loginCheck(String user_id, String password) throws SQLException{
		Connection conn = ConnectionProvider.getConnection();
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM user_info WHERE user_id = ? AND user_pw = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_id);
			pstmt.setString(2,password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				if(rs.getInt(1) != 0) {
					result = true;
				}
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		
	}

	/* 모든유저의 정보를 불러오는 method- 작업자 : 조중현 */
	public List<MemberDTO> AllMemberShow() throws SQLException{
		String sql = "SELECT * FROM USER_INFO";
		List<MemberDTO> list = new LinkedList<MemberDTO>();
		Connection conn = ConnectionProvider.getConnection();
		ResultSet rs = conn.createStatement().executeQuery(sql);
		
		while(rs.next()) {
			String userId = rs.getString("user_id");
			String userPw = "****";
			String userName = rs.getString("user_name");
			Date userBirth = rs.getDate("user_birth");
			String userNickname = rs.getString("user_nickname"); 
			String userGender = rs.getString("user_gender");
			String userTlno = rs.getString("user_tlno");
			Date userJoinDate = rs.getDate("user_joindate");
			list.add(new MemberDTO(userId, userPw, userName, userBirth, userNickname, userGender, userTlno, userJoinDate));
		}
		return list;
	}

	/* 유저 id를 받아 삭제 시키는 method- 작업자 : 조중현*/
	public int DeleteMember(String id) throws SQLException {
		String sql = "DELETE FROM USER_INFO WHERE USER_ID = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		int delRow = pstmt.executeUpdate();
		return delRow;
	}
}
