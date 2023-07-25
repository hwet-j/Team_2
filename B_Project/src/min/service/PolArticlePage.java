package min.service;

import java.util.List;

import min.model.PolArticle;


//p648
public class PolArticlePage {
	//필드 p649 9라인
	private int total;				//총게시글수
	private int currentPage;		//보고싶은 페이지=>현재 페이지
	private List<PolArticle> content;  //polarticle목록
	private int  totalPages;		//총페이지수   15
	private int  startPage;			//시작페이지   12345  678910   11~15
	private int  endPage;			//끝페이지

	
	
	//생성자
	//ListPolArticleService에서 총게시글 수 + polarticle목록 페이징처리
	
	
	public PolArticlePage(int total, int currentPage,int size, List<PolArticle> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total==0) {
			totalPages=0;
			startPage =0;
			endPage = 0;
		}else {
			totalPages=total/size;
			if(total%size>0) {
				totalPages++;
			}
			
			int modVal = currentPage%5;
			startPage=currentPage/5*5+1;
			if(modVal==0)  startPage=startPage-5;
			
			endPage=startPage+4;	//끝페이지-p649 33라인
			if(endPage>totalPages) endPage=totalPages;
			
		}
	}




	public int getTotal() {
		return total;
	}

	public boolean hasNoPolArticle() {
		return total==0;
	}


	public List<PolArticle> getContent() {
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
		return "PolArticlePage [total=" + total + ", currentPage=" + currentPage + ", content=" + content
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
