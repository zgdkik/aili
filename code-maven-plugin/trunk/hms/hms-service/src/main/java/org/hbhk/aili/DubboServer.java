package org.hbhk.aili;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.container.Container;

public class DubboServer {

	public static final String CONTAINER_KEY = "dubbo.container";

	public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";

	private static final Logger logger = LoggerFactory.getLogger(DubboServer.class);

	private static final ExtensionLoader<Container> loader = ExtensionLoader
			.getExtensionLoader(Container.class);

	private static volatile boolean running = true;

	public static void main(String[] args) {
		try {
			if (args == null || args.length == 0) {
				String config = ConfigUtils.getProperty(CONTAINER_KEY,
						loader.getDefaultExtensionName());
				args = Constants.COMMA_SPLIT_PATTERN.split(config);
			}

			final List<Container> containers = new ArrayList<Container>();
			for (int i = 0; i < args.length; i++) {
				containers.add(loader.getExtension(args[i]));
			}
			logger.info("Use container type(" + Arrays.toString(args)
					+ ") to run dubbo serivce.");

			if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
				Runtime.getRuntime().addShutdownHook(new Thread() {
					public void run() {
						for (Container container : containers) {
							try {
								container.stop();
								logger.info("Dubbo "
										+ container.getClass().getSimpleName()
										+ " stopped!");
							} catch (Throwable t) {
								logger.error(t.getMessage(), t);
							}
							synchronized (DubboServer.class) {
								running = false;
								DubboServer.class.notify();
							}
						}
					}
				});
			}

			for (Container container : containers) {
				container.start();
				logger.info("Dubbo " + container.getClass().getSimpleName()
						+ " started!");
			}
			String zkHost = "139.196.180.16:2181";
			ZkClient zkClient = new ZkClient(zkHost, 25000);
			zkClient.setZkSerializer(new ZkSerializer() {
				public byte[] serialize(Object data) throws ZkMarshallingError {
					return data.toString().getBytes();
				}

				public Object deserialize(byte[] bytes)
						throws ZkMarshallingError {
					return new String(bytes);
				}
			});
			String dubboSub = "/dubboSub";
			if (!zkClient.exists(dubboSub)) {
				zkClient.createPersistent(dubboSub);
			}
			zkClient.writeData(dubboSub, System.currentTimeMillis() + "");
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]")
					.format(new Date()) + " Dubbo service server started!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			System.exit(1);
		}
		synchronized (DubboServer.class) {
			while (running) {
				try {
					DubboServer.class.wait();
				} catch (Throwable e) {
				}
			}
		}
	}

}
