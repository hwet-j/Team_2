package tak.article.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.service.ArticlePage;
import article.service.ListArticleService;
import mvc.command.CommandHandler;

//p651
//목록조회요청 담당 컨트롤러 클래스이다
//요청주소  http://localhost/article/list.do
public class ListArticleHandler implements CommandHandler {

	private String FORM_VIEW = "/view/article/listArticle.jsp";
	private ListArticleService listArticleService = new ListArticleService();
	
	//p652 15라인
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListArticleHandler-process()진입");
		//1.파라미터받기-p652 17라인
		String strPageNo = request.getParameter("pageNo"); //user가 선택한 페이지번호
		int pageNo = 1; //user가 선택안했을 때 기본적으로 보여지는 페이지번호
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		//2.비즈니스로직<->Service<->DAO<->DB
		/*파라미터  int pageNo : 보고싶은 페이지(=>현재 페이지)*/
		ArticlePage articlePage = listArticleService.getArticlePage(pageNo);//총 게시글수
		
		//3.Model
	    /*ArticlePage articlePage에는  
	      총 게시글수포함(getTotal()호출)
		  article목록포함(getContent()호출) 
		  int  totalPages;	//총페이지수   
		  int  startPage;	//시작페이지  
		  innt  endPage;	//끝페이지*/
		request.setAttribute("articlePage", articlePage);
		request.setAttribute("nowPage", pageNo); //현재페이지
		
		//4.View
		return request.getContextPath()+FORM_VIEW;
	}

}












