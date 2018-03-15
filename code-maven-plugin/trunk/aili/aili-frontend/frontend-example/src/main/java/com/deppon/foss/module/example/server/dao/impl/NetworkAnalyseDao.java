package com.deppon.foss.module.example.server.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import com.deppon.foss.framework.server.components.dataaccess.mongodb.MongoSupport;
import com.deppon.foss.module.example.server.dao.INetworkAnalyseDao;
import com.deppon.foss.module.example.shared.domain.Coordinate;
import com.deppon.foss.module.example.shared.domain.Network;
/**
 * mongodb数据访问层
 * @author 129903-阮正华
 *
 */
public class NetworkAnalyseDao extends MongoSupport implements INetworkAnalyseDao{

	/**
	 * 查询当前网点附近的所有其他网点信息和相距的距离
	 * 由近到远排序
	 * @param name
	 * @param distance （单位 KILOMETERS）
	 * @return
	 */
	public List<GeoResult<Network>> findNearNetworksByName(String name , double distance){
		return findNearNetworksByName(name,distance,-1);
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
		Date startDate = new Date();
		Network curentNetwork = findCurrentNetworksByName(name);
		double longitude = curentNetwork.getCoordinate().getLongitude();//当前网点的经度
		double latitude = curentNetwork.getCoordinate().getLatitude();//当前网点的维度
		Point currentPoint = new Point(longitude,latitude);//当前网点的地理位置
		
		NearQuery nearQuery = NearQuery.near(currentPoint,Metrics.KILOMETERS).maxDistance(distance);
		if(recordNum > 0 ){
			nearQuery.num(recordNum);
		}
		if(recordNum==0){
			return null;
		}
		GeoResults<Network> geoResults = mongoTemplate.geoNear(nearQuery, Network.class , "network");
		List<GeoResult<Network>> geoResultList= geoResults.getContent();
		Date endDate = new Date();
		System.out.println("time:"+(endDate.getTime()-startDate.getTime()));
		return geoResultList;
	}
	/**
	 * 查询当前网点信息
	 * @param name
	 * @return
	 */
	public Network findCurrentNetworksByName(String name){
		Query query = new Query(where("name").is(name));
		return mongoTemplate.findOne(query, Network.class);
	}
	/**
	 * 通过当前客户的地理位置查询附近的网点
	 * @param currentPoint
	 * @return
	 */
	@Override
	public List<GeoResult<Network>> findNearNetworksByPoint(Point currentPoint , double distance , int recordNum) {
		NearQuery nearQuery = NearQuery.near(currentPoint,Metrics.KILOMETERS).maxDistance(distance);
		if(recordNum > 0 ){
			nearQuery.num(recordNum);
		}
		if(recordNum==0){
			return null;
		}
		GeoResults<Network> geoResults = mongoTemplate.geoNear(nearQuery, Network.class , "network");
		List<GeoResult<Network>> geoResultList= geoResults.getContent();
		return geoResultList;
	}
	/**
	 * 通过当前客户的经纬度查询附近的网点
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@Override
	public List<GeoResult<Network>> findNearNetworksByCoordinate(Coordinate coordinate , double distance , int recordNum) {
		Point currentPoint = new Point(coordinate.getLongitude(),coordinate.getLatitude());//当前网点的地理位置
		return findNearNetworksByPoint(currentPoint , distance , recordNum);
	}
	
}
