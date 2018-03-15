package org.hbhk.aili.client.core.component.focuspolicy;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

/**
 * 
 * @内容：设置焦点顺序策略
 * @作者：林文升
 * @时间：2012-5-9
 */
public final class FocusTraversalPolicy {
	
	private FocusTraversalPolicy(){
		
	}

	public static void setFocusTraversalPolicy(final Component[] components,
			final List<IFocusPolicyHandle> handles,
			Map<Component, Integer> seqMap) {
		final int length = components.length;
		if (length <= 0){
			return;
		}

		for (Component component : components) {

			component.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					if (ke.getKeyCode() == KeyEvent.VK_LEFT) {// keyCode:左
						toPrevFocus(ke);
						
					} else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {// 39
						toNextFocus(ke);

					} else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {// 40
						toNextFocus(ke);
						
					}

				}

				private void toNextFocus(KeyEvent ke) {

					int focusIndex = -1;
					
					for (int j = 0; j < length; j++) {
						if (components[j].isFocusOwner()){
							focusIndex = j;
							handleSpecialSeq(ke.getKeyCode(), handles,
									components, j);
							break;
						}
					}

					if (focusIndex >= 0) {
						//找到下一个可编辑，可见的组件
						for (int j = 0; j < length; j++) {
							Component next = components[(focusIndex + j + 1) % length];
							if (next.isEnabled() && next.isVisible()){
								next.requestFocusInWindow();
								break;
							}
						}
					}
				}
				
				private void toPrevFocus(KeyEvent ke) {

					int focusIndex = -1;
					
					for (int j = 0; j < length; j++) {
						if (components[j].isFocusOwner()){
							focusIndex = j;
							handleSpecialSeq(ke.getKeyCode(), handles,
									components, j);
							break;
						}
					}

					if (focusIndex >= 0) {
						//找到下一个可编辑，可见的组件
						for (int j = 0; j < length; j++) {
							Component prev = components[(focusIndex + length - j - 1) % length];
							if (prev.isEnabled() && prev.isVisible()){
								prev.requestFocusInWindow();
								break;
							}
						}
					}
				}

				/**
				 * 
				 * @内容：处理焦点顺序
				 * @作者：林文升
				 * @时间：2012-5-9
				 * @返回类型：void
				 */
				@SuppressWarnings({ "rawtypes", "unchecked" })
				private void handleSpecialSeq(int keyCode,
						List<IFocusPolicyHandle> handles,
						Component[] components, int currentPoint) {

					for (IFocusPolicyHandle handle : handles) {

						if ((keyCode == handle.getKeyCode().getKeyCode())
								&& (handle.getType()
										.isInstance(components[currentPoint]))) {
							handle.handle(components[currentPoint], components,
									currentPoint);
							break;
						}

					}

				}

			});

		}

	}

}
