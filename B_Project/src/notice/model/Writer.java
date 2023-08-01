package notice.model;

//(여기에서는)article테이블의 writer_id의 컬럼값을 저장 및 제공 등의 기능을 한다.
//작성자 정보를 저장 및 제공 등의 기능을 가진 클래스이다.
public class Writer {
	//필드
	private String id; //writer_id 작성자 id

	//생성자
	public Writer(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Writer [id=" + id + "]";
	}
	
	
}
