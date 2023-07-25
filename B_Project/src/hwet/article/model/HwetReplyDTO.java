package hwet.article.model;

import java.util.Date;

public class HwetReplyDTO {
    private int reply_id;           // 댓글 번호
    private int board_id;           // 게시판 번호
    private String writer;          // 작성자 아이디
    private String writer_nick;     // 작성자 닉네임   
    private Date created_at;        // 댓글 생성시간
    private Date updated_at;        // 댓글 수정시간
    private String content;         // 내용
    private int positive_count;     // 추천수 
    private int negative_count;     // 비추천수

    public HwetReplyDTO() {
        
    }
    
    public HwetReplyDTO(int reply_id, int board_id, String writer, String writer_nick, Date created_at, Date updated_at,
            String content, int positive_count, int negative_count) {
        this.reply_id = reply_id;
        this.board_id = board_id;
        this.writer = writer;
        this.writer_nick = writer_nick;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.content = content;
        this.positive_count = positive_count;
        this.negative_count = negative_count;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
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

    public String getWriter_nick() {
        return writer_nick;
    }

    public void setWriter_nick(String writer_nick) {
        this.writer_nick = writer_nick;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPositive_count() {
        return positive_count;
    }

    public void setPositive_count(int positive_count) {
        this.positive_count = positive_count;
    }

    public int getNegative_count() {
        return negative_count;
    }

    public void setNegative_count(int negative_count) {
        this.negative_count = negative_count;
    }

}
