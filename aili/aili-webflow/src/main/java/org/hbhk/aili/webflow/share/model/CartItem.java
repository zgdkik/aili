package org.hbhk.aili.webflow.share.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_flow_test")
public class CartItem implements Serializable {
	private static final long serialVersionUID = 8388627124326126637L;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment") 
	private Integer id;
	private Product product;
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "pid")
	private Integer pid;

	public CartItem() {
	}

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return this.quantity * this.product.getPrice();
	}

	public void increaseQuantity() {
		this.quantity++;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}