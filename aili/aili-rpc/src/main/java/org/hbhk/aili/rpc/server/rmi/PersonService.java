package org.hbhk.aili.rpc.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
* @Description:此为远程对象的实现类，须继承UnicastRemoteObject 
* @author 何波
* @date 2015年2月13日 下午5:40:26 
*
 */
public class PersonService extends UnicastRemoteObject implements
		IPersonService {
	protected PersonService() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = -1938264316312417823L;
	public static final Logger log = LoggerFactory
			.getLogger(PersonService.class);

	
	@Override
	public String deal1(String data) {
		return data+"";
	}
}
