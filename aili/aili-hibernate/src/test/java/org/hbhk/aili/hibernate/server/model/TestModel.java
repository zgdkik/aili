package org.hbhk.aili.hibernate.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hbhk.aili.hibernate.share.model.BaseModel;
@Table(name="test_model")
@Entity
public class TestModel extends BaseModel {

	private static final long serialVersionUID = 1080378129495071507L;
	
	@Id
    @Column(name="id")
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
