package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import mvc.command.CommandHandler;

/* admin/deleteUser.do */
public class MemberDeleteController implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("user_id");
		int delRow = new MemberDAO().DeleteMember(id);
		
		if(delRow==1) {
			return "/admin/showAll.do";
		}
		else {
			return "/admin/showAll.do";
		}
	}

}
