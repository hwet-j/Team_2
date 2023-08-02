package angel.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.dao.ArticleDAO;
import angel.model.Article;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class SearchHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String type = request.getParameter("type");
		String subject = request.getParameter("subject");
		
//		ArticleDAO articleDAO = new ArticleDAO();
//		articleDAO.select(conn, type, subject);
		
		Connection conn = ConnectionProvider.getConnection(); 
		List<Article> select =new ArticleDAO().select(conn, type, subject);
		request.setAttribute("ARTICLE", select);
		
		JDBCUtil.close(conn);
		
		return request.getContextPath() + "/view/ANGEL/article/Article.jsp";
	}
	
}
