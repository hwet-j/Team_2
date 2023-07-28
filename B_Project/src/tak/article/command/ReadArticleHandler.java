package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.OurArticleData;
import article.service.ReadArticleService;
import mvc.command.CommandHandler;

//p659
//상세보기 요청을 처리하는 담당 컨트롤러이다
//요청주소   http://localhost/article/read.do?no=글번호
public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readArticleService = new ReadArticleService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		String strNo = request.getParameter("no"); //상세하게 보고 싶은 글번호
		int no = Integer.parseInt(strNo); //String을 int로 변환
		System.out.println("ReadArticleHandler- process() no="+no);
		
		//2.비즈니스로직<->Service<->DAO<->DB
		try {
			//파라미터 int no : 상세조회할 글 번호
			//리턴 OurArticleData ora : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
			OurArticleData ora = readArticleService.getDetail(no);  
			
			//3.Model
			request.setAttribute("ora", ora);
			
			//4.View
			return request.getContextPath()+"/view/article/readArticle.jsp";
		}catch(ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
		
	}

}






