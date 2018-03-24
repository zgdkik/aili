package com.yimidida.ows.home.server.service.impl;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ows.home.server.dao.IOrderInfoDao;
import com.yimidida.ows.home.server.service.IOrderInfoService;
import com.yimidida.ows.home.server.util.BeanWrapper;
import com.yimidida.ows.home.server.util.HttpUtil;
import com.yimidida.ows.home.share.entity.OrderBase;
import com.yimidida.ows.home.share.entity.OrderInfo;
import com.yimidida.ows.home.share.vo.OrderBack;
import com.yimidida.ymdp.core.share.util.JsonUtil;

@Service
public class OrderInfoService implements IOrderInfoService {
	@Autowired
	IOrderInfoDao orderInfoDao;

	@Override
	public int insert(OrderInfo t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(OrderInfo t) {
		return orderInfoDao.update(t);
	}

	@Override
	public OrderInfo getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return orderInfoDao.get(params);
	}

	@Override
	public List<OrderInfo> getPage(Map<String, Object> params, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pagination<OrderInfo> getPagination(Map<String, Object> params, Page page, Sort... sorts) {

		return orderInfoDao.getPagination(params, page, sorts);
	}

	// @Override
	// public int addOrder(OrderInfo order) {
	// String jsonData=JsonUtil.toJson(order);
	// String resultStr = "";
	// try {
	// jsonData= URLEncoder.encode(jsonData, "UTF-8");
	//
	// URL url = new
	// URL("http://192.168.10.87:8080/yimi/pda/saveOrderInfoNew.action?jsonData="+jsonData);
	// //URL url = new URL(protocol, host, file)
	// HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	// connection.setRequestMethod("POST");
	// connection.setRequestProperty("Content-type", "text/html");
	// connection.setRequestProperty("Accept-Charset", "UTF-8");
	// connection.setRequestProperty("contentType", "UTF-8");
	// connection.connect();
	// BufferedReader reader = new BufferedReader(new
	// InputStreamReader(connection.getInputStream(),"UTF-8"));
	//
	// String lines;
	//
	// while ((lines = reader.readLine()) != null) {
	// resultStr+=lines;
	// }
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch(IOException e){
	// e.printStackTrace();
	// }
	// System.out.println(resultStr);
	// OrderBack backDetail=JsonUtil.parseJson(resultStr, OrderBack.class);
	// int flag=0;
	// if(backDetail.isSuccess()){
	// flag =orderInfoDao.insert(backDetail.getOrderInfoObj());
	// }
	//
	// return flag;
	// }
	@Override
	public Map<String, Object> addOrder(OrderBase order, String userId) {
		String jsonDate = JsonUtil.toJson(order);
		String resultStr = "";
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.add("jsonData", jsonDate);
		System.out.println(SystemParameterUtil.getValue("interface.ymdd"));
		resultStr = HttpUtil.sendPost(SystemParameterUtil.getValue("interface.ymdd") + "/pda/saveOrderInfoNew.action",
				param, String.class);
//		resultStr = HttpUtil.sendPost("http://192.168.50.33:8080/yimi" + "/pda/saveOrderInfoNew.action", param,
//				String.class);
		String createTm = resultStr.substring(resultStr.indexOf("createTm") + 11, resultStr.indexOf("createTm") + 30)
				.replace("T", " ");
		String sendTm = resultStr.substring(resultStr.indexOf("sendTm") + 9, resultStr.indexOf("sendTm") + 28)
				.replace("T", " ");
		OrderBack backDetail = JsonUtil.parseJson(resultStr, OrderBack.class);
		int flag = 0;
		if (backDetail.isSuccess()) {
			OrderBase base = backDetail.getOrderInfoObj();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				base.setCreateTm(sdf.parse(createTm));
				base.setSendTm(sdf.parse(sendTm));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			OrderInfo info = new OrderInfo();
			BeanWrapper.copyProperties(base, info);
			info.setUserId(userId);
			flag = orderInfoDao.insert(info);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("msg", backDetail.getError());
		return map;

		// TODO 测试代码
		// int flag = 0;
		//
		// OrderInfo info = new OrderInfo();
		// info.setUserId(userId);
		// info.setOrderId(UUID.randomUUID().toString());
		// info.setOrderNo(UUID.randomUUID().toString());
		// info.setConsignorName("测试用户");
		// info.setConsignorPhone("13122552288");
		// info.setConsignorCompName("测试公司");
		// info.setConsignorAddr("测试地址");
		// info.setAddresseeName("测试用户2");
		// info.setAddresseePhone("13122552299");
		// info.setAddresseeAddr("测试地址2");
		// info.setSendWay("S1");
		// info.setPaymentTypeCode("1");
		// info.setGoodsName("测试物品");
		// info.setCollectionFee(0.0d);
		// info.setValuationFee(0.0d);
		// info.setCreateTm(new Date());
		// info.setSendTm(new Date());
		// info.setDeptCode("CS001");
		// info.setOrderState("1");
		// info.setBankAccount("");
		// info.setBankType("0");
		// info.setExpressFee(0.0d);
		// info.setWeightQty(1d);
		// info.setVolume(0.0d);
		// info.setDestZoneCode("CD01");
		// info.setQuantity(1);
		// info.setSrcDistCode("CS1102");
		// info.setCardNumber("");
		// info.setCardName("");
		// info.setPackingNumber("muxiang-1");
		// info.setDelivery("1");
		// info.setDealFlg("0");
		// info.setOrderChannel("1");
		// flag = orderInfoDao.insert(info);
		//
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("flag", flag);
		// map.put("msg", "");
		// return map;

	}

	@Override
	public int deleteOrder(Map<String, Object> params) {

		return orderInfoDao.deleteOrder(params);
	}
}
