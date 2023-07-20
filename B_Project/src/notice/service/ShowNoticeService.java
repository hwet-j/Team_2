package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import notice.Notice;
import notice.dao.NoticeDAO;
import notice.dao.NoticeDao3;

public class ShowNoticeService {
	
	public List<Notice> showAllNotice() throws SQLException {
		Connection conn=null;
		List<Notice> noticeList=null;
		try {
			conn = ConnectionProvider.getConnection();
			
			NoticeDAO noticeDAO = new NoticeDAO();
			noticeList = noticeDAO.showAllNotice(conn);
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
	
	public List<Notice> showNoticePaged(int pageNo) throws SQLException {
		Connection conn = null;
		List<Notice> noticeList=null;
		try {
			conn = ConnectionProvider.getConnection();
			NoticeDAO noticeDAO = new NoticeDAO();
			noticeList = noticeDAO.showNoticePaged(conn, pageNo);
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}
}
