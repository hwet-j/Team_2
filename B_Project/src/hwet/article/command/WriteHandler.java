package hwet.article.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hwet.article.service.WriteArticleService;
import mvc.command.CommandHandler;

// /hwet/article/write.do
public class WriteHandler implements CommandHandler {
	
	private WriteArticleService writeService = new WriteArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {// 요청방식이 get방식이면  FORM_VIEW
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {// 요청방식이 post방식이면 기능
			return processSubmit(request,response);  
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
	
	// 글작성 처리 
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String link = request.getParameter("link");
		String content = request.getParameter("content");
		
		// 글작성이 실패,성공에 따라 조건을 설정하기 위해 result 변수 설정
		int result = -1;
		result = writeService.writeArticle(writer, category, title, link, content);
		
		// 글작성 성공
		if (result != -1) {
			// 글 작성 성공하고, 작성한 글 확인 또는 리스트로 돌아가기 선택
			try {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<title>Insert Success!!</title>");
				out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@9\"></script>");
				out.println("</head>");
				out.println("<body>");
				out.println("<script>");
				out.println("Swal.fire({");
				out.println("    icon: 'success',");
				out.println("    title: '글작성이 성공적으로 이뤄졌습니다.',");
				out.println("    text: '작성한 글을 확인하시겠습니까?',");
				out.println("    showCancelButton: true,");
				out.println("    confirmButtonColor: '#3085d6',");
				out.println("    cancelButtonColor: '#d33',");
				out.println("    confirmButtonText: 'Yes'");
				out.println("}).then((result) => {");
				out.println("if (result.isConfirmed) {");
				out.println("    window.location.href = '" + request.getContextPath() + "/hwet/article/read.do?no=" + result + "&pageNo=1';");
				out.println("	} else {");
				out.println("    window.location.href = '" + request.getContextPath() + "/hwet/article/list.do';");
				out.println("	}");
				out.println("})");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "/hwet/article/list.do";
		} else {	// 실패
			try {
				// redirect 방식
				//response.sendRedirect(request.getContextPath()+"/view/loginError.jsp");
				
				// forward 방식이 주소 변경없이 경고문구를 작성해주고 페이지 이동을 하기 때문에 좀더 좋아 보이지만, 비밀번호가 유출된거같다는 경고문구가 나옴
				// RequestDispatcher dispatcher = request.getRequestDispatcher("/view/loginError.jsp");
				// dispatcher.forward(request, response);
				
				// 페이지 이동없이 진행 (forward와 동일한 작업을 java코드로 진행)
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
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
				out.println("    icon: 'error',");
				out.println("    title: '글작성 실패',");
				out.println("    text: '목록으로 돌아갑니다.',");
				out.println("}).then(function() {");
				out.println("    window.location.href = '" + request.getContextPath() + "/hwet/article/list.do';");
				out.println("});");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		return "/view/HWET/article/registForm.jsp";
	}


	// 로그인 상태에 따라 글작성폼 보여주기 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		// 로그인 상태에서는 글작성 가능, 그렇지않으면 알림창 이후에 로그인페이지 이동
		if (session.getAttribute("AUTH_USER") == null) {
			try {
				// 로그인 하지 않은 상태에서는 경고창을 띄우고, 로그인 페이지 이동 또는 취소
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<title>Login Failed!!</title>");
				out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@9\"></script>");
				out.println("</head>");
				out.println("<body>");
				out.println("<script>");
				out.println("Swal.fire({");
				out.println("    icon: 'error',");
				out.println("    title: '글작성 권한이 없습니다.',");
				out.println("    text: '로그인 페이지로 이동하시겠습니까?',");
				out.println("    showCancelButton: true,");
				out.println("    confirmButtonColor: '#3085d6',");
				out.println("    cancelButtonColor: '#d33',");
				out.println("    confirmButtonText: 'Yes'");
				out.println("}).then((result) => {");
				out.println("if (result.isConfirmed) {");
				out.println("    window.location.href = '" + request.getContextPath() + "/login.do';");
				out.println("	} else {");
				out.println("	window.location.href = document.referrer;");
				out.println("	}");
				out.println("})");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		} else {
			// 로그인 상태면 글작성 Form이동
			return "/view/HWET/article/registForm.jsp";
		}
		
	}

}
