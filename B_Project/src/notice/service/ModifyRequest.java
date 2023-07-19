package notice.service;

import java.util.Map;

public class ModifyRequest {
	private String user_id;
	private int notice_number;
	private String notice_title;
	private String notice_content;
	
	public ModifyRequest(String user_id, int notice_number, String notice_title, String nontice_content) {
		super();
		this.user_id = user_id;
		this.notice_number = notice_number;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
	}

	public String getUser_id() {
		return user_id;
	}

	public int getNotice_number() {
		return notice_number;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public String getNontice_content() {
		return notice_content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(notice_title==null || notice_title.trim().isEmpty()) {
			errors.put("notice_title",Boolean.TRUE);
		}
		if(notice_content==null || notice_content.trim().isEmpty()) {
			errors.put("notice_content", Boolean.TRUE);
		}
	}

	@Override
	public String toString() {
		return "ModifyRequest [user_id=" + user_id + ", notice_number=" + notice_number + ", notice_title="
				+ notice_title + ", notice_content=" + notice_content + "]";
	}
	

}
