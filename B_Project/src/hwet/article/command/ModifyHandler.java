package hwet.article.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.service.ModifyArticleService;
import mvc.command.CommandHandler;

// /hwet/article/modify.do
public class ModifyHandler implements CommandHandler {
	
	ModifyArticleService modifyArticleService = new ModifyArticleService();
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 수정을 위해 필요한 데이터 가져오기
		String bNo = request.getParameter("boardId"); 
		int no = Integer.parseInt(bNo);
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String link = request.getParameter("link");
		String content = request.getParameter("content");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));

		// 가져온 데이터를 DTO 객체화
		HwetArticleDTO data = new HwetArticleDTO();
		data.setBoardId(no);
		data.setTitle(title);
		data.setCategory(category);
		data.setLink(link);
		data.setContent(content);
		
		// 수정 요청을 진행하며, 리턴값은 실패하면 0
		int result = modifyArticleService.modifyArticle(data);
		
		// 0 이아니면 성공
		if (result != 0) {
			// 수정에 성공했을때, 수정한 게시글을 다시 확인 또는 리스트로 돌아가기 선택
			try {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<title>Insert Failed!!</title>");
				out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@9\"></script>");
				out.println("</head>");
				out.println("<body>");
				out.println("<script>");
				out.println("Swal.fire({");
				out.println("    icon: 'success',");
				out.println("    title: '글수정이 성공적으로 이뤄졌습니다.',");
				out.println("    text: '작성한 글을 확인하시겠습니까?',");
				out.println("    showCancelButton: true,");
				out.println("    confirmButtonColor: '#3085d6',");
				out.println("    cancelButtonColor: '#d33',");
				out.println("    confirmButtonText: 'Yes'");
				out.println("}).then((result) => {");
				out.println("if (result.isConfirmed) {");
				out.println("    window.location.href = '" + request.getContextPath() + "/hwet/article/read.do?no=" + no + "&pageNo=" + pageNo + "';");
				out.println("	} else {");
				out.println("    window.location.href = '" + request.getContextPath() + "/hwet/article/list.do';");
				out.println("	}");		
				out.println("})");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		} else {
			return null;
		}
		
		
       
    }

}
