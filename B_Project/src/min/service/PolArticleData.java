package min.service;

import java.util.Date;

public class PolArticleData {
	
	//필드
	private Integer	number;		//PolArticle_no 	글번호
	private String	writer_id; 	//writer_id		작성자id
	private String	writer_name; //writer_name	작성자명
	private String	title; 		//title			제목
	private Date	regDate;	//regdate		작성일
	private Date	modifiedDate;//moddate		수정일
	private int		readCount;	//read_cnt		조회수
	private String	content; 	//content		내용
	
	
	public PolArticleData() {}
	
	
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





	public String getWriter_name() {
		return writer_name;
	}





	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public Date getRegDate() {
		return regDate;
	}





	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}





	public Date getModifiedDate() {
		return modifiedDate;
	}





	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}





	public int getReadCount() {
		return readCount;
	}





	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "PolArticleData [number=" + number + ", writer_id=" + writer_id + ", writer_name=" + writer_name
				+ ", title=" + title + ", regDate=" + regDate + ", modifiedDate=" + modifiedDate + ", readCount="
				+ readCount + ", content=" + content + "]";
	}





	
	
	
	
	
	
}
