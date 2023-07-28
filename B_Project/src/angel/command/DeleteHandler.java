package angel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class DeleteHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		
		ArticleService articleService = new ArticleService();
		Boolean isDelete = articleService.delete(articleNo);
		System.out.println("dao 되니?"+isDelete);

		if(isDelete) {
			return request.getContextPath() + "/view/ANGEL/article/DeleteTrue.jsp";
		} else {
			return request.getContextPath() + "/view/ANGEL/article/DeleteFalse.jsp";
		}
	}

}
