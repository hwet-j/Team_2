package board.whi.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import board.whi.model.WhiBoardArticle;
import board.whi.service.WhiBoardWriteService;
import mvc.command.CommandHandler;

public class WhiBoardWriteHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//게시글 텍스트성질 내용들 받아 작성
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		WhiBoardArticle whiBoardArticle = new WhiBoardArticle(id, title, content, category);    
		WhiBoardWriteService whiBoardWriteService = new WhiBoardWriteService();
		int writeRow = whiBoardWriteService.writeArticle(whiBoardArticle);
		
			return request.getContextPath()+"/CJH/whi_board/list.do?pageNo=1";

	}


	
	
	
	
}
