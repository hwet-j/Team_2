package notice.service;

import java.util.List;

import notice.model.Notice;



//총게시글수,notice목록,페이징처리정보
public class NoticePage {
	private int total; //총게시글수
	private int currentPage; //보고싶은 페이지=>현재 페이지
	private List<Notice> content; //notice목록
	private int totalPages; //총페이지수
	private int startPage; //시작페이지
	private int endPage; //끝페이지
	
	//생성자
			//NoticeService에서   총게시글수+board목록,(페이징처리정보) 정보
			/*int total               	//총게시글수
			  int currentPage		    //보고싶은 페이지=>현재 페이지
			  int size    			    //1페이지당 출력할 게시글 수
			  List<Board> content  		//board목록 */
	
	
	NoticePage(int total,int currentPage, int size, List<Notice> content){
		this.total=total;	//총게시글수
		this.currentPage=currentPage;//보고싶은 페이지=>현재 페이지
		this.content=content; //notice목록
		if(total==0) {//게시글이 존재하지 않는 경우
			totalPages=0;
			startPage=0;
			endPage=0;
			
		}else {//게시글이 존재하는 경우
		
		totalPages=total/size;	 //총페이지수
		if(total%size>0) { 
			totalPages++;
		}	
				
	int modVal = currentPage%3; // 현재페이지를 3으로 나눈 나머지=> 3의 배수 3,6,9~
	//시작페이지
	startPage=currentPage/3*3+1; 
	if(modVal==0) startPage = startPage-3;
/*	1  2  3  4  5            
	6  7  8  9  10           
	11 12 13 14 15
	보고싶은페이지(currentPage)가 1이면   1/3*3+1; =>시작페이지는 1
	보고싶은페이지(currentPage)가 2이면   2/3*3+1; =>시작페이지는 1
	보고싶은페이지(currentPage)가 3이면   3/3*3+1; =>4이면 강제로 시작페이지는 1
	보고싶은페이지(currentPage)가 4이면   4/3*3+1; =>시작페이지는 4
	보고싶은페이지(currentPage)가 5이면   5/3*3+1; =>시작페이지는 4

	보고싶은페이지(currentPage)가 6이면   6/3*3+1; =>7이면 강제로 시작페이지는 4
	보고싶은페이지(currentPage)가 9이면   9/3*3+1; =>10이면 강제로 시작페이지는 7
	보고싶은페이지(currentPage)가 10이면  10/3*3+1;=>시작페이지는 10*/
	endPage=startPage+2; //끝페이지
	if(endPage>totalPages)endPage = totalPages;
	/*시작페이지가 1이면  끝페이지 3
	 *시작페이지가 6이면  끝페이지 8 
	 *시작페이지가 11이면 끝페이지 13 */
		}//게시글이 존재하는 경우의 끝
}
	public int getTotal() {
		return total; //총게시글수
	}
	//총게시글(수가 0이면)없으면  true리턴, 그렇지 않으면 false
	public boolean hasNoNotice() {
		return total==0;
	}
	
	public List<Notice> getContent() {
		return content; //notice목록
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
		return "NoticePage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
				+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
