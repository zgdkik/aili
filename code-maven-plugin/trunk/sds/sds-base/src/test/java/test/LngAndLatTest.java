package test;

import com.feisuo.sds.base.share.util.LngAndLatUtil;
import com.feisuo.sds.base.share.util.LocationUtil;

public class LngAndLatTest {

	public static void main(String[] args) {
		System.out.println(LngAndLatUtil.getLngAndLat("上海市青浦区华徐公路999号"));
		System.out.println(LngAndLatUtil.getLngAndLat("上海市青浦区育才路明珠路路口"));
		double distance = LocationUtil.getDistance(LngAndLatUtil.getLngAndLat("上海市青浦区华徐公路999号").getLat(),
				LngAndLatUtil.getLngAndLat("上海市青浦区华徐公路999号").getLng(),
				LngAndLatUtil.getLngAndLat("上海市青浦区育才路明珠路路口").getLat(),
				LngAndLatUtil.getLngAndLat("上海市青浦区育才路明珠路路口").getLng());
		System.out.println(distance);

	}
}
