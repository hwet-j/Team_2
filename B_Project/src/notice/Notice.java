package notice;

import notice.model.User;

public class Notice {

	private Integer	number;
	private User user_id;
	private String notice_title;
	private String   notice_content;
	private int	   notice_views;
	
	public Notice(Integer number, User user_id, String notice_title, String notice_content, int notice_views) {
		
		this.number = number;
		this.user_id = user_id;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_views = notice_views;
	}
	
	
	public Integer getNumber() {
		return number;
	}
	public User getUser() {
		return user_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public int getNotice_views() {
		return notice_views;
	}


	@Override
	public String toString() {
		return "Notice [number=" + number + ", user_id=" + user_id + ", notice_title=" + notice_title
				+ ", notice_content=" + notice_content + ", notice_views=" + notice_views + "]";
	}
	
	
	
	
}
