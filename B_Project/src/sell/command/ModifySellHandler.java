package sell.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;		// 회원 정보에 접근하기
import mvc.command.CommandHandler;
import sell.model.SellDTO;
import sell.model.Writer;
import sell.service.ModifyRequest;
import sell.service.ModifySellService;
import sell.service.ReadSellService;
import sell.service.SellNotFoundException;

public class ModifySellHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/sell/modifySell.jsp";
	
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
		User user = (User)request.getSession().getAttribute("AUTH_USER");
		
		if(!canModify(user, sellDTO)) { 
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 제목과 내용
		ModifyRequest modRequest = 
			new ModifyRequest(user.getId(), no, sellDTO.getSell_title(), sellDTO.getSell_category(), sellDTO.getSell_price(), sellDTO.getSell_loc(), sellDTO.getSell_content(), sellDTO.getSell_file(), sellDTO.getSell_fav());
		
		request.setAttribute("modRequest", modRequest);
		
		return request.getContextPath()+FORM_VIEW;
	}

	private boolean canModify(User user, SellDTO sellDTO) {
		return   user.getId().equals(sellDTO.getUser_id());
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {

	
		User user = (User)request.getSession().getAttribute("AUTH_USER");
		ModifyRequest modRequest = new ModifyRequest(user.getId(),
										Integer.parseInt(request.getParameter("sell_no")),
										request.getParameter("sell_title"),
										request.getParameter("sell_category"),
										Integer.parseInt(request.getParameter("sell_price")),
										request.getParameter("sell_loc"),
										request.getParameter("sell_content"),
										request.getParameter("sell_file"),
										request.getParameter("sell_fav"));
		
		
		System.out.println("여기서부터 시작");
		System.out.println("no"+request.getParameter("sell_no"));
		System.out.println("title"+request.getParameter("sell_title"));
		System.out.println("cate"+request.getParameter("sell_category"));
		System.out.println("price"+request.getParameter("sell_price"));
		System.out.println("loc"+request.getParameter("sell_loc"));
		System.out.println("con"+request.getParameter("sell_content"));
		System.out.println("file"+request.getParameter("sell_file"));
		System.out.println("fav"+request.getParameter("sell_fav"));
		
		System.out.println(modRequest);
		
		request.setAttribute("modRequest", modRequest); 
		
		modifySellService.modify(modRequest);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);
		if(!errors.isEmpty()) { 
			return request.getContextPath()+FORM_VIEW;
		}
		
		//수정 성공하면 목록 전체조회 페이지로 이동
		response.sendRedirect(request.getContextPath()+"/sellList.do");
		return null;
	}
	
}
