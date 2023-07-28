package gwon.sell.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gwon.sell.model.SellDTO;
import gwon.sell.model.Writer;
import gwon.sell.service.ModifyRequest;
import gwon.sell.service.ModifySellService;
import gwon.sell.service.ReadSellService;
import gwon.sell.service.SellNotFoundException;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

public class ModifySellHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/GWON/sell/modifySell.jsp";
	
	private ReadSellService readSellService = new ReadSellService();
	private ModifySellService modifySellService = new ModifySellService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException, SellNotFoundException {

		int no = Integer.parseInt(request.getParameter("no"));

		SellDTO sellDTO = readSellService.getSell(no);
		MemberDTO user_data = (MemberDTO)request.getSession().getAttribute("AUTH_USER");
		
		if(!canModify(user_data, sellDTO)) { 
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 제목과 내용
		ModifyRequest modRequest = 
			new ModifyRequest(user_data.getUser_id(), no, sellDTO.getSell_title(), sellDTO.getSell_category(), sellDTO.getSell_price(), sellDTO.getSell_loc(), sellDTO.getSell_content(), sellDTO.getSell_file());
		
		request.setAttribute("modRequest", modRequest);
		
		return request.getContextPath()+FORM_VIEW;
	}

	private boolean canModify(MemberDTO user_data, SellDTO sellDTO) {
		return   user_data.getUser_id().equals(sellDTO.getUser_id());
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {

	
		MemberDTO user_data = (MemberDTO)request.getSession().getAttribute("AUTH_USER");
		ModifyRequest modRequest = new ModifyRequest(user_data.getUser_id(),
										Integer.parseInt(request.getParameter("sell_no")),
										request.getParameter("sell_title"),
										request.getParameter("sell_category"),
										Integer.parseInt(request.getParameter("sell_price")),
										request.getParameter("sell_loc"),
										request.getParameter("sell_content"),
										request.getParameter("sell_file"));
		
		
		request.setAttribute("modRequest", modRequest); 
		
		modifySellService.modify(modRequest);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);
		if(!errors.isEmpty()) { 
			return request.getContextPath()+FORM_VIEW;
		}
		
		//수정 성공하면 목록 전체조회 페이지로 이동
		response.sendRedirect(request.getContextPath()+"/gwon/sell/sellList.do");
		return null;
	}
	
}