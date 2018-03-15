package org.hbhk.aili.client.core.component.focuspolicy;

import java.awt.event.KeyEvent;

/**
 * 
 * @内容：键盘点击类型
 */
public enum KeyType {

	key_up ,
	key_down ,
	key_left ,
	key_right,
	key_enter ;

	public int getKeyCode() {
		switch (this) {
		case key_up:return KeyEvent.VK_UP;
		case key_down:return KeyEvent.VK_DOWN;
		case key_left:return KeyEvent.VK_LEFT;
		case key_right:return KeyEvent.VK_RIGHT;
		case key_enter:return KeyEvent.VK_ENTER;
		default :return -1;
		
		}
	
	}
	
	
	

}
