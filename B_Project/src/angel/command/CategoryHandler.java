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

public class CategoryHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category");
		
		Connection conn = ConnectionProvider.getConnection();
		ArticleDAO articleDAO = new ArticleDAO();
		List<Article> article = articleDAO.category(conn, category);
		request.setAttribute("ARTICLE", article);
		
		
		JDBCUtil.close(conn);
		
		return request.getContextPath() + "/view/ANGEL/article/Article.jsp";
	}

}
