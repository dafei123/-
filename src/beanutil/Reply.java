package beanutil;

public class Reply {
	private int Re_id;	//回答编号
	private int U_id;	//用户编号
	private int Q_id;	//问题编号
	private String Re_content;   //回复内容
	private String Re_date;	//回复时间
	private String Re_picture;   //回复图片
	private String U_username;//级联查询用户昵称
	public Reply() {
	}
	

	public Reply(int u_id, int q_id, String re_content, String re_date) {
		super();
		U_id = u_id;
		Q_id = q_id;
		Re_content = re_content;
		Re_date = re_date;
	}
	

	public Reply(int re_id, String re_content, String re_date,
			String re_picture, String u_username) {
		super();
		Re_id = re_id;
		Re_content = re_content;
		Re_date = re_date;
		Re_picture = re_picture;
		U_username = u_username;
	}


	public Reply(String re_content, String re_date) {
		super();
		Re_content = re_content;
		Re_date = re_date;
	}


	public Reply(int u_id, int q_id, String re_content,
			String re_date, String re_picture,String u_username) {
		super();
		
		U_id = u_id;
		Q_id = q_id;
		Re_content = re_content;
		Re_date = re_date;
		Re_picture = re_picture;
		U_username=u_username;
	}
	public Reply(int re_id, String re_content,
			String re_date, String u_username) {
		super();		
		Re_id = re_id;
		Re_content = re_content;
		Re_date = re_date;
		U_username=u_username;
	}
	

	public String getU_username() {
		return U_username;
	}

	public void setU_username(String u_username) {
		U_username = u_username;
	}

	public int getRe_id() {
		return Re_id;
	}

	public void setRe_id(int re_id) {
		Re_id = re_id;
	}

	public int getU_id() {
		return U_id;
	}

	public void setU_id(int u_id) {
		U_id = u_id;
	}

	public int getQ_id() {
		return Q_id;
	}

	public void setQ_id(int q_id) {
		Q_id = q_id;
	}

	public String getRe_content() {
		return Re_content;
	}

	public void setRe_content(String re_content) {
		Re_content = re_content;
	}

	public String getRe_date() {
		return Re_date;
	}

	public void setRe_date(String re_date) {
		Re_date = re_date;
	}

	public String getRe_picture() {
		return Re_picture;
	}

	public void setRe_picture(String re_picture) {
		Re_picture = re_picture;
	}

	@Override
	public String toString() {
		return "Reply [Re_id=" + Re_id + ", U_id=" + U_id + ", Q_id=" + Q_id
				+ ", Re_content=" + Re_content + ", Re_date=" + Re_date
				+ ", Re_picture=" + Re_picture + "]";
	}	
}
