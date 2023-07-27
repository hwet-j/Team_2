package tak.article.service;

import java.util.Map;

import article.model.Writer;

//p637
//글등록폼에서 입력한 제목,내용/작성자정보(여기에서는 session에  담긴 회원id, 회원name)
//필수입력 기능 제공
public class WriteRequest {
	//필드
	private Writer writer; //작성자정보(여기에서는 session에  담긴 회원id, 회원name)
	private String title; //제목
	private String content; //내용
	
	//생성자
	public WriteRequest(Writer writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	//필수입력-p638 31라인
	public void validate(Map<String,Boolean> errors) {
		if(  title==null ||  title.trim().isEmpty() ) {
			errors.put("title", Boolean.TRUE);
		}
		if(  content==null ||  content.isEmpty() ) {
			errors.put("content", Boolean.TRUE);
		}
	}
	
	@Override
	public String toString() {
		return "WriteRequest [writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
	
}








