package hwet.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.service.ReadArticleService;
import mvc.command.CommandHandler;

// /hwet/article/delete.do
public class DeleteHandler implements CommandHandler {
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        return "/view/HWET/article/detail.jsp";
    }

}
