package org.hbhk.aili.base.server.context;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hbhk.aili.core.share.entity.Token;

public class Session<V> implements ISession<V> {
    
    private javax.servlet.http.HttpSession session;
    
    @Override
    public void setObject(String k, V v) {
    	//先验证设置的值是否被允许
        validateSessionValue(v);
        session.setAttribute(k, v);
    }
    
    @Override
    public V getObject(String k) {
        Object value = null;
        value = session.getAttribute(k);
        return (V) value;
    }
    
    @Override
    public void init(HttpSession session) {
        this.session = session;
    }
    
    void validateSessionValue(V v) {
        if (null == v) {
        	return;
        }
        if (java.lang.String.class == v.getClass()) {
        	return;
        }
        if (java.lang.Long.class == v.getClass()) {
        	return;
        }
        if (java.lang.Integer.class == v.getClass()) {
        	return;
        }
        if (java.util.Date.class == v.getClass()) {
        	return;
        }
        if(java.util.Locale.class == v.getClass()){ 
        	return;
        }
        //SessionValue注解标注的放过
        if (v.getClass().isAnnotationPresent(SessionValue.class)) {
        	return;
        }
        //对于Collection及Map的遍历里面所有元素进行判断
        if (Collection.class.isAssignableFrom(v.getClass())) {
            Collection<?> collect = (Collection<?>) v;
            for (Object t : collect) {
                validateSessionValue((V) t);
            }
            return;
        }
        if (Map.class.isAssignableFrom(v.getClass())) {
            Map<?, ?> map = (Map<?, ?>) v;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                validateSessionValue((V) entry.getValue());
            }
            return;
        }
        if (Token.class.isAssignableFrom(v.getClass())) {
            return;
        }
        throw new IllegalArgumentException();
    }

	@Override
	public void invalidate() {
		session.invalidate();
		
	}

	@Override
	public String getId() {
		return session.getId();
	}

    @Override
    public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
    }
}
