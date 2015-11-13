package com.gemptc.beans;

import java.io.Serializable;

public class CollectBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int co_id;
	private int u_id;
	private int q_id;
	private int rs_id;
	private int p_id;

	public CollectBean(int p_id) {
		super();
		this.p_id = p_id;
	}

	public int getCo_id() {
		return co_id;
	}

	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getQ_id() {
		return q_id;
	}

	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public int getRs_id() {
		return rs_id;
	}

	public void setRs_id(int rs_id) {
		this.rs_id = rs_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public CollectBean(int co_id, int u_id, int p_id) {
		super();
		this.co_id = co_id;
		this.u_id = u_id;
		this.p_id = p_id;
	}

	@Override
	public String toString() {
		return "CollectBean [co_id=" + co_id + ", u_id=" + u_id + ", p_id="
				+ p_id + "]";
	}

}
