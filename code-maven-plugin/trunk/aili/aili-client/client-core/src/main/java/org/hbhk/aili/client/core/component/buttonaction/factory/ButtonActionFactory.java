package org.hbhk.aili.client.core.component.buttonaction.factory;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.util.ImageUtil;
import org.hbhk.aili.client.core.component.buttonaction.IButtonActionListener;
import org.hbhk.aili.client.core.component.buttonaction.annotation.ButtonAction;


/**
 * 
 * 
 * <b style="font-family:微软雅黑"><small>Description:按钮事件工厂</small></b> </br> <b
 */
public class ButtonActionFactory {

	private static final Log log = LogFactory.getLog(ButtonActionFactory.class);

	private static ButtonActionFactory instance;

	/**
	 * 静态方法获取按钮行为工厂实例，这里运用双重检查机制。 保证了多线程访问的时候对象创建的唯一性。 getIntsance
	 * 
	 * @return ButtonActionFactory
	 * @since:0.6
	 */
	public static ButtonActionFactory getIntsance() {
		if (instance == null) {
			synchronized (log) {
				if (instance == null) {
					instance = new ButtonActionFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * 功能：bindButtonAction 根据按钮行为类别绑定按钮事件
	 * 
	 * @param:Container
	 * @return void
	 * @since:1.6
	 */
	
	@SuppressWarnings("unchecked")
	public void bindButtonAction(Container root) {

		Map<Component, Field> map = scanComponentField(root);
		

		for (Map.Entry<Component, Field> entry : map.entrySet()) {

			if (entry.getKey() instanceof JButton) {

				Field field = entry.getValue();
				JButton button = (JButton) entry.getKey();
				ButtonAction buttonAction = field
						.getAnnotation(ButtonAction.class);
				boolean oldAccessible = field.isAccessible();
				field.setAccessible(true);
				try {
					@SuppressWarnings("rawtypes")
					IButtonActionListener<Container> actionListner = (IButtonActionListener) buttonAction
							.type().newInstance();

					actionListner.setInjectUI(root);
					
					String iconPara = buttonAction.icon();
					
					if(StringUtils.isNotEmpty(iconPara)){
						ImageIcon icon = ImageUtil.getImageIcon(root.getClass(),
								buttonAction.icon());
						
						button.setIcon(createMovedIcon((Icon) icon, 1, 1));
					}
					
					button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					
					addKeyListener(button,
							actionListner, buttonAction.shortcutKey());

				} catch (InstantiationException e) {
					log.error("InstantiationException", e);
				} catch (IllegalAccessException e) {
					log.error("IllegalAccessException", e);
				} finally {

					field.setAccessible(oldAccessible);
				}

			}

		}

	}

	/**
	 * 功能：addKeyListener
	 * 
	 * @param:
	 * @return void
	 * @since:1.6
	 */@SuppressWarnings("rawtypes")
	private void addKeyListener(JButton button,
			 IButtonActionListener actionListener, String shortcutKey) {

		button.registerKeyboardAction(actionListener,
				KeyStroke.getKeyStroke(shortcutKey),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button.addActionListener(actionListener);
	}

	/**
	 * 
	 * 功能：scanComponentField对root 进行扫描，获取conpoment 的键值对
	 * 
	 * @param:
	 * @return Map<Component,Field>
	 * @since:1.6
	 */
	Map<Component, Field> scanComponentField(Component component) {
		Map<Component, Field> componentAndFields = new HashMap<Component, Field>();

		Field[] fields = component.getClass().getDeclaredFields();

		for (Field field : fields) {

			if (JButton.class.isAssignableFrom(field.getType())) {

				ButtonAction buttonAction = field
						.getAnnotation(ButtonAction.class);
				if (buttonAction == null) {
					continue;
				}
				Component componentField = null;
				boolean oldAccessible = field.isAccessible();
				field.setAccessible(true);
				try {
					componentField = (Component) field.get(component);

					componentAndFields.put(componentField, field);

				} catch (Exception e) {
					if (log.isWarnEnabled()) {
						log.warn("Container scanner can't get field value.", e);
					}
				} finally {
					field.setAccessible(oldAccessible);
				}
			} 
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
	 * 功能：createMovedIcon
	 * 
	 * @param:
	 * @return Icon
	 * @since:1.6
	 */
	private static Icon createMovedIcon(final Icon icon, final int offsetX,
			final int offsetY) {

		Icon icon2 = new Icon() {

			public void paintIcon(Component c, Graphics g, int x, int y) {
				icon.paintIcon(c, g, x + offsetX, y + offsetY);
			}

			public int getIconWidth() {
				return icon.getIconWidth();
			}

			public int getIconHeight() {
				return icon.getIconHeight();
			}
		};

		return icon2;

	}
}
