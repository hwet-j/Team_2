package gwon.sell.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gwon.sell.service.ListSellService;
import gwon.sell.service.SearchRequest;
import gwon.sell.service.SellPage;
import mvc.command.CommandHandler;

public class SearchSellHandler implements CommandHandler {


	
	// 단순히 가져온 게 아니라 데이터를 담아 DAO까지 보내서 다시 가져와야 함
	private String FORM_VIEW = "/view/GWON/sell/listSell.jsp";
	private ListSellService listSellService = new ListSellService();
	
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

		
		int pageNo = 1;

		SearchRequest searchrequest = createSearchRequest(request);
		
		String strPageNo = request.getParameter("pageNo");
		
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		SellPage sellPage = listSellService.searchSellPage(pageNo,searchrequest); 
		
		request.setAttribute("sellPage", sellPage);
		request.setAttribute("nowPage", pageNo);
		
		if (request.getParameter("searchColumn") == "제목") {
			request.setAttribute("searchColumn",searchrequest.getSearchColumn());
		} else if (request.getParameter("searchColumn") == "작성자") {
			request.setAttribute("searchColumn",searchrequest.getSearchColumn());
		} else if (request.getParameter("searchTitleNUser") == "카테고리") {
			request.setAttribute("searchColumn",searchrequest.getSearchColumn());
		} else if (request.getParameter("searchTitleNUser") == "희망가격") {
			request.setAttribute("searchColumn",searchrequest.getSearchColumn());
		} else if (request.getParameter("searchTitleNUser") == "지역") {
			request.setAttribute("searchColumn",searchrequest.getSearchColumn());
		} else if (request.getParameter("searchColumn").equals("선택하세요")) {
			return request.getContextPath()+FORM_VIEW;
		} else if (request.getParameter("searchText") == null || request.getParameter("searchText").equals("")) {
			return request.getContextPath()+FORM_VIEW;
		}
		
		return request.getContextPath()+FORM_VIEW;
	}
	
	public SearchRequest createSearchRequest(HttpServletRequest request) {
		return new SearchRequest(
				request.getParameter("searchColumn"),
				request.getParameter("searchText"));
	}
	
}
