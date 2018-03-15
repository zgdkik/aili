package org.hbhk.aili.webflow.share.model;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BaseModel;

@Table("t_flow_product")
public class Product  extends BaseModel {

	private static final long serialVersionUID = -2027021562412577102L;
	@Column("description")
	private String description;
	@Column("name")
	private String name;
	@Column("price")
	private int price;

	public Product() {
	}

	public Product(int id, String description, int price) {
		this.description = description;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
