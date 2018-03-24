package com.yimidida.ows.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yimidida.ows.common.share.entity.DictValueEntity;
import com.yimidida.ows.common.share.vo.ComplaintsTypeVo;
import com.yimidida.ymdp.core.share.ex.BusinessException;

public class Constants {
	// 是
	public static Integer Y = 1;
	// 否
	public static Integer N = 0;
	// 数据字典(业务组织，行政组织)根节点code
	public static String ROOT_CODE = "0";
	// 行政组织叶子节点标示
	public static String DISTRICT_LEAF_LEVEL = "3";
	// 操作人系统
	public static String OPER_SYS = "system";

	public static void validateTime(Date beginTime, Date endTime,
			int intervalDays) {
		if (beginTime == null || beginTime == null || endTime == null
				|| endTime == null) {
			throw new BusinessException("查询时间不能为空");
		}
		if (endTime.getTime() - beginTime.getTime() < 0
				|| endTime.getTime() - beginTime.getTime() > 86400000L * intervalDays) {
			throw new BusinessException("查询时间不符合范围,0至" + intervalDays + "天");
		}
	}

	// 1-现结,2-代收扣运,3-月结
	public static enum BizType {
		DISTRICT_CARGO("区域件", String.valueOf(1)), UNION_CARGO("联运件", String
				.valueOf(2));
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private BizType(String name, String code) {
			this.name = name;
			this.code = code;
		}

