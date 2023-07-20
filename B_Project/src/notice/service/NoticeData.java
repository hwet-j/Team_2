package notice.service;

import notice.model.User_notUse;

public class NoticeData {
	private Integer	number;
	private String user_id;
	private String notice_title;
	private String   notice_content;
	private int	   notice_views;
	public NoticeData() {}
	public NoticeData(Integer number, String user_id, String notice_title, String notice_content, int notice_views) {
		
		this.number = number;
		this.user_id = user_id;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_views = notice_views;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public int getNotice_views() {
		return notice_views;
	}

	public void setNotice_views(int notice_views) {
		this.notice_views = notice_views;
	}

	@Override
	public String toString() {
		return "NoticeData [number=" + number + ", user_id=" + user_id + ", notice_title=" + notice_title
				+ ", notice_content=" + notice_content + ", notice_views=" + notice_views + "]";
	}
	
	
	
}
