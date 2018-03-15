package org.hbhk.aili.client.core.core.binding;

/**
 * 
 *	延迟情况下的绑定器。此绑定器需要通过手动来触发数据变化。
 */
public interface IBufferedBinder<T> extends IBinder<T> {
	/**
	 * 绑定器通过此方法来触发数据变化。
	 * flush void
	 * @since:0.6
	 */
	public void flush();
	
	/**
	 * 绑定器通过此方法来重新设置表现层已经变化了的数据
	 * reset void
	 * @since:0.6
	 */
	public void reset();
}
