package board.whi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import board.whi.model.WhiPhotoArticle;
import jdbc.JDBCUtil;

public class WhiPhotoDAO {
PreparedStatement pstmt1 = null;
PreparedStatement pstmt2 = null;
Statement stmt1 = null;

ResultSet rs1 = null;
//페이징 사이즈 지정
static int pagingSize = 10;

	// 사진을 포함한 자료 업로드 - 조회수, 좋아요, 싫어요, 등록일자 default 사용 - 작업자:조중현
	public int insertArticle(Connection conn, WhiPhotoArticle article) throws SQLException {
		//sql문 작성, 테이블명 whi_photo (대소문자주의)
		String sql1 = "INSERT INTO whi_photo(user_id, title, content, img_src) "
				+ "values(?,?,?,?)";
		//db접근
		pstmt1 = conn.prepareStatement(sql1);
		pstmt1.setString(1, article.getUser_id());
		pstmt1.setString(2, article.getTitle());
		pstmt1.setString(3, article.getContent());
		pstmt1.setString(4, article.getImg_src());
		int insertCnt = pstmt1.executeUpdate();
		//insert에 성공했을 경우 입력한 게시글 번호를 가져와 사용
		int PhotoArticleNo = 0;
		if(insertCnt==1) {
			pstmt2 = conn.prepareStatement("select last_insert_id() from whi_board");
			rs1 = pstmt2.executeQuery();
			if(rs1.next()) {
				PhotoArticleNo = rs1.getInt(1);
			}
		}
		JDBCUtil.close(pstmt1);
		JDBCUtil.close(pstmt2);
		JDBCUtil.close(rs1);
		return PhotoArticleNo;
	}

	// 글 목록 조회 - 글번호, 작성자명, 제목, 작성일, 조회수, 좋아요 - 작업자:조중현
	public List<WhiPhotoArticle> photoArticleList(Connection conn, int pageNo){
		//쿼리문
		String sql = "select * from whi_photo order by article_no desc limit ?,?";
		
		//객체 list 생성
		List<WhiPhotoArticle> artlcleList = new LinkedList<WhiPhotoArticle>();
		
		
	
		//db접근
		try {
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, 0+(pageNo-1)*pagingSize);
			pstmt1.setInt(2, pagingSize);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
				int article_no = rs1.getInt("article_no");
				String user_id = rs1.getString("user_id");
				String title = rs1.getString("title");
				String content = rs1.getString("content");
				String reg_date = rs1.getString("reg_date");
				int read_cnt = rs1.getInt("read_cnt");
				int like_cnt = rs1.getInt("like_cnt");
				int dislike_cnt = rs1.getInt("dislike_cnt");
				String img_src = rs1.getString("img_src");
				WhiPhotoArticle article = new WhiPhotoArticle(article_no, user_id, title, content, reg_date, read_cnt, like_cnt, dislike_cnt, img_src);
				artlcleList.add(article);
			}
			return artlcleList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs1);
			JDBCUtil.close(pstmt1);
		}
		
		return artlcleList;		
	}

	// 리스트의 글 개수를 조회하는 메소드 - 작업자:조중현
	public int pageCnt(Connection conn) {
		//쿼리문
		String sql = "select count(*) from whi_photo";
		//결과 개수 변수 선언
		int pageCnt = 0;	//페이지개수
		int articleCnt =0;	//게시글개수
		
		try {
			pstmt1 = conn.prepareStatement(sql);
			rs1 = pstmt1.executeQuery();
			if(rs1.next()) {articleCnt = rs1.getInt(1);}
			pageCnt = articleCnt / pagingSize +1;
			if(articleCnt % pagingSize ==0) {pageCnt -=1;}
			return pageCnt;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs1);
			JDBCUtil.close(pstmt1);
		}
		return 0;
	}

	//선택된 게시글을 가져오는 메소드 - 작업자:조중현
	public WhiPhotoArticle selectArticle(Connection conn, int articleNo) {
		//쿼리문작성1 - 해당하는 정보의 read_cnt 1 증가시키기
		String sql1 = "UPDATE whi_photo " + 
				"SET read_cnt = read_cnt + 1 " + 
				"WHERE article_no = ?";
		//db접근1 + 업데이트 체크 객체 생성
		int updateCk = 0;
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt2.setInt(1, articleNo);
			updateCk = pstmt2.executeUpdate();
			if(updateCk==0) {return null;}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//쿼리문작성2 - 해당하는 정보 가져오기
		String sql2 = "select * from whi_photo where article_no=?";
		//객체 생성
		WhiPhotoArticle article = null;
		//db접근2
		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, articleNo);
			rs1 = pstmt2.executeQuery();
			//객체 생성 후 리턴
			if(rs1.next()) {
				int article_no = rs1.getInt("article_no");
				String user_id = rs1.getString("user_id");
				String title = rs1.getString("title");
				String content = rs1.getString("content");
				String reg_date = rs1.getString("reg_date");
				int read_cnt = rs1.getInt("read_cnt");
				int like_cnt = rs1.getInt("like_cnt");
				int dislike_cnt = rs1.getInt("dislike_cnt");
				String img_src = rs1.getString("img_src");
				article = new WhiPhotoArticle(article_no, user_id, title, content, reg_date, read_cnt, like_cnt, dislike_cnt, img_src);
			}
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs1);
			JDBCUtil.close(pstmt1);
			JDBCUtil.close(pstmt2);
		}
		return null;
	}

	//선택된 게시글을 삭제하는 메소드 - 작업자:조중현
	public int delete(Connection conn, int articleNo) {
		//쿼리문작성
		String sql1 = "delete from whi_photo where article_no=?";
		//DB접근
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, articleNo);
			int delChk = pstmt1.executeUpdate();
			return delChk;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs1);
			JDBCUtil.close(pstmt1);
		}
		return 0;
	}

	public int rating(Connection conn, int articleNo, boolean like) {
		//쿼리문작성
		String select="";
		if(like) {select="like_cnt = like_cnt + 1 ";}
		else {select="dislike_cnt = dislike_cnt + 1 ";}
		String sql1 = "UPDATE whi_photo " + 
				"SET "+select+ 
				"WHERE article_no = ?";
		
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, articleNo);
			int updateCk = pstmt1.executeUpdate();
			return updateCk;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs1);
			JDBCUtil.close(pstmt1);
		}
		return 0;
		
	}


	
	
}
