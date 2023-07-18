package hwet.article.model;

import java.util.Date;

public class HwetArticleDTO {
    private int boardId;
    private String writer;
    private String title;
    private String category;
    private String link;
    private String content;
    private Date regDate;
    private int hit;
    private Date updateDate;
    
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

