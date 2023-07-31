package angel.model;

public class Comment {
	int articleNo;
	int commentNo;
	String name;
	String comment;
	String regdate;
	
	public Comment(int articleNo, int commentNo, String name, String comment, String regdate) {
		this.articleNo = articleNo;
		this.commentNo = commentNo;
		this.name = name;
		this.comment = comment;
		this.regdate = regdate;
	}
	
	public Comment(int articleNo, String name, String comment) {
		this.articleNo = articleNo;
		this.name = name;
		this.comment = comment;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public String getRegdate() {
		return regdate;
	}

	@Override
	public String toString() {
		return "Comment [articleNo=" + articleNo + ", commentNo=" + commentNo + ", name=" + name + ", comment="
				+ comment + ", regdate=" + regdate + "]";
	}
}
