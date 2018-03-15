package org.hbhk.aili.log.tail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hbhk.aili.log.websocket.WebsocketHandler;

public class TailLogThread {

	private BufferedReader reader;
	private Process process;
	private InputStream inputStream;

	private ExecutorService service = Executors.newCachedThreadPool();

	public TailLogThread() {

	}
	public void start(final String uid,final String file) {
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String line;
					// /usr/local/tomcat7/tomcat-1/logs/catalina.out
					process = Runtime.getRuntime().exec("tail -f " +file);
					inputStream = process.getInputStream();
					reader = new BufferedReader(new InputStreamReader(inputStream));
					System.out.println("tail log 启动结束");
					while ((line = reader.readLine()) != null) {
						// 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
						WebsocketHandler.broadcastLog(line,uid);
					}
//					while (true) {
//						// 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
//						WebsocketHandler.broadcastLog(file,uid);
//						TimeUnit.SECONDS.sleep(2);
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}