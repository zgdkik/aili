package org.hbhk.aili.client.core.component.focuspolicy;

/**
 * 配载类型
 */
public enum AssemblyType {

	PIANXIAN{
		@Override
		public String toString() {
			return "偏线";
		}
	},
	HEDAPIAO{
		@Override
		public String toString() {
			return "和大票";
		}
	},
	DANDUKAIDAN{
		@Override
		public String toString() {
			return "单独开单";
		}
	},
	ZHUANXIAN{
		@Override
		public String toString() {
			return "专线";
		}
	},
	KONGYUN{
		public String toString(){
			return "空运";
		}
	}
}
