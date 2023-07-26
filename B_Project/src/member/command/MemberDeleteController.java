package member.command;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import mvc.command.CommandHandler;

public class MemberDeleteController implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("userId");
		int delRow = new MemberDAO().DeleteMember(id);
		if(delRow==1) {
			request.setAttribute("MSG", "Delete Success!");
			return request.getContextPath()+"/admin/showAll.do";
		}
		else {
			String delFail = "삭제에 실패했습니다.";
			request.setAttribute("MSG", "Delete Fail!");
			response.sendRedirect("/admin/showAll.do");

		}
		return null;
	}

}
