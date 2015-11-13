package beanutil;

import java.io.Serializable;

public class Publish implements Serializable {
     private int p_id;
     private String p_title;
     private String p_content;
     private String p_date;
     private String p_picture;
     private String U_name;
     
     
	public Publish(int p_id, String p_title, String p_content,
			String p_date, String p_picture, String u_name) {
		super();
		this.p_id = p_id;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_date = p_date;
		this.p_picture = p_picture;
		U_name = u_name;
	}

	public String getU_name() {
		return U_name;
	}

	public void setU_name(String u_name) {
		U_name = u_name;
	}

	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public String getP_picture() {
		return p_picture;
	}
	public void setP_picture(String p_picture) {
		this.p_picture = p_picture;
	}
	
	
	@Override
	public String toString() {
		return "Publish [p_id=" + p_id + ",  p_title="
				+ p_title + ", p_content=" + p_content + ", p_date=" + p_date
				+ ", p_picture=" + p_picture + ", ]";
	}
	
     
}
