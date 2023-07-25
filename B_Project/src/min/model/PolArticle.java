package min.model;

import java.util.Date;

public class PolArticle {
		//필드 p631
		private Integer number; //polArticle_no 글번호 pk
		private Writer writer;	//writer_id, writer_name의 값을 저장하는 별도의 Writer클래스
		private String title; 
		private Date   regdate;
		private Date   modifiedDate;
		private int    readCount;
		
		
		//생성자 p631 14라인 소스-컨스트럭트-유징필드
		public PolArticle(Integer number, Writer writer, String title, Date regdate, Date modifiedDate, int readCount) {
			super();
			this.number = number;
			this.writer = writer;
			this.title = title;
			this.regdate = regdate;
			this.modifiedDate = modifiedDate;
			this.readCount = readCount;
		}


		//메서드 - p631 24라인
		public Integer getNumber() {
			return number;
		}


		public Writer getWriter() {
			return writer;
		}


		public String getTitle() {
			return title;
		}


		public Date getRegdate() {
			return regdate;
		}


		public Date getModifiedDate() {
			return modifiedDate;
		}


		public int getReadCount() {
			return readCount;
		}


		@Override //왜하는지 모름
		public String toString() {
			return "polArticle [number=" + number + ", writer=" + writer + ", title=" + title + ", regdate=" + regdate
					+ ", modifiedDate=" + modifiedDate + ", readCount=" + readCount + "]";
		}
		
		
		
		
		
		
}
