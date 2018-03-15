package org.hbhk.aili.core.share.util;
/**
 * 
* @Description: 计算表达式
* @author hbhk 
* @date 2015年7月7日 下午4:36:51
 */
public class CalculatorUtil {
	public static Long calculate(String src) {
		src = reverseRpn(src);
		CalcuatorStack<String> stack = new CalcuatorStack<String>();
		for (int i = 0; i < src.length(); i++) {
			String foo = src.charAt(i) + "";
			if (!isOperator(foo)) {
				stack.push(foo);
			} else {
				// 如果是运算符
				// 取栈顶两元素进行计算，并将计算结果放入栈顶
				String a = stack.pop();
				String b = stack.pop();
				Long temp = count(b, a, foo);
				stack.push(temp + "");

			}
		}
		// 最后栈顶元素的值就是表达式的值了
		return Long.parseLong(stack.pop());
	}

	// 是否为运算符
	private static boolean isOperator(String e) {
		if (null == e || "".equals(e)) {
			return false;
		}
		return "+".equals(e) || "*".equals(e) || "-".equals(e) || "/".equals(e);
	}

	// 是否是左括号
	private static boolean isLeft(String s) {
		return "(".equals(s);
	}

	// 是否是右括号
	private static boolean isRight(String s) {
		return ")".equals(s);
	}

	// 根据运算符（e）计算两个数的值班(a,b)
	public static Long count(String a, String b, String e) {
		Long temp1 = Long.parseLong(a);
		Long temp2 = Long.parseLong(b);
		if ("+".equals(e)) {
			return temp1 + temp2;
		} else if ("-".equals(e)) {
			return temp1 - temp2;
		} else if ("*".equals(e)) {
			return temp1 * temp2;
		} else {
			return temp1 / temp2;
		}
	}

	// 将中缀表达式转化为后缀表达式（逆波兰式）
	private static String reverseRpn(String src) {
		CalcuatorStack<String> stack = new CalcuatorStack<String>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < src.length(); i++) {
			String temp = src.charAt(i) + "";
			if (!isOperator(temp) && !isRight(temp) && !isLeft(temp)) {
				// 操作数，+-直接输出
				sb.append(temp);
			} else if (isOperator(temp)) {
				// 如果是操作符
				if (stack.size() == 0) {// 如果zhan为空则入zhan
					stack.push(temp);
				} else if ("+".equals(temp) || "-".equals(temp)) {
					if (isLeft(stack.top())) {// 如果栈顶为左括号，则直接入栈
						stack.push(temp);// 直接将操作符入栈
					} else {// 从栈顶往下找，直到找到当前栈顶的操作符优先级小于等于当前操作符
						String top = stack.top();
						boolean next = true;
						while (("*".equals(top) || "/".equals(top)) && next) {
							top = stack.pop();
							sb.append(top);// 弹出顶部元素
							next = stack.size() == 0 ? false : true;
						}
						stack.push(temp);//
					}

				} else {// 操作符为:"*"或"/" 直接入栈
					stack.push(temp);// +-的优先级比任何运算符都高，所以直接入栈

				}
			} else if (isLeft(temp)) {// 如果是左括号直接入栈
				stack.push(temp);

			} else if (isRight(temp)) {// 如果是右括号，则找到左括号，并将找的过程中的字符全部出栈
				String top = stack.pop();
				boolean next = true;
				while (!isLeft(top) && next) {
					sb.append(top);
					top = stack.pop();
					next = stack.size() == 0 ? false : true;
				}
			}
		}
		while (stack.size() > 0) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println("计算结果:"
				+ CalculatorUtil.calculate("((1+2)*2)*4)+9*(1+2)"));
	}

}
