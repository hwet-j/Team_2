//package notice.service;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import jdbc.connection.ConnectionProvider;
//import notice.dao.NoticeDAO_temp;
//import notice.model.NoticeContent;
//
//public class ShowNoticeContentService {
//	Connection conn = null;
//	public NoticeContent showNoticeContentService(int noticeNum)throws SQLException {
//		NoticeContent noticeContent = null;
//		try {
//				conn = ConnectionProvider.getConnection();
//				NoticeDAO_temp noticeDAO = new NoticeDAO_temp();
//				noticeContent = noticeDAO.showNoticeContent(conn, noticeNum);
//				System.out.println("Service 호출"+noticeContent.toString());
//				return noticeContent;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			conn.close();
//		}
//		return noticeContent;
//	}
//		public int showNoticeCnt() {
//			int noticeCnt=0;
//			
//			try {
//					conn = ConnectionProvider.getConnection();
//					NoticeDAO_temp noticeDAO = new NoticeDAO_temp();
//					noticeCnt = noticeDAO.noticeCnt(conn);
//			}catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return 0;
//		}
//	}