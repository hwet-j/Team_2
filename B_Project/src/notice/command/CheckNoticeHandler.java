package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.NoticeData;
import notice.service.NoticeNotFoundException;
import notice.service.ReadNoticeService;

public class ReadNoticeHandler implements CommandHandler {
	private ReadNoticeService readNoticeService = new ReadNoticeService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		System.out.println(" ReadNoticeHandler- process() no="+no);
		try {
			NoticeData noc =readNoticeService.getDetail(no);
			
			request.setAttribute("noc", noc);
			return request.getContextPath()+"/view/notice/readNotice.jsp";
		}catch(NoticeNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
