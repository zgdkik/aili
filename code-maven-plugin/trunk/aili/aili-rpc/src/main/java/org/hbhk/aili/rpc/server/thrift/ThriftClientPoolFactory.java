package org.hbhk.aili.rpc.server.thrift;

import java.net.InetSocketAddress;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.hbhk.aili.rpc.server.thrift.support.ThriftServerAddressProvider;

/**
 * 连接池,thrift-client for spring
 */
public class ThriftClientPoolFactory extends BasePoolableObjectFactory<TServiceClient>{

    private final ThriftServerAddressProvider addressProvider;
    
    private final TServiceClientFactory<TServiceClient> clientFactory;
    
    private PoolOperationCallBack callback;

    protected ThriftClientPoolFactory(ThriftServerAddressProvider addressProvider,TServiceClientFactory<TServiceClient> clientFactory) throws Exception {
        this.addressProvider = addressProvider;
        this.clientFactory = clientFactory;
    }
    
    protected ThriftClientPoolFactory(ThriftServerAddressProvider addressProvider,TServiceClientFactory<TServiceClient> clientFactory,PoolOperationCallBack callback) throws Exception {
        this.addressProvider = addressProvider;
        this.clientFactory = clientFactory;
        this.callback = callback;
    }



    @Override
    public TServiceClient makeObject() throws Exception {
        InetSocketAddress address = addressProvider.selector();
        TSocket tsocket = new TSocket(address.getHostName(),address.getPort());
        TProtocol protocol = new TBinaryProtocol(tsocket);
        TServiceClient client = this.clientFactory.getClient(protocol);
        tsocket.open();
        if(callback != null){
        	try{
        		callback.make(client);
        	}catch(Exception e){
        		//
        	}
        }
        return client;
    }

    public void destroyObject(TServiceClient client) throws Exception {
    	if(callback != null){
    		try{
    			callback.destroy(client);
    		}catch(Exception e){
    			//
    		}
        }
    	TTransport pin = client.getInputProtocol().getTransport();
    	pin.close();
    }

    public boolean validateObject(TServiceClient client) {
    	TTransport pin = client.getInputProtocol().getTransport();
    	return pin.isOpen();
    }
    
    static interface PoolOperationCallBack {
    	//销毁client之前执行
    	void destroy(TServiceClient client);
    	//创建成功是执行
    	void make(TServiceClient client);
    }

}
