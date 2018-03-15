package com.feisuo.yhhc.module.bseservice_gis.api.server.service;

/**
* 判断一个坐标点是否在给定的任意多边形内（不规则）的接口 ＜br＞
* @author liuronghui ＜br＞
*/
import java.util.ArrayList;
import java.util.List;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.PointDTO;


public interface IsPointInPolygonService {
	/**
	* 判断一个坐标点是否在给定的任意多边形内（不规则）的接口 ＜br＞
	* @author liuronghui ＜br＞
	* @param PointDTO point给定需要计算的坐标点, 
	* @param ArrayList<PointDTO> PointList 给定需要计算的多边形顶点
	*/	
	 public boolean isPointInPolygon(PointDTO point,  ArrayList<PointDTO> PointList)throws Exception;
	 /**
	  * 判断一个坐标点是否在给定的任意多边形内（不规则）的接口 ＜br＞
	  * @author liuronghui ＜br＞
	  * @param double px 坐标点经度,
	  * @param double py坐标点纬度,
	  * @param ArrayList<Double> polygonXA不规则多边形经度顶点, 
	  * @param ArrayList<Double> polygonYA 不规则多边形纬度顶点
	 */	
	 public boolean isPointInPolygon(double px, double py,List<Double> polygonXA, List<Double> polygonYA)throws Exception;

}
