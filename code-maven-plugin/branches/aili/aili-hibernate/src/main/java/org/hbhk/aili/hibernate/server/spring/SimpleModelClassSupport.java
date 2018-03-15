package org.hbhk.aili.hibernate.server.spring;



public class SimpleModelClassSupport implements ModelClassSupport {

	private Class<?> modelClass;
	
	public Class<?> getModelClass() {
		return modelClass;
	}

	public void setModelClass(Class<?> modelClass){
		this.modelClass = modelClass;
	}
}
