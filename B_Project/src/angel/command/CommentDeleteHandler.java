package angel.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import angel.dao.ArticleDAO;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class CommentDeleteHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		ArticleDAO articleDAO = new ArticleDAO();
		Connection conn = ConnectionProvider.getConnection();
		int result = articleDAO.commentDelete(conn, commentNo);
		
		JSONObject jsonResponse = new JSONObject();
		
		String message = "";
		if (result > 0) {
			message = "success";
		} else {
			message = "failed";
		}
		
		jsonResponse.put("message", message);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(jsonResponse.toString());
		JDBCUtil.close(conn);
		return null;
	}

}
