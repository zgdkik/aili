package org.hbhk.aili.client.core.widget.memory;

import java.util.EventObject;
/**
* <b style="font-family:微软雅黑"><small>Description:内存事件，内存改变时触发</small></b>   </br>
 */
public class MemoryEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final long max;

	private final long total;
	
	private final long free;
	
	private final boolean fine;
	/**
	 * 
	 * <p>Title:</p>
	 * <p>Description:</p>
	 *
	 * @param source
	 * @param max
	 * @param total
	 * @param free
	 * @param fine
	 */
	public MemoryEvent(Object source, long max, long total, long free, boolean fine) {
		super(source);
		this.max = max;
		this.total = total;
		this.free = free;
		this.fine = fine;
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
	 * @Description:TODO
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean isFine() {
		return fine;
	}
}
