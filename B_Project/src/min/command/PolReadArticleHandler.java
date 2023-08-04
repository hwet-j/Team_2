package min.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.service.PolArticleData;
import min.service.PolArticleNotFoundException;
import min.service.ReadPolArticleService;
import mvc.command.CommandHandler;

//p659
//상세보기 요청을 처리하는 담당 컨트롤러이다
//요청주소   http://localhost/article/read.do?no=글번호
public class PolReadArticleHandler implements CommandHandler {

	private ReadPolArticleService readPolArticleService = new ReadPolArticleService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		String strNo = request.getParameter("no"); //상세하게 보고 싶은 글번호
		int no = Integer.parseInt(strNo); //String을 int로 변환
		
		//2.비즈니스로직<->Service<->DAO<->DB
		try {
			//파라미터 int no : 상세조회할 글 번호
			//리턴 OurArticleData pda : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
			PolArticleData pad = readPolArticleService.getDetail(no);  
			
			//3.Model
			request.setAttribute("pad", pad);
			
			//4.View
			return request.getContextPath()+"/view/min/readPolArticle.jsp";
		}catch(PolArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
		
	}

}






