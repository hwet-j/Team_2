package member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberDTO;
import member.service.MemberListService;
import mvc.command.CommandHandler;


/* admin/showAll.do */
public class MemberAdminController implements CommandHandler {
	MemberListService memberListService = new MemberListService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 회원아이디값을 불러와서 admin을 포함하는지 확인 
		HttpSession session = request.getSession(true);
		if (session != null) {
			MemberDTO login_id = (MemberDTO) session.getAttribute("AUTH_USER");
			if (login_id != null) {
                String userID = login_id.getUser_id();
                if (userID.contains("admin")) {
                	// 검색 및 페이징 처리
            		String keyword = request.getParameter("keyword");
                    String search_type = request.getParameter("search_type");
            		String str_page_no = request.getParameter("page_no");
            		int page_no = 1; 
                    if (str_page_no != null) {	
                    	page_no = Integer.parseInt(str_page_no);
                    }
                    // 검색 타입이 null값이면 검색 기능을 사용하지 않은것이므로 ""으로 초기화 작업
                    if (search_type == null) {
                    	search_type = "";
                    	keyword = "";
                    }
                 
                    int list_size = 10;	// 페이지 에 표시할 데이터 개수를 설정
                    int total_data_count = memberListService.allMemberCount(search_type, keyword);
                    int total_pages = (int) Math.ceil((double) total_data_count / list_size);
                    
                    List<MemberDTO> list = memberListService.AllMemberShow(page_no, list_size, search_type, keyword);
            		request.setAttribute("USERS", list);
            		request.setAttribute("total_pages", total_pages);
            		
            		return "/view/Admin/AdminUser.jsp";
                }
            } else {
                return "/view/errorPage.jsp";
            }
		} else {
			return "/view/errorPage.jsp";
		}
		
		
		
		return "/view/errorPage.jsp";
	}
	
	

}
