package bean;

import java.io.Serializable;

public class Friendbean implements Serializable {
	private int friend_id;
	 private String friend_username;
	 private String friend_sex;
	 private int friend_age;
	 private String friend_class;
	 private String friend_school;
	 private String friend_signator;
	 private String friend_image;
	public Friendbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Friendbean(int friend_id, String friend_username, String friend_sex,
			int friend_age, String friend_class, String friend_school,
			String friend_signator, String friend_image) {
		super();
		this.friend_id = friend_id;
		this.friend_username = friend_username;
		this.friend_sex = friend_sex;
		this.friend_age = friend_age;
		this.friend_class = friend_class;
		this.friend_school = friend_school;
		this.friend_signator = friend_signator;
		this.friend_image = friend_image;
	}
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	public String getFriend_username() {
		return friend_username;
	}
	public void setFriend_username(String friend_username) {
		this.friend_username = friend_username;
	}
	public String getFriend_sex() {
		return friend_sex;
	}
	public void setFriend_sex(String friend_sex) {
		this.friend_sex = friend_sex;
	}
	public int getFriend_age() {
		return friend_age;
	}
	public void setFriend_age(int friend_age) {
		this.friend_age = friend_age;
	}
	public String getFriend_class() {
		return friend_class;
	}
	public void setFriend_class(String friend_class) {
		this.friend_class = friend_class;
	}
	public String getFriend_school() {
		return friend_school;
	}
	public void setFriend_school(String friend_school) {
		this.friend_school = friend_school;
	}
	public String getFriend_signator() {
		return friend_signator;
	}
	public void setFriend_signator(String friend_signator) {
		this.friend_signator = friend_signator;
	}
	public String getFriend_image() {
		return friend_image;
	}
	public void setFriend_image(String friend_image) {
		this.friend_image = friend_image;
	}
	@Override
	public String toString() {
		return "Friendbean [friend_id=" + friend_id + ", friend_username="
				+ friend_username + ", friend_sex=" + friend_sex + ", friend_age="
				+ friend_age + ", friend_class=" + friend_class
				+ ", friend_school=" + friend_school + ", friend_signator="
				+ friend_signator + ", friend_image=" + friend_image + "]";
	}
}
