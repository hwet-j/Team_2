package notice.service;

import java.util.Date;

public class NoticeData {
	private Integer	number;
	private String writer_id;
	private String title;
	private String content;
	private Date writeDate;
	private int	 views;
	public NoticeData() {}
	public NoticeData(Integer number, String writer_id, String title, String content, Date writeDate, int views) {
		this.number = number;
		this.writer_id = writer_id;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.views = views;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	@Override
	public String toString() {
		return "NoticeData [number=" + number + ", writer_id=" + writer_id + ", title=" + title + ", content=" + content
				+ ", writeDate=" + writeDate + ", views=" + views + "]";
	}
	
	
	
}
