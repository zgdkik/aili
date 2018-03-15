package org.hbhk.core.server;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

public class AppTest {

	public static void main(String[] args) {
		String str = UUID.randomUUID().toString();
		System.out.println(str);
		
		 str = "尊敬的客户，单号%s已到达，因比预计时间晚，发送%s张优惠券共%s元作为补偿，详情可登录德邦官网输入单号查询！【德邦物流】";
		 System.out.println(String.format(str, new Object[]{1,2,3}));
		 Date date = new Date(1439568000000l);
		 System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
	}
}