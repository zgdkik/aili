package org.hbhk.module.framework.shared.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Person implements  Serializable {
 
	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    
 //   List<EmpEntity>    ls;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//	public List<EmpEntity> getLs() {
//		return ls;
//	}
//	public void setLs(List<EmpEntity> ls) {
//		this.ls = ls;
//	}
//	

 
    
} 
