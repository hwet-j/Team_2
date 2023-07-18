package notice.service;

import java.util.Map;

import notice.model.User;

public class WriteRequest {
	private User user_id;
	private String title;
	private String content;
	
	public WriteRequest(User user_id, String title, String content) {

		this.user_id = user_id;
		this.title = title;
		this.content = content;
	}

	public User getUser() {
		return user_id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if( title==null || title.trim().isEmpty() ) {
			errors.put("title", Boolean.TRUE);
		}
		if( content==null || content.trim().isEmpty() ) {
			errors.put("content", Boolean.TRUE);
		}
	}

	@Override
	public String toString() {
		return "WriteRequest [user_id=" + user_id + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
}
