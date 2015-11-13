package com.gemptc.beans;

import java.io.Serializable;

public class MyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String u_username;
    private String u_sex;
    private String u_age;
    private String u_image;
    private double distance;//两个用户之间的距离
	public String getU_username() {
		return u_username;
	}
	public void setU_username(String u_username) {
		this.u_username = u_username;
	}
	public String getU_sex() {
		return u_sex;
	}
	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}
	public String getU_age() {
		return u_age;
	}
	public void setU_age(String u_age) {
		this.u_age = u_age;
	}
	public String getU_image() {
		return u_image;
	}
	public void setU_image(String u_image) {
		this.u_image = u_image;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public MyBean(String u_username, String u_sex, String u_age,
			String u_image, double distance) {
		super();
		this.u_username = u_username;
		this.u_sex = u_sex;
		this.u_age = u_age;
		this.u_image = u_image;
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "MyBean [u_username=" + u_username + ", u_sex=" + u_sex
				+ ", u_age=" + u_age + ", u_image=" + u_image + ", distance="
				+ distance + "]";
	}
	
}
