package org.hbhk.aili.mobile.exp.util;

import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKEvent;
import com.baidu.mapapi.MKGeneralListener;

public class BaiDuMapUtil {
	private static Context mContext;

	// �ٶ�MapAPI�Ĺ�����
	public BMapManager mBMapMan = null;

	public final static String mStrKey = "3CF06A8FF7A8B0803595A843C8A5189730ED0D8D"; // ����key

	private static boolean m_bKeyRight = true; // ��ȨKey��ȷ����֤ͨ��

	private static BaiDuMapUtil instance = null;

	public BaiDuMapUtil(Context context) {
		mContext = context;
		initBaiDuManage();
	}

	public static BaiDuMapUtil getInstance(Context context) {
		if (instance == null) {
			instance = new BaiDuMapUtil(context);
		}
		return instance;
	}

	/** �����¼�������������ͨ�������������Ȩ��֤����� **/
	static class MyGeneralListener implements MKGeneralListener {

		public void onGetNetworkState(int arg0) {
			Toast.makeText(mContext, "��������������", Toast.LENGTH_LONG).show();
		}

		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				Toast.makeText(mContext, "����BMapApiDemoApp.java�ļ�������ȷ����ȨKey��",
						Toast.LENGTH_LONG).show();
				m_bKeyRight = false;
			}
		}

	}

	/**
	 * ��ʼ���ٶȵ�ͼ������
	 */
	public void initBaiDuManage() {
		mBMapMan = new BMapManager(mContext);
		mBMapMan.init(this.mStrKey, new MyGeneralListener());
		mBMapMan.getLocationManager().setNotifyInternal(1000, 1000);
	}

	/**
	 * ��ٰٶȵ�ͼ������
	 */
	public void destoryBaiDuMange() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
	}

	public void setInstanceNull() {
		instance = null;
	}

	/**
	 * ֹͣ��λ���£�
	 */
	public void stopLocationUpdate(LocationListener listener) {
		if (mBMapMan != null) {
			mBMapMan.getLocationManager().removeUpdates(listener);
			mBMapMan.stop();
		}
	}

	/**
	 * ��ʼ��λ���£�
	 */
	public void startLocationUpdate(LocationListener listener) {
		if (mBMapMan == null) {
			initBaiDuManage();
		}
		mBMapMan.start();
		mBMapMan.getLocationManager().requestLocationUpdates(listener);
	}
}
