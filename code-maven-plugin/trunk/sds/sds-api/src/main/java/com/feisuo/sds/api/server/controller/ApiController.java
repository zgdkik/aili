package com.feisuo.sds.api.server.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.util.AnnotationScanningUtil;
import org.hbhk.aili.core.share.util.JavaParamsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisuo.sds.api.share.entity.ApiGroupInfo;
import com.feisuo.sds.api.share.entity.ApiInfo;
import com.feisuo.sds.base.server.controller.AbstractController;

@Controller
@RequestMapping("/api")
public class ApiController extends AbstractController {

	public Log log = LogFactory.getLog(getClass());

	@RequestMapping("/get/{groupName}")
	public String getApiInfo(@PathVariable String groupName) {
		try {
			List<Class<?>> conClasses = AnnotationScanningUtil
					.getAnnotatedClasses(RequestMapping.class,
							"com.**.api.server.controller");
			if (!CollectionUtils.isEmpty(conClasses)) {
				List<ApiGroupInfo> apiGroupInfos = new ArrayList<ApiGroupInfo>();
				for (Class<?> cls : conClasses) {
					ApiGroupInfo apiGroupInfo = new ApiGroupInfo();
					RequestMapping rm = cls.getAnnotation(RequestMapping.class);
					String name = (rm == null ? null : rm.value()[0]);
					apiGroupInfo.setName(name);
					Method[] methods = cls.getDeclaredMethods();
					for (Method method : methods) {
						RequestMapping rmm = method
								.getAnnotation(RequestMapping.class);
						if (rmm == null) {
							continue;
						}
						String url = (rmm == null ? null : rmm.value()[0]);
						url = name + url;
						ApiInfo api = new ApiInfo();
						api.setUrl(url);
						Class<?>[] parameterTypes = method.getParameterTypes();
						String methodName = method.getName();
						for (Class<?> clas : parameterTypes) {
							String parameterType = clas.getSimpleName();
							System.out.println("参数名称:" + parameterType);
							JavaParamsUtil.getMethodParamNames(cls, methodName, parameterTypes);
						}
					}
					apiGroupInfos.add(apiGroupInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/get")
	@ResponseBody
	public String getALlApiInfo(String groupName) {

		return "hbhk";
	}

}
