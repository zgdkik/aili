package com.deppon.foss.module.example.server.action;

import java.util.List;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.module.example.server.service.INetworkAnalyseService;
import com.deppon.foss.module.example.shared.domain.Coordinate;
import com.deppon.foss.module.example.shared.domain.NearNetwork;
/**
 * 网点查询Action
 * @author 129903-阮正华
 *
 */
public class NetworkAnalyseAction extends AbstractAction {

	private static final long serialVersionUID = -5634319512998163176L;
	private INetworkAnalyseService networkAnalyseService;
	private Coordinate coordinate;
	private double distance;
	private List<NearNetwork> nearNetworkList;

	public String queryNetworksByCoordinate(){
		nearNetworkList = networkAnalyseService.findNearNetworksByCoordinate(coordinate, distance, limit);
		return SUCCESS;
	}

	public void setNetworkAnalyseService(
			INetworkAnalyseService networkAnalyseService) {
		this.networkAnalyseService = networkAnalyseService;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public List<NearNetwork> getNearNetworkList() {
		return nearNetworkList;
	}

	public void setNearNetworkList(List<NearNetwork> nearNetworkList) {
		this.nearNetworkList = nearNetworkList;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
