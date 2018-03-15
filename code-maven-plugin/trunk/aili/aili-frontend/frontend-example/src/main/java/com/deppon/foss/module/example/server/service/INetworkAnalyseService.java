package com.deppon.foss.module.example.server.service;

import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.Point;

import com.deppon.foss.module.example.shared.domain.Coordinate;
import com.deppon.foss.module.example.shared.domain.NearNetwork;
import com.deppon.foss.module.example.shared.domain.Network;
/**
 * 网点查询服务接口
 * @author 129903-阮正华
 *
 */
public interface INetworkAnalyseService {

	/**
	 * 查询当前网点附近的所有其他网点信息和相距的距离
	 * 由近到远排序
	 * @param name
	 * @param distance （单位 KILOMETERS）
	 * @return
	 */
	public List<GeoResult<Network>> findNearNetworksByName(String name , double distance);
	/**
	 * 查询当前网点附近的所有其他网点信息和相距的距离
	 * 由近到远排序
	 * @param name
	 * @param distance （单位 KILOMETERS）
	 * @param recordNum 结果返回的记录数
	 * @return
	 */
	public List<GeoResult<Network>> findNearNetworksByName(String name , double distance , int recordNum);
	/**
	 * 查询当前网点信息
	 * @param name
	 * @return
	 */
	public Network findCurrentNetworksByName(String name);
	/**
	 * 通过当前客户的地理位置查询附近的网点
	 * @param currentPoint
	 * @return
	 */
	public List<GeoResult<Network>> findNearNetworksByPoint(Point currentPoint , double distance , int recordNum);
	/**
	 * 通过当前客户的经纬度查询附近的网点
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public List<NearNetwork> findNearNetworksByCoordinate(Coordinate coordinate , double distance , int recordNum);
}
