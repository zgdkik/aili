package org.hbhk.aili.base.shared.define;

/**
 * 系统通用样式常量
 */
public enum FrontendCCssConstants {
	/** 空图标样式 */
	dpap_icons_emp {
		public String clsName() { return "foss_icons_emp"; }
	},
	/** 顶级菜单样式 */
	ye1_node_lvl0 {
		public String clsName() { return "ye1-node-lvl0"; }
	},
	/** 1级菜单样式 */
	ye1_node_lvl1 {
		public String clsName() { return "ye1-node-lvl1"; }
	},
	/** 2级菜单样式 */
	ye1_node_lvl2 {
		public String clsName() { return "ye1-node-lvl2"; }
	},
	/** 3级菜单样式 */
	ye1_node_lvl3 {
		public String clsName() { return "ye1-node-lvl3"; }
	},
	/** 4级菜单样式 */
	ye1_node_lvl4 {
		public String clsName() { return "ye1-node-lvl4"; }
	};
	
	/**
	 * <p>样式名称</p> 
	 */
	public String clsName() {
		return "";
	}
	
}
