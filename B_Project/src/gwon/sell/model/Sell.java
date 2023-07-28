package gwon.sell.model;

import java.util.Date;

public class Sell {
	
	private Integer	sell_no;
	private Writer	writer;
	private String	sell_title;
	private String	sell_category;
	private int	sell_price;
	private String	sell_loc;
	private Date	sell_regdate;
	private Date	sell_moddate;
	private int		sell_read_cnt;

	public Sell(Integer sell_no, Writer writer, String sell_title, String sell_category, int sell_price,
			String sell_loc, Date sell_regdate, Date sell_moddate, int sell_read_cnt) {
		this.sell_no = sell_no;
		this.writer = writer;
		this.sell_title = sell_title;
		this.sell_category = sell_category;
		this.sell_price = sell_price;
		this.sell_loc = sell_loc;
		this.sell_regdate = sell_regdate;
		this.sell_moddate = sell_moddate;
		this.sell_read_cnt = sell_read_cnt;
	}
	
	public Integer getSell_no() {
		return sell_no;
	}
	public Writer getWriter() {
		return writer;
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
	public Date getSell_regdate() {
		return sell_regdate;
	}
	public Date getSell_moddate() {
		return sell_moddate;
	}
	public int getSell_read_cnt() {
		return sell_read_cnt;
	}
	
	@Override
	public String toString() {
		return "Sell [sell_no=" + sell_no + ", writer=" + writer + ", sell_title=" + sell_title + ", sell_category="
				+ sell_category + ", sell_price=" + sell_price + ", sell_loc=" + sell_loc + ", sell_regdate="
				+ sell_regdate + ", sell_moddate=" + sell_moddate + ", sell_read_cnt=" + sell_read_cnt + "]";
	}
	
	
	
}