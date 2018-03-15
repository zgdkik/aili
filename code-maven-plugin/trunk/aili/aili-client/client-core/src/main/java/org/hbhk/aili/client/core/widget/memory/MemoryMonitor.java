package org.hbhk.aili.client.core.widget.memory;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.util.NamedThreadFactory;
import org.hbhk.aili.client.core.widget.network.NetworkMonitor;

/**
 * <b style="font-family:微软雅黑"><small>Description:内存监视器，显示内存状态</small></b> </br>
 */
public final class MemoryMonitor {

	private static final Log log = LogFactory.getLog(NetworkMonitor.class);

	private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1, new NamedThreadFactory("Memory Check Thread", true));

	private static final MemoryMonitor INSTANCE = new MemoryMonitor();

	public static MemoryMonitor getMemoryMonitor() {
		return INSTANCE;
	}

	private final Set<MemoryListener> listeners = new CopyOnWriteArraySet<MemoryListener>();

	private volatile ScheduledFuture<?> future;

	private static final long DEFAULT_INTERVAL = 3000;

	private long interval = DEFAULT_INTERVAL;

	private long max = Runtime.getRuntime().maxMemory();

	private long total = Runtime.getRuntime().totalMemory();

	private long free = Runtime.getRuntime().freeMemory();

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 */
	private MemoryMonitor() {
	}

	/**
	 * 
	 * @Title:getMax
	 * @Description:TODO
	 * @param @return
	 * @return long
	 * @throws
	 */
	public long getMax() {
		return max;
	}

	/**
	 * 
	 * @Title:getTotal
	 * @Description:TODO
	 * @param @return
	 * @return long
	 * @throws
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 
	 * @Title:getFree
	 * @Description:TODO
	 * @param @return
	 * @return long
	 * @throws
	 */
	public long getFree() {
		return free;
	}

	/**
	 * 
	 * @Title:isFine
	 * @Description:最大内存减去使用内存，小于1M则为true
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean isFine() {
		return max - total + free > 1024;
	}

	/**
	 * 
	 * @Title:getCheckInterval
	 * @Description:TODO
	 * @param @return
	 * @return long
	 * @throws
	 */
	public long getCheckInterval() {
		return interval;
	}

	/**
	 * 
	 * @Title:setCheckInterval
	 * @Description:TODO
	 * @param @param interval
	 * @return void
	 * @throws
	 */
	public void setCheckInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * 
	 * @Title:init
	 * @Description:初始化内存状态，包括总内存、空闲内存、使用内存等。
	 * @param
	 * @return void
	 * @throws
	 */
	private void init() {
		if (future != null) {
			return;
		}
		synchronized (this) {
			if (future != null) { // double check
				return;
			}
			future = executor.scheduleWithFixedDelay(new Runnable() {
				public void run() {
					try {
						long m = Runtime.getRuntime().maxMemory();
						long t = Runtime.getRuntime().totalMemory();
						long f = Runtime.getRuntime().freeMemory();
						if (log.isDebugEnabled()) {
							log.debug("Check memory: max: " + m + ", total: " + t + ", free:" + f);
						}
						if (m != max || t != total || f != free) {
							max = m;
							total = t;
							free = f;
							MemoryEvent event = new MemoryEvent(MemoryMonitor.this, m, t, f, isFine());
							for (MemoryListener listener : listeners) {
								try {
									listener.onMemoryChanged(event);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
							}
						}
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}
				}
			}, interval, interval, TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * 
	 * @Title:addMemoryListener
	 * @Description:添加内存监听器
	 * @param @param listener
	 * @return void
	 * @throws
	 */
	public void addMemoryListener(MemoryListener listener) {
		listeners.add(listener);
		init();
		MemoryEvent event = new MemoryEvent(this, max, total, free, isFine());
		listener.onMemoryChanged(event);
	}

	/**
	 * 
	 * @Title:removeMemoryListener
	 * @Description:删除内存监听器
	 * @param @param listener
	 * @return void
	 * @throws
	 */
	public void removeMemoryListener(MemoryListener listener) {
		listeners.remove(listener);
	}

	/**
	 * 
	 * @Title:destory
	 * @Description:关闭监听线程
	 * @param
	 * @return void
	 * @throws
	 */
	public void destory() {
		if (future != null && !future.isCancelled()) {
			future.cancel(true);
		}
	}

}
