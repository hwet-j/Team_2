package min.service;

import java.util.Map;

//p666
//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 db
public class PolModifyRequest {

		private String userId; 		//세션에서 가져온 회원id
		private int  polarticleNumber; //글번호
		private String title; 		//db에서 가져온 제목
		private String content; 	//db에서 가져온 내용 
	
		public PolModifyRequest(String userId, int polarticleNumber, String title, String content) {
			this.userId = userId;
			this.polarticleNumber = polarticleNumber;
			this.title = title;
			this.content = content;
		}

		public String getUserId() {
			return userId;
		}

		public int getPolarticleNumber() {
			return polarticleNumber;
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
				errors.put("content",Boolean.TRUE);
			}
		}

		@Override
		public String toString() {
			return "PolModifyRequest [userId=" + userId + ", polarticleNumber=" + polarticleNumber + ", title=" + title
					+ ", content=" + content + "]";
		}
	
		
}
