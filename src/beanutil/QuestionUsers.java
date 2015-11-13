package beanutil;

public class QuestionUsers {
private int Q_id;
private String Q_username;
private String Q_content;
private String Q_picture;
private String Q_date;
public QuestionUsers() {
	super();
}
public QuestionUsers(int q_id, String q_username, String q_content,
		String q_picture, String q_date) {
	super();
	Q_id = q_id;
	Q_username = q_username;
	Q_content = q_content;
	Q_picture = q_picture;
	Q_date = q_date;
}
public int getQ_id() {
	return Q_id;
}
public void setQ_id(int q_id) {
	Q_id = q_id;
}
public String getQ_username() {
	return Q_username;
}
public void setQ_username(String q_username) {
	Q_username = q_username;
}
public String getQ_content() {
	return Q_content;
}
public void setQ_content(String q_content) {
	Q_content = q_content;
}
public String getQ_picture() {
	return Q_picture;
}
public void setQ_picture(String q_picture) {
	Q_picture = q_picture;
}
public String getQ_date() {
	return Q_date;
}
public void setQ_date(String q_date) {
	Q_date = q_date;
}

}
