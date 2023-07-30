package hwet.article.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.model.HwetReplyDTO;
import hwet.article.service.ReadArticleService;
import hwet.article.service.ReadReplyService;
import mvc.command.CommandHandler;

/* hwet/article/read.do  게시글 하나를 자세히 확인하는 기능 */
public class ReadHandler implements CommandHandler {
	
	private ReadArticleService readArticleService = new ReadArticleService();
	private ReadReplyService readReplyService = new ReadReplyService();
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 상세하게 보고 싶은 글번호
		int board_id = Integer.parseInt(request.getParameter("no"));
		String keyword = request.getParameter("keyword");
        String search_type = request.getParameter("search_type");
        String cate_param = request.getParameter("category_info");
        
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
		// 조회수 증가 (현재 조건 없음, 조회하면 무조건 증가함 이후에 필요하면 조건설정)
        readArticleService.increaseHit(board_id);
		
		// 조회수 증가 후 데이터 가져오기
		HwetArticleDTO data = readArticleService.getDetail(board_id);
		
		System.out.println(data.getContent());
		
		// 댓글 정보 가져오기 (페이징 처리 없음) 
		List<HwetReplyDTO> re_data = readReplyService.getReplyList(board_id);
		
		// 게시판 정보
		request.setAttribute("data", data);
		// 페이지 정보 
		request.setAttribute("keyword", keyword);
        request.setAttribute("search_type", search_type);
        request.setAttribute("category_info", cate_info);
        // 댓글 정보 
        request.setAttribute("reply_data", re_data);
        
        
        
        return "/view/HWET/article/detail.jsp";
    }

}
