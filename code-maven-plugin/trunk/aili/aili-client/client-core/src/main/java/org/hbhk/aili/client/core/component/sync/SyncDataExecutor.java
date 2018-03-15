package org.hbhk.aili.client.core.component.sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.task.ITaskContext;


/**
 * 
 * <p>Description: 执行数据获取与调度数据存储</p>
 *
 */
public class SyncDataExecutor {
	

	//log
	private static final Log LOG = LogFactory
					.getLog(SyncDataExecutor.class);
	private AbstractSyncDataProcessor processor;
	private boolean isWork;
	private List<SyncDataRequest> requests;
	private List<SyncDataRequest> requestsAfter;
	private ITaskContext context;

	/**
	 * 
	 * <p>Title: SyncDataExecutor</p>
	 * <p>Description: 构造函数</p>
	 * @param processor 同步数据处理者
	 * @param requests 同步数据请求
	 */
	public SyncDataExecutor(AbstractSyncDataProcessor processor,
			List<SyncDataRequest> requests3) {
		this.processor = processor;
		this.requests = requests3;
		
		
		//这里要对requests做一个整理

		
	}

	/**
	 * 执行同步的方法
	 * @throws Exception 
	 */
	public void Execute() throws Exception {
	
		
		int len = 2;
		int i = 1;
		if(context!=null){
			context.setCancelable(false);
		}
		this.isWork = true;
		
		try{
			i = syncData( len, i, requests);
		}catch(Exception e){
			LOG.error("下载出错  重试一次", e);
			i = syncData( len, i, requests);
		}
		requestsAfter = new ArrayList<SyncDataRequest>();
		List<SyncDataRequest> requestsAfter2 = processor.parseSyncDataConfigsAfter(true);
		
		for(SyncDataRequest r : requestsAfter2){
			if("Y".equals(r.getNeedOrgSearch())){
				//需要将不同的request进行合并
				boolean hasRequest = false;
				
				for(SyncDataRequest r2 :this.requestsAfter ){
					if(r2.getSyncKey()==null || r.getSyncKey()==null){
						continue;
					}
					
					if(r2.getSyncKey().getName().equals(r.getSyncKey().getName())){
						r2.getList().add(r);//对象请求合并 减少请求数量
						hasRequest = true;
					}
				}
			
				if(!hasRequest){
					r.getList().add(r);//对象请求合并 减少请求数量
					this.requestsAfter.add(r);
				}
				
			}
		}
		try{
			syncData(len, i, requestsAfter);
		}catch(Exception e){
			LOG.error("下载出错  重试一次", e);
			syncData( len, i, requestsAfter);
		}
	}

