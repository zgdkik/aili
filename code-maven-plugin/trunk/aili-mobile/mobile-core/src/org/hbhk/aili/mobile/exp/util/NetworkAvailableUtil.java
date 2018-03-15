package org.hbhk.aili.mobile.exp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkAvailableUtil {

	/**
	 * ��������Ƿ����
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean  netFlag = false;
		if (connectivity == null) {
			netFlag = false;
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					netFlag= true;
				}
			}
			// if (info != null) {
			// for (int i = 0; i < info.length; i++) {
			// System.out.println("state:"+info[0].getState());
			// if (info[0].getState() == NetworkInfo.State.CONNECTED) {
			// return true;
			// }
			// }
			// }
		}
		return netFlag;
	}

	public static void networkMsg(Activity activity) {
		AlertDialog.Builder builders = new AlertDialog.Builder(activity);
		builders.setTitle("�����쳣");
		builders.setMessage("��Ǹ,��ʱ�޷������������");
		CharSequence string2 = "ȷ��";
		builders.setPositiveButton(string2,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ExitAppUtil.getInstance().exit();
					}
				});

		builders.show();
	}

}
