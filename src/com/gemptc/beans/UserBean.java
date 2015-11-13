package com.gemptc.beans;

public class UserBean {
  int u_id;
  String u_username;
  String u_password;
  String u_sex;
  int u_age;
  String u_class;
  String u_school;
  String u_tel;
  String u_signator;
  String u_image;
@Override
public String toString() {
	return "UserBean [u_id=" + u_id + ", u_username=" + u_username
			+ ", u_password=" + u_password + ", u_sex=" + u_sex + ", u_age="
			+ u_age + ", u_class=" + u_class + ", u_school=" + u_school
			+ ", u_tel=" + u_tel + ", u_signator=" + u_signator + ", u_image="
			+ u_image + "]";
}

public UserBean() {
	super();
}

public UserBean(int u_id, String u_username, String u_password, String u_sex,
		int u_age, String u_class, String u_school, String u_tel,
		String u_signator, String u_image) {
	super();
	this.u_id = u_id;
	this.u_username = u_username;
	this.u_password = u_password;
	this.u_sex = u_sex;
	this.u_age = u_age;
	this.u_class = u_class;
	this.u_school = u_school;
	this.u_tel = u_tel;
	this.u_signator = u_signator;
	this.u_image = u_image;
}

public int getU_id() {
	return u_id;
}
public void setU_id(int u_id) {
	this.u_id = u_id;
}
public String getU_username() {
	return u_username;
}
public void setU_username(String u_username) {
	this.u_username = u_username;
}
public String getU_password() {
	return u_password;
}
public void setU_password(String u_password) {
	this.u_password = u_password;
}
public String getU_sex() {
	return u_sex;
}
public void setU_sex(String u_sex) {
	this.u_sex = u_sex;
}
public int getU_age() {
	return u_age;
}
public void setU_age(int u_age) {
	this.u_age = u_age;
}
public String getU_class() {
	return u_class;
}
public void setU_class(String u_class) {
	this.u_class = u_class;
}
public String getU_school() {
	return u_school;
}
public void setU_school(String u_school) {
	this.u_school = u_school;
}
public String getU_tel() {
	return u_tel;
}
public void setU_tel(String u_tel) {
	this.u_tel = u_tel;
}
public String getU_signator() {
	return u_signator;
}
public void setU_signator(String u_signator) {
	this.u_signator = u_signator;
}
public String getU_image() {
	return u_image;
}
public void setU_image(String u_image) {
	this.u_image = u_image;
}
}
