package org.hbhk.aili.client.boot.app;

import java.util.Set;

import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.core.core.security.ComponentGuard;
import org.hbhk.aili.client.core.core.security.IAuthorizationDelegate;

/**
 * 客户端界面UI安全控制manager
 */
public class UISecurityManager {
	/**
	 * 定义一个静态的控件保护类对象
	 */
	private static ComponentGuard componentGuard;

	public static ComponentGuard getComponentGuard() {
		/**
		 * 判断当前类的属性componentGuard是否为空
		 */
		if (componentGuard == null) {
			/**
			 * 创建一个ComponentGuard类的对象
			 */
			componentGuard = new ComponentGuard(
					new UserPermissionAuthorization());
		}
		return componentGuard;
	}

	public static boolean checkPermission(Object permission) {
		/**
		 * 获取当前用户信息
		 */
		UserEntity user = (UserEntity) SessionContext.getCurrentUser();
		/**
		 * 判断user是否为空
		 */
		if (user == null) {
			/**
			 * 如果user为空，则返回false
			 */
			return false;
		}
		// 服务端组织框架变动
		/**
		 * 修改时间：2012/05/02
		 */

		/**
		 * 获取当前的FunctionCodes，保存到Set集合
		 */
		Set<String> functionSet = SessionContext.getCurrentFunctionCodes();
		/**
		 * 判断functionSet是否为空
		 */
		if (functionSet == null) {
			return false; // 如果functionSet为空，返回false
		}
		/**
		 * 返回一个布尔值（Set集合functionSet是否包含参数permission）
		 */
		return functionSet.contains(permission);

	}

	private static class UserPermissionAuthorization implements
			IAuthorizationDelegate {

		@Override
		public boolean check(Object arg0) {
			/**
			 * 验证权限
			 */
			return checkPermission(arg0);
		}
	}
}