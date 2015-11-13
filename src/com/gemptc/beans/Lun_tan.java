package com.gemptc.beans;

import java.io.Serializable;

public class Lun_tan implements Serializable {

	private static final long serialVersionUID = 1L;
	private int lun_tanid;// 论坛id
	private int userid;// 用户id
	private String title;// 标题
	private String content;// 内容
	private int replyid;// 回复id。如果是帖子，则为0
	private int rootid;// 所属的帖子id
	private String date;// 发表时间
	private int have_replay;// 是否有回复，0表示没有，1表示有
	private String username;// 发表用户
	private String picture;// 用户头像

	public Lun_tan() {
	}

	public Lun_tan(int lun_tanid, int userid, String title, String content,
			int replyid, int rootid, String date, int have_replay,
			String username, String picture) {
		super();
		this.lun_tanid = lun_tanid;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.replyid = replyid;
		this.rootid = rootid;
		this.date = date;
		this.have_replay = have_replay;
		this.username = username;
		this.picture = picture;
	}

	public int getLun_tanid() {
		return lun_tanid;
	}

	public void setLun_tanid(int lun_tanid) {
		this.lun_tanid = lun_tanid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReplyid() {
		return replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

	public int getRootid() {
		return rootid;
	}

	public void setRootid(int rootid) {
		this.rootid = rootid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHave_replay() {
		return have_replay;
	}

	public void setHave_replay(int have_replay) {
		this.have_replay = have_replay;
	}

	public String getUsername() {
		return username;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Lun_tan [lun_tanid=" + lun_tanid + ", userid=" + userid
				+ ", title=" + title + ", content=" + content + ", replyid="
				+ replyid + ", rootid=" + rootid + ", date=" + date
				+ ", have_replay=" + have_replay + ", username=" + username
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lun_tanid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lun_tan other = (Lun_tan) obj;
		if (lun_tanid != other.lun_tanid)
			return false;
		return true;
	}

}
