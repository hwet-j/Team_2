package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tak.article.service.ArticleNotFoundException;
import tak.article.service.GoodService;
import tak.article.service.OurArticleData;
import mvc.command.CommandHandler;

public class GoodHandler implements CommandHandler{
	
	private GoodService goodService = new GoodService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo); //String을 int로 변환
		System.out.println("goodHandler진입 글번호="+no);
		
		//2.비즈니스로직<->Service<->DAO<->DB
		try {
			//파라미터 int no : 상세조회할 글 번호
			//리턴 OurArticleData ora : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
			OurArticleData ora = goodService.getDetail(no);  
			
			//3.Model
			request.setAttribute("ora", ora);
			
			//4.View
			System.out.println("굿핸들러 오라반환전");
			return request.getContextPath()+"/view/TAK/readArticle.jsp";
		}catch(ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
	}
	
	
}
