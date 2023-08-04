package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tak.article.service.ArticleNotFoundException;
import tak.article.service.GoodService;
import tak.article.service.OurArticleData;
import mvc.command.CommandHandler;

public class GoodHandler implements CommandHandler{
	
	private GoodService goodService = new GoodService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		
		try {
			OurArticleData ora = goodService.getDetail(no);  
			
			request.setAttribute("ora", ora);
			
			return request.getContextPath()+"/view/TAK/readArticle.jsp";
		}catch(ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
	}
	
	
}
