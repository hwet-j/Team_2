package tak.article.service;

import java.util.Map;


//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 db
public class ModifyRequest {
	private String userId; 		//세션에서 가져온 회원id
	private int  articleNumber; //글번호
	private String title; 		//db에서 가져온 제목
	private String content; 	//db에서 가져온 내용 
	
	public ModifyRequest(String userId, int articleNumber, String title, String content) {
		this.userId = userId;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	
	public void validate(Map<String, Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
		if(content==null || content.trim().isEmpty()) {
			errors.put("content",Boolean.TRUE);
		}
	}
	
	
	@Override
	public String toString() {
		return "ModifyRequest [userId=" + userId + ", articleNumber=" + articleNumber + ", title=" + title
				+ ", content=" + content + "]";
	}
	
	
	
}




