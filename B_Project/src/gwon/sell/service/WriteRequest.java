package gwon.sell.service;

import java.util.Map;

import gwon.sell.model.Writer;

public class WriteRequest {

	private Writer	writer;
	private String	sell_title;
	private String	sell_category;
	private int		sell_price;
	private String	sell_loc;
	private String	sell_content;
	private String	sell_file;

	public WriteRequest(Writer writer, String sell_title, String sell_category, int sell_price, String sell_loc,
			String sell_content, String sell_file) {
		this.writer = writer;
		this.sell_title = sell_title;
		this.sell_category = sell_category;
		this.sell_price = sell_price;
		this.sell_loc = sell_loc;
		this.sell_content = sell_content;
		this.sell_file = sell_file;
	}


	@Override
	public String toString() {
		return "WriteRequest [writer=" + writer + ", sell_title=" + sell_title + ", sell_category=" + sell_category
				+ ", sell_price=" + sell_price + ", sell_loc=" + sell_loc + ", sell_content=" + sell_content
				+ ", sell_file=" + sell_file +  "]";
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

	public String getSell_content() {
		return sell_content;
	}

	public String getSell_file() {
		return sell_file;
	}


	public void validate(Map<String,Boolean> errors) {
		if(  sell_title==null ||  sell_title.trim().isEmpty() ) {
			errors.put("sell_title", Boolean.TRUE);
		}
		if(  sell_price==0 ||  sell_price < 0 ) {
			errors.put("sell_price", Boolean.TRUE);
		}
		if(  sell_content==null ||  sell_content.isEmpty() ) {
			errors.put("sell_content", Boolean.TRUE);
		}
	}
}
