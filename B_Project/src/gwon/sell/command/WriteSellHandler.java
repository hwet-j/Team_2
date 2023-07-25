package gwon.sell.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;	// 회원 정보에 접근하기
import gwon.sell.model.Writer;
import gwon.sell.service.WriteRequest;
import gwon.sell.service.WriteSellService;
import mvc.command.CommandHandler;

public class WriteSellHandler implements CommandHandler {

	
	private static final String FORM_VIEW = "/view/GWON/sell/writeSell.jsp";
	WriteSellService writeSellService = new WriteSellService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else{
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		//int sell_price = Integer.parseInt(request.getParameter("sell_price"));
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		MemberDTO user_data = (MemberDTO) request.getSession().getAttribute("AUTH_USER");
		WriteRequest writerequest = createWriteRequest(user_data,request);
		
		writerequest.validate(errors); 
		
		if(!errors.isEmpty()) {
			return request.getContextPath() + FORM_VIEW;
		}
		
		System.out.println("error 찾아라");
		int writeSellinfo = writeSellService.write(writerequest);
		
		
		System.out.println("error 찾아라2");
		request.setAttribute("writeSellinfo", writeSellinfo);
		
		
		return request.getContextPath() + "/view/GWON/sell/writeSellComplete.jsp";
	}

	private WriteRequest createWriteRequest(MemberDTO user_data, HttpServletRequest request) {
		return new WriteRequest(
				new Writer(user_data.getUser_id(), user_data.getUser_name()),
				request.getParameter("sell_title"),
				request.getParameter("sell_category"),
				Integer.parseInt(request.getParameter("sell_price")),
				request.getParameter("sell_loc"),
				request.getParameter("sell_content"),
				request.getParameter("sell_file"),
				request.getParameter("sell_fav"));
	}
	
	
}