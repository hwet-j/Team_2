package noticeBoard.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdbc.JDBCUtil;

public class NoticeBoardDao {
	
	public NoticeBoard insert(Connection conn, NoticeBoard noticeBoard) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into noticeBoard"
					+ "(USER_ID,notice_title,notice_content,notice_views)"
					+"values(?,?,?,0)");
			pstmt.setString(1, noticeBoard.getUSER().getId());
			pstmt.setString(2, noticeBoard.getTitle());
			pstmt.setString(3, noticeBoard.getContent());
			int insertedCount = pstmt.executeUpdate();
			
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs= stmt.executeQuery("select last_insert_id() from noticeBoard");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new NoticeBoard(newNum,
								noticeBoard.getUSER(),
								noticeBoard.getTitle(),
								noticeBoard.getContent(),
								0);
				}
			}
			return null;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
			JDBCUtil.close(pstmt);
		}
	}
}
