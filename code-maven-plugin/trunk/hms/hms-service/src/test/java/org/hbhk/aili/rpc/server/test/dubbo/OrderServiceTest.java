package org.hbhk.aili.rpc.server.test.dubbo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.rest.server.service.IOrderService;
import org.hbhk.aili.rest.share.entity.Order;
import org.junit.Test;

import com.alibaba.dubbo.config.annotation.Reference;

public class OrderServiceTest extends  SpringTest {
	
	
	@Reference
	private  IOrderService orderService;
	
	
	@Test
	public void  testSave(){
		orderService.save();
	}

	
	@Test
	public void  testGet(){
		Map<String, Object> p = new HashMap<>();
		p.put("orderNo", "3");
		List<Order>     l =  orderService.get(p);
		if(l!=null){
			for (Order order : l) {
				System.out.println(order.getOrderNo());
			}
		}
	}

}
