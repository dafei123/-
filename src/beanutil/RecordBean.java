package beanutil;

public class RecordBean {
	private int R_id;
	private int R_sumtime;
	private int U_id;
	private String R_date;	
	@Override
	public String toString() {
		return "RecordBean [R_id=" + R_id + ", R_sumtime=" + R_sumtime
				+ ", U_id=" + U_id + ", R_date=" + R_date + "]";
	}	
	public RecordBean(int r_id, int r_sumtime, int u_id, String r_date) {
		super();
		R_id = r_id;
		R_sumtime = r_sumtime;
		U_id = u_id;
		R_date = r_date;
	}
	public RecordBean(int r_sumtime, int u_id, String r_date) {
		super();
		R_sumtime = r_sumtime;
		U_id = u_id;
		R_date = r_date;
	}
	public int getR_id() {
		return R_id;
	}
	public void setR_id(int r_id) {
		R_id = r_id;
	}
	public int getR_sumtime() {
		return R_sumtime;
	}
	public void setR_sumtime(int r_sumtime) {
		R_sumtime = r_sumtime;
	}
	public int getU_id() {
		return U_id;
	}
	public void setU_id(int u_id) {
		U_id = u_id;
	}
	public String getR_date() {
		return R_date;
	}
	public void setR_date(String r_date) {
		R_date = r_date;
	}
	
}
