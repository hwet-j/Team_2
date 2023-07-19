package hwet.article.model;

import java.util.Date;

public class HwetArticleDTO {
    private int boardId;		// 글번호
    private String writer;		// 작성자
    private String title;		// 제목
    private String category;	// 카테고리
    private String link;		// 링크
    private String content;		// 내용
    private Date regDate;		// 첫 작성일자
    private int hit;			// 조회수
    private Date updateDate;	// 수정일자
    
    public HwetArticleDTO() {
    	
    }
    
    public HwetArticleDTO(int boardId, String writer, String title, String category, String link, String content,
			Date regDate, int hit, Date updateDate) {
		this.boardId = boardId;
		this.writer = writer;
		this.title = title;
		this.category = category;
		this.link = link;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
		this.updateDate = updateDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

