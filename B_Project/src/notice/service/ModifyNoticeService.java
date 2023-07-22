//package notice.service;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import jdbc.JDBCUtil;
//import jdbc.connection.ConnectionProvider;
//import notice.dao.NoticeDao3;
//
//public class ModifyNoticeService {
//	private NoticeDao3 noticeDao = new NoticeDao3();
//
//	public void modify(ModifyRequest modReq) {
//		Connection conn = null;
//		try {
//			conn =ConnectionProvider.getConnection();
//			conn.setAutoCommit(false);
//			
//			NoticeData notice = noticeDao.getDetail(conn, modReq.getNotice_number());
//			if(notice==null) {
//				throw new NoticeNotFoundException();
//			}
//			if(!canModify(modReq.getUser_id(), notice)) {
//				throw new PermissionDeniedException();
//			}
//			noticeDao.update(conn, modReq.getNotice_number(), modReq.getNotice_title());
//			
//			conn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JDBCUtil.rollback(conn);
//			throw new RuntimeException();
//		}catch(PermissionDeniedException e) {
//			JDBCUtil.rollback(conn);
//			throw e;
//		}finally {
//			JDBCUtil.close(conn);
//		}
//	}
//	private boolean canModify(String modifyingUser_id, NoticeData notice) { return
//			modifyingUser_id.equals(notice.getUser_id()); }
//	}
//
