//package notice.service;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import jdbc.connection.ConnectionProvider;
//import notice.dao.NoticeDAO_temp;
//import notice.model.Notice;
//
//public class WriteNoticeService {
//	public int writeNotice(Notice notice) {
//		Connection conn = null;
//		int writeCnt = 0;
//		try {
//			conn = ConnectionProvider.getConnection();
//			NoticeDAO_temp noticeDAO = new NoticeDAO_temp();
//			System.out.println("service ="+notice.toString());
//			return writeCnt = noticeDAO.writeNotice(conn, notice);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0; 
//	}
//}
