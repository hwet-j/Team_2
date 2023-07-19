package auth.service;

public class Member {
	private String id;
	
	
	public Member(String id) {
		this.id = id;
	
	}

	

	

	public String getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Member [id=" + id + "]";
	}






	
	
	
}
