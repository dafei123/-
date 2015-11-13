package com.gemptc.beans;

import java.io.Serializable;



public class ResponseUserBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 论坛回复表编号
	 */
	private String rs_id;
	private String u_id;//用户编号
	private String be_id;//回复者编号（用户编号）
	private String p_id;//论坛发布编号
	/**
	 * 论坛回复时间
	 */
	private String rs_date;
	private String rs_content;//论坛回复内容
	private String rs_title;//回复标题
	private String u_username;//用户名
	private String u_image;//用户头像
	private String rs_picture;//回复的图片（图片均为一张）
	private String rs_storey;//回复的楼层
	public ResponseUserBean(String p_id, String rs_content) {
		super();
		this.p_id = p_id;
		this.rs_content = rs_content;
	}
	public ResponseUserBean(String rs_date, String rs_content,
			String u_username, String u_image, String rs_picture,
			String rs_storey) {
		super();
		this.rs_date = rs_date;
		this.rs_content = rs_content;
		this.u_username = u_username;
		this.u_image = u_image;
		this.rs_picture = rs_picture;
		this.rs_storey = rs_storey;
	}
	public String getU_username() {
		return u_username;
	}
	public void setU_username(String u_username) {
		this.u_username = u_username;
	}
	public String getU_image() {
		return u_image;
	}
	public void setU_image(String u_image) {
		this.u_image = u_image;
	}	
	public ResponseUserBean(String rs_id, String u_id, String be_id,
			String p_id, String rs_date, String rs_content, String rs_title,
			String u_username, String u_image, String rs_picture,
			String rs_storey) {
		super();
		this.rs_id = rs_id;
		this.u_id = u_id;
		this.be_id = be_id;
		this.p_id = p_id;
		this.rs_date = rs_date;
		this.rs_content = rs_content;
		this.rs_title = rs_title;
		this.u_username = u_username;
		this.u_image = u_image;
		this.rs_picture = rs_picture;
		this.rs_storey = rs_storey;
	}
	public String getRs_storey() {
		return rs_storey;
	}
	public void setRs_storey(String rs_storey) {
		this.rs_storey = rs_storey;
	}
	public ResponseUserBean(String rs_date, String rs_content,
			String u_username, String u_image, String rs_picture) {
		super();
		this.rs_date = rs_date;
		this.rs_content = rs_content;
		this.u_username = u_username;
		this.u_image = u_image;
		this.rs_picture = rs_picture;
	}
	public ResponseUserBean(String rs_id, String u_id, String be_id,
			String p_id, String rs_date, String rs_content, String rs_title,
			String u_username, String u_image, String rs_picture) {
		super();
		this.rs_id = rs_id;
		this.u_id = u_id;
		this.be_id = be_id;
		this.p_id = p_id;
		this.rs_date = rs_date;
		this.rs_content = rs_content;
		this.rs_title = rs_title;
		this.u_username = u_username;
		this.u_image = u_image;
		this.rs_picture = rs_picture;
	}
	
	@Override
	public String toString() {
		return "ResponseUserBean [rs_id=" + rs_id + ", u_id=" + u_id
				+ ", be_id=" + be_id + ", p_id=" + p_id + ", rs_date="
				+ rs_date + ", rs_content=" + rs_content + ", rs_title="
				+ rs_title + ", rs_picture=" + rs_picture + "]";
	}
	public ResponseUserBean() {
		super();
	}

	public ResponseUserBean(String rs_id, String u_id, String be_id,
			String p_id, String rs_date, String rs_content, String rs_title,
			String rs_picture) {
		super();
		this.rs_id = rs_id;
		this.u_id = u_id;
		this.be_id = be_id;
		this.p_id = p_id;
		this.rs_date = rs_date;
		this.rs_content = rs_content;
		this.rs_title = rs_title;
		this.rs_picture = rs_picture;
	}
	public String getRs_id() {
		return rs_id;
	}
	public void setRs_id(String rs_id) {
		this.rs_id = rs_id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getBe_id() {
		return be_id;
	}
	public void setBe_id(String be_id) {
		this.be_id = be_id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getRs_date() {
		return rs_date;
	}
	public void setRs_date(String rs_date) {
		this.rs_date = rs_date;
	}
	public String getRs_content() {
		return rs_content;
	}
	public void setRs_content(String rs_content) {
		this.rs_content = rs_content;
	}
	public String getRs_title() {
		return rs_title;
	}
	public void setRs_title(String rs_title) {
		this.rs_title = rs_title;
	}
	public String getRs_picture() {
		return rs_picture;
	}
	public void setRs_picture(String rs_picture) {
		this.rs_picture = rs_picture;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
