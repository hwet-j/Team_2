package service;

import java.util.List;

import notice.Notice;

public class NoticePage {
	private int total;
	private int currentPage;
	private List<Notice> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	
	NoticePage(int total,int currentPage, int size, List<Notice> content){
		this.total=total;	
		this.currentPage=currentPage;
		this.content=content; 
		if(total==0) {
			totalPages=0;
			startPage=0;
			endPage=0;
			
		}else {
		
		totalPages=total/size;	 
		if(total%size>0) { 
			totalPages++;
		}			
		startPage=currentPage/3*3+1;
	int modVal = currentPage%3;
	if(modVal==0) startPage = startPage-3;
	endPage=startPage+2;
	if(endPage>totalPages)endPage = totalPages;
		}
}
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoNotice() {
		return total==0;
	}
	
	public List<Notice> getContent() {
		return content;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	
	
	@Override
	public String toString() {
		return "NoticePage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
				+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
