package beanutil;

public class ReplyUsers {
private int U_id;
private int Q_id;
private String Q_username;
private String Re_date;
private String Re_content;
private String Re_picture;
public ReplyUsers() {
	super();
}

public ReplyUsers(int u_id, int q_id, String q_username, String re_date,
		String re_content, String re_picture) {
	super();
	U_id = u_id;
	Q_id = q_id;
	Q_username = q_username;
	Re_date = re_date;
	Re_content = re_content;
	Re_picture = re_picture;
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
public String getRe_date() {
	return Re_date;
}
public void setRe_date(String re_date) {
	Re_date = re_date;
}
public String getRe_content() {
	return Re_content;
}
public void setRe_content(String re_content) {
	Re_content = re_content;
}
public String getRe_picture() {
	return Re_picture;
}
public void setRe_picture(String re_picture) {
	Re_picture = re_picture;
}

}
