package board.whi.model;

public class WhiBoardArticle {
	int articleNo;
	String userId;
	String title;
	String content;
	String regDate;
	String category;
	int readCnt;
	String imgSrc;
	public int getArticleNo() {
		return articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getRegDate() {
		return regDate;
	}
	public String getCategory() {
		return category;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	
	public WhiBoardArticle(String userId, String title, String content, String category) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	public WhiBoardArticle(int articleNo, String title, String content) {
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
		
	}
	public WhiBoardArticle(int articleNo, String userId, String title, String content, String regDate, String category,
			int readCnt) {
		this.articleNo = articleNo;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.category = category;
		this.readCnt = readCnt;
	}
	public WhiBoardArticle(int articleNo, String userId, String title, String content, String regDate, String category,
			int readCnt, String imgSrc) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.category = category;
		this.readCnt = readCnt;
		this.imgSrc = imgSrc;
	}
	@Override
	public String toString() {
		return "WhiBoardArticle [articleNo=" + articleNo + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", regDate=" + regDate + ", category=" + category + ", readCnt=" + readCnt + ", imgSrc="
				+ imgSrc + "]";
	}
	
	
	
	
}
