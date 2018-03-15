package org.hbhk.aili.log.tail;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class StartTailLogContext implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("tail log 开始启动");
//		TailLogThread tlt = new TailLogThread();
//		tlt.start("log");
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					WebsocketHandler.broadcastLog("test","log1");
//					try {
//						TimeUnit.SECONDS.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		t.start();
		
		
	}

}
