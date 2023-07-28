package tak.article.service;

import java.util.List;

import article.model.Article;

//p648
//총게시글수,article목록,페이징처리정보
public class ArticlePage {
	//필드-p649 9라인
	private int total;				//총게시글수
	private int currentPage;		//보고싶은 페이지=>현재 페이지
	private List<Article> content;  //article목록
	private int  totalPages;		//총페이지수   15
	private int  startPage;			//시작페이지   12345  678910   11~15
	private int  endPage;			//끝페이지
	
	//생성자
	//ListArticleService에서   총게시글수+article목록,(페이징처리정보) 정보
	/*int total               	//총게시글수
	  int currentPage		    //보고싶은 페이지=>현재 페이지
	  int size    			    //1페이지당 출력할 게시글 수
	  List<Article> content  	//article목록 */
	ArticlePage(int total, int currentPage, int size, List<Article> content){
		this.total=total;		//총게시글수
		this.currentPage=currentPage; //보고싶은 페이지=>현재 페이지
		this.content=content;	//article목록
		if(total==0) { //게시글이 존재하지 않는 경우
			totalPages=0;
			startPage=0;
			endPage=0;
		}else { //게시글이 존재하는 경우
		
			totalPages=total/size;	//총페이지수-p649 25라인  10/3
			if(total%size>0) { 
				totalPages++; 
			}
	        
			int modVal = currentPage%5;	//현재페이지를 5로 나눈 나머지=> 5의 배수 5,10,15~
			//시작페이지-p649 30라인                                                  
			startPage=currentPage/5*5+1;
			if(modVal==0)  startPage=startPage-5;
/*			1  2  3  4  5            
			6  7  8  9  10           
			11 12 13 14 15
			보고싶은페이지(currentPage)가 1이면   1/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 2이면   2/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 3이면   3/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 4이면   4/5*5+1; =>시작페이지는 1
			보고싶은페이지(currentPage)가 5이면   5/5*5+1; =>6이면 강제로 시작페이지는 1
	
			보고싶은페이지(currentPage)가 6이면   6/5*5+1; =>시작페이지는 6
			보고싶은페이지(currentPage)가 9이면   9/5*5+1; =>시작페이지는 6
			보고싶은페이지(currentPage)가 10이면  10/5*5+1;=>11 강제로  시작페이지는 6*/
			                                                  
			endPage=startPage+4;	//끝페이지-p649 33라인
			if(endPage>totalPages) endPage=totalPages;
			/*시작페이지가 1이면  끝페이지 5
			 *시작페이지가 6이면  끝페이지 10 
			 *시작페이지가 11이면 끝페이지 15 */
		}//게시글이 존재하는 경우의 끝
	}

	//p650 38라인
	public int getTotal() {
		return total; //총게시글수
	}
	
	//p650 42라인 - 총게시글(수가 0이면)없으면  true리턴, 그렇지 않으면 false
	public boolean hasNoArticles() {
		return total==0;
	}

	//p650 58라인
	public List<Article> getContent() {
		return content; //article목록
	}

	//보고싶은 페이지=>현재 페이지	
	public int getCurrentPage() {
		return currentPage; 
	}

	//총페이지수
	public int getTotalPages() {
		return totalPages;
	}
	
	//시작페이지
	public int getStartPage() {
		return startPage; 
	}

	//끝페이지
	public int getEndPage() {
		return endPage;  
	}

	@Override
	public String toString() {
		return "ArticlePage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
				+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}









