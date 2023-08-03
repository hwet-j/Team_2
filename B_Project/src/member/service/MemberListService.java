package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDAO;
import member.model.MemberDTO;

public class MemberListService {
	private MemberDAO  memberDAO = new MemberDAO();
	
	/* 회원 정보 가져오기 */
	public List<MemberDTO> AllMemberShow(int page_no, int list_size, String search_type,String keyword) {
		Connection conn = null;
		List<MemberDTO> result = new ArrayList<MemberDTO>();
		try {
			conn = ConnectionProvider.getConnection();
			// autoCommit false 설정 
			conn.setAutoCommit(false); 
			
			result = memberDAO.AllMemberShow(conn, page_no, list_size, search_type, keyword);
						
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
	
	/* 검색된 회원 총 길이 가져오기 */
	public int allMemberCount(String search_type,String keyword) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			// autoCommit false 설정 
			conn.setAutoCommit(false); 
			
			result = memberDAO.allMemberCount(conn, search_type, keyword);
			
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
	
	/* 전체 회원 아이디 가져오기 */
	public List<String> AllMemberID() {
		Connection conn = null;
		List<String> result = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			// autoCommit false 설정 
			conn.setAutoCommit(false); 
			
			result = memberDAO.AllMemberID(conn);
						
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











