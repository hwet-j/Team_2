package min.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.service.PolArticlePage;
import min.service.PolListArticleService;
import mvc.command.CommandHandler;

public class PolSearchHandler implements CommandHandler {
	private String FORM_VIEW = "/view/min/listPolArticle.jsp";
	private PolListArticleService polListArticleService = new PolListArticleService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기-p652 17라인
		String strPageNo = request.getParameter("pageNo"); //user가 선택한 페이지번호
		String search = request.getParameter("searchcontent");
		int pageNo = 1; //user가 선택안했을 때 기본적으로 보여지는 페이지번호
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		//2.비즈니스로직<->Service<->DAO<->DB
		/*파라미터  int pageNo : 보고싶은 페이지(=>현재 페이지)*/
		PolArticlePage polArticlePage = polListArticleService.getPolArticlePage(pageNo,search);
		
		//3.Model
	    /*ArticlePage articlePage에는  
	      총 게시글수포함(getTotal()호출)
		  article목록포함(getContent()호출) 
		  int  totalPages;	//총페이지수   
		  int  startPage;	//시작페이지  
		  innt  endPage;	//끝페이지*/
		request.setAttribute("polArticlePage", polArticlePage);
		request.setAttribute("nowPage", pageNo); //현재페이지
		
		//4.View
		return request.getContextPath()+FORM_VIEW;
		
	}

}
