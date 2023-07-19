package hwet.article.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.service.ListArticleService;
import mvc.command.CommandHandler;

// /hwet/article/list.do
public class ListHandler implements CommandHandler {
	
	private ListArticleService listService = new ListArticleService();
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String strPageNo = request.getParameter("pageNo"); // user가 선택한 페이지번호

        int pageNo = 1; // user가 선택 안했을 때 기본적으로 보여지는 페이지번호
        if (strPageNo != null) {	// 페이지 번호를 받아왔으면 pageNo를 해당 값으로 초기화
            pageNo = Integer.parseInt(strPageNo);
        }

        // 페이지 당 표시할 데이터 개수를 설정
        int pageSize = 5;

        // 서비스를 통해 해당 페이지의 데이터를 가져옵니다.
        List<HwetArticleDTO> boardList = listService.getBoardListWithPaging(pageNo, pageSize);
        request.setAttribute("boardList", boardList);
        request.setAttribute("listSize", pageSize);
        // System.out.println(boardList);

        // 페이징 처리를 위해 전체 데이터 개수를 구합니다.
        int totalDataCount = listService.getTotalDataCount();
        // System.out.println("전체 데이터 개수 : " + totalDataCount);
        
        // 총 데이터 개수(totalDataCount)를 기반으로 총 페이지 수(totalPages)를 계산
        int totalPages = (int) Math.ceil((double) totalDataCount / pageSize);

        // jsp파일에 총 페이지 수(totalPages)를 전달
        request.setAttribute("totalPages", totalPages);

        // 페이지 이동 처리
        String viewPage = "/view/HWET/article/list.jsp"; // 기본 페이지
        
        // 만약 데이터가 없는 페이지이고, 페이지 번호가 1보다 크다면 첫 페이지로 이동합니다. (get방식을 통해 고의적으로 이상한 페이지를 작성했을 때를 대비)
        if (boardList.isEmpty() && pageNo > 1) {
            response.sendRedirect(request.getContextPath() + "/hwet/article/list.do");
            return null; // 이동한 후에는 더 이상의 처리를 하지 않도록 null을 반환
        }

        return viewPage;
    }

}
