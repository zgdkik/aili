package org.hbhk.aili.mobile.exp.service;

import java.util.List;

import org.hbhk.aili.mobile.exp.util.BaiDuMapUtil;
import org.hbhk.aili.mobile.exp.util.GPSUtil;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.baidu.mapapi.LocationListener;

public class GPSService extends Service {

	private GPSBinder gpsBinder = new GPSBinder();

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("info", "gpsBinder");
		return gpsBinder;
	}

	@Override
	public void onCreate() {
		if (hasGPSDevice(this)) {
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Location location = GPSUtil.getGPSLatLng(locationManager);
			BaiDuMapUtil.getInstance(this).startLocationUpdate(
					new MyLocationListener());
			Log.i("info", "onCreate");
			super.onCreate();

		}
	}

	@Override
	public void onDestroy() {
		Log.i("info", "onDestroy");
		super.onDestroy();
	}

	public class GPSBinder extends Binder {
		public GPSService getService() {
			return GPSService.this;
		}
	}

	class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location arg0) {
			double jindu1 = arg0.getLatitude();
			double weidu1 = arg0.getLongitude();
			System.out.println("经度" + jindu1 + ",纬度" + weidu1);
		}

	}

	public boolean hasGPSDevice(Context context) {
		final LocationManager mgr = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (mgr == null) {
			return false;
		}

		final List<String> providers = mgr.getAllProviders();
		if (providers == null) {
			return false;
		}

		return providers.contains(LocationManager.GPS_PROVIDER);
	}

}
