package beanutil;

public class StudyBean {
	private int S_id;
	private int U_id;
	private String S_time;
	private String S_date;	
	@Override
	public String toString() {
		return "StudyBean [S_id=" + S_id + ", U_id=" + U_id + ",S_time=" + S_time + ", S_date=" + S_date + "]";
	}
	public StudyBean(int s_id, int u_id, String s_time, String s_date) {
		super();
		S_id = s_id;
		U_id = u_id;
		S_time = s_time;
		S_date = s_date;
	}
	public int getS_id() {
		return S_id;
	}
	public void setS_id(int s_id) {
		S_id = s_id;
	}
	public int getU_id() {
		return U_id;
	}
	public void setU_id(int u_id) {
		U_id = u_id;
	}
	public String getS_time() {
		return S_time;
	}
	public void setS_time(String s_time) {
		S_time = s_time;
	}
	public String getS_date() {
		return S_date;
	}
	public void setS_date(String s_date) {
		S_date = s_date;
	}
	
}
