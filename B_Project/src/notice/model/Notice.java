package notice.model;

import java.util.Date;


//공지사항 테이블의 값을 저장 및 제공 등의 기능을 가진 클래스
public class Notice {
	//필드
	private Integer number; //notice_no 글번호.PK
	private Writer writer; // writer_id 작성자 아이디
	private String title; // title 제목
	private String content; // content 내용
	private Date writeDate ; // writedate 작성일
	private int views;  //views 조회수
	
	
	public Notice() {}


	public Notice(Integer number, Writer writer, String title, String content, Date writeDate, int views) {

		this.number = number;
		this.writer = writer;
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


	public Writer getWriter() {
		return writer;
	}


	public void setWriter(Writer writer) {
		this.writer = writer;
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
		return "Notice [number=" + number + ", title=" + title + ", content=" + content
				+ ", writeDate=" + writeDate + ", views=" + views + "]";
	}


	
}

