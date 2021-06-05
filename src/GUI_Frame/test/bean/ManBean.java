package GUI_Frame.test.bean;

import java.sql.Date;

public class ManBean {
	private int id;
	private String userName;
	private String sex; 
	private Date birthday;
	private Bike bike;
	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public ManBean(){}
	
	public ManBean(int id, String userName, String sex, Date birthday,String bikeType) {
		super();
		this.id = id;
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.bike = new Bike(bikeType);
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String toString(){
		return this.userName;
	}
}
