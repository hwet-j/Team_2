package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import tak.article.service.DeleteArticleService;


public class DeleteArticleHandler implements CommandHandler {

	private DeleteArticleService deleteArticleService = new DeleteArticleService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));//삭제하고 싶은 글번호
		
		deleteArticleService.deleteUp(no);
		
		response.sendRedirect(request.getContextPath()+"/tak/article/list.do");
		return null;
	}
	
}
