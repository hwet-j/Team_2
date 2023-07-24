package gwon.sell.service;

import java.util.Map;

public class ModifyRequest {
	
	private String	user_id;
	private int		sell_no;
	private String	sell_title;
	private String	sell_category;
	private int	  	sell_price;
	private String	sell_loc;
	private String	sell_content;
	private String	sell_file;
	private String	sell_fav;


	public ModifyRequest(String user_id, int sell_no, String sell_title, String sell_category, int sell_price,
			String sell_loc, String sell_content, String sell_file, String sell_fav) {
		this.user_id = user_id;
		this.sell_no = sell_no;
		this.sell_title = sell_title;
		this.sell_category = sell_category;
		this.sell_price = sell_price;
		this.sell_loc = sell_loc;
		this.sell_content = sell_content;
		this.sell_file = sell_file;
		this.sell_fav = sell_fav;
	}

	public String getSell_fav() {
		return sell_fav;
	}

	public String getUser_id() {
		return user_id;
	}

	public int getSell_no() {
		return sell_no;
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

	public String getSell_content() {
		return sell_content;
	}

	public String getSell_file() {
		return sell_file;
	}

	
	@Override
	public String toString() {
		return "ModifyRequest [user_id=" + user_id + ", sell_no=" + sell_no + ", sell_title=" + sell_title
				+ ", sell_category=" + sell_category + ", sell_price=" + sell_price + ", sell_loc=" + sell_loc
				+ ", sell_content=" + sell_content + ", sell_file=" + sell_file + ", sell_fav=" + sell_fav + "]";
	}
	
	public void validate(Map<String,Boolean> errors) {
		if(  sell_title==null ||  sell_title.trim().isEmpty() ) {
			errors.put("title", Boolean.TRUE);
		}
		if(  sell_category==null ||  sell_category.isEmpty() ) {
			errors.put("category", Boolean.TRUE);
		}
		if(  sell_price==0 ||  sell_price < 0 ) {
			errors.put("price", Boolean.TRUE);
		}
		if(  sell_content==null ||  sell_content.isEmpty() ) {
			errors.put("content", Boolean.TRUE);
		}
	}
	
}
