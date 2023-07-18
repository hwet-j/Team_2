package notice.service;

import java.sql.Connection;
import java.sql.SQLException;


import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import notice.Notice;
import notice.dao.NoticeDao;

public class WriteNoticeService {
	private NoticeDao noticeDao = new NoticeDao();
	
	public Integer write(WriteRequest req) {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice notice =toNotice(req);
			
			Notice savedNotice = noticeDao.insert(conn,notice);
			if(savedNotice==null) {
				throw new RuntimeException("Fail to insert notice");
			}
			
			conn.commit();
			return savedNotice.getNumber();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e) {
			e.printStackTrace();
			JDBCUtil.rollback(conn);
			throw e;
		} finally {
			JDBCUtil.close(conn);
		}		
	}
	
	private Notice toNotice(WriteRequest req) {

		return new Notice(null, req.getUser(), req.getTitle(), req.getContent(), 0);
	}
	
	
}
