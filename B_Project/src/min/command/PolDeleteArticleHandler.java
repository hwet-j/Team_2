package min.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.service.PolDeleteArticleService;
import mvc.command.CommandHandler;

public class PolDeleteArticleHandler implements CommandHandler {

	private PolDeleteArticleService deleteArticleService = new PolDeleteArticleService();
	
	/*
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
				int no = Integer.parseInt(request.getParameter("no"));//삭제하고 싶은 글번호
				
				//2.비즈니스로직<->Service<->DAO<->DB
				deleteArticleService.deleteUp(no);
				
				//3.Model
				//4.View
				//여기에서는  성공시  목록보기요청
				response.sendRedirect(request.getContextPath()+"/article/polList.do");
				return null;
			}
			*/
	
	//delete쿼리를 통한 글삭제
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));//삭제하고 싶은 글번호
		
		//2.비즈니스로직<->Service<->DAO<->DB
		deleteArticleService.delete(no);
		
		//3.Model
		//4.View
		//여기에서는  성공시  목록보기요청
		response.sendRedirect(request.getContextPath()+"/min/article/polList.do");
		return null;
	}

			
}
