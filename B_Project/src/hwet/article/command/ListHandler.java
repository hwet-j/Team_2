package hwet.article.command;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.service.ListArticleService;
import mvc.command.CommandHandler;

/* hwet/article/list.do  게시판 글 리스트를 확인하는 기능(페이징처리, 카테고리, 검색기능) */
public class ListHandler implements CommandHandler {
	
	private ListArticleService listService = new ListArticleService();
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String str_page_no = request.getParameter("page_no"); // user가 선택한 페이지번호
        String cate_param = request.getParameter("category_info");
        String keyword = request.getParameter("keyword");
        String search_type = request.getParameter("search_type");
        
        // 페이지
        int page_no = 1; 
        if (str_page_no != null) {	
        	page_no = Integer.parseInt(str_page_no);
        }
        
        // 카테고리 설정
        String cate_info = "전체";
        if (cate_param != null) {	// 페이지 번호를 받아왔으면 pageNo를 해당 값으로 초기화
        	cate_info = cate_param;
        }
        
        // 검색 타입이 null값이면 검색 기능을 사용하지 않은것이므로 ""으로 초기화 작업
        if (search_type == null) {
        	search_type = "";
        	keyword = "";
        }
        
        // 페이지 당 표시할 데이터 개수를 설정
        int list_size = 5;
        
        // 설정된 정보에 따라(카테고리, 검색어, 행 개수) 서비스를 통해 해당 페이지의 데이터를 가져옵니다.
        List<HwetArticleDTO> board_list = listService.getBoardListWithPaging(page_no, list_size, cate_info, search_type, keyword);
        // 검색된 리스트, 표시할 데이터 개수, 카테고리, 검색타입, 검색어를 전달 
        request.setAttribute("board_list", board_list);
        request.setAttribute("list_size", list_size);
        request.setAttribute("category_info", cate_info);
        request.setAttribute("keyword", keyword);
        request.setAttribute("search_type", search_type);

        // 페이징 처리를 위해 전체 데이터 개수를 구합니다.
        // int total_data_count = listService.getTotalDataCount(); // --> 검색 설정으로 인하여 전체 리스트 길이를 가져오면 안됨
        int total_data_count = listService.getCategoryDataCount(cate_info, search_type, keyword);
        // System.out.println("전체 데이터 개수 : " + total_data_count);
        
        // 총 데이터 개수(totalDataCount)를 기반으로 총 페이지 수(totalPages)를 계산
        int total_pages = (int) Math.ceil((double) total_data_count / list_size);

        // jsp파일에 총 페이지 수(totalPages)를 전달
        request.setAttribute("total_pages", total_pages);

        // 페이지 이동 처리
        String view_page = "/view/HWET/article/list.jsp"; // 기본 페이지
        
        // 만약 데이터가 없는 페이지이고, 페이지 번호가 1보다 크다면 첫 페이지로 이동합니다. (get방식을 통해 고의적으로 이상한 페이지를 작성했을 때를 대비)
        // 단, 카테고리, 검색어, 검색타입은 유지한 상태로 첫 페이지 이동
        if (board_list.isEmpty() && page_no > 1) {
        	// senRedirect로 데이터를 전송하는과정에서 한글이 들어가면 에러가 발생하여 찾아본 결과 URL은 ASCII 문자로만 이루어져있어야 하기 때문에
        	// 한글과 같은 비 ASCII 문자를 인코딩하여 입력해 줘야 한다. 
        	String encoded_keyword = URLEncoder.encode(keyword, "UTF-8");
            response.sendRedirect("/hwet/article/list.do?page_no=1&search_type="+search_type+"&keyword="+ encoded_keyword);
            return null; // 이동한 후에는 더 이상의 처리를 하지 않도록 null을 반환
        }

        return view_page;
    }

}
