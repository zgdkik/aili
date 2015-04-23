package org.hbhk.aili.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailySalesFactory {
	private static DailySales[] data = {
			new DailySales(" 货号 1", " 物品１ ", 1, 1000, getDailyZoom()),
			new DailySales(" 货号 2", " 物品 2", 2, 2000, getDailyZoom()),
			new DailySales(" 货号 3", " 物品 3", 3, 3000, getDailyZoom()),
			new DailySales(" 货号 4", " 物品 4", 4, 4000, getDailyZoom()),
			new DailySales(" 货号 5", " 物品 5", 5, 5000, getDailyZoom()),
			new DailySales(" 货号 6", " 物品 6", 6, 6000, getDailyZoom()),
			new DailySales(" 货号 7", " 物品 7", 7, 7000, getDailyZoom()),
			new DailySales(" 货号 8", " 物品 8", 8, 8000, getDailyZoom()),
			new DailySales(" 货号 9", " 物品 9", 9, 9000, getDailyZoom()),
			new DailySales(" 货号 10", " 物品 10", 10, 10000, getDailyZoom()) };

	public static Object[] getBeanArray() {
		return data;
	}

	public static List<DailySales> getBeanCollection() {
		return Arrays.asList(data);
	}

	public static List<DailyZoom> getDailyZoom() {
		List<DailyZoom> dailyZooms = new ArrayList<DailyZoom>();
		dailyZooms.add(new DailyZoom("厦门", new BigDecimal(123.00)));
		dailyZooms.add(new DailyZoom("南昌", new BigDecimal(456.00)));
		return dailyZooms;
	}
}
