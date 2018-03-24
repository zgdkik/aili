package com.yimidida.ows.common.share.conts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class ServiceFeeType implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 全部费用类型列表
	 */
	private static List<ServiceFeeType> allList = new ArrayList<ServiceFeeType>();
	//SF01-运费,SF02-代收款,SF03-保价,SF04-签回单(付),SF05-包装,F06-转寄,SF07-等通知,SF08-派送费(上),SF09-垫付代收,SF10-垫付运费,SF11-仓管费,SF12-其它费,SF13-原返费,SF14-叉车费
	public static final ServiceFeeType EXPRESS_FEE = new ServiceFeeType("SF01", "运费"/*Messages.getString("servicetype.expressFee")*/, null);
	public static final ServiceFeeType GOODS_CHARGE_AGENT = new ServiceFeeType("SF02", "代收款"/*Messages.getString("servicetype.goodsChargeAgent")*/, "1");
	public static final ServiceFeeType INSURANCE_FEE = new ServiceFeeType("SF03", "保价"/*Messages.getString("servicetype.insurance")*/, "2");
	public static final ServiceFeeType SIGNED_BACK_FEE = new ServiceFeeType("SF04", "签回单"/*Messages.getString("servicetype.waitNotify")*/, "3");
	public static final ServiceFeeType PACKAGE_FEE = new ServiceFeeType("SF05", "包装"/*Messages.getString("servicetype.signback")*/, "5");
	public static final ServiceFeeType FORWARD_FEE = new ServiceFeeType("SF06", "转寄"/*Messages.getString("servicetype.package")*/, "6");
	public static final ServiceFeeType WAIT_NOTIFY_FEE = new ServiceFeeType("SF07", "等通知"/*Messages.getString("servicetype.debours")*/, "8");
	public static final ServiceFeeType DELIVER_FEE = new ServiceFeeType("SF08", "派送费"/*Messages.getString("servicetype.store")*/, "4");
	public static final ServiceFeeType DEBOURS_GOODS_FEE = new ServiceFeeType("SF09", "垫付代收"/*Messages.getString("servicetype.deliver")*/, null);
	public static final ServiceFeeType DEBOURS_WAY_FEE = new ServiceFeeType("SF10", "垫付运费"/*Messages.getString("servicetype.forward")*/, "7");
	public static final ServiceFeeType STORE_FEE = new ServiceFeeType("SF11", "仓管费"/*Messages.getString("servicetype.otherFee")*/, null);
	public static final ServiceFeeType OTHER_FEE = new ServiceFeeType("SF12", "其它费"/*Messages.getString("servicetype.otherFee")*/, null);
	public static final ServiceFeeType BACK_CARGO_FEE = new ServiceFeeType("SF13", "原返费"/*Messages.getString("servicetype.otherFee")*/, null);
	public static final ServiceFeeType FORKLFIT_FEE = new  ServiceFeeType("SF14","叉车费", "9");
	public static final ServiceFeeType KICKBACKS_FEE = new  ServiceFeeType("SF15","回扣", null);
	public static final ServiceFeeType ZHIDAN_FEE = new  ServiceFeeType("SF16","制单", null);
	

	public static final ServiceFeeType LOST_FEE = new  ServiceFeeType("SF24","挂失费", null);
	
	/**
	 * 类型代码
	 */
	private String typeCode;
	
	/**
	 * 类型名称
	 */
	private String typeName;
	
	/**
	 * 快捷键定义
	 */
	private String shortcutKey;

	/**
	 * 初始化函数，因为费用类型有限，所以不能被任意初始化
	 * 
	 * @param id
	 * @param name
	 */
	private ServiceFeeType(String code, String name, String shortcutKey) {
		this.typeCode = code;
		this.typeName = name;
		this.shortcutKey = shortcutKey; 
		allList.add(this);
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 得到全部费用类型列表
	 * 
	 * @return
	 */
	public static List<ServiceFeeType> getAll() {
		return allList;
	}

	/**
	 * 根据费用类型代码得到类型实体
	 * 
	 * @param code
	 * @return
	 */
	public static ServiceFeeType getTypeByCode(String code) {
		try {
			for (int i = 0; i < allList.size(); i++) {
				ServiceFeeType type = allList.get(i);
				if (type.typeCode.equals(code)) {
					return type;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 通过快捷键获取增值服务类型
	 * @param shortcutKey
	 * @return
	 */
	public static ServiceFeeType getTypeByShortcutKey(String shortcutKey) {
		try {
			for (int i = 0; i < allList.size(); i++) {
				ServiceFeeType type = allList.get(i);
				if (type.shortcutKey != null && type.shortcutKey.equals(shortcutKey)) {
					return type;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 根据费用代码获取费用类型名称
	 * 
	 * @param code
	 * @return
	 */
	public static String getFeeTypeName(String code) {
		for (int i = 0; i < allList.size(); i++) {
			ServiceFeeType type = allList.get(i);
			if (type.typeCode.equals(code)) {
				return type.getTypeName();
			}
		}
		return "";
	}

	/**
	 * 判断两种费用类型是否相同
	 * 
	 * @param another
	 * @return
	 */
	public boolean equals(ServiceFeeType another) {
		return another.typeCode.equals(this.typeCode);
	}
	
	/**
	 * 获取快捷键
	 * @return
	 */
	public String getShortcutKey() {
		return shortcutKey;
	}
}