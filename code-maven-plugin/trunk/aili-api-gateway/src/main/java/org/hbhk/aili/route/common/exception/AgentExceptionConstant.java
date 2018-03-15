package org.hbhk.aili.route.common.exception;

/**
 * S000001	ESB-ESBServiceCode消息头中所定义的服务编码%S不存在。	
 * 			调用端检查是否输入了正确的服务编码
 * S000002	用户名%S/密码验证不通过	检查是否输入了正确的用户名
			检查是否对密码做了MD5摘要，并且转换成了小写的16进制字母
			S000003	后端服务不可用	可再次重试，如果还是出现错误需要联系系统管理员查错。
   S000004	参数（JSON字符串）解析错误	查看生成的JSON是否符合接口约定的格式，如日期格式，日期是否合法等
   S000099	未知错误	查看异常详细信息，并联系系统管理员。
   S000005	用户没有权限访问该接口	
   S000006	该接口当天访问量达到上限
   S000007	特定用户当天访问接口量达到上限
   S000008  ESB规范要求的头信息%S未填写完毕
   S000009  接口%S并发量到上限值%S;
   S000010  用户%S访问接口%S并发值到上限%S;
   S000011  请求消息内容为空;

 * @author qiancheng
 *
 */
public class AgentExceptionConstant {
	public static final String AUTH_ESBSERVICE_NOT_EXIST="S000001";
	public static final String AUTH_USER_PASSWORD_ERROR="S000002";
	public static final String BACKEND_CANNOT_CALLED="S000003";
	public static final String JSON_ERROR="S000004";
	public static final String AUTH_UESR_INTERFACE_ERROR="S000005";
	public static final String FLOWCONTROL_INTERFACE_LIMIT="S000006";
	public static final String FLOWCONTROL_USER_INTERFACE_LIMIT="S000007";
	public static final String INCOMPLETE_SPECIFICATION="S000008";
	public static final String UNKNOW="S000099";
	public static final String CONCURRENT_ITF_EEROR="S000009";
	public static final String CONCURRENT_USER_ITF_EEROR="S000010";
	public static final String REQUEST_BODY_NULL="S000011";
	public static final String TYPE_SYS="SYS";
	public static final String TYPE_APP="APP";
}
