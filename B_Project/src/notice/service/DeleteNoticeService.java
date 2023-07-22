//package notice.service;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import jdbc.connection.ConnectionProvider;
//import notice.dao.NoticeDAO_temp;
//
//public class DeleteNoticeService {
//	public int deleteNotice(int noticeNo) {
//		Connection conn = null;
//		int deleteCnt =0;
//		try {
//			conn = ConnectionProvider.getConnection();
//			NoticeDAO_temp noticeDAO = new NoticeDAO_temp();
//			deleteCnt = noticeDAO.deleteNotice(conn, noticeNo);
//			return deleteCnt;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//		}
//	}