		// 普通方法
		public static String getName(String code) {
			for (BizType c : BizType.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// 账户类型:1-平台结算账户 2-干线结算账户 3-企业结算账户
	public static enum AccountType {
		PLATE_SETTLE_ACCOUNT("平台结算账户", String.valueOf(1)), TRANK_SETTLE_ACCOUNT(
				"干线结算账户", String.valueOf(2)), ENTER_SETTLE_ACCOUNT("企业结算账户",
				String.valueOf(3));
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private AccountType(String name, String code) {
			this.name = name;
			this.code = code;
		}

		// 普通方法
		public static String getName(String code) {
			for (AccountType c : AccountType.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// 付款类型
	public static enum GoodsChargeOperateType {
		NO_OPERATION("无操作", String.valueOf(0)), NO_OPERATIO("寄付", String
				.valueOf(1)), NO_OPERATI("到付", String.valueOf(2)), CANCEL_LOAN(
				"取消发款", String.valueOf(10)), FROZEN_LOAN("冻结", String
				.valueOf(11)), REGISTER_LOAN("登记", String.valueOf(12)), OVER_DAYS(
				"超期", String.valueOf(14)), GOODS_CHARGE_DEL("代收款删除", String
				.valueOf(15)), GOODS_CHARGE_LOCK("代收款取消锁定", String.valueOf(16)), UN_APPLY(
				"未申请", String.valueOf(17)), RETURN_BILL_APPLY("回款申请", String
				.valueOf(18)), RETURN_BILL_DENY("回款驳回", String.valueOf(19)), RETURN_BILL_AUDIT(
				"回款审核", String.valueOf(20)), TRANS_BILL_CONFIRM("转账确认", String
				.valueOf(40)), FROZENED("解冻", String.valueOf(41)), REGISTER_CANCEL(
				"取消登记", String.valueOf(42)), FORMAL_LOSS("正式挂失", String
				.valueOf(43)), OVER_DAYS_REQUEST("超期申请", String.valueOf(44)), REGISTER_AUDIT(
				"登记审核", String.valueOf(72)), CANCEL_LOSS("解挂失", String
				.valueOf(73)), OVER_DAYS_AUDIT("超期审核", String.valueOf(74));
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private GoodsChargeOperateType(String name, String code) {
			this.name = name;
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		// 普通方法
		public static String getName(String code) {
			for (GoodsChargeOperateType c : GoodsChargeOperateType.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}
	}

	/**
	 * 获取对应的代收货款操作类型
	 * 
	 * @author IT-000036-zhangxingwang
	 * @date 2016-4-15 19:15:31
	 * @return
	 */
	public static List<DictValueEntity> getGoodsChargeOperators() {
		List<DictValueEntity> dictValueList = new ArrayList<DictValueEntity>();
		for (GoodsChargeOperateType c : GoodsChargeOperateType.values()) {
			DictValueEntity dictValue = new DictValueEntity();
			dictValue.setKey(c.getCode());
			dictValue.setValue(c.getName());
			dictValueList.add(dictValue);
		}
		return dictValueList;
	}

	// 投诉类型
	public static enum TypeParentCode {
		OVER_DAYS_AUDIT("--全部--", null), NO_OPERA("延误", "1"), NO_OPERATIO("遗失",
				"2"), NO_OPERATI("破损", "3"), CANCEL_LOAN("短少", "4"), FROZEN_LOAN(
				"污染", "5"), REGISTER_LOAN("回单问题", "6"), OVER_DAYS("异常违规", "7");

		private String code;
		private String name;

		// 构造方法
		private TypeParentCode(String name, String code) {
			this.name = name;
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		// 普通方法
		public static String getName(String code) {
			for (TypeParentCode c : TypeParentCode.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}
	}

	// 投诉类别
	public static enum TypeCode {
		OVER_DAYS_AUDIT("110", "分拨延误"), NO_OPERA("111", "网点延误"), NO_OPERATIO(
				"112", "干线延误"), NO_OPERATI("113", "发件遗失"), CANCEL_LOAN("114",
				"中转遗失"), FROZEN_LOAN("115", "派送遗失"), REGISTER_LOAN("116",
				"多方遗失"), OVER_DAYS("117", "分拨破损"), GOODS_CHARGE_DEL("118",
				"网点破损"), GOODS_CHARGE_LOCK("119", "多方破损"), UN_APPLY("120",
				"分拨短少"), RETURN_BILL_APPLY("121", "网点短少"), RETURN_BILL_DENY(
				"122", "多方短少"), RETURN_BILL_AUDIT("123", "分拨污染"), TRANS_BILL_CONFIRM(
				"124", "网点污染"), FROZENED("125", "多方污染"), ON_CAR("126", "回单延误"), TRANK_SETTLE_ACCOUNT(
				"127", "回单遗失"), ENTER_SETTLE_ACCOUNT("128", "无效回单"), INIT_STATE(
				"129", "禁运品"), REDUCE_INIT("130", "差重差方"), CANCEL_INIT("131",
				"乱收费"), UN_PROCESS("132", "虚假信息");
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private TypeCode(String code, String name) {
			this.name = name;
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		// 普通方法
		public static String getName(String code) {
			for (TypeCode c : TypeCode.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}
	}

	// 增值类型
	public static enum AppreciationType {
		OVER_DAYS_AUDIT("无操作", "SF00"), NO_OPERA("运费", "SF01"), NO_OPERATIO(
				"代收款", "SF02"), NO_OPERATI("保价", "SF03"), CANCEL_LOAN("签回单",
				"SF04"), FROZEN_LOAN("包装", "SF05"), REGISTER_LOAN("转寄", "SF06"), OVER_DAYS(
				"等通知", "SF07"), GOODS_CHARGE_DEL("派送费", "SF08"), GOODS_CHARGE_LOCK(
				"垫付代收", "SF09"), UN_APPLY("垫付运费", "SF10"), RETURN_BILL_APPLY(
				"仓管费", "SF11"), RETURN_BILL_DENY("其它费", "SF12"), RETURN_BILL_AUDIT(
				"原返费", "SF13"), TRANS_BILL_CONFIRM("代收货款服务费", "SF14"), FROZENED(
				"叉车费", "SF15");
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private AppreciationType(String name, String code) {
			this.name = name;
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		// 普通方法
		public static String getName(String code) {
			for (AppreciationType c : AppreciationType.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}
	}

	/**
	 * 获取对应的增值类型
	 * 
	 * @author IT-000036-zhangxingwang
	 * @date 2016-4-15 19:15:31
	 * @return
	 */
	public static List<DictValueEntity> getGoodsChargeSellets() {
		List<DictValueEntity> dictValueList = new ArrayList<DictValueEntity>();
		for (AppreciationType c : AppreciationType.values()) {
			DictValueEntity dictValue = new DictValueEntity();
			dictValue.setKey(c.getCode());
			dictValue.setValue(c.getName());
			dictValueList.add(dictValue);
		}
		return dictValueList;
	}

	// 操作类型:30-装车,32-卸车,5-派件交款(签收)
	// {"装车","30"}, {"卸车","32"}, {"派件交款","5"}
	public static enum ExceptOperType {
		ON_CAR("装车", String.valueOf(30)), TRANK_SETTLE_ACCOUNT("卸车", String
				.valueOf(32)), ENTER_SETTLE_ACCOUNT("派件交款", String.valueOf(5));
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private ExceptOperType(String name, String code) {
			this.name = name;
			this.code = code;
		}

		// 普通方法
		public static String getName(String code) {
			for (ExceptOperType c : ExceptOperType.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// 处理状态:0-初始状态,1-扣成本,2-取消扣成本
	// {"初始状态","0"}, {"扣成本","1"}, {"取消扣成本","2"}
	public static enum ExceptStateType {
		INIT_STATE("初始状态", String.valueOf(0)), REDUCE_INIT("扣成本", String
				.valueOf(1)), CANCEL_INIT("取消扣成本", String.valueOf(2));
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private ExceptStateType(String name, String code) {
			this.name = name;
			this.code = code;
		}

		// 普通方法
		public static String getName(String code) {
			for (ExceptStateType c : ExceptStateType.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// 处理状态:0-未处理 1-待处理 2-处理中 3-已处理 4-撤销/删除 5-总部审核完成 6-待总部审核（仅显示）
	public static enum AbnormalType {
		UN_PROCESS("未处理 ", String.valueOf(0)), STAY_PROCESS("待处理", String
				.valueOf(1)), PROCESS_ING("处理中", String.valueOf(2)), PROCESSED(
				"已处理", String.valueOf(3)), DEPRA_DEL("撤销/删除", String.valueOf(4)), PT_AUDIT_PROCESSED(
				"撤销/删除", String.valueOf(5)), PT_AUDIT_PROCESSING("待总部审核",
				String.valueOf(6));
		// 成员变量
		private String code;
		private String name;

		// 构造方法
		private AbnormalType(String name, String code) {
			this.name = name;
			this.code = code;
		}

		// 普通方法
		public static String getName(String code) {
			for (AbnormalType c : AbnormalType.values()) {
				if (c.getCode() != null && c.getCode().equals(code)) {
					return c.name;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static List<ComplaintsTypeVo> getComplaintsType() {
		List<ComplaintsTypeVo> complaintsTypes = new ArrayList<ComplaintsTypeVo>();
		ComplaintsTypeVo complaintsType_1 = new ComplaintsTypeVo("1", null,
				"延误");
		ComplaintsTypeVo complaintsType_2 = new ComplaintsTypeVo("2", null,
				"遗失");
		ComplaintsTypeVo complaintsType_3 = new ComplaintsTypeVo("3", null,
				"破损");
		ComplaintsTypeVo complaintsType_4 = new ComplaintsTypeVo("4", null,
				"短少");
		ComplaintsTypeVo complaintsType_5 = new ComplaintsTypeVo("5", null,
				"污染");
		ComplaintsTypeVo complaintsType_6 = new ComplaintsTypeVo("6", null,
				"回单问题");
		ComplaintsTypeVo complaintsType_7 = new ComplaintsTypeVo("7", null,
				"异常违规");

		ComplaintsTypeVo complaintsType_8 = new ComplaintsTypeVo("110", "1",
				"分拨延误");
		ComplaintsTypeVo complaintsType_9 = new ComplaintsTypeVo("111", "1",
				"网点延误");
		ComplaintsTypeVo complaintsType_10 = new ComplaintsTypeVo("112", "1",
				"干线延误");

		ComplaintsTypeVo complaintsType_11 = new ComplaintsTypeVo("113", "2",
				"发件遗失");
		ComplaintsTypeVo complaintsType_12 = new ComplaintsTypeVo("114", "2",
				"中转遗失");
		ComplaintsTypeVo complaintsType_13 = new ComplaintsTypeVo("115", "2",
				"派送遗失");
		ComplaintsTypeVo complaintsType_14 = new ComplaintsTypeVo("116", "2",
				"多方遗失");

		ComplaintsTypeVo complaintsType_15 = new ComplaintsTypeVo("117", "3",
				"分拨破损");
		ComplaintsTypeVo complaintsType_16 = new ComplaintsTypeVo("118", "3",
				"网点破损");
		ComplaintsTypeVo complaintsType_17 = new ComplaintsTypeVo("119", "3",
				"多方破损");

		ComplaintsTypeVo complaintsType_18 = new ComplaintsTypeVo("120", "4",
				"分拨短少");
		ComplaintsTypeVo complaintsType_19 = new ComplaintsTypeVo("121", "4",
				"网点短少");
		ComplaintsTypeVo complaintsType_20 = new ComplaintsTypeVo("122", "4",
				"多方短少");

		ComplaintsTypeVo complaintsType_21 = new ComplaintsTypeVo("123", "5",
				"分拨污染");
		ComplaintsTypeVo complaintsType_22 = new ComplaintsTypeVo("124", "5",
				"网点污染");
		ComplaintsTypeVo complaintsType_23 = new ComplaintsTypeVo("125", "5",
				"多方污染");

		ComplaintsTypeVo complaintsType_24 = new ComplaintsTypeVo("126", "6",
				"回单延误");
		ComplaintsTypeVo complaintsType_25 = new ComplaintsTypeVo("127", "6",
				"回单遗失");
		ComplaintsTypeVo complaintsType_26 = new ComplaintsTypeVo("128", "6",
				"无效回单");

		ComplaintsTypeVo complaintsType_27 = new ComplaintsTypeVo("129", "7",
				"禁运品");
		ComplaintsTypeVo complaintsType_28 = new ComplaintsTypeVo("130", "7",
				"差重差方");
		ComplaintsTypeVo complaintsType_29 = new ComplaintsTypeVo("131", "7",
				"乱收费");
		ComplaintsTypeVo complaintsType_30 = new ComplaintsTypeVo("132", "7",
				"虚假信息");

		complaintsTypes.add(complaintsType_30);
		complaintsTypes.add(complaintsType_29);
		complaintsTypes.add(complaintsType_28);
		complaintsTypes.add(complaintsType_27);
		complaintsTypes.add(complaintsType_26);
		complaintsTypes.add(complaintsType_25);
		complaintsTypes.add(complaintsType_24);
		complaintsTypes.add(complaintsType_23);
		complaintsTypes.add(complaintsType_22);
		complaintsTypes.add(complaintsType_21);
		complaintsTypes.add(complaintsType_20);
		complaintsTypes.add(complaintsType_19);
		complaintsTypes.add(complaintsType_18);
		complaintsTypes.add(complaintsType_17);
		complaintsTypes.add(complaintsType_16);
		complaintsTypes.add(complaintsType_15);
		complaintsTypes.add(complaintsType_14);
		complaintsTypes.add(complaintsType_13);
		complaintsTypes.add(complaintsType_12);
		complaintsTypes.add(complaintsType_11);
		complaintsTypes.add(complaintsType_10);
		complaintsTypes.add(complaintsType_9);
		complaintsTypes.add(complaintsType_8);
		complaintsTypes.add(complaintsType_7);
		complaintsTypes.add(complaintsType_6);
		complaintsTypes.add(complaintsType_5);
		complaintsTypes.add(complaintsType_4);
		complaintsTypes.add(complaintsType_3);
		complaintsTypes.add(complaintsType_2);
		complaintsTypes.add(complaintsType_1);
		return complaintsTypes;
	}

	public static String getTypeParentCode(String typeCode) {
		String typeparentcode = "";
		List<ComplaintsTypeVo> complaintsTypes = Constants.getComplaintsType();
		for (int i = 0; i < complaintsTypes.size(); i++) {
			if (typeCode.equals(complaintsTypes.get(i).getId())) {
				String pid = complaintsTypes.get(i).getPid();
				if (pid == null) {
					typeparentcode = "";
				} else {
					for (int j = 0; j < complaintsTypes.size(); j++) {
						if (pid.equals(complaintsTypes.get(j).getId())) {
							typeparentcode = complaintsTypes.get(j).getId();
						}
					}
				}
			}
		}
		return typeparentcode;
	}
}
