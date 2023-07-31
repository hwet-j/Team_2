package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

public class MemberDAO {
	
	/* user_info 테이블에 정보 넣기 (회원가입) - > 관리자도 함께 사용가능할듯 */
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
		
		JDBCUtil.close(pstmt);
		
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
		
		JDBCUtil.close(rs);
		JDBCUtil.close(pstmt);
		JDBCUtil.close(conn);
		
		return result;
	}
	
	/* AJAX - nickname이 존재하는지 확인 (userid와 동일) - 유효성 검사에 사용 */
	public boolean nicknameDuplicateCheck(String user_nickname){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			
			int cnt = 0;
			String sql = "SELECT COUNT(*) FROM user_info WHERE user_nickname = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_nickname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				cnt = rs.getInt(1);
			}
			if (cnt == 0) {
				result = false;
			} else {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return result;
	}
	
	/* AJAX- 전화번호가 존재하는지 확인 (userid와 동일) - 유효성 검사에 사용 */
	public boolean tlnoDuplicateCheck(String user_tlno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			
			int cnt = 0;
			String sql = "SELECT COUNT(*) FROM user_info WHERE user_tlno = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,user_tlno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				cnt = rs.getInt(1);
			}
			if (cnt == 0) {
				result = false;
			} else {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		return result;
	}
	
	/* 전화번호로 회원 정보 가져오기 - 아이디, 비밀번호 찾기 */
	public MemberDTO findUserInfo(String user_tlno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO user_data = null;
		
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			
			int cnt = 0;
			String sql = "SELECT * FROM user_info WHERE user_tlno = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user_tlno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				user_data = new MemberDTO(rs.getString("user_id"), rs.getString("user_pw"), 
						rs.getString("user_name"), rs.getDate("user_birth"), rs.getString("user_nickname"), 
						rs.getString("user_gender"), rs.getString("user_tlno"),  rs.getDate("user_joindate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		return user_data;
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

	/* 유저의 정보를 불러오는 method- 작업자 : 조중현  */
	public List<MemberDTO> AllMemberShow(Connection conn, int page_no, int list_size, String search_type,String keyword) throws SQLException{
		
		List<MemberDTO> list = new LinkedList<MemberDTO>();
		int start_index = (page_no - 1) * list_size;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 검색
		if(!search_type.equals("")) {
			if (search_type.equals("id")) {
				sql = "SELECT * FROM user_info WHERE user_id LIKE CONCAT('%', ? , '%') LIMIT ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, start_index);
				pstmt.setInt(3, list_size);
			} else if (search_type.equals("name")) {
				sql = "SELECT * FROM user_info WHERE user_name LIKE CONCAT('%', ? , '%') LIMIT ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, start_index);
				pstmt.setInt(3, list_size);
			} else if (search_type.equals("nickname")) {
				sql = "SELECT * FROM user_info WHERE user_nickname LIKE CONCAT('%', ? , '%') LIMIT ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, start_index);
				pstmt.setInt(3, list_size);
			} else if (search_type.equals("join_date")) {
				sql = "SELECT * FROM user_info WHERE user_joindate LIKE CONCAT('%', ? , '%') LIMIT ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, start_index);
				pstmt.setInt(3, list_size);
			} 
		} else {
			sql = "SELECT * FROM user_info LIMIT ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start_index);
			pstmt.setInt(2, list_size);
		}
		
		rs = pstmt.executeQuery();
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
		
		JDBCUtil.close(rs);
		JDBCUtil.close(pstmt);
		return list;
	}

	/* 유저 id를 받아 삭제 시키는 method- 작업자 : 조중현*/
	public int DeleteMember(String id) throws SQLException {
		String sql = "DELETE FROM user_info WHERE USER_ID = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		int delRow = pstmt.executeUpdate();
		
		JDBCUtil.close(pstmt);
		JDBCUtil.close(conn);
		return delRow;
	}
	
	/* 유저 개수를 반환하는 메서드  */
	public int allMemberCount(Connection conn, String search_type,String keyword){
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			if(!search_type.equals("")) {
				if (search_type.equals("id")) {
					sql = "SELECT COUNT(*) total_count FROM user_info WHERE user_id LIKE CONCAT('%', ? , '%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keyword);
				} else if (search_type.equals("name")) {
					sql = "SELECT COUNT(*) total_count FROM user_info WHERE user_name LIKE CONCAT('%', ? , '%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keyword);
				} else if (search_type.equals("nickname")) {
					sql = "SELECT COUNT(*) total_count FROM user_info WHERE user_nickname LIKE CONCAT('%', ? , '%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keyword);
				} else if (search_type.equals("join_date")) {
					sql = "SELECT COUNT(*) total_count FROM user_info WHERE user_joindate LIKE CONCAT('%', ? , '%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keyword);
				} 
			} else {
				sql = "SELECT COUNT(*) total_count FROM user_info";
				pstmt = conn.prepareStatement(sql);
			}
	    	rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("total_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JDBCUtil.close(rs);
		JDBCUtil.close(pstmt);
		return result;
	}
	
	/* 관리자용 회원가입 메서드 (아이디와 비밀번호만 입력) */
	public int joinAdminInsert(Connection conn, String user_id, String password) {
		String sql = "INSERT INTO user_info "
				+ "(user_id, user_pw) " + 
				"VALUES(?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_id);
			pstmt.setString(2,password);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 자원반납
			JDBCUtil.close(pstmt);
		}
		
		return 0;
	}
	
	/* 하나의 회원 정보를 가져오는 메서드 - 마이페이지, 관리자페이지 */ 
	public MemberDTO getMemberDetail(Connection conn, String user_id){
		MemberDTO user_data = null;
		String sql = "SELECT user_id, user_pw, user_name, user_birth, user_nickname, user_gender, user_tlno, user_joindate "
				+ "FROM user_info WHERE user_id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				user_data = new MemberDTO(rs.getString("user_id"), rs.getString("user_pw"), 
						rs.getString("user_name"), rs.getDate("user_birth"), rs.getString("user_nickname"), 
						rs.getString("user_gender"), rs.getString("user_tlno"),  rs.getDate("user_joindate"));
			}
			
			return user_data;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 자원반납
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return user_data;
	}
	
	/* 회원정보를 수정하는 메서드 - 관리자 페이지에서 관리자가 수정 */
	public int editMember(Connection conn, MemberDTO member) {
		String sql = "UPDATE user_info " + 
				"SET user_pw = ?, user_name = ?, user_birth = ?," + 
				"user_nickname = ?, user_gender = ?, user_tlno = ?, user_joindate = ? " + 
				"WHERE user_id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getUser_pw());
			
			if (member.getUser_name() == null || member.getUser_name().equals("")) {
				pstmt.setNull(2, Types.VARCHAR);		// DB에 null값을 입력하는 방법
			} else {
				pstmt.setString(2,member.getUser_name());
			}
			
			if (member.getUser_birth() == null || member.getUser_birth().equals("")) {
				pstmt.setNull(3, Types.DATE);
			} else {
				pstmt.setTimestamp(3, new Timestamp(member.getUser_birth().getTime()));
			}
			
			if (member.getUser_nickname() == null || member.getUser_nickname().equals("")) {
				pstmt.setNull(4, Types.VARCHAR);
			} else {
				pstmt.setString(4,member.getUser_nickname());
			}
			
			
			if (member.getUser_gender() == null || member.getUser_gender().equals("")) {
				pstmt.setNull(5, Types.VARCHAR);
			} else {
				pstmt.setString(5, member.getUser_gender());
			}
			
			if (member.getUser_tlno() == null || member.getUser_tlno().equals("")) {
				pstmt.setNull(6, Types.VARCHAR);	
			} else {
				pstmt.setString(6,member.getUser_tlno());
			}
			
			pstmt.setTimestamp(7, new Timestamp(member.getUser_join_date().getTime()));
			
			pstmt.setString(8,member.getUser_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return result;
	}
	
	
	
}
