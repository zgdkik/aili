package org.hbhk.aili.client.core.commons.util;

import javax.swing.KeyStroke;

public abstract class KeyUtils {
	private final static String ValidKeySet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijKlmnopqrstuvwxyz";
	
	
	private KeyUtils(){
		
	}
	
	public static KeyStroke explainKey(String keyText) {
		int keyCode = 0;
		int modifiers = 0;
		String[] keys = keyText.split("[+]");
		for (int i = 0; i < keys.length - 1; i++) {
			if ("shift".equalsIgnoreCase(keys[i])) {
				modifiers |= 1;
			} else if ("ctrl".equalsIgnoreCase(keys[i])) {
				modifiers |= 2;
			} else if ("meta".equalsIgnoreCase(keys[i])) {
				modifiers |= 4;
			} else if ("alt".equalsIgnoreCase(keys[i])) {
				modifiers |= 8;
			} else {
				throw new RuntimeException("error keyText [" + keyText + "]");
			}
		}
		try {
			keyCode = getKeyCode(keys[keys.length - 1]);
		} catch (Exception e) {
			throw new RuntimeException("error keyText [" + keyText + "]",e);
		}

		return KeyStroke.getKeyStroke(keyCode, modifiers);
	}

	private static int getKeyCode(String key) throws Exception {
		if (key.length() == 1) {
			char c = key.charAt(0);
			if (ValidKeySet.indexOf(c) == -1) {
				throw new RuntimeException();
			}
			return Character.toUpperCase(c) - '0' + 48;
		} else if ('f' == Character.toLowerCase(key.charAt(0))
				&& key.length() <= 3) {
			int n = Integer.parseInt(key.substring(1));
			if (n > 12) {
				throw new RuntimeException();
			}
			return 111 + n;
		} else {
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		try {
			String[] keys = "a,A,C,4,5,7,F1,F12,f12,f5".split("[,]");
			for (String key : keys) {
				System.out.println(key + "==>" + getKeyCode(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
