package com.weidian.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
    private String orderid;

    private Long payment;

    private Integer statu;

    private Date createTime;

    private String userid;
    
    private List<Item> itemlist=new ArrayList<Item>();
    
    public List<Item> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}

	public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}