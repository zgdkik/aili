package org.hbhk.aili.client.main.client.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;


/**
 * 
 * 德邦GUI Border样式
 */
public class JDTitledBorder extends CompoundBorder{
	

	private static final long serialVersionUID = 2353183664101905050L;

	/**
	 * 不带文字的边框样式
	 */
	public JDTitledBorder() {
	    	//执行构造函数
		this("");
	}
	
	/**
	 * 
	 * 带文字，默认灰色边框显示
	 */
	public JDTitledBorder(String title) {
	    	//执行构造函数
		this(title, Color.GRAY);
	}
	
	/**
	 * 
	 * 带文字，自定义边框显示颜色
	 */
	public JDTitledBorder(String title,Color lineColor){
	    	/**
	    	 * 调用父类的构造函数
	    	 */
		super(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title),
				BorderFactory.createEmptyBorder(0, 0, 4, 0));
	}
	

}