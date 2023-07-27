package member.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import member.service.MemberEditService;
import mvc.command.CommandHandler;


/* admin/editUser.do */
public class MemberEditController implements CommandHandler {
	
	MemberEditService memberEditService = new MemberEditService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 날짜데이터 형 변환을 위한 객체
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String user_birth = request.getParameter("user_birth");
		String user_gender = request.getParameter("user_gender");
		String user_nickname = request.getParameter("user_nickname");
		String user_tlno = request.getParameter("user_tlno");
		String user_join_date = request.getParameter("user_join_date");
		
		Date birth = null;
		Date join_date = null;
		if (user_birth != null && !user_birth.equals("")) {
			birth = format.parse(user_birth);
		}
		
		join_date = format.parse(user_join_date);
		
		MemberDTO member_data = memberEditService.getMemberDetail(user_id);
		member_data.setUser_name(user_name);
		member_data.setUser_birth(birth);
		member_data.setUser_gender(user_gender);
		member_data.setUser_nickname(user_nickname);
		member_data.setUser_tlno(user_tlno);
		member_data.setUser_join_date(join_date);
		
		memberEditService.editMember(member_data);
		
		return null;
	}
	
	

}
