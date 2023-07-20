package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDao3;

public class ReadNoticeService {

	private NoticeDao3 noticeDao= new NoticeDao3();
	
	public NoticeData getDetail(int no) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			noticeDao.increaseNotice_Views(conn, no);
			
			NoticeData noticeData = noticeDao.getDetail(conn,no);
			if(noticeData==null) {
				throw new NoticeNotFoundException();
			}
			return noticeData;
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
