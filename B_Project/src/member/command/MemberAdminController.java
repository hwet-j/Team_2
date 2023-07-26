package member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


// /admin/showAll.do
public class MemberAdminController implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MemberDTO> list = new MemberDAO().AllMemberShow();
		request.setAttribute("USERS", list);
		
		return "/view/Admin/AdminUser.jsp";
	}
	
	

}
