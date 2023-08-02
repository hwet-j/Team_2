package angel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class CommentModifyHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String comment = request.getParameter("comment");
		
		ArticleService articleService = new ArticleService();
		int row = articleService.modifyComment(commentNo, comment);

		return request.getContextPath() + "/Angel/detail.do?articleNo=" + articleNo;
	}

}
