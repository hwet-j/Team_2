package gwon.sell.service;

public class SearchRequest {

	private String searchColumn;
	private String searchText;
	
	public SearchRequest() {}
	
	public SearchRequest(String searchColumn, String searchText) {
		this.searchColumn = searchColumn;
		this.searchText = searchText;
	}
	
	public String getSearchColumn() {
		return searchColumn;
	}
	public String getSearchText() {
		return searchText;
	}
	
	@Override
	public String toString() {
		return "SellRequest [searchColumn=" + searchColumn + ", searchText=" + searchText + "]";
	}
	
	
}