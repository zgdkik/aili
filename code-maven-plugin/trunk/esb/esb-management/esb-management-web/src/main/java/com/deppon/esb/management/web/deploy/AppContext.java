/**
 * @Title AppContext.java
 * @Package com.deppon.esb.management.web.deploy.struts
 * @Description TODO
 * @author HuangHua
 * @date 2012-4-16 下午10:59:44
 */
package com.deppon.esb.management.web.deploy;

/**
 * @Description 应用上下文
 * @author HuangHua
 * @date 2012-4-16 下午10:59:44
 */
public abstract class AppContext {

    /**
     * 静态资源地址
     */
    private final String staticServerAddress;

    public String getStaticServerAddress() {
        return staticServerAddress;
    }

    public AppContext(String staticServerAddress) {
        this.staticServerAddress = staticServerAddress;
    }

    private static AppContext context;

    public static void initAppContext(String staticServerAddress) {
        if (context == null)
            context = new AppContext(staticServerAddress) {};
    }

    public static AppContext getAppContext() {
        return context;
    }
}
