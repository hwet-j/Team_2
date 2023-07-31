package angel.model;

public class Article {
	int articleNo;
	String memberid;
	String name;
	String title;
	String regdate;
	int readCnt;
	String content;
	
	public Article(int articleNo, String memberid, String name, String title, String regdate, int readCnt,
			String content) {
		this.articleNo = articleNo;
		this.memberid = memberid;
		this.name = name;
		this.title = title;
		this.regdate = regdate;
		this.readCnt = readCnt;
		this.content = content;
	}
	
	public Article(int articleNo, String title, String content) {
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
	}
	
	public Article(String memberid, String title, String name, String content) {
		this.memberid = memberid;
		this.title = title;
		this.name = name;
		this.content = content;
	}

	public int getArticleNo() {
		return articleNo;
	}
	public String getMemberid() {
		return memberid;
	}
	public String getName() {
		return name;
	}
	public String getTitle() {
		return title;
	}
	public String getRegdate() {
		return regdate;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Article [articleNo=" + articleNo + ", memberid=" + memberid + ", name=" + name + ", title=" + title
				+ ", regdate=" + regdate + ", readCnt=" + readCnt + ", content=" + content + "]";
	}
}
