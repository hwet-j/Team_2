package board.whi.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.whi.service.WhiBoardDeleteService;
import mvc.command.CommandHandler;

public class WhiBoardDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		WhiBoardDeleteService whiBoardDeleteService = new WhiBoardDeleteService();
		Boolean deleteChk = whiBoardDeleteService.deleteArticle(articleNo);
		
		return request.getContextPath()+"/CJH/whi_board/list.do?pageNo=1";
		
		
	}

}
