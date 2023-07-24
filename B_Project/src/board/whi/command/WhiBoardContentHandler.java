package board.whi.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.whi.model.WhiBoardArticle;
import board.whi.service.WhiBoardContentService;
import mvc.command.CommandHandler;

public class WhiBoardContentHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			int articleNo=Integer.parseInt(request.getParameter("articleNo"));
			WhiBoardContentService whiBoardContentService = new WhiBoardContentService();
			WhiBoardArticle article = whiBoardContentService.whiSelectContent(articleNo);
			
			request.setAttribute("SELCTED_ARTICLE", article);
		} catch(NullPointerException e) {
			return request.getContextPath()+"/view/CJH/whi_board/articleNoNull.jsp";
		}
		
		return request.getContextPath()+"/view/CJH/whi_board/whi_board_list_content.jsp";
	}

}
