package notice.service;

import java.util.List;

import notice.model.Notice;

public class SearchNoticePage extends NoticePage {

	private String field;
	private String search;
	
	
	
	public SearchNoticePage(int total, int currentPage, int size, List<Notice> content, String field, String search) {
		super(total, currentPage, size, content);
		this.field = field;
		this.search = search;
	}

	
	


	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}



	public String getSearch() {
		return search;
	}



	public void setSearch(String search) {
		this.search = search;
	}



	@Override
	public String toString() {
		return "SearchNoticePage [field=" + field + ", search=" + search + "]";
	}
	
	
	
}
