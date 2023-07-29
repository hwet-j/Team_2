package angel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.model.Article;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class ModifyHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println("핸들러시작");
		Article article = new Article(articleNo, title, content);
		
		ArticleService articleService = new ArticleService();
		int row = articleService.modify(article);
		System.out.println("핸들러 마무리 접근");
		return request.getContextPath() + "/view/ANGEL/main.jsp" ;
	}

}
