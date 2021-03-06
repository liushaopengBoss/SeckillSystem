package com.weidian.pojo;

import java.io.Serializable;

public class User implements Serializable{
    private String userid;

    private String username;

    private String pwd;

    private Byte isSeller;

    private Integer balance;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Byte getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Byte isSeller) {
        this.isSeller = isSeller;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", pwd="
				+ pwd + ", isSeller=" + isSeller + ", balance=" + balance + "]";
	}
    
}