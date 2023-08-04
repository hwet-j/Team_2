package member.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDAO;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

public class MyPageHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		request.getParameter("utf-8");
		String user_id = request.getParameter("user_id");
		
		Connection conn = ConnectionProvider.getConnection();
		
		MemberDTO user_data = new MemberDAO().getMemberDetail(conn, user_id);
		
		request.setAttribute("USER_INFO", user_data);
		
		JDBCUtil.close(conn);
		return request.getContextPath()+"/view/member/myPage.jsp";
	}

}
