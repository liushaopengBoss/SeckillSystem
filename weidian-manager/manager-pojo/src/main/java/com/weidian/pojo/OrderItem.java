package com.weidian.pojo;

import java.io.Serializable;

public class OrderItem implements Serializable{
    private Long orderItemid;

    private String orderid;

    private Long itemid;

    private Integer quantity;

   
    public Long getOrderItemid() {
		return orderItemid;
	}

	public void setOrderItemid(Long orderItemid) {
		this.orderItemid = orderItemid;
	}

	public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}