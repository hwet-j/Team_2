//package notice.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.LinkedList;
//import java.util.List;
//
//import jdbc.JDBCUtil;
//import notice.model.Notice;
//import notice.model.NoticeContent;
//
////notice테이블관련 DB작업용 클래스이다
//public class NoticeDAO_temp {
//	Statement stmt = null;
//	PreparedStatement stmt1=null;
//	PreparedStatement stmt2=null;
//	ResultSet rs=null; //
//
//	
//	//전체조회
//    public  List<Notice> showAllNotice(Connection conn){ 
//    	String sql= "select * " + 
//    				"from notice " +
//    				"order by notice_no desc";
//    List<Notice> NoticeList = new LinkedList<>();
//    	try {
//			 stmt1 =conn.prepareStatement(sql);
//			 rs = stmt1.executeQuery();
//				while(rs.next()) {
//					int notice_no = rs.getInt("notice_no");
//					String writer_id = rs.getString("writer_id");
//					String title = rs.getString("title");
//					String regdate = rs.getString("regdate");
//					int read_cnt = rs.getInt("read_cnt");
//					Notice notice = new Notice(notice_no, writer_id, title, regdate, read_cnt);
//					NoticeList.add(notice);
//				}
//				
//				return NoticeList;
//    	} catch (SQLException e) {
//			e.printStackTrace();
//			}finally {
//				JDBCUtil.close(stmt1);
//				JDBCUtil.close(rs);
//			}
//    		return null;
//		}
//		@SuppressWarnings("finally")//
//
//   public NoticeContent showNoticeContent(Connection conn,int noticeNum) {
//			String sql = "SELECT N.notice_no, N.writer_id, N.title, N.regdate, N.read_cnt, NC.content " +
//						"FROM notice N, notice_content NC " +
//						"WHERE N.notice_no = NC.notice_no and N.notice_no=?";
//						String sql2 = "UPDATE Notice "
//								+ "SET READ_CNT=READ_CNT+1 "
//								+ "WHERE notice_NO=?";
//						try {
//							stmt1 = conn.prepareStatement(sql);
//							stmt1.setInt(1, noticeNum);
//							rs = stmt1.executeQuery();
//							if(rs.next()) {
//								int notice_no=rs.getInt(1);
//								String writer_id=rs.getString(2);
//								String title = rs.getString(3);
//								String regdate = rs.getString(4);
//								int read_cnt = rs.getInt(5)+1;
//								String content = rs.getString(6);
//								NoticeContent noticeContent = new NoticeContent(notice_no, writer_id, title, regdate, read_cnt, content);
//								System.out.println("dao에서 처리성공"+noticeContent.toString());
//								
//								stmt2 = conn.prepareStatement(sql2);
//								stmt2.setInt(1, notice_no);
//								int updateCnt = stmt2.executeUpdate();
//								System.out.println("조회수 증가?"+updateCnt);
//								if(updateCnt==1) {return noticeContent;}
//								else {return null;}
//							}else {
//								return null;
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						} finally {
//							JDBCUtil.close(stmt1);
//							JDBCUtil.close(stmt2);
//							JDBCUtil.close(rs);	
//						}
//						return null;
//		}
//    
//		public int deleteNotice(Connection conn, int noticeNo) throws SQLException {
//			int deleteCnt=0;
//			String sql = "delete N, NC " +
//							"FROM NOTICE N " +
//							"JOIN NOTICE_CONTENT NC ON N.notice_no = NC.notice_no " +
//							"WHERE N.notice_no = ?";
//			try {
//				stmt1 = conn.prepareStatement(sql);
//				stmt1.setInt(1,noticeNo);
//				deleteCnt = stmt1.executeUpdate();
//				return deleteCnt;
//			} catch (SQLException e) {
//				JDBCUtil.rollback(conn);
//				e.printStackTrace();
//			} finally {
//				JDBCUtil.close(stmt1);
//			}
//			return 0;
//	}
//	
//		public int writeNotice(Connection conn, Notice notice) {
//			String sql1 = "INSERT INTO Notice(WRITER_ID, writer_name, TITLE, regdate, moddate, READ_CNT) "
//					+ "VALUES(?,?,?,?,?,0)";
//			String sql2 = "INSERT INTO Notice_CONTENT(CONTENT) " + 
//					"VALUES(?)";
//			System.out.println("DAO="+notice.toString());
//			String id = notice.getWriter_id();
//			String name = notice.getWriter_name();
//			String title = notice.getTitle();
//			String regdate = notice.getRegdate();
//			String moddate = notice.getModdate();
//			String content = notice.getContent();
//			int writeCnt = 0;
//			try {
//				conn.setAutoCommit(false);
//				stmt1 = conn.prepareStatement(sql1);
//				stmt1.setString(1, id);
//				stmt1.setString(2, name);
//				stmt1.setString(3, title);
//				stmt1.setString(4, regdate);
//				stmt1.setString(5, moddate);
//
//				int write1 = stmt1.executeUpdate();
//				stmt2 = conn.prepareStatement(sql2);
//				stmt2.setString(1, content);
//				int write2 = stmt2.executeUpdate();
//				writeCnt = write1+write2;
//				if(writeCnt!=2) {
//					System.out.println("공지글 작성 쿼리실행 오류 발생");
//					conn.rollback();
//					return writeCnt;
//					}
//				else {
//					conn.commit();
//					return writeCnt;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				JDBCUtil.close(stmt1);
//				JDBCUtil.close(stmt2);
//			}
//			return writeCnt;
//		}
//		
//		public int noticeCnt(Connection conn) {
//			String sql = "SELECT COUNT(*) FROM notice";
//			int noticeCnt =0;
//			try {
//				stmt = conn.createStatement();
//				rs = stmt.executeQuery(sql);
//				if(rs.next()) {
//					noticeCnt = rs.getInt(1);
//					return noticeCnt;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				JDBCUtil.close(stmt);
//				JDBCUtil.close(rs);			
//			}
//			return noticeCnt;
//		}
//    
//		public List<Notice> showNoticePaged(Connection conn,int pageNo){
//	    	int totalNoticeNo = noticeCnt(conn);
//	    	int pagingUnit =5;
//	    	int startNo = pagingUnit*(pageNo-1);
//	    	int endNo = (pagingUnit*pageNo)-1;
//			if(endNo>totalNoticeNo) {endNo=totalNoticeNo;}
//			String sql = "select * from notice "
//					+ "ORDER BY notice_NO DESC "
//					+ "LIMIT ?,?";
//			List<Notice> noticeList = new LinkedList<>();
//			try {
//				stmt1 = conn.prepareStatement(sql);
//				stmt1.setInt(1, startNo);
//				stmt1.setInt(2, pagingUnit);
//				rs = stmt1.executeQuery();
//				while(rs.next()) {
//					int notice_no = rs.getInt("notice_no");
//					String writer_id = rs.getString("writer_id");
//					String title = rs.getString("title");
//					String regdate = rs.getString("regdate");
//					int read_cnt = rs.getInt("read_cnt");
//					Notice notice = new Notice(notice_no, writer_id, title, regdate, read_cnt);
//					noticeList.add(notice);
//			}
//				return noticeList;
//	    } catch (SQLException e) {
//	    	e.printStackTrace();
//	    } finally {
//	    	JDBCUtil.close(stmt1);
//	    	JDBCUtil.close(rs);
//	    }
//	    return null;
//			
//	    }
//	
//	
//	
//}
//	
//
