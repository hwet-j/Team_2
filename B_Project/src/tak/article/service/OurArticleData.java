package tak.article.service;

import java.util.Date;

//교재에서는 P655 selectById()이용하여 article테이블의 내용가져오기
//			 p656 selectById()이용하여 article_content테이블의 내용가져오기
//2개의 테이블의 내용을 ArticleData클래스(p657)로 묶어서 처리하였다
//article테이블 => Article(p631)
//article_content테이블 => Article(p632)

//여기에서는 2개의 테이블의 내용을 join하여 처리하는 방식으로 진행
//(article테이블,article_content테이블)정보를 저장 및 제공하는 기능을 가진 클래승이다
public class OurArticleData {
	//필드
	private Integer	number;		//article_no 	글번호
	private String	writer_id; 	//writer_id		작성자id
	private String	writer_name; //writer_name	작성자명
	private String	title; 		//title			제목
	private Date	regDate;	//regdate		작성일
	private Date	modifiedDate;//moddate		수정일
	private int		readCount;	//read_cnt		조회수
	private String	content; 	//content		내용
	private int 	good;		//좋아요

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	//생성자
	public OurArticleData() {}
	//나중에 파라미터생성자 추가예정	

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
		return "OurArticleData [number=" + number + ", writer_id=" + writer_id + ", writer_name=" + writer_name
				+ ", title=" + title + ", regDate=" + regDate + ", modifiedDate=" + modifiedDate + ", readCount="
				+ readCount + ", content=" + content + "]";
	}
	
	
}









