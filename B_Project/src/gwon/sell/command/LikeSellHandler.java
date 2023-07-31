package gwon.sell.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gwon.sell.model.SellDTO;
import gwon.sell.service.ReadSellService;
import gwon.sell.service.SellNotFoundException;
import mvc.command.CommandHandler;

public class LikeSellHandler implements CommandHandler {

	
	private String FORM_VIEW = "/view/GWON/sell/readSell.jsp";
	private ReadSellService readSellService = new ReadSellService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else{
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	// 폼 내용을 보내기
	public String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int sellNum = Integer.parseInt(request.getParameter("no"));
		
		int likeCheck = readSellService.getLikeSell(sellNum);
		
		if(likeCheck != 1) {
			return request.getContextPath() + "/view/GWON/sell/readSell.jsp";
		}
		
		try {
			SellDTO sellDTO = readSellService.getSell(sellNum);
			request.setAttribute("sellDTO", sellDTO);
			return request.getContextPath() + "/view/GWON/sell/readSell.jsp";
		} catch (SellNotFoundException e){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} 
	}
	
}
