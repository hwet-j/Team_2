package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tak.article.service.ArticleNotFoundException;
import tak.article.service.OurArticleData;
import tak.article.service.ReadArticleService;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readArticleService = new ReadArticleService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strNo = request.getParameter("no"); 
		int no = Integer.parseInt(strNo);
		
		try {
			OurArticleData ora = readArticleService.getDetail(no);  
			
			request.setAttribute("ora", ora);
			
			return request.getContextPath()+"/view/TAK/readArticle.jsp";
		}catch(ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}






