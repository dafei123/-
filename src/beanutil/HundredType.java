package beanutil;

import android.R.integer;

public class HundredType {
	private int Ht_id;//百科编号
	private String Ht_title;
	private String ht_content;
	public HundredType() {
	}
	public HundredType(int ht_id, String ht_title, String ht_content) {
		super();
		Ht_id = ht_id;
		Ht_title = ht_title;
		this.ht_content = ht_content;
	}
	public int getHt_id() {
		return Ht_id;
	}
	public void setHt_id(int ht_id) {
		Ht_id = ht_id;
	}
	public String getHt_title() {
		return Ht_title;
	}
	public void setHt_title(String ht_title) {
		Ht_title = ht_title;
	}
	public String getHt_content() {
		return ht_content;
	}
	public void setHt_content(String ht_content) {
		this.ht_content = ht_content;
	}
	@Override
	public String toString() {
		return "HundredType [Ht_id=" + Ht_id + ", Ht_title=" + Ht_title
				+ ", ht_content=" + ht_content + "]";
	}
	
	
}
