package bean;

public class Wo_Userbean {
	private int u_id;
	private String username;
	private String password;
	private String u_sex;
	private int age;
	private String u_class;
	private String school, tel, signator, u_image;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getU_sex() {
		return u_sex;
	}
	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getU_class() {
		return u_class;
	}
	public void setU_class(String u_class) {
		this.u_class = u_class;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSignator() {
		return signator;
	}
	public void setSignator(String signator) {
		this.signator = signator;
	}
	public String getU_image() {
		return u_image;
	}
	public void setU_image(String u_image) {
		this.u_image = u_image;
	}
	@Override
	public String toString() {
		return "Wo_Userbean [u_id=" + u_id + ", username=" + username
				+ ", password=" + password + ", u_sex=" + u_sex + ", age="
				+ age + ", u_class=" + u_class + ", school=" + school
				+ ", tel=" + tel + ", signator=" + signator + ", u_image="
				+ u_image + "]";
	}
	
}
