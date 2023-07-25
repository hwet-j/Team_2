package member.model;

import java.util.Date;

public class MemberDTO {
    private String user_id;         // 회원아이디
    private String user_pw;         // 비밀번호
    private String user_name;       // 이름
    private Date user_birth;        // 생일
    private String user_nickname;   // 닉네임
    private String user_gender;     // 성별
    private String user_tlno;       // 핸드폰번호
    private Date user_join_date;    // 회원가입날짜
    
    
    public MemberDTO(String user_id,String user_name) {
    	this.user_id = user_id;
    	this.user_name = user_name;
    }
    
    public MemberDTO(String user_id, String user_pw, String user_name, Date user_birth, String user_nickname,
            String user_gender, String user_tlno, Date user_join_date) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_birth = user_birth;
        this.user_nickname = user_nickname;
        this.user_gender = user_gender;
        this.user_tlno = user_tlno;
        this.user_join_date = user_join_date;
    }
    
    
    public MemberDTO(String user_id, String user_pw, String user_name, Date user_birth, String user_nickname,
            String user_gender, String user_tlno) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_birth = user_birth;
        this.user_nickname = user_nickname;
        this.user_gender = user_gender;
        this.user_tlno = user_tlno;
    }
    
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_pw() {
        return user_pw;
    }
    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public Date getUser_birth() {
        return user_birth;
    }
    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }
    public String getUser_nickname() {
        return user_nickname;
    }
    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }
    public String getUser_gender() {
        return user_gender;
    }
    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }
    public String getUser_tlno() {
        return user_tlno;
    }
    public void setUser_tlno(String user_tlno) {
        this.user_tlno = user_tlno;
    }
    public Date getUser_join_date() {
        return user_join_date;
    }
    public void setUser_join_date(Date user_join_date) {
        this.user_join_date = user_join_date;
    }
}
