package org.hbhk.hms.gateway.mq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.hbhk.hms.mq.rabbit.EventController;
import org.hbhk.hms.mq.rabbit.IEventMessageStorage;
import org.hbhk.hms.mq.rabbit.IMessageAdapterHandler;
import org.hbhk.hms.mq.rabbit.MqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mq服务发现
 */
public class MqServiceDiscovery {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqServiceDiscovery.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private volatile List<String> dataList = new ArrayList<>();

    private String registryAddress;
    private ZooKeeper zookeeper;
    
    private EventController eventController;
    
    private  IMessageAdapterHandler messagerResponseAdapterHandler =null;
    
    private IEventMessageStorage eventMessageStorage = null;

    public MqServiceDiscovery(String registryAddress,EventController eventController) {
        this.registryAddress = registryAddress;
        this.eventController = eventController;
        eventMessageStorage = new DefaultEventMessageStorage();
        messagerResponseAdapterHandler = new MessagerResponseAdapterHandler(eventMessageStorage);
        zookeeper = connectServer();
        if (zookeeper != null) {
            watchNode(zookeeper);
        }
    }

    public String discover() {
        String data = null;
        int size = dataList.size();
        if (size > 0) {
            if (size == 1) {
                data = dataList.get(0);
                LOGGER.debug("using only data: {}", data);
            } else {
                data = dataList.get(ThreadLocalRandom.current().nextInt(size));
                LOGGER.debug("using random data: {}", data);
            }
        }
        return data;
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, MqConstant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            LOGGER.error("", e);
        }
        return zk;
    }

    private void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(MqConstant.ZK_REGISTRY_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        watchNode(zk);
                    }
                }
            });
            List<String> dataList = new ArrayList<>();
            for (String node : nodeList) {
                byte[] bytes = zk.getData(MqConstant.ZK_REGISTRY_PATH + "/" + node, false, null);
                dataList.add(new String(bytes));
            }
            LOGGER.debug("node data: {}", dataList);
            this.dataList = dataList;

            LOGGER.debug("Service discovery triggered updating connected server node.");
            UpdateConnectedServer();
        } catch (KeeperException | InterruptedException e) {
            LOGGER.error("", e);
        }
    }

    private void UpdateConnectedServer(){
       // ConnectManage.getInstance().updateConnectedServer(this.dataList);
    	//刷新mq服務配置
    	
    	eventController.setStarted(false);
    	for (String data : dataList) {
			String[] arr = data.split("=");
			if(arr.length == 2){
				String queueName  = arr[0];
				String exchangeName = arr[1];
				LOGGER.info("mq discovery  queueName:" +queueName+"   exchangeName:"+exchangeName);
				eventController.add(queueName, exchangeName);
				eventController.add(queueName+MqConstant._r_request_queue+MqConstant.mq_response_subfix, 
				exchangeName+MqConstant._r_request_exchange+MqConstant.mq_response_subfix, messagerResponseAdapterHandler);
			}
		}
    	eventController.start();
    }

    public void stop(){
        if(zookeeper!=null){
            try {
                zookeeper.close();
            } catch (InterruptedException e) {
                LOGGER.error("", e);
            }
        }
    }
}
