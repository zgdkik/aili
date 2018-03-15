package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;

public class test {
	public static void main(String[] args) throws ParseException {
		Map<Object, Object> parm = new HashMap<Object, Object>();
		parm.put("url", "10.174.107.27");
		parm.put("port", 27017);
		parm.put("dataBase", "TruckNo1_Lbs");
		parm.put("tableName", "20151106");
		parm.put("UserID", "");
		parm.put("drivingStart", "");
		parm.put("drivingEnd", "");
		SearchList s = new SearchList();
		List<TrackOrdersHistoricalDTO> list = s.searchTrackOrdersHistorical(parm);
		for (TrackOrdersHistoricalDTO trackOrdersHistoricalDTO : list) {
			System.out.println(trackOrdersHistoricalDTO.getUserID());
			System.out.println(trackOrdersHistoricalDTO.getCity());
			System.out.println(trackOrdersHistoricalDTO.getLatitude());
			System.out.println(trackOrdersHistoricalDTO.getLongitude());
		}
	}
}
