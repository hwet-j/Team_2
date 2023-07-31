package board.whi.model;

public class WhiPhotoArticle {
	private int article_no;
	private String user_id;
	private String title;
	private String content;
	private String reg_date;
	private int read_cnt;
	private int like_cnt;
	private int dislike_cnt;
	private String img_src;

	

	public WhiPhotoArticle(int article_no, String user_id, String title, String content, String reg_date, int read_cnt,
			int like_cnt, int dislike_cnt, String img_src) {
		super();
		this.article_no = article_no;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.read_cnt = read_cnt;
		this.like_cnt = like_cnt;
		this.dislike_cnt = dislike_cnt;
		this.img_src = img_src;
	}

	public int getArticle_no() {
		return article_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public int getRead_cnt() {
		return read_cnt;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public int getDislike_cnt() {
		return dislike_cnt;
	}

	public String getImg_src() {
		return img_src;
	}

	public WhiPhotoArticle(String user_id, String title, String content, String img_src) {

		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.img_src = img_src;
	}
	
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public void setDislike_cnt(int dislike_cnt) {
		this.dislike_cnt = dislike_cnt;
	}
	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}
	
	
}

