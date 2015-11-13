package bean;

import java.io.Serializable;

public class Rankingbean implements Serializable{
  private String username;
  private String userimage;
  private String signator;
public Rankingbean(String username, String userimage, String signator) {
	super();
	this.username = username;
	this.userimage = userimage;
	this.signator = signator;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserimage() {
	return userimage;
}
public void setUserimage(String userimage) {
	this.userimage = userimage;
}
public String getSignator() {
	return signator;
}
public void setSignator(String signator) {
	this.signator = signator;
}
@Override
public String toString() {
	return "Rankingbean [username=" + username + ", userimage=" + userimage
			+ ", signator=" + signator + "]";
}
  
}
