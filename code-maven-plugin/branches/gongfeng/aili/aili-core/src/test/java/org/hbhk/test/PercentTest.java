package org.hbhk.test;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

public class PercentTest {

	public static void main(String[] args) {
		double x_double = 200;
		double tempresult = 126;
		NumberFormat  format = NumberFormat.getInstance();
		format.setMinimumIntegerDigits(2);
		String sss = format.format(x_double/tempresult);
		System.out.println(sss);
		System.out.println(x_double/tempresult);
	}

	public static String getPercent(int x, int total) {
		String result = "";// 接受百分比的值
		double x_double = x * 1.0;
		double tempresult = x / total;
		// NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是一种方法
		// nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("0.00%"); // ##.00%
														// 百分比格式，后面不足2位的用0补齐
		// result=nf.format(tempresult);
		result = df1.format(tempresult);
		return result;
	}
	
	
	@Test
	public void testName1() throws Exception {
		String str = "尊敬的客户，单号%s已到达，因比预计时间晚，发送%s张优惠券共%s元作为补偿，详情可登录德邦官网输入单号查询";
		System.out.println(String.format(str, new Object[]{"1","2","3"}));
	}

}
