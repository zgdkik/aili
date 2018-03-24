package com.yimidida.ows.common.share.conts;

public interface IBarConstants {

	/**
	 * 上门收件 01 --暂时后台没有这个操作码
	 */
	static final String OPER_TYPE_CODE_SMSJ = "01";

	/**
	 * 收件入仓;
	 */
	static final String OPER_TYPE_CODE_SJ = "11";

	/**
	 * 派件出仓;
	 */
	static final String OPER_TYPE_CODE_PJ = "12";

	/**
	 *二程接驳收件
	 */
	static final String OPER_TYPE_CODE_SJJB = "13";
	/**
	 *二程接驳派件
	 */
	static final String OPER_TYPE_CODE_PJJB = "14";

	/**
	 *交收件联
	 */
	static final String OPER_TYPE_CODE_JSJ = "15";

	/**
	 *交派件联
	 */
	static final String OPER_TYPE_CODE_JPJ = "16";

	/**
	 *滞留件入仓
	 */
	static final String OPER_TYPE_CODE_ZLRC = "17";

	/**
	 * 滞留件出仓
	 */
	static final String OPER_TYPE_CODE_ZLCC = "18";

	/**
	 * 收件且装车
	 */
	static final String OPER_TYPE_CODE_SJZC = "19";

	/**
	 * 中转发车
	 */
	static final String OPER_TYPE_CODE_ZZFC = "20";

	/**
	 * 中转到车
	 */
	static final String OPER_TYPE_CODE_ZZDC = "21";

	/**
	 * 中转落货
	 */
	static final String OPER_TYPE_CODE_ZZLH = "22";

	/**
	 * 过车操作
	 */
	static final String OPER_TYPE_CODE_GC = "23";

	/**
	 * 中转滞留
	 */
	static final String OPER_TYPE_CODE_ZZZL = "24";

	/**
	 * 中转批量滞留
	 */
	static final String OPER_TYPE_CODE_ZZPLZL = "25";


	/**
	 * 装件入包
	 */
	static final String OPER_TYPE_CODE_KJZB = "26";

	/**
	 * 整车滞留
	 */
	static final String OPER_TYPE_CODE_SCBLD = "27";

	/**
	 * 快件解包笼袋
	 */
	static final String OPER_TYPE_CODE_KJJBLD = "28";

	/**
	 * 快件装笼
	 */
	static final String OPER_TYPE_CODE_ZJRL = "29";


	/**
	 * 快件装车
	 */
	static final String OPER_TYPE_CODE_KJZC = "30";

	/**
	 * 从车中删除
	 */
	static final String OPER_TYPE_CODE_SCCAR = "31";

	/**
	 * 快件卸车
	 */
	static final String OPER_TYPE_CODE_KJXC = "32";

	/**
	 * 	封车
	 */
	static final String OPER_TYPE_CODE_FC = "33";

	/**
	 * 解封车
	 */
	static final String OPER_TYPE_CODE_JFC = "34";

	/**
	 * 卸车且派件出仓;
	 */
	static final String OPER_TYPE_CODE_XCPJ = "35";

	/**
	 * 卸车操作码
	 */
	final static String UN_CAR_OPER_TYPE = "32";

	/**
	 * 发车操作
	 */
	final static String STARTOFF_CAR_OPER_TYPE = "20";

	/**
	 * 到车操作码
	 */
	final static String ARRIVE_CAR_OPER_TYPE = "21";
	
	/**
	 * 干线到车操作码
	 */
	final static String TRUNK_ARRIVE_CAR_OPER_TYPE = "61";
	
	/**
	 * 干线发车操作码
	 */
	final static String TRUNK_STARTOFF_CAR_OPER_TYPE = "60";

	/**
	 * 手工配载
	 */
	final static String MANUAL_INPUT_TYPE = "2";





	/**库存操作类型*/
//	public final static String DEPT_INVENTORY_OP_ADD = "1";//录单
//	public final static String DEPT_INVENTORY_OP_XC = "2";//卸车
//	public final static String DEPT_INVENTORY_OP_ZC = "3";//装车
//	public final static String DEPT_INVENTORY_OP_YK = "4";//申请移库

	/**库存状态*/
//	状态：1-在库  2-派送  3-已装车  4-在途  5-到达  6-移库 7-签收  8-清库
	public final static String DEPT_INVENTORY_STATUS_ZK = "1";
	public final static String DEPT_INVENTORY_STATUS_PS = "2";
	public final static String DEPT_INVENTORY_STATUS_ZC = "3";
	public final static String DEPT_INVENTORY_STATUS_ZT = "4";
	public final static String DEPT_INVENTORY_STATUS_DD = "5";
	public final static String DEPT_INVENTORY_STATUS_YK = "6";
	public final static String DEPT_INVENTORY_STATUS_QS = "7";
	public final static String DEPT_INVENTORY_STATUS_SDQK = "8";


