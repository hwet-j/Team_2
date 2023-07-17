package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.NoticeDao;
import jdbc.connection.ConnectionProvider;
import notice.Notice;

public class ListNoticeService {
	NoticeDao noticeDao = new NoticeDao();
	int size =3;
	
	public NoticePage getNoticePage(int pageNum) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = noticeDao.selectCount(conn);
			
			List<Notice> content = noticeDao.select(conn,(pageNum-1)*size,size);
			
			NoticePage np = new NoticePage(total, pageNum, size, content);
			System.out.println("ListNoticeService- getNoticePage()의 결과 np="+np);
			return np;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}			
			
			
	}
}
