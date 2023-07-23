package auth.service;


//로그인에 성공한 회원정보를 저장 및 제공기능을 가진 클래스 => 세션에 저장될 정보
public class User {
	private String  id;   //회원id
	private String  name; //회원명
	
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	
}