	/**
	 * 库存类型:0-正常  1-移库  2-清库
	 */
	public final static String DEPT_INVENTORY_OP_NORMAL = "0";
	public final static String DEPT_INVENTORY_OP_MOVE = "1";
	public final static String DEPT_INVENTORY_OP_CLEAR = "2";


	/**
	 * 操作类型 0-删除运单，1-作废运单，2-交款,3-手工到车,4-移库，5-取消移库，6-清库，7-补录，8-其他  9-回单入库
	 * 操作类型： 1—录单 2—装车 3—卸车 4—发车 5—到车 6—签收 7—删单作废 8—取消签收 9—取消装车 10—取消到车 11—移库
	 */
	public final static String DEPT_INVENTORY_OP_DELETE = "0";
	public final static String DEPT_INVENTORY_OP_CANCEL = "1";
	public final static String DEPT_INVENTORY_OP_PAY = "2";
	public final static String DEPT_INVENTORY_OP_TOCAR = "3";
	public final static String DEPT_INVENTORY_OP_MOVEIN = "4";
	public final static String DEPT_INVENTORY_OP_NOTMOVEIN = "5";
	public final static String DEPT_INVENTORY_OP_DELETEMOVEIN = "6";
	public final static String DEPT_INVENTORY_OP_INPUTMOVEIN = "7";
	public final static String DEPT_INVENTORY_OP_OTHER = "8";
	public final static String DEPT_INVENTORY_OP_GATHERED = "9";

	/**入库操作类型*/
	public final static String DEPT_INVENTORY_INOP_ADD = "1";//录单初始化
	public final static String DEPT_INVENTORY_INOP_XC = "2";//巴枪扫描
	public final static String DEPT_INVENTORY_INOP_ZC = "3";//PC操作
	public final static String DEPT_INVENTORY_INOP_YK = "4";//移库接收
	public final static String DEPT_INVENTORY_INOP_BL = "5";//回单入库


	/**
	 * 配载操作状态定义: 0-新建 1-已配货 2-发车  3-到车  4-卸车
	 */
	public final static String STOWAGE_OP_ADD = "0";//新建
	public final static String STOWAGE_OP_ZC = "1";//装车
	public final static String STOWAGE_OP_FC = "2";//发车
	public final static String STOWAGE_OP_DC = "3";//到车
	public final static String STOWAGE_OP_XC = "4";//卸车



	/**
	 * 配载明细录入定义: 配载类型：1-录单初始化  2-手工配载  3-巴枪扫描 4-移库
	 */
	public final static String STOWAGE_DETAILS_INPUT_ZC = "1";
	public final static String STOWAGE_DETAILS_INPUT_SD = "2";
	public final static String STOWAGE_DETAILS_INPUT_XC = "3";
	public final static String STOWAGE_DETAILS_INPUT_YK = "4";


	/**
	 * 操作类型： 1—录单 2—装车 3—卸车 4—发车 5—到车 6—签收 7—删单作废 8—取消签收 9—取消装车 10—取消卸车 11—移库 12-取消发车 13-取消到车
	 */
	public final static String INVENTORY_OP_INPUT_BILL = "1";
	public final static String INVENTORY_OP_LOAD = "2";
	public final static String INVENTORY_OP_UNLOAD = "3";
	public final static String INVENTORY_OP_SEND = "4";
	public final static String INVENTORY_OP_ARRIVED = "5";
	public final static String INVENTORY_OP_SIGN = "6";
	public final static String INVENTORY_OP_DELETE = "7";
	public final static String INVENTORY_OP_CANEL_SIGN = "8";
	public final static String INVENTORY_OP_CANEL_LOAD = "9";
	public final static String INVENTORY_OP_CANEL_UNLOAD = "10";
	public final static String INVENTORY_OP_CANEL_TRANSFER = "11";
	public final static String INVENTORY_OP_CANEL_SEND = "12";
	public final static String INVENTORY_OP_CANEL_ARRIVED = "13";


	/**
	 * 推送任务处理状态:0-未处理,1-待处理,2-正在处理,3-处理成功,4-处理失败,5-找不到记录
	 */
	public final static String CAR_SEND_TASK_STATE_NOT_PROCESS = "0";
	public final static String CAR_SEND_TASK_STATE_WAIT_PROCESS = "1";
	public final static String CAR_SEND_TASK_STATE_BEING_PROCESS = "2";
	public final static String CAR_SEND_TASK_STATE_PROCESS_SUCCESS = "3";
	public final static String CAR_SEND_TASK_STATE_PROCESS_FAIL = "4";
	public final static String CAR_SEND_TASK_STATE_NOT_EXIST = "5";
}
