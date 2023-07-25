package notice.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.Member;
import auth.service.User;
import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.ModifyRequest;
import notice.service.NoticeData;
import notice.service.NoticeService;

//글수정 담당 컨트롤러
//요청주소 http://localhost/notice/modify.do
public class ModifyNoticeHandler implements CommandHandler{
	
	//필드
	private static final String FORM_VIEW = "/view/notice/modifyNoticeForm.jsp";
	
	//수정폼보여줄 때 상세내용을 가져오기위한 service
	private NoticeService noticeService  = new NoticeService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ModifyNoticeHandler의 process진입");
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}//process
	//수정폼 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));//수정할 글번호
		//2.비즈니스로직<->Service<->DAO<->DB
		//파라미터 int no: 상세조회하고 싶은 글번호
		NoticeData nod = noticeService.getDetail(no);
		HttpSession session = request.getSession();
		User user =(User)request.getSession().getAttribute("AUTH_USER");//세션로그인유저정보
		session.setAttribute("AUTH_USER", user);
		if(!canModify(user, nod)) {//수정불가시
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 제목과 내용
		ModifyRequest modReq = new ModifyRequest(user.getId(), no, nod.getTitle(),nod.getContent());
		request.setAttribute("modReq", modReq);
		
		return request.getContextPath()+FORM_VIEW;	

}
	//수정가능여부체크
	// 로그인한 user의 id가 글작성자id와 일치하는지 비교하여 동일하면 수정가능
	//리턴 boolean - 수정할 수 있으면 true반환, 불가하면 false반환
	private boolean canModify(User user, NoticeData nod) {
		System.out.println("user.getId()="+user.getId());
		return user.getId().equals(nod.getWriter_id());
	}
	//수정처리
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		System.out.println("ModifyHandler의 process()진입");
		int no =Integer.parseInt(request.getParameter("no"));
		String title =request.getParameter("title");
		String content=request.getParameter("content");
		String writer_id=request.getParameter("writer_id");
		User authUser = (User)request.getSession().getAttribute("AUTH_USER");
	
	//수정처리를 위한 데이터를 ModifyRequest객체로 생성	
	//System.out.println("Modify핸들러 modReq="+modReq);
	ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,title,content);	
	request.setAttribute("modReq", modReq);

	//유효성검사
	//에러정보가 담기는 Map
	Map<String, Boolean> errors = new HashMap<String, Boolean>();
	request.setAttribute("errors", errors);
	if(!errors.isEmpty()) {
		return request.getContentType()+FORM_VIEW;
			
	}
	System.out.println("Modify핸들러 modReq="+modReq);
	//2.비즈니스로직<->Service<->DAO<->DB
	//파라미터 ModifyRequest modReq - 수정처리를 위한 세션에서 가져온 회원id, 글번호, 제목, 내용
	noticeService.modify(modReq);
	//3.Model
	//4.View 
	//성공시 목록보기요청
	response.sendRedirect(request.getContextPath()+"/notice/list.do");
	return null;//수정변경하기
	
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
