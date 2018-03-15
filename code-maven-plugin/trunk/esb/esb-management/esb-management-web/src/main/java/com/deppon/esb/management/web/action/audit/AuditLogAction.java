package com.deppon.esb.management.web.action.audit;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.audit.service.IEsbAuditService;
import com.deppon.esb.management.audit.view.EsbAuditInfoQueryBean;
import com.deppon.esb.management.audit.view.EsbAuditInfoView;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;
import com.deppon.esb.management.web.util.DateUtil;

@Controller("auditLogAction")
@Scope("prototype") 
public class AuditLogAction extends  ESBActionSupport{
	//限制最大查询时间跨度,默认为7天。
	private  int queryDateLimit=7;
	private static final long serialVersionUID = -5179814204187191117L;
	//审计日志服务
	@Resource
	private IEsbAuditService esbAuditService;
	//查询条件
	private EsbAuditInfoQueryBean queryBean;
	//审计日志列表
	private List<EsbAuditInfoView> list ;
	//审计日志详细信息
	private String auditLogBody;
	//错误信息
	private String errorMsg;
	//根据条件过滤的记录总条数
	private int count;
	//提示信息
	private String tips;
	
	private Logger LOGGER = Logger.getLogger(AuditLogAction.class);

	/**
	 * 
	 * 查询审计日志列表
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-27 下午2:44:08
	 * @return
	 */
	
	public String queryAuditLogList(){
/*		if(!paramCheck()){
			tips = "please check the param:"+((tips==null)?"":tips);
			success=false;
			return SUCCESS;
		}*/
		try {
			queryBean.setStart(start);
			queryBean.setLimit(limit);
			resultCount = esbAuditService.queryAuditLogCount(queryBean);	
			list = esbAuditService.queryEsbAuditLogList(queryBean);
			success=true;
			//tips="test";
		} catch (Exception e) {
			success=false;
			tips="查询出现未知错误："+e.getMessage();
			LOGGER.error(e.getMessage(), e);
		}
		return SUCCESS;
	}
	
	/**
	 * 生成测试查询条件
	 * 
	 */
	public EsbAuditInfoQueryBean creatTestData(){
		EsbAuditInfoQueryBean queryBean = new EsbAuditInfoQueryBean();
		EsbHeader header = new EsbHeader();
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 01, 01);
		Date d1 = cal.getTime();
		Date d2 =new Date();
		cal.set(2013, 02, 23);
		d2 = cal.getTime();
		queryBean.setStartTime(d1);
		queryBean.setEndTime(d2);
		queryBean.setStart(1);
		queryBean.setLimit(20);
		queryBean.setEsbHeader(header);
		return queryBean;
	}
	
	/**
	 * 
	 * 查询详细审计日志信息
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-27 下午2:47:41
	 * @return
	 */
	public String queryAuditLogBody(){
		try {
			if (queryBean == null || queryBean.getFid() == null
					|| queryBean.getFid().length() == 0) {
				tips = "查询参数为空";
				return ERROR;
			}
			auditLogBody = esbAuditService.queryAuditLogBody(queryBean.getFid());
			//格式化报文
			auditLogBody = format(auditLogBody);
			return SUCCESS;
		} catch (Exception e) {
			tips= e.getMessage();
			return ERROR;
		}
	}
	
	public boolean paramCheck(){
		boolean flag = false;
		//查询时间跨度不能超过7天
		if(queryBean !=null&& queryBean.getStartTime()!=null && queryBean.getEndTime()!=null){
			flag = DateUtil.compareDate(queryBean.getCreateTime(),queryBean.getEndTime(),queryDateLimit);
		}
		tips="查询时间不能超过7天";
		return flag;
	}
	/**
	 * 
	 * 格式化输出报文
	 * @author qiancheng
	 * @param 
	 * @date 2013-3-28 下午5:33:17
	 * @return
	 */
	private  String format(String str)
			throws Exception {
		Document doc =null;
		// 注释：创建输出(目标)
		StringWriter out =null;
		// 注释：创建输出流
		XMLWriter writer =null;
		try {
			SAXReader reader = new SAXReader();
			StringReader in = new StringReader(str);
			doc = reader.read(in);
			//设置输出格式
			OutputFormat formater = OutputFormat.createPrettyPrint();
			formater.setEncoding("utf-8");
			//输出格式化的串到目标中，执行后。格式化后的串保存在out中。
			out = new StringWriter();
			writer = new XMLWriter(out, formater);
			writer.write(doc);
		}
		finally{
			if(writer !=null){
				writer.close();
			}
			if(out != null){
				out.close();
			}
		}
		return out.toString();
	}
	
	public EsbAuditInfoQueryBean getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(EsbAuditInfoQueryBean queryBean) {
		this.queryBean = queryBean;
	}

	public List<EsbAuditInfoView> getList() {
		return list;
	}

	public void setList(List<EsbAuditInfoView> list) {
		this.list = list;
	}

	public String getAuditLogBody() {
		return auditLogBody;
	}

	public void setAuditLogBody(String auditLogBody) {
		this.auditLogBody = auditLogBody;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getQueryDateLimit() {
		return queryDateLimit;
	}

	public void setQueryDateLimit(int queryDateLimit) {
		this.queryDateLimit = queryDateLimit;
	}
	
}
