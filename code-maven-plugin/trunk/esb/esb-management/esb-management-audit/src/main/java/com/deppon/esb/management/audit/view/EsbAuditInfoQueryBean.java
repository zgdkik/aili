package com.deppon.esb.management.audit.view;

import java.util.Date;

import com.deppon.esb.management.audit.domain.EsbAuditInfo;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;

/**
 * 封装前台查询条件
 * @author qiancheng
 * @date 2013-2-27 下午2:59:15
 */
public class EsbAuditInfoQueryBean extends EsbAuditInfo{
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private String fid;
	private Date startTime;//起始时间
	private Date endTime;//结束时间
	private int start;//起始位置
	private int limit;//页大小
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getFid() {
		return fid;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("fid:").append(fid).append(";")
		.append("start:").append(start).append(";")
		.append("limit:").append(limit)
		.append("startTime:").append(startTime)
		.append("endTime:").append(endTime);
		if(getEsbHeader()!= null){
			EsbHeader header = getEsbHeader();
			buffer.append("esbHeader.version:").append(header.getVersion()).append(";\n")
			.append("esbHeader.backServicode").append(header.getBackServiceCode()).append(";\n")
			.append("esbHeader.businessId").append(header.getBusinessId()).append(";\n")
			.append("esbHeader.businessDesc1").append(header.getBusinessDesc1()).append(";\n")
			.append("esbHeader.businessDesc2").append(header.getBusinessDesc2()).append(";\n")
			.append("esbHeader.businessDesc3").append(header.getBusinessDesc3()).append(";\n")
			.append("esbHeader.requestId").append(header.getRequestId()).append(";\n")
			.append("esbHeader.responseId").append(header.getResponseId()).append(";\n")
			.append("esbHeader.esbServiceCode").append(header.getEsbServiceCode()).append(";\n")
			.append("esbHeader.backServiceCode").append(header.getMessageFormat()).append(";\n")
			.append("esbHeader.sourceSystem").append(header.getSourceSystem()).append(";\n")
			.append("esbHeader.targetSystem").append(header.getTargetSystem()).append(";\n")
			.append("esbHeader.exchangePattern").append(header.getExchangePattern()).append(";\n")
			.append("esbHeader.resultCode").append(header.getResultCode()).append(";\n")
			.append("esbHeader.sentSequence").append(header.getSentSequence()).append(";\n");
		}
		return super.toString();
	}
}
