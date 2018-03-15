package org.hbhk.aili.client.core.core.security;

import java.awt.Component;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;
/**
 * 
 *控件保护类保护。通过权限控制类IAuthorizationDelegate检测当前控件是否有权限，然后根据设置相应的控件enable或者visible。
 */
public class ComponentGuard {
	private IAuthorizationDelegate delegate;
	private ComponentGuardType defaultGuardType;;
	
	public ComponentGuard(IAuthorizationDelegate delegate) {
		this(delegate, ComponentGuardType.INVISIBLE);
	}
	
	public ComponentGuard(IAuthorizationDelegate delegate, ComponentGuardType defaultGuardType) {
		this.delegate = delegate;
		this.defaultGuardType = defaultGuardType;
	}
	
	public void protectComponent(Component component, Object permission) {
		this.protectComponent(component, permission, null);
	}
	
	public void protectComponent(Component component, Object permission, ComponentGuardType guardType) {
		if (guardType == null) {
			guardType = defaultGuardType;
		}
		
		ComponentGuardListener listener = new ComponentGuardListener(permission, guardType);
		component.addHierarchyListener(listener);
		component.addPropertyChangeListener(listener);
	}
	/**
	 * 
	 *******************************************
	 * <b style="font-family:微软雅黑">
	 *	<small>Description:
	 *当组件的层次结构变化的时候就会根据当前权限控制器来控制组件的是否可用或者是否可见。
	 *	</small>
	 * </b></br>
	 * <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
	 * <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
	 ********************************************
	 * <div style="font-family:微软雅黑,font-size:70%"> 
	 * 1 2011-3-24 rogger 新增
	 * </div>  
	 ********************************************
	 */
	private class ComponentGuardListener implements HierarchyListener, PropertyChangeListener {
		private Object permission;
		private ComponentGuardType guardType;
		
		public ComponentGuardListener(Object permission, ComponentGuardType guardType) {
			this.permission = permission;
			this.guardType = guardType;
		}

		@Override
		public void hierarchyChanged(HierarchyEvent e) {
			Component component = e.getComponent();			
			
			if (!delegate.check(permission)) {
				guardComponent(component);
			}			
		}

		private void guardComponent(final Component component) {			
			if (guardType == ComponentGuardType.DISABLE) {
				if (component.isEnabled()) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							component.setEnabled(false);
						}
					});
				}
			} else {
				if (component.isVisible()) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							component.setVisible(false);
						}
					});
				}
			}
		}
		/**
		 * 只有当控件可用或者可见的属性被修改的时候才会进行相应的保护动作
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 * propertyChange
		 * @param evt
		 * @since:
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (!("enabled".equals(evt.getPropertyName()) && guardType == ComponentGuardType.DISABLE) &&
					!("visible".equals(evt.getPropertyName()) && guardType == ComponentGuardType.INVISIBLE)) {
				return;
			}
			
			if (!delegate.check(permission)) {
				guardComponent((Component)evt.getSource());
			}
		}
	}
}
