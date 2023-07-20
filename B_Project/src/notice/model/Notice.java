package notice.model;

import java.io.Serializable;

public class Notice implements Serializable{
	private int notice_no;
	private String writer_id;
	private String writer_name;
	private String title;
	private String regdate;
	private String moddate;
	private int read_cnt;
	private String Content;
	
	
	
	public String getContent() {
		return Content;
	}
	
	
	public void setContent(String content) {
		Content = content;
	}


	public int getNotice_no() {
		return notice_no;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public String getWriter_name() {
		return writer_name;
	}
	public String getTitle() {
		return title;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getModdate() {
		return moddate;
	}
	public int getRead_cnt() {
		return read_cnt;
	}


	@Override
	public String toString() {
		return "Notice [notice_no=" + notice_no + ", writer_id=" + writer_id + ", writer_name=" + writer_name
				+ ", title=" + title + ", regdate=" + regdate + ", moddate=" + moddate + ", read_cnt=" + read_cnt + "]";
	}


	public Notice(int notice_no, String writer_id, String title, String regdate, int read_cnt) {
		this.notice_no = notice_no;
		this.writer_id = writer_id;
		this.title = title;
		this.regdate = regdate;
		this.read_cnt = read_cnt;
	}


	public Notice(int notice_no, String writer_id, String title, String regdate, String moddate, int read_cnt) {
		super();
		this.notice_no = notice_no;
		this.writer_id = writer_id;
		this.title = title;
		this.regdate = regdate;
		this.moddate = moddate;
		this.read_cnt = read_cnt;
	}


	public Notice(String writer_id, String title, String content) {
		super();
		this.writer_id = writer_id;
		this.title = title;
		Content = content;
	}



	
	public class NoticeContent {
		int noticeNo;
		String writer_id;
		String title;
		String regdate;
		int read_cnt;
		String Content;
		public int getNoticeNo() {
			return noticeNo;
		}
		public String getWriter_id() {
			return writer_id;
		}
		public String getTitle() {
			return title;
		}
		public String getRegdate() {
			return regdate;
		}
		public int getRead_cnt() {
			return read_cnt;
		}
		public String getContent() {
			return Content;
		}
		@Override
		public String toString() {
			return "NoticeContent [noticeNo=" + noticeNo + ", writer_id=" + writer_id + ", title=" + title
					+ ", regdate=" + regdate + ", read_cnt=" + read_cnt + ", Content=" + Content + "]";
		}
		public NoticeContent(int noticeNo, String writer_id, String title, String regdate, int read_cnt,
				String content) {
			this.noticeNo = noticeNo;
			this.writer_id = writer_id;
			this.title = title;
			this.regdate = regdate;
			this.read_cnt = read_cnt;
			Content = content;
		}
		
		
	
	}
}

