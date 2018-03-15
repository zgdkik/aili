package org.hbhk.aili.rpc.server.test.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftClient {

	
	public static void main(String[] args) {  
        try {  
            TTransport transport = new TSocket("localhost", 7911);  
            transport.open();  
            TProtocol protocol = new TBinaryProtocol(transport);  
            ThriftService.Client client = new ThriftService.Client(protocol);  
            System.out.println(client.add(77, 5));  
            transport.close();  
  
        } catch (TTransportException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (TException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}
