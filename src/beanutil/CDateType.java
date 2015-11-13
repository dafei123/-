package beanutil;

import java.io.Serializable;

public class CDateType implements Serializable {
	private int Cd_id;	//子资料编号
	private int Dt_id;	//资料百科编号
	private String Cd_title; //子资料标题
	private String Cd_context; //子资料内容
	
	public CDateType() {
		
	}
	public CDateType(int cd_id, String cd_title, String cd_context) {
		super();
		Cd_id = cd_id;
		Cd_title = cd_title;
		Cd_context = cd_context;
	}

	public CDateType(int cd_id, int dt_id, String cd_title, String cd_context) {
		super();
		Cd_id = cd_id;
		Dt_id = dt_id;
		Cd_title = cd_title;
		Cd_context = cd_context;
	}
	public CDateType(String cd_title){
		super();
		Cd_title=cd_title;
	}

	public int getCd_id() {
		return Cd_id;
	}

	public void setCd_id(int cd_id) {
		Cd_id = cd_id;
	}

	public int getDt_id() {
		return Dt_id;
	}

	public void setDt_id(int dt_id) {
		Dt_id = dt_id;
	}

	public String getCd_title() {
		return Cd_title;
	}

	public void setCd_title(String cd_title) {
		Cd_title = cd_title;
	}

	public String getCd_context() {
		return Cd_context;
	}

	public void setCd_context(String cd_context) {
		Cd_context = cd_context;
	}

	@Override
	public String toString() {
		return "CDateType [Cd_id=" + Cd_id + ", Cd_context=" + Cd_context + "]";
	}
	
	
}
