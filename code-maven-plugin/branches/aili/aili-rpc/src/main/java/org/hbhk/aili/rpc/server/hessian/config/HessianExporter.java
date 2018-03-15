package org.hbhk.aili.rpc.server.hessian.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.rpc.server.hessian.annotation.HessianRemoting;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

public class HessianExporter implements BeanFactoryPostProcessor {

	private final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 瀵筨eanfactory涓墍鏈塕emote娉ㄨВ鐨刡ean娉ㄥ唽涓篟emoteExporter
	 * 
	 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
	 *      postProcessBeanFactory
	 * @param beanFactory
	 * @throws BeansException
	 * @since:0.7
	 */
	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinitionRegistry registry = null;
		if (beanFactory instanceof BeanDefinitionRegistry) {
			registry = (BeanDefinitionRegistry) beanFactory;
		}

		// 閬嶅巻bean宸ュ巶锛屾煡鎵炬墦涓婃敞瑙emote鐨刡ean
		for (String beanName : beanFactory.getBeanDefinitionNames()) {

			Class<?> beanType = beanFactory.getType(beanName);
			if (!AnnotationUtils.isAnnotationDeclaredLocally(HessianRemoting.class,
					beanType)) {
				continue;
			}

			HessianRemoting remote = AnnotationUtils.findAnnotation(beanType,
					HessianRemoting.class);

			Class<?> serviceInterface = findServiceInterface(beanType, remote);

			String remoteBeanName;
			if (remote.name().equals("")) {
				String interfaceName = serviceInterface.getName();
				interfaceName = interfaceName.substring(serviceInterface
						.getPackage().getName().length() + 1);
				remoteBeanName = interfaceName.substring(1, 2).toLowerCase()
						+ interfaceName.substring(2);
			} else {
				remoteBeanName = remote.name();
			}

			String pkgName = beanType.getPackage().getName();
			String preFix = "";
			if (pkgName.indexOf(".server")>-1) {
				String tmpStr = pkgName.substring(0,pkgName.indexOf(".server"));
				preFix = "/" + tmpStr.substring(tmpStr.lastIndexOf('.')+1, tmpStr.length());
			}

			remoteBeanName = preFix + "/" + remoteBeanName;

			if (registry == null) {
				Object bean = beanFactory.getBean(beanName);

				RemoteExporter remoteExporter = createRemoteExporter(
						serviceInterface, bean, remote);
				beanFactory.registerSingleton(remoteBeanName, remoteExporter);
			} else {
				GenericBeanDefinition gbd = new GenericBeanDefinition();
				gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
				gbd.setBeanClass(remote.serviceExporter());

				MutablePropertyValues mpv = new MutablePropertyValues();
				RuntimeBeanReference serviceRef = new RuntimeBeanReference(
						beanName);
				PropertyValue servicePv = new PropertyValue("service",
						serviceRef);

				mpv.addPropertyValue(servicePv);

				PropertyValue serviceInterfacePv = new PropertyValue(
						"serviceInterface", serviceInterface);
				mpv.addPropertyValue(serviceInterfacePv);

				if (RmiServiceExporter.class.isAssignableFrom(beanType)) {
					PropertyValue serviceNamePv = new PropertyValue(
							"serviceName", serviceInterface.getName());
					mpv.addPropertyValue(serviceNamePv);
				}

				gbd.setPropertyValues(mpv);
				registry.registerBeanDefinition(remoteBeanName, gbd);
			}
		}
	}

	/**
	 * 鏌ユ壘Hessian鏈嶅姟绫绘帴鍙� * findServiceInterface
	 * 
	 * @param beanType
	 * @param remote
	 * @return
	 * @return Class
	 * @since:0.7
	 */
	@SuppressWarnings("rawtypes")
	private Class findServiceInterface(Class beanType, HessianRemoting remote) {
		Class serviceInterface = null;

		serviceInterface = remote.serviceInterface();
		if (serviceInterface == Object.class) {
			Class[] interfaces = beanType.getInterfaces();
			if (interfaces != null && interfaces.length == 1) {
				Class interfaceClazz = interfaces[0];
				String interfaceName = interfaceClazz.getName();
//				if (!interfaceName.endsWith("Remoting")) {
//					throw new BeanCreationException(
//							interfaceName
//									+ " : the deploy hessian server must implements the interface with the *Remoting tags..");
//				}
				return interfaceClazz;
			}
			throw new BeanCreationException(
					"Can't determine interface for remote exporter");
		} else {
			return serviceInterface;
		}
	}

	/**
	 * 涓哄埗瀹氫笟鍔＄被鍒涘缓鍙戝竷绫� * createRemoteExporter
	 * 
	 * @param serviceInterface
	 * @param service
	 * @param remote
	 * @return
	 * @return RemoteExporter
	 * @since:0.7
	 */
	private RemoteExporter createRemoteExporter(
			@SuppressWarnings("rawtypes") Class serviceInterface,
			Object service, HessianRemoting remote) {
		RemoteExporter remoteExporter = null;
		try {
			remoteExporter = (RemoteExporter) remote.serviceExporter()
					.newInstance();
			remoteExporter.setService(service);
			remoteExporter.setServiceInterface(serviceInterface);
			if (remoteExporter instanceof RmiServiceExporter) {
				((RmiServiceExporter) remoteExporter)
						.setServiceName(serviceInterface.getName());
			}
			if (remoteExporter instanceof InitializingBean) {
				((InitializingBean) remoteExporter).afterPropertiesSet();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
		}
		return remoteExporter;
	}
}
