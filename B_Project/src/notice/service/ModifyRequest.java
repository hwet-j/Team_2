package notice.service;

import java.util.Map;

public class ModifyRequest {
	private String writer_id;
	private int number;
	private String title;
	private String content;
	
	

	public ModifyRequest(String writer_id, int number, String title, String content) {
		super();
		this.writer_id = writer_id;
		this.number = number;
		this.title = title;
		this.content = content;
	}

	
	
	public String getWriter_id() {
		return writer_id;
	}



	public int getNumber() {
		return number;
	}



	public String getTitle() {
		return title;
	}



	public String getContent() {
		return content;
	}


//유효성검사
	public void validate(Map<String, Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
		if(content==null || content.trim().isEmpty()) {
			errors.put("content", Boolean.TRUE);
		}
	}



	@Override
	public String toString() {
		return "ModifyRequest [writer_id=" + writer_id + ", number=" + number + ", title=" + title + ", content="
				+ content + "]";
	}

	

}
