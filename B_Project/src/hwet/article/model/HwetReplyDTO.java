package hwet.article.model;

import java.util.Date;

public class HwetReplyDTO {
    private int replyId;			// 댓글 번호
    private int boardId;			// 게시판 번호
    private String writer;			// 작성자 아이디
    private String writerNick;		// 작성자 닉네임	
    private Date createdAt;			// 댓글 생성시간
    private Date updatedAt;			// 댓글 수정시간
    private String content;			// 내용
    private int positiveCount;		// 추천수 
    private int negativeCount;		// 비추천수

    public HwetReplyDTO() {
    	
    }
    
	public HwetReplyDTO(int replyId, int boardId, String writer, String writerNick, Date createdAt, Date updatedAt,
			String content, int positiveCount, int negativeCount) {
		this.replyId = replyId;
		this.boardId = boardId;
		this.writer = writer;
		this.writerNick = writerNick;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.content = content;
		this.positiveCount = positiveCount;
		this.negativeCount = negativeCount;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriterNick() {
		return writerNick;
	}

	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPositiveCount() {
		return positiveCount;
	}

	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}

	public int getNegativeCount() {
		return negativeCount;
	}

	public void setNegativeCount(int negativeCount) {
		this.negativeCount = negativeCount;
	}

    


}