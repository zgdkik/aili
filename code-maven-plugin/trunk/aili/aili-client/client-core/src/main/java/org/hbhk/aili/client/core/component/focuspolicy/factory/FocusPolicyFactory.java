package org.hbhk.aili.client.core.component.focuspolicy.factory;

import java.awt.Component;
import java.awt.Container;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.component.buttonaction.factory.ButtonActionFactory;
import org.hbhk.aili.client.core.component.focuspolicy.FocusTraversalPolicy;
import org.hbhk.aili.client.core.component.focuspolicy.IFocusPolicyHandle;
import org.hbhk.aili.client.core.component.focuspolicy.TabFocusTraversPolicy;
import org.hbhk.aili.client.core.component.focuspolicy.annotation.ContainerSeq;
import org.hbhk.aili.client.core.component.focuspolicy.annotation.FocusSeq;
import org.hbhk.aili.client.core.component.focuspolicy.handle.ButtonFocusHandle;

/**
 * 
 * @内容：setFocusTraversalPolicy
 */
public class FocusPolicyFactory {
	private static final Log log = LogFactory.getLog(ButtonActionFactory.class);

	private static FocusPolicyFactory instance;

	private static List<IFocusPolicyHandle> handles = new ArrayList<IFocusPolicyHandle>();

	/**
	 * 静态方法获取按钮行为工厂实例，这里运用双重检查机制。 保证了多线程访问的时候对象创建的唯一性。 getIntsance
	 * 
	 * @return ButtonActionFactory
	 * @since:0.6
	 */
	public static FocusPolicyFactory getIntsance() {
		if (instance == null) {
			synchronized (log) {
				if (instance == null) {
					instance = new FocusPolicyFactory();

					registerHandle(instance);
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * @内容：注册处理器
	 * @返回类型：void
	 */
	private static void registerHandle(FocusPolicyFactory instance) {

//		instance.registerHandle(new CheckBoxFocusHandle());
		instance.registerHandle(new ButtonFocusHandle());
	}

	/**
	 * 
	 * @内容：注册处理器
	 * @返回类型：void
	 */
	@SuppressWarnings("rawtypes")
	public void registerHandle(IFocusPolicyHandle handle) {

		handles.add(handle);
	}

	/**
	 * 移除处理器
	 */
	@SuppressWarnings("rawtypes")
	public void removeHandle(IFocusPolicyHandle handle) {
		handles.remove(handle);
	}

	/**
	 * 功能：setFocusTraversalPolicy
	 * 
	 * @param:Container
	 * @return void
	 * @since:1.6
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setFocusTraversalPolicy(Container root) {

		Map<String, Map> map = scanComponentField(root);

		LinkedList<Object> linkedList = sort(map);
		linkedList = sort(linkedList);

		Component[] components = new Component[linkedList.size()];

		Map<Component, Integer> seqMap = new HashMap<Component, Integer>();
		for (int i = 0; i < linkedList.size(); i++) {
			components[i] = (Component) linkedList.get(i);
			seqMap.put(components[i], i);
		}
		FocusTraversalPolicy.setFocusTraversalPolicy(components, handles,
				seqMap);
		root.setFocusCycleRoot(true);
		root.setFocusTraversalPolicy(new TabFocusTraversPolicy(components));
	}



	/**
	 * 
	 * 功能：scanComponentField对root 进行扫描，获取conpoment 的键值对
	 */
	@SuppressWarnings("rawtypes")
	Map<String, Map> scanComponentField(Component component) {
		Map<String, Map> componentAndFields = new HashMap<String, Map>();

		ContainerSeq containerSeq = null;
		for (Annotation annotation : component.getClass().getAnnotations()) {
			if (annotation instanceof ContainerSeq) {
				containerSeq = (ContainerSeq) annotation;
			}
		}

		if (containerSeq != null) {
			int cSeq = containerSeq.seq();
			Field[] fields = component.getClass().getDeclaredFields();
			Map map = pase(fields, component);
			componentAndFields.put(cSeq + "", map);
		}
		if (component instanceof Container) {
			for (Component componentChild : ((Container) component)
					.getComponents()) {
				componentAndFields.putAll(scanComponentField(componentChild));
			}
		}
		return componentAndFields;
	}

	/**
	 * 
	 * @内容：Map排序
	 */
	@SuppressWarnings("rawtypes")
	private LinkedList<Object> sort(Map<String, Map> map) {
		LinkedList<Object> linkedList = new LinkedList<Object>();

		for (int i = 0; i < map.keySet().size(); i++) {
			int seq = i + 1;
			Object c = map.get(seq + "");
			if (c != null) {
				linkedList.add(c);
			}

		}
		return linkedList;
	}

	/**
	 * @内容：LinkedList排序
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private LinkedList<Object> sort(LinkedList<Object> linkedList) {

		LinkedList<Object> ll = new LinkedList<Object>();
		for (int i = 0; i < linkedList.size(); i++) {

			Map<String, Map> map = (Map<String, Map>) linkedList.get(i);
			if (map != null) {
				ll.addAll(sort(map));
			}

		}
		return ll;
	}

	/**
	 * @内容：解析Field
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, Component> pase(Field[] fields, Component component) {
		Map<String, Component> map = new HashMap<String, Component>();

		for (Field field : fields) {

			if (Component.class.isAssignableFrom(field.getType())) {

				FocusSeq focusSeq = field.getAnnotation(FocusSeq.class);
				if (focusSeq == null) {
					continue;
				}

				int fSeq = focusSeq.seq();

				Component componentField = null;
				boolean oldAccessible = field.isAccessible();
				field.setAccessible(true);
				try {
					componentField = (Component) field.get(component);

					map.put(fSeq + "", componentField);
				} catch (Exception e) {
					if (log.isWarnEnabled()) {
						log.warn("Container scanner can't get field value.", e);
					}
				} finally {
					field.setAccessible(oldAccessible);
				}
			}
		}
		return map;
	}

}
