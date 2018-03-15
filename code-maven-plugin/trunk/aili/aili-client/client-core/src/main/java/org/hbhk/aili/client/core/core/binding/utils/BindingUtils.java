package org.hbhk.aili.client.core.core.binding.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.hbhk.aili.client.core.core.binding.BindingAssociation;
import org.hbhk.aili.client.core.core.binding.IBindingArgWrapper;
import org.hbhk.aili.client.core.core.binding.RadioButtonBindingArg;

/**
 * 
 *   数据绑定的工具类。
 */
public final class BindingUtils {

	private BindingUtils(){
		
	}
	
	
	/**
	 * 创建组件的绑定关联列表
	 * @param components
	 * @param args
	 * @return List
	 */
	public static List<BindingAssociation> createAssociations(JComponent[] components, Object[] args) {
		if (components.length != args.length){
			throw new IllegalArgumentException("Size of components and size of arguments must be match");
		}
			
		List<BindingAssociation> associations = new ArrayList<BindingAssociation>();
		for (int i = 0; i < components.length; i++) {
			IBindingArgWrapper argWrapper = createBindingArgWrapper(args[i]);
			
			associations.add(new BindingAssociation(components[i], argWrapper));
		}
	
		return associations;
	}

	/**
	 * 创建一个组件的关联关系
	 * @param components
	 * @param args
	 * @return IBindingArgWrapper
	 */
	public static IBindingArgWrapper createBindingArgWrapper(Object obj) {
		IBindingArgWrapper argWrapper = null;
		if (obj instanceof String) {
			argWrapper = new StringBindingArgWrapper((String)obj);
		} else if (obj instanceof RadioButtonBindingArg) {
			argWrapper = new RadioButtonBindingArgWrapper((RadioButtonBindingArg)obj);
		} else {
			throw new IllegalArgumentException(String.format("Unspported binding argument type %s",
					obj.getClass().getName()));
		}
		return argWrapper;
	}
	
	private static class StringBindingArgWrapper implements IBindingArgWrapper {
		private String propertyName;
		
		public StringBindingArgWrapper(String propertyName) {
			this.propertyName = propertyName;
		}
		
		@Override
		public String getPropertyName() {
			return propertyName;
		}

		@Override
		public Object getArg() {
			return propertyName;
		}
		
	}
	
	private static class RadioButtonBindingArgWrapper implements IBindingArgWrapper {
		private RadioButtonBindingArg arg;
		
		public RadioButtonBindingArgWrapper(RadioButtonBindingArg arg) {
			this.arg = arg;
		}
		
		@Override
		public String getPropertyName() {
			return arg.getPropertyName();
		}

		@Override
		public Object getArg() {
			return arg;
		}
		
	}

}
