package member.service;

import member.model.MemberDAO;
import member.model.MemberDTO;

public class FindUserService {
	private MemberDAO  memberDAO = new MemberDAO();
	
	/* 아이디, 비밀번호 찾기에 사용될 메서드 - 전화번호가 DB에 있는가? */
	public boolean existTlnoCheck(String user_tlno) {
		boolean result = false;
		result = memberDAO.tlnoDuplicateCheck(user_tlno);
		
		return result;
	}
	
	/* 아이디, 비밀번호 찾기에 사용될 메서드 - 전화번호로 유저 정보 가져오기 */
	public MemberDTO findUserInfo(String user_tlno) {
		MemberDTO result = memberDAO.findUserInfo(user_tlno);
		
		return result;
	}
	
}











