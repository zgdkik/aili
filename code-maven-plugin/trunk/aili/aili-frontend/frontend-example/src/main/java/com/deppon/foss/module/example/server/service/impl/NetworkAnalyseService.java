package com.deppon.foss.module.example.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.Point;
import com.deppon.foss.module.example.server.dao.INetworkAnalyseDao;
import com.deppon.foss.module.example.server.service.INetworkAnalyseService;
import com.deppon.foss.module.example.shared.domain.Coordinate;
import com.deppon.foss.module.example.shared.domain.NearNetwork;
import com.deppon.foss.module.example.shared.domain.Network;
/**
 * 网点查询服务类
 * @author 129903-阮正华
 *
 */
public class NetworkAnalyseService  implements INetworkAnalyseService{
	
	private INetworkAnalyseDao networkAnalyseDao;

	/**
	 * 查询当前网点附近的所有其他网点信息和相距的距离
	 * 由近到远排序
	 * @param name
	 * @param distance （单位 KILOMETERS）
	 * @return
	 */
	public List<GeoResult<Network>> findNearNetworksByName(String name , double distance){
		return networkAnalyseDao.findNearNetworksByName(name, distance);
	}
	/**
	 * 查询当前网点附近的所有其他网点信息和相距的距离
	 * 由近到远排序
	 * @param name
	 * @param distance （单位 KILOMETERS）
	 * @param recordNum 结果返回的记录数
	 * @return
	 */
	public List<GeoResult<Network>> findNearNetworksByName(String name , double distance , int recordNum){
		return networkAnalyseDao.findNearNetworksByName(name, distance, recordNum);
	}
	/**
	 * 查询当前网点信息
	 * @param name
	 * @return
	 */
	public Network findCurrentNetworksByName(String name){
		return networkAnalyseDao.findCurrentNetworksByName(name);
	}
	/**
	 * 通过当前客户的地理位置查询附近的网点
	 * @param currentPoint
	 * @return
	 */
	@Override
	public List<GeoResult<Network>> findNearNetworksByPoint(Point currentPoint , double distance , int recordNum) {
		return networkAnalyseDao.findNearNetworksByPoint(currentPoint, distance, recordNum);
	}
	/**
	 * 通过当前客户的经纬度查询附近的网点
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@Override
	public List<NearNetwork> findNearNetworksByCoordinate(Coordinate coordinate , double distance , int recordNum) {
		List<GeoResult<Network>> geoResultList = networkAnalyseDao.findNearNetworksByCoordinate(coordinate, distance, recordNum);
		List<NearNetwork> nearNetworkList = new ArrayList<NearNetwork>();
		for(GeoResult<Network> geoResult: geoResultList){
			Network network = geoResult.getContent();
			NearNetwork nearNetwork = new NearNetwork();
			nearNetwork.setId(network.getId());
			nearNetwork.setName(network.getName());
			nearNetwork.setPhone(network.getPhone());
			nearNetwork.setTel(network.getTel());
			nearNetwork.setType(network.getType());
			nearNetwork.setDescription(network.getDescription());
			nearNetwork.setCoordinate(network.getCoordinate());
			nearNetwork.setCode(network.getCode());
			nearNetwork.setAddress(network.getAddress());
			nearNetwork.setNearDistance(geoResult.getDistance().getValue());
			nearNetworkList.add(nearNetwork);
		}
		return nearNetworkList;
	}
	public INetworkAnalyseDao getNetworkAnalyseDao() {
		return networkAnalyseDao;
	}
	public void setNetworkAnalyseDao(INetworkAnalyseDao networkAnalyseDao) {
		this.networkAnalyseDao = networkAnalyseDao;
	}
	
}
