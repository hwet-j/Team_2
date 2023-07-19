package hwet.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.service.ReadArticleService;
import mvc.command.CommandHandler;

// /hwet/article/read.do
public class ReadHandler implements CommandHandler {
	
	private ReadArticleService readService = new ReadArticleService();
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 상세하게 보고 싶은 글번호
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		
		// 조회수 증가 (현재 조건 없음, 조회하면 무조건 증가함 이후에 필요하면 조건설정)
		readService.increaseHit(no);
		
		// 조회수 증가 후 데이터 가져오기
		HwetArticleDTO data = readService.getDetail(no);
		
		request.setAttribute("data", data);
		
        return "/view/HWET/article/detail.jsp";
    }

}
