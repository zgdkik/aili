package org.hbhk.aili.mobile.exp.util;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPSUtil {

	public static Location getGPSLatLng(LocationManager locationManager) {
		String provider;
		Location location = null;
		if (locationManager
				.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
				|| locationManager
						.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)) {
			// ����λ�ò�ѯ����
			Criteria criteria = new Criteria();
			// ��ѯ���ȣ���
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			// �Ƿ��ѯ��������
			criteria.setAltitudeRequired(false);
			// �Ƿ��ѯ��λ��:��
			criteria.setBearingRequired(false);
			// �Ƿ����?�ѣ���
			criteria.setCostAllowed(false);
			// ����Ҫ�󣺵�
			criteria.setPowerRequirement(Criteria.POWER_LOW);
			// ��������ʵķ��������provider����2������Ϊtrue˵��,���ֻ��һ��provider����Ч��,�򷵻ص�ǰprovider
			provider = locationManager.getBestProvider(criteria, true);
			// ���δ����λ��Դ����GPS���ý���
			// ��ȡλ��
			location = locationManager.getLastKnownLocation(provider);

		}

		return location;
	}

	// // ��ȡ��ַ��Ϣ
	// private List<Address> getAddressbyGeoPoint(Location location) {
	// List<Address> result = null;
	// // �Ƚ�Locationת��ΪGeoPoint
	// // GeoPoint gp=getGeoByLocation(location);
	// try {
	// if (location != null) {
	// // ��ȡGeocoder��ͨ��Geocoder�Ϳ����õ���ַ��Ϣ
	// Geocoder gc = new Geocoder(this, Locale.getDefault());
	// result = gc.getFromLocation(location.getLatitude(),
	// location.getLongitude(), 1);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return result;
	// }

}
