package com.gemptc.beans;

import java.io.Serializable;

public class LunTanBean implements Serializable {	
	private String p_title;//论坛标题
	private String p_date;//发表论坛的日期
	private String u_image;//用户头像
	private String u_username;//用户姓名
	private String u_id;//用户id
	private String p_content;//论坛内容
	private String p_type;//论坛内容的类型
	
	public LunTanBean(String p_id, String p_title, String p_content,
			String p_date, String p_picture, String u_name) {//修改过
		super();
		this.p_id = p_id;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_date = p_date;
		this.p_picture = p_picture;
		this.u_username = u_username;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public LunTanBean(String p_title, String p_content, String p_picture) {
		super();
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_picture = p_picture;
	}
	private String p_picture;//论坛图片ID为INT类型
	private String p_id;//论坛编号
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public String getU_image() {
		return u_image;
	}
	public void setU_image(String u_image) {
		this.u_image = u_image;
	}
	public String getU_username() {
		return u_username;
	}
	public void setU_username(String u_username) {
		this.u_username = u_username;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getP_picture() {
		return p_picture;
	}
	public void setP_picture(String p_picture) {
		this.p_picture = p_picture;
	}

	public LunTanBean(String p_title, String p_date, String u_image,
			String u_username, String u_id, String p_content, String p_picture,
			String p_id) {
		super();
		this.p_title = p_title;
		this.p_date = p_date;
		this.u_image = u_image;
		this.u_username = u_username;
		this.u_id = u_id;
		this.p_content = p_content;
		this.p_picture = p_picture;
		this.p_id = p_id;
	}
	public LunTanBean() {
		super();
	}
	@Override
	public String toString() {
		return "LunTanBean [p_title=" + p_title + ", p_date=" + p_date
				+ ", u_image=" + u_image + ", u_username=" + u_username
				+ ", u_id=" + u_id + ", p_content=" + p_content + ", p_type="
				+ p_type + ", p_picture=" + p_picture + ", p_id=" + p_id + "]";
	}
	public LunTanBean(String p_title, String p_content, String p_type,
			String p_picture) {
		super();
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_type = p_type;
		this.p_picture = p_picture;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p_id == null) ? 0 : p_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LunTanBean other = (LunTanBean) obj;
		if (p_id == null) {
			if (other.p_id != null)
				return false;
		} else if (!p_id.equals(other.p_id))
			return false;
		return true;
	}

}