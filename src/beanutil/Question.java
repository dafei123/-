package beanutil;

public class Question {
	private int Q_id;	//问题编号
	
	private String Q_content;  //问题内容
	private String Q_date;	//问题时间
	private int U_id;	//发布者id（用户）
	private String Q_picture;  //问题照片
	private String Q_sort;	//分类
	private String U_username;//级联查询用户昵称
	public Question() {
	}

	public Question(int q_id, String q_content, String q_date,int u_id,
			String q_picture, String q_sort,String u_username) {
		super();
		Q_id = q_id;
		U_id = u_id;
		Q_content = q_content;
		Q_date = q_date;
		Q_picture = q_picture;
		Q_sort = q_sort;
		U_username=u_username;
	}
	
	
	
	public Question(String q_content, String q_date, int u_id,
			String q_picture, String u_username) {
		super();
		Q_content = q_content;
		Q_date = q_date;
		U_id = u_id;
		Q_picture = q_picture;
		U_username = u_username;
	}

	public String getU_username() {
		return U_username;
	}

	public void setU_username(String u_username) {
		U_username = u_username;
	}
	
	public int getQ_id() {
		return Q_id;
	}

	public void setQ_id(int q_id) {
		Q_id = q_id;
	}

	public int getU_id() {
		return U_id;
	}

	public void setU_id(int u_id) {
		U_id = u_id;
	}

	public String getQ_content() {
		return Q_content;
	}

	public void setQ_content(String q_content) {
		Q_content = q_content;
	}

	public String getQ_date() {
		return Q_date;
	}

	public void setQ_date(String q_date) {
		Q_date = q_date;
	}

	public String getQ_picture() {
		return Q_picture;
	}

	public void setQ_picture(String q_picture) {
		Q_picture = q_picture;
	}

	public String getQ_sort() {
		return Q_sort;
	}

	public void setQ_sort(String q_sort) {
		Q_sort = q_sort;
	}
	
}
