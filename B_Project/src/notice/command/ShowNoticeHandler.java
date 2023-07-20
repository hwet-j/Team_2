package notice.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.model.Notice;
import notice.service.ShowNoticeService;

public class ShowNoticeHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		NoticeDAO dao = new NoticeDAO();
		int pagingUnit=5;
		Connection conn = ConnectionProvider.getConnection();
		
		int pageCnt = dao.noticeCnt(conn)/pagingUnit +1;
		conn.close();
		System.out.println(pageCnt);
		
		ShowNoticeService showNoticeService = new ShowNoticeService();
		List<Notice>noticeList =showNoticeService.showNoticePaged(pageNo);
		HttpSession session3 = request.getSession();
		session3.setAttribute("ARTICLE", noticeList);
		session3.setAttribute("PAGE_COUNT", pageCnt);
		return request.getContextPath()+"/view/board/ShowNotice.jsp";
	}

}
