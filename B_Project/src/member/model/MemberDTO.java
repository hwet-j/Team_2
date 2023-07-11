package member.model;

import java.util.Date;

public class MemberDTO {
	private String userId;         // 회원아이디
    private String userPw;          // 비밀번호
    private String userName;        // 이름
    private Date userBirth;   		// 생일
    private String userNickname;    // 닉네임
    private String userGender;      // 성별
    private String userTlno;        // 핸드폰번호
    private Date userJoinDate; 		// 회원가입날짜
    
	public MemberDTO(String userId, String userPw, String userName, Date userBirth, String userNickname,
			String userGender, String userTlno, Date userJoinDate) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userNickname = userNickname;
		this.userGender = userGender;
		this.userTlno = userTlno;
		this.userJoinDate = userJoinDate;
	}
	
	public MemberDTO(String userId, String userPw, String userName, Date userBirth, String userNickname,
			String userGender, String userTlno) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userNickname = userNickname;
		this.userGender = userGender;
		this.userTlno = userTlno;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserTlno() {
		return userTlno;
	}
	public void setUserTlno(String userTlno) {
		this.userTlno = userTlno;
	}
	public Date getUserJoinDate() {
		return userJoinDate;
	}
	public void setUserJoinDate(Date userJoinDate) {
		this.userJoinDate = userJoinDate;
	}
    
    
    
    
}