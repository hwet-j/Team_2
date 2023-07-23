package notice.service;

import java.util.Map;


public class WriteRequest {
	private String writer_id;
	private String title;
	private String content;
	
	public WriteRequest(String writer_id, String title, String content) {

		this.writer_id = writer_id;
		this.title = title;
		this.content = content;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if( title==null || title.trim().isEmpty() ) {
			errors.put("title", Boolean.TRUE);
		}
		if( content==null || content.trim().isEmpty() ) {
			errors.put("content", Boolean.TRUE);
		}
	}

	@Override
	public String toString() {
		return "WriteRequest [writer_id=" + writer_id + ", title=" + title + ", content=" + content + "]";
	}

	
	
	
}
