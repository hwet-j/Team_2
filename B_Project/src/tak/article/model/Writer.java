package tak.article.model;


//p630 - (여기에서는 )article테이블의 writer_id, writer_name의 컬럼의 값을 저장 및 제공 등의 기능을 가진 클래스이다
//작성자 정보를 저장 및 제공 등의 기능을 가진 클래스이다
public class Writer {
	//필드
	private String  id;   //writer_id	작성자id
	private String  name; //writer_name 작성자명

	//생성자
	public Writer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Writer [id=" + id + ", name=" + name + "]";
	}
	
}
