package GUI_Frame.test.bean;

public class SexBean {
	private String sex;

	public SexBean(String sex) {
		super();
		this.sex = sex;
	}

	public SexBean() {
		super();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String toString(){
		return this.sex;
	}
}
