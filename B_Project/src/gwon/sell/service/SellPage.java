package gwon.sell.service;

import java.util.List;

import gwon.sell.model.Sell;

public class SellPage {
	
	private int total;				
	private int currentPage;		
	private List<Sell> content;  
	private int  totalPages;		
	private int  startPage;			
	private int  endPage;		
	
	public SellPage(int total, int currentPage, int size, List<Sell> content) {

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
	        
			int modVal = currentPage%10;
			startPage=currentPage/10*10+1;
			if(modVal==0)  startPage=startPage-10;
			
			endPage=startPage+9;
			if(endPage>totalPages) endPage=totalPages;
		}
	}
	
		
	public int getTotal() {
		return total; 
	}
	
	public boolean hasNoSells() {
		return total==0;
	}

	public List<Sell> getContent() {
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
		return "SellPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
				+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

	
		
}	
