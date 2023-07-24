package auth.service;


//로그인에 성공한 회원정보를 저장 및 제공기능을 가진 클래스 => 세션에 저장될 정보
public class User {
	private String  id;   //회원id
	
	
	public User(String id) {
		this.id = id;
		
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

		
	
}