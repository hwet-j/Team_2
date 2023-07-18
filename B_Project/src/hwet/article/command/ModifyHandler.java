package hwet.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.service.ReadArticleService;
import mvc.command.CommandHandler;

// /hwet/article/modify.do
public class ModifyHandler implements CommandHandler {
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo = request.getParameter("no"); // 수정할 글번호
		int no = Integer.parseInt(strNo); // String을 int로 변환
		System.out.println(no);
		
		
        return "/view/HWET/article/detail.jsp";
    }

}
