package org.hbhk.aili.client.core.widget.service;

import java.util.List;

import org.hbhk.aili.client.core.widget.itservice.common.ITServiceResultDto;
import org.hbhk.aili.client.core.widget.itservice.common.ITServiceVo;

/**
 * 一键上报接口
 *
 */
public interface IUploadITService {
	
	/**
	 * 一键上报至IT服务台
	 */
	public ITServiceResultDto uploadItServiceForGui(List<ITServiceVo> itList);
	
	/**
	 * 是否启用一键上报
	 */
	public boolean isStartItServiceUpload();
}
