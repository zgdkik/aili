package org.hbhk.aili.client.core.widget.memory;

import javax.swing.Icon;
import javax.swing.JLabel;

import org.hbhk.aili.client.core.commons.util.ImageFactory;
/**
* <b style="font-family:微软雅黑"><small>Description:内存状态，包括内存正常和内存不足</small></b>   </br>
 */
public class MemoryStatus extends JLabel implements MemoryListener {
	
	private static final long serialVersionUID = 1L;

	private static final Icon FINE_ICON = ImageFactory.getIcon("memory/fine.gif");
	
	private static final Icon WARN_ICON = ImageFactory.getIcon("memory/warn.gif");

	private long max;

	private long total;
	
	private long free;
	
	private Boolean fine;
	/**
	 * 
	 * <p>Title:</p>
	 * <p>Description:</p>
	 *
	 */
	public MemoryStatus() {
		MemoryMonitor.getMemoryMonitor().addMemoryListener(this);
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
		return fine == null || fine.booleanValue();
	}
	/**
	 * 
	 * <p>Title:onMemoryChanged
	 * <p>Description:</p>
	 * @param event
	 * @see org.hbhk.aili.client.core.widget.memory.MemoryListener#onMemoryChanged(org.hbhk.aili.client.core.widget.memory.MemoryEvent)
	 */
	@Override
	public void onMemoryChanged(MemoryEvent event) {
		if (fine == null || fine.booleanValue() != event.isFine()) { 
			fine = event.isFine();
			max = event.getMax();
			total = event.getTotal();
			free = event.getFree();
			super.setIcon(event.isFine() ? FINE_ICON : WARN_ICON);
			super.setToolTipText(toMemoryString(event.getMax(), event.getTotal(), event.getFree(), event.isFine()));
		}
	}
	/**
	 * 
	 * @Title:toMemoryString
	 * @Description:"内存" + (fine ? "正常" : "不足") + "： 限制: " + (max / 1024 / 1024) + "M, 已分配: " + 
	 * (total / 1024 / 1024) + "M, 已使用: " + (total / 1024 / 1024 - free / 1024 / 1024) + "M, 空闲: " + 
	 * (free / 1024 / 1024) + "M"
	 * @param @param max
	 * @param @param total
	 * @param @param free
	 * @param @param fine
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String toMemoryString(long max, long total, long free, boolean fine) {
		return "内存" + (fine ? "正常" : "不足") + "： 限制: " + (max / 1024 / 1024) + "M, 已分配: " + (total / 1024 / 1024) + "M, 已使用: " + (total / 1024 / 1024 - free / 1024 / 1024) + "M, 空闲: " + (free / 1024 / 1024) + "M";
	}

}
