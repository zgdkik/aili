1. 需要在web.xml中引入的spring配置文件：
	esb-runtime-common：
	com/deppon/esb/common/META-INF/common-config.xml;(公共配置信息)
	
	esb-runtime-server-common：
	com/deppon/esb/server/common/META-INF/*/*.xml;(jms和ws的公共配置)
	
	esb-runtime-server-xx:
	com/deppon/esb/server/xx/META-INF目录及子目录下的所有spring配置文件;


2.服务配置信息组件说明：
	此组件是在esb-runtime-server-common中，由于是通过JMS来拿服务配置信息，而且不能依赖spring，
	只能通过java和sun jms来获取MQ资源和操作队列。
	这里是采用的获取JBOSS的jndi资源。因此需要在JBOSS中配置MQ资源的时候，写上相应的名称。
	配置文件(/esb-runtime-server-common/src/main/resources/com/deppon/esb/server/common/META-INF/jms/jms.properties)说明：
	
	#在JBOSS的MQ队列管理器的JNDI名称(注：在实际的JBOSS的jndi名称前要加java:)
	connection_factory_jndi=java:jms/ESBMQCONF
	
	#发送请求配置信息的队列的JNDI名称
	request_queue_jndi=jms/confRequest
	
	#接收响应配置信息的队列的JNDI名称
	response_queue_jndi=jms/confResponse
	
	#在接收响应配置信息的超时时间, 单位：秒
	receive_timeout=60
	
	#JmsComponent的ID(在接口平台中，配置org.apache.camel.component.jms.JmsComponent的id)
	jms_component=ESBMQ