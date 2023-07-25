package gwon.sell.model;


public class SellContent {

	private Integer	sell_no;
	private String	sell_content;
	private String	sell_file;
	private String	sell_fav;

	
	public SellContent(Integer sell_no, String sell_content, String sell_file) {
		this.sell_no = sell_no;
		this.sell_content = sell_content;
		this.sell_file = sell_file;
	}
	
	public SellContent(Integer sell_no, String sell_content, String sell_file, String sell_fav) {
		this.sell_no = sell_no;
		this.sell_content = sell_content;
		this.sell_file = sell_file;
		this.sell_fav = sell_fav;
	}


	public Integer getSell_no() {
		return sell_no;
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


	@Override
	public String toString() {
		return "SellContent [sell_no=" + sell_no + ", sell_content=" + sell_content + ", sell_file=" + sell_file
				+ ", sell_fav=" + sell_fav + "]";
	}
		
}