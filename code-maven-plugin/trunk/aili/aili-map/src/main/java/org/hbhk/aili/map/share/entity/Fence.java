package org.hbhk.aili.map.share.entity;

public class Fence {

	
	private String name ;
	
	private String descp;
	
	
	private String creator;
	//必选，被监控者的entity_name，使用英文逗号”,”分割，至少一个，最多五个。
	private String monitored_persons;
	//必选，观察者的entity_name，使用英文逗号”,”分割，至少一个，最多五个。
	private String observers;
	//必选，一天中的几点几分到 几点几分生效。至少含有一段生效时间，多个时间段使用分号”;”分隔。比如：“0820,0930;1030,1130”
	private String valid_times;
	//必选，标识valid_times是否周期性生效，可以使用如下数值：
	//1：不重复 2：工作日循环 3：周末循环 4：每天循环 5：自定义 当为5时，需要定义valid_days，标识在周几生效。
	private int valid_cycle;
	//当valid_cycle为1时必选，例如：20150908。
	private String valid_date;
	//1到7，分别表示周一到周日，当valid_cycle为5时必选。
	private int valid_days;
	//必选，围栏有两种形状：1代表圆形和2代表多边形。目前只支持圆形。
	private int shape;
	//必选，坐标类型定义如下：
	//1：GPS经纬度 2：国测局经纬度 3：百度经纬度
	private int coord_type;
	
	//shape为1时必选。格式为：经度,纬度。示例：116.4321,38.76623
	private double center;
	//围栏半径 当shape=1时必选。单位：米，取值范围(0,5000]
	private double radius;
	//	可选。1：进入时触发提醒 2：离开时触发提醒 3：进入离开均触发提醒。默认值为3
	private int alarm_condition;
}

