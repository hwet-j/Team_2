package min.service;

import java.util.Map;

import min.model.PolWriter;

//p637 게시글 쓰기에 필요한 데이터를 제공한다.
//글등록폼에서 입력한 제목,내용/작성자정보(여기에서는 session에  담긴 회원id, 회원name)
//필수입력 기능 제공
public class PolWriteRequest {
	
	//필드
	private PolWriter writer;
	
	private String title;
	private String content;
	
	
	//생성자
	public PolWriteRequest(PolWriter writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}


	public PolWriter getWriter() {
		return writer;
	}


	public String getTitle() {
		return title;
	}


	public String getContent() {
		return content;
	}
	
	public void validate(Map<String,Boolean>errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
		if (content==null || content.trim().isEmpty() ) {
		 errors.put("content", Boolean.TRUE);
		}
	
	}


	@Override
	public String toString() {
		return "PolWriteRequest [writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
	
	
}
