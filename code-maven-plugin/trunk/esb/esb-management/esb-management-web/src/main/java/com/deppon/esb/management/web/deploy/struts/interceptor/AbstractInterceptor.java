package com.deppon.esb.management.web.deploy.struts.interceptor;

/**
 * @Description 抽象拦截器类,平台中要定义的拦截器都必须继承它
 * @author HuangHua
 * @Date 2012-4-12
 *
 */
public abstract class AbstractInterceptor extends com.opensymphony.xwork2.interceptor.AbstractInterceptor{

   private static final long serialVersionUID = 1L;
   
   /**
    * 拦截器的销毁
    * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#destroy()
    * destroy
    * @since:
    */
   @Override
   public void destroy() {
       
   }
   
   /**
    * 拦截器的实例化
    * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#init()
    * init
    * @since:
    */
   @Override
   public void init() {
       
   }
}
