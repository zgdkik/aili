package org.hbhk.maikkr.core.shared.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hbhk.maikkr.core.shared.pojo.ProvinceInfo;

public class AreaUtils {

	private final static Map<Integer, String> provinces = new TreeMap<Integer, String>();

	static {
		provinces.put(1, "北京");
		provinces.put(2, "上海");
		provinces.put(3, "天津");
		provinces.put(4, "重庆");
		provinces.put(5, "河北");
		provinces.put(6, "山西");
		provinces.put(7, "河南");
		provinces.put(8, "辽宁");
		provinces.put(9, "吉林");
		provinces.put(10, "黑龙江");
		provinces.put(11, "内蒙古");
		provinces.put(12, "江苏");
		provinces.put(13, "山东");
		provinces.put(14, "安徽");
		provinces.put(15, "浙江");
		provinces.put(16, "福建");
		provinces.put(17, "湖北");
		provinces.put(18, "湖南");
		provinces.put(19, "广东");
		provinces.put(20, "广西");
		provinces.put(21, "江西");
		provinces.put(22, "四川");
		provinces.put(23, "海南");
		provinces.put(24, "贵州");
		provinces.put(25, "云南");
		provinces.put(26, "西藏");
		provinces.put(27, "陕西");
		provinces.put(28, "甘肃");
		provinces.put(29, "青海");
		provinces.put(30, "宁夏");
		provinces.put(31, "新疆");
		provinces.put(32, "台湾");
		provinces.put(42, "香港");
		provinces.put(43, "澳门");
		provinces.put(84, "钓鱼岛");
	}

	public static List<ProvinceInfo> getProvinces() {
		List<ProvinceInfo> ps = new ArrayList<ProvinceInfo>();
		Set<Integer> keys = provinces.keySet();
		for (Integer key : keys) {
			ProvinceInfo p = new ProvinceInfo();
			p.setId(String.valueOf(key));
			p.setName(provinces.get(key));
			ps.add(p);
		}
		return ps;

	}
}