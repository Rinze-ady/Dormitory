package GUI_Frame.test.bean;

public class SchoolBean {
	private int id;
	private String schoolName;
	public SchoolBean(int id, String schoolName) {
		super();
		this.id = id;
		this.schoolName = schoolName;
	}
	public SchoolBean() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String toString(){
		return this.schoolName;
	}
}
