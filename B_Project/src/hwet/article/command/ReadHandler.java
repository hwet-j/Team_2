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
		String strNo = request.getParameter("no"); // 상세하게 보고 싶은 글번호
		int no = Integer.parseInt(strNo); // String을 int로 변환
		System.out.println(no);
		
		HwetArticleDTO data = readService.getDetail(no);
		
		request.setAttribute("data", data);
		
        return "/view/HWET/article/detail.jsp";
    }

}
