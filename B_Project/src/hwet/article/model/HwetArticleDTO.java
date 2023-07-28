package hwet.article.model;

import java.util.Date;

public class HwetArticleDTO {
    private int board_id;           // 글번호
    private String writer;          // 작성자
    private String title;           // 제목
    private String category;        // 카테고리
    private String link;            // 링크
    private String content;         // 내용
    private Date reg_date;          // 첫 작성일자
    private int hit;                // 조회수
    private Date update_date;       // 수정일자
    
    public HwetArticleDTO() {
        
    }
    
    public HwetArticleDTO(int board_id, String writer, String title, String category, String link, String content,
            Date reg_date, int hit, Date update_date) {
        this.board_id = board_id;
        this.writer = writer;
        this.title = title;
        this.category = category;
        this.link = link;
        this.content = content;
        this.reg_date = reg_date;
        this.hit = hit;
        this.update_date = update_date;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
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

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
