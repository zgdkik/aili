package org.hbhk.code.template;

public enum ParamConstants {
	MODULE {
		public String expression() { return "${module}"; }
	},
	PROJECT {
		public String expression() { return "${project}"; }
	},
	ITERATOR {
		public String expression() { return "${iterator}"; }
	},
	TYPENAME {
		public String expression() { return "${typename}"; }
	};
	
	public String expression() {
		return this.name();
	}
	
	public String replace(String source, String replacement) {
		return source.replace(this.expression(), replacement);
	}
}
