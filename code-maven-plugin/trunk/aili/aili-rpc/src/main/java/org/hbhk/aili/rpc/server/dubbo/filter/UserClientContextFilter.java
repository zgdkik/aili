package org.hbhk.aili.rpc.server.dubbo.filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
@Activate("userContextFilter")
public class UserClientContextFilter implements Filter {
	public static final Logger log = LoggerFactory
			.getLogger(UserClientContextFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		RpcInvocation rpcInvocation = (RpcInvocation) invocation;
		log.debug("设置当前用户:");
		rpcInvocation.setAttachment("userName", "");
		return invoker.invoke(rpcInvocation);
	}

}

