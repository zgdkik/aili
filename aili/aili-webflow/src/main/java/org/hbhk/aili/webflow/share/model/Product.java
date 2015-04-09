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
@Table(name = "t_flow_product")
public class Product implements Serializable {

	private static final long serialVersionUID = -2027021562412577102L;
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment") 
	private int id;
	@Column(name = "description")
	private String description;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private int price;

	public Product() {
	}

	public Product(int id, String description, int price) {
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
