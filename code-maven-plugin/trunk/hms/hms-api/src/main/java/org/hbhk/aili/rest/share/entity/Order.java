package org.hbhk.aili.rest.share.entity;

import java.io.Serializable;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_order")
public class Order implements Serializable {

	private static final long serialVersionUID = -1209783836196799605L;

	@Id
	@Column("order_id")
	private Long orderId;
	@Column("order_no")
	private String orderNo;
	@Column("create_time")
	private String createTime;
	@Column("user_id")
	private Long  userId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}
