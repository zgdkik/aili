package org.hbhk.aili.mybatis.share;

import java.util.ArrayList;
import java.util.List;

public class CriteriaBuilder {

	private List<Criterion> criterions = new ArrayList<Criterion>();
	
	
	public  static  final  String  and = " and ";
	public  static  final  String  or = " or ";
	
	
	public  static  final  String  equal = " = ";
	public  static  final  String  like = " like ";
	public  static  final  String  greater = " > ";
	public  static  final  String  less = " < ";
	public  static  final  String  greater_equal = " >= ";
	public  static  final  String  less_equal = " <= ";
	
	public CriteriaBuilder addAndCriterion(String condition, Object value,Object secondValue,String property,String connector,boolean listValue) {
		if (value == null) {
			throw new RuntimeException("Value for " + property+ " cannot be null");
		}
		criterions.add(new Criterion(condition, value, secondValue, property, connector, listValue));
		return this;
	}

	public CriteriaBuilder addAndCriterion(String condition, Object value,String property) {
		addAndCriterion(condition, value,null ,property,CriteriaBuilder.equal,false);
		return this;
	}
	
	public CriteriaBuilder addAndScopeCriterion(String condition, Object value, Object secondValue,String property,String... connector) {
		addAndCriterion(condition, value,secondValue ,property,CriteriaBuilder.equal,false);
		return this;
	}
	
	public CriteriaBuilder addOrCriterion(String condition, Object value,String property) {
		addAndCriterion(condition, value,null ,property,CriteriaBuilder.or,false);
		return this;
	}
	
	public CriteriaBuilder addOrScopeCriterion(String condition, Object value, Object secondValue,String property,String... connector) {
		addAndCriterion(condition, value,secondValue ,property,CriteriaBuilder.or,false);
		return this;
	}
}