	/**
	 * @param service
	 * @param len
	 * @param i
	 * @return
	 * @throws Exception 
	 */@SuppressWarnings("rawtypes")
	private int syncData( int len, int i, List<SyncDataRequest> requestsList) throws Exception {
		ISyncDataRemoting service = processor.getSyncRemoteService();
		
		for (SyncDataRequest request : requestsList) {
			Date date = null;
			String regionID = null;
			if (!this.isWork){
				break;
			}
			
			//zxy 20140423 MANA-2018 1.4 中断下载抛异常
			//zxy 20140313 MANA-2018 start 新增:查询开关,如果关闭则不继续下载
			if(!processor.isActive())
				throw new Exception("下载控制器active=N,停止下载");
			//zxy 20140313 MANA-2018 end 新增:查询开关,如果关闭则不继续下载
			
			ISyncDataSaver dataSaver = processor.getDataSaver(request.getSyncKey());
			LOG.warn("现在在下载数据的数据对象："
					+request.getSyncKey().getName());
		
			
			SyncDataRequest currentRequest = request;
			SyncDataResponse response = null;
			
			List list = new ArrayList();;
			
			if("Y".equals(request.getPagination())){//需要分页
				context.setMessage("现在分页下载数据："+request.getSyncKey().getSimpleName() + ",第1页 等待服务器返回...");
				
				response = service.processSyncData(currentRequest);
				List subList =  response.getFromData();
				
				if(subList==null || subList.size()==0){
					LOG.info("没有下载到任何分页数据"+request.getSyncKey().getName() 
							+" 时间" + request.getFromDate()  );
				}
				int loopCount = 0;
				while(subList!=null && subList.size()>0 ){
					//zxy 20140423 MANA-2018 1.4 中断下载抛异常
					//zxy 20140313 MANA-2018 start 新增:查询开关,如果关闭则不继续下载
					if(!processor.isActive())
						throw new Exception("下载控制器active=N,停止下载");
					//zxy 20140313 MANA-2018 end 新增:查询开关,如果关闭则不继续下载
					
					loopCount ++;
					if(loopCount > processor.getMaxPageSize()){
						break;
					}
					boolean finishDown =false;
					Object obj = subList.get(0);
					if(obj instanceof Map){
						Map param = (Map)obj;
						Set set  = param.keySet();
						for (Iterator iterator = set.iterator(); iterator
								.hasNext();) {
							Object object = (Object) iterator.next();
							Object value = param.get(object);
							if(value instanceof List){
								List listinner = (List)value;
								if(listinner == null || listinner.size()<1){
									finishDown = true;
									list = null;
								}
							}
						}
					}
					
					if(finishDown){
						break;
					}
					if(subList.size()>10){
						context.setMessage("下载数据："+request.getSyncKey().getSimpleName()+ " 服务器返回"+subList.size()+"条数据，正在保存到本地");
					}else{
						context.setMessage("下载数据："+request.getSyncKey().getSimpleName()+ " 服务器返回数据，正在保存到本地");
					}
					LOG.info("下载数据"+request.getSyncKey().getName() 
							+" 时间" + request.getFromDate() + " total: "+ subList.size()  );
					date = dataSaver.saveData(subList);
					LOG.info("date" + date +" region id" + dataSaver.getRegionID());
					regionID = dataSaver.getRegionID();
					if(date!=null){
						// 同步数据请求
						SyncDataRequest saveRequest = new SyncDataRequest();
						saveRequest.setSyncKey(response.getSyncKey());
						
						saveRequest.setOrgCode(request.getOrgCode());
						saveRequest.setFromDate(date);
						saveRequest.setNeedOrgSearch(request.getNeedOrgSearch());
						saveRequest.setRegionID(regionID);
						saveRequest.setList(request.getList());
						processor.getDataSaver(request.getSyncKey()).updateBaseLine(
								saveRequest);
					}
					
					
					currentRequest.setSyncPage(currentRequest.getSyncPage()+1);
					context.setMessage("现在分页下载数据："+request.getSyncKey().getSimpleName() + ",第"+(currentRequest.getSyncPage()+1)+"页  等待服务器返回...");
					response = service.processSyncData(currentRequest);
					subList =  response.getFromData();
				}
				
			}else{//不需要分页
				context.setMessage("现在下载数据："+request.getSyncKey().getSimpleName() + ", 等待服务器返回...");
				response = service.processSyncData(currentRequest);
				list= response.getFromData();
				processor.onStatusChange(request.getSyncKey());
			}
			
		
			
			if(list!=null && list.size()>0){
				
				LOG.info("下载数据"+request.getSyncKey().getName() 
						+" 时间" + request.getFromDate() + " total: "+ list.size()  );
				if(dataSaver!=null){
					
					Object fistObject = list.get(0);
					String isOrgIncremet = null;
					if(fistObject instanceof Map ){
						
						Map map =(Map)fistObject;
						isOrgIncremet =(String) map.get("isOrgIncremet");
					}
					
					if("Y".equals(isOrgIncremet)){
						Map map =(Map)fistObject;
						List list2 =(List)map.get("list");
						context.setMessage("下载数据："+request.getSyncKey().getSimpleName()+ " 服务器返回"+list2.size()+"条数据，正在保存到本地");
						
						dataSaver.saveData(list2);
						
						List versionList =(List) map.get("verionList");
						
						for (Iterator iterator = versionList.iterator(); iterator.hasNext();) {
							Map version = (Map) iterator.next();
							String orgCode = (String)version.get("orgCode");
							Date date2 = (Date) version.get("version");
							SyncDataRequest saveRequest = new SyncDataRequest();
							saveRequest.setSyncKey(response.getSyncKey());
							
							saveRequest.setOrgCode(orgCode);
							saveRequest.setFromDate(date2);
							saveRequest.setNeedOrgSearch(request.getNeedOrgSearch());
							saveRequest.setRegionID(regionID);
							saveRequest.setList(request.getList());
							
							processor.getDataSaver(request.getSyncKey()).updateBaseLineByOrgCode(
									saveRequest);
						}
											}else{
						if(list!=null && list.size()>1000){
							context.setMessage("下载数据："+request.getSyncKey().getSimpleName()+ " 服务器返回"+list.size()+"条数据，正在保存到本地");

							List list2 = new ArrayList();
							int k=0;
							int comman = 0;
							for(; k<list.size(); k++){
								
								Object object = list.get(k);
								list2.add(object);
								if(k%1000==0 && k>0){//每1000条commmit一次
									comman ++;
									//normal channel
								
									context.setMessage(request.getSyncKey().getSimpleName()+"1000 条记录正保存, 已保存  "
									+(comman*1000)+" 条记录" );
									
									date = dataSaver.saveData(list2);
									LOG.info("date" + date +" region id" + dataSaver.getRegionID());
									regionID = dataSaver.getRegionID();
									if(date!=null){
										// 同步数据请求
										SyncDataRequest saveRequest = new SyncDataRequest();
										saveRequest.setSyncKey(response.getSyncKey());
										
										saveRequest.setOrgCode(request.getOrgCode());
										saveRequest.setFromDate(date);
										saveRequest.setNeedOrgSearch(request.getNeedOrgSearch());
										saveRequest.setRegionID(regionID);
										saveRequest.setList(request.getList());
										processor.getDataSaver(request.getSyncKey()).updateBaseLine(
												saveRequest);
									}
									list2 = new ArrayList();
								}
							}
							context.setMessage(""+request.getSyncKey().getSimpleName()+" 最后 "+ list2.size()+" 条记录正保存, 已保存 "+(comman*1000)+" 条记录" );
							date = dataSaver.saveData(list2);
							LOG.info("date" + date +" region id" + dataSaver.getRegionID());
							regionID = dataSaver.getRegionID();

							if(date!=null){
								// 同步数据请求
								SyncDataRequest saveRequest = new SyncDataRequest();
								saveRequest.setSyncKey(response.getSyncKey());
								
								saveRequest.setOrgCode(request.getOrgCode());
								saveRequest.setFromDate(date);
								saveRequest.setNeedOrgSearch(request.getNeedOrgSearch());
								saveRequest.setRegionID(regionID);
								saveRequest.setList(request.getList());
								processor.getDataSaver(request.getSyncKey()).updateBaseLine(
										saveRequest);
							}
						}else{
							//normal channel
							date = dataSaver.saveData(list);
							LOG.info("date" + date +" region id" + dataSaver.getRegionID());
							regionID = dataSaver.getRegionID();
							if(date!=null){
								// 同步数据请求
								SyncDataRequest saveRequest = new SyncDataRequest();
								saveRequest.setSyncKey(response.getSyncKey());
								
								saveRequest.setOrgCode(request.getOrgCode());
								saveRequest.setFromDate(date);
								saveRequest.setNeedOrgSearch(request.getNeedOrgSearch());
								saveRequest.setRegionID(regionID);
								saveRequest.setList(request.getList());
								processor.getDataSaver(request.getSyncKey()).updateBaseLine(
										saveRequest);
							}
						}
					}
				}
			}else{
				
				if(!"Y".equals(request.getPagination())){
					LOG.info("没有下载到任何数据"+request.getSyncKey().getName() 
						+" 时间" + request.getFromDate()  );
				}
			}	
			
			if(context!=null){
				context.setTitle("正在下载数据");
				context.setMessage("正在下载数据....");
				int k = i * len;
				if(k>=100){
					k =99;
				}
				context.setProgress(k);
				
				i++;
			}
			
		}
		return i;
	}

	/**
	 * 
	 * <p>Title: stop</p>
	 * <p>Description: 停止</p>
	 */
	public void stop() {
		this.isWork = false;
	}

	/**
	 * @return the context
	 */
	public ITaskContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(ITaskContext context2) {
		context = context2;
	}
	
}
