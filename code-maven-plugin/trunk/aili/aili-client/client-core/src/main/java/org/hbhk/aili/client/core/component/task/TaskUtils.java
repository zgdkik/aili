package org.hbhk.aili.client.core.component.task;

import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.task.ITaskContext;
/**
 * <b style="font-family:微软雅黑"><small>Description:任务执行工具类</small></b>   </br>
 */
public final class TaskUtils {
	
	private TaskUtils(){
		
	}
	// 国际化对象
	private static final II18n i18n = I18nManager.getI18n(TaskUtils.class);
	
	/**
	 * 
	 * @Title:getRemainTime
	 * @Description:根据任务上下文获得任务执行剩余时间
	 * @param @param context 任务上下文
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getRemainTime(ITaskContext context ) {
		if (context == null) {
			return "";
		}
		int percent = context.getProgress(); 
		long duration = context.getDuration();
		if (percent <= 0 || duration <= 0) {
			return "";
		}
		long time = (duration / percent) * 100 - duration;
		
		
		String display;
		if (time > 1000 * 60 * 60 * 24) {
			display = ((int)(time / (1000 * 60 * 60 * 24))) + i18n.get("Day");
		} else if (time > 1000 * 60 * 60) {
			display = ((int)(time / (1000 * 60 * 60))) + i18n.get("Hours");
		} else if (time > 1000 * 60) {
			display = ((int)(time / (1000 * 60))) + i18n.get("Minutes");
		} else {
			display = ((int)(time / 1000)) + i18n.get("Seconds");
		}	
		return "";//i18n.get("TimeRemain", display);
	}
}