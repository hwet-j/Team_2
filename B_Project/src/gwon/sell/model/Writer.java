package gwon.sell.model;

public class Writer {
	private String  user_id;   
	private String  user_name;
	
	public Writer(String user_id, String user_name) {
		this.user_id = user_id;
		this.user_name = user_name;
	}

	public Writer(String id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	@Override
	public String toString() {
		return "Writer [user_id=" + user_id + ", user_name=" + user_name + "]";
	} 

	
}