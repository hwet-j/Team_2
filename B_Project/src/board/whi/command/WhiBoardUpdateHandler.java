package board.whi.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.whi.model.WhiBoardArticle;
import board.whi.service.WhiBoardDeleteService;
import board.whi.service.WhiBoardModifyService;
import mvc.command.CommandHandler;

public class WhiBoardUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		WhiBoardArticle whiBoardArticle = new WhiBoardArticle(articleNo, title, content); 
		WhiBoardModifyService whiBoardModifyService = new WhiBoardModifyService(); 
		int modifyRow = whiBoardModifyService.modifyArticle(whiBoardArticle);
		if(modifyRow == 1) {
			return request.getContextPath()+"/CJH/whi_board/content.do?articleNo="+articleNo;
		}else {
			return request.getContextPath()+"/view/CJH/whi_board/modifyFail.jsp";
		}
		
	}

}
