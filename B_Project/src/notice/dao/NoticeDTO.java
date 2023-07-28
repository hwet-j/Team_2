package notice.dao;

import java.util.Date;

public class NoticeDTO {
	private int notice_no;
	private String writer_id;
	private String title;
	private String content;
	private Date writedate;
	private int views;
	public NoticeDTO(int notice_no, String writer_id, String title, String content, Date writedate, int views) {
		//매개변수에 입력받으면 선언된 변수에 값을 저장
		super();
		this.notice_no = notice_no;
		this.writer_id = writer_id;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.views = views;
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
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
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	
	
}
