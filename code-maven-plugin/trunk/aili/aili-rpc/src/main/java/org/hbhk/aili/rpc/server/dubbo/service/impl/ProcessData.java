package org.hbhk.aili.rpc.server.dubbo.service.impl;

import org.hbhk.aili.rpc.server.dubbo.TableConfig;
import org.hbhk.aili.rpc.server.dubbo.service.IProcessData;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 
 * @Description: 远程调用增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
@Service
public class ProcessData implements IProcessData {

	@Override
	public String deal(String data) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished:" + data);
		return "Finished:" + data;
	}

	@Override
	public String deal1(TableConfig test, String data) {
		return test.getCaption()+"";
	}
}
