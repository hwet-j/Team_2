package gwon.sell.model;

import java.util.Date;

public class SellDTO {

	private int		sell_no;
	private String 	user_id;
	private String	user_name;
	private String	sell_title;
	private String	sell_category;
	private int		sell_price;
	private String	sell_loc;
	private Date	sell_regDate;
	private Date	sell_modDate;
	private	int		sell_read_cnt;
	private String	sell_content;
	private String	sell_file;
	private String	sell_fav;
	
	public SellDTO() {}

	public SellDTO(int sell_no, String user_id, String user_name, String sell_title, String sell_category,
			int sell_price, String sell_loc, Date sell_regDate, Date sell_modDate, int sell_read_cnt,
			String sell_content, String sell_file, String sell_fav) {
		this.sell_no = sell_no;
		this.user_id = user_id;
		this.user_name = user_name;
		this.sell_title = sell_title;
		this.sell_category = sell_category;
		this.sell_price = sell_price;
		this.sell_loc = sell_loc;
		this.sell_regDate = sell_regDate;
		this.sell_modDate = sell_modDate;
		this.sell_read_cnt = sell_read_cnt;
		this.sell_content = sell_content;
		this.sell_file = sell_file;
		this.sell_fav = sell_fav;
	}

	public int getSell_no() {
		return sell_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getSell_title() {
		return sell_title;
	}

	public String getSell_category() {
		return sell_category;
	}

	public int getSell_price() {
		return sell_price;
	}

	public String getSell_loc() {
		return sell_loc;
	}

	public Date getSell_regDate() {
		return sell_regDate;
	}

	public Date getSell_modDate() {
		return sell_modDate;
	}

	public int getSell_read_cnt() {
		return sell_read_cnt;
	}

	public String getSell_content() {
		return sell_content;
	}

	public String getSell_file() {
		return sell_file;
	}

	public String getSell_fav() {
		return sell_fav;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setSell_title(String sell_title) {
		this.sell_title = sell_title;
	}

	public void setSell_category(String sell_category) {
		this.sell_category = sell_category;
	}

	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}

	public void setSell_loc(String sell_loc) {
		this.sell_loc = sell_loc;
	}

	public void setSell_regDate(Date sell_regDate) {
		this.sell_regDate = sell_regDate;
	}

	public void setSell_modDate(Date sell_modDate) {
		this.sell_modDate = sell_modDate;
	}

	public void setSell_read_cnt(int sell_read_cnt) {
		this.sell_read_cnt = sell_read_cnt;
	}

	public void setSell_content(String sell_content) {
		this.sell_content = sell_content;
	}

	public void setSell_file(String sell_file) {
		this.sell_file = sell_file;
	}

	public void setSell_fav(String sell_fav) {
		this.sell_fav = sell_fav;
	}

	@Override
	public String toString() {
		return "SellDTO [sell_no=" + sell_no + ", user_id=" + user_id + ", user_name=" + user_name + ", sell_title="
				+ sell_title + ", sell_category=" + sell_category + ", sell_price=" + sell_price + ", sell_loc="
				+ sell_loc + ", sell_regDate=" + sell_regDate + ", sell_modDate=" + sell_modDate + ", sell_read_cnt="
				+ sell_read_cnt + ", sell_content=" + sell_content + ", sell_file=" + sell_file + ", sell_fav="
				+ sell_fav + "]";
	};
	
	
	
}