package com.gemptc.beans;

import java.io.Serializable;

import android.R.integer;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	int u_id;
	String u_username;
	String u_password;
	String u_sex;
	int u_age;	
	String u_image;
	private double lon;// 经度
	private double lan;// 纬度
	private double distance;//两个用户之间的距离
	public Person(int u_id, String u_username, String u_password, String u_sex,
			int u_age, String u_image, double lon, double lan, double distance) {
		super();
		this.u_id = u_id;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_sex = u_sex;
		this.u_age = u_age;
		this.u_image = u_image;
		this.lon = lon;
		this.lan = lan;
		this.distance = distance;
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
	public String getU_image() {
		return u_image;
	}
	public void setU_image(String u_image) {
		this.u_image = u_image;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLan() {
		return lan;
	}
	public void setLan(double lan) {
		this.lan = lan;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Person(String u_username, String u_password, String u_sex,
			int u_age, String u_image, double lon, double lan, double distance) {
		super();
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_sex = u_sex;
		this.u_age = u_age;
		this.u_image = u_image;
		this.lon = lon;
		this.lan = lan;
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "Person [u_id=" + u_id + ", u_username=" + u_username
				+ ", u_password=" + u_password + ", u_sex=" + u_sex
				+ ", u_age=" + u_age + ", u_image=" + u_image + ", lon=" + lon
				+ ", lan=" + lan + ", distance=" + distance + "]";
	}
	
	
}
