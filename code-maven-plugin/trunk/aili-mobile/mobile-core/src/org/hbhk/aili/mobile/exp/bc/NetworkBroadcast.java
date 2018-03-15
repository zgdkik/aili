package org.hbhk.aili.mobile.exp.bc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class NetworkBroadcast extends BroadcastReceiver {
	boolean success = false;

	private static  int count = 0;
	@Override
	public void onReceive(Context context, Intent intent) {

		success = isNetworkAvailable(context);

		if (!success) {
			Toast toast = Toast.makeText(context, "您的网络连接已中断",
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 135);
			toast.show();
			count++;
		} else if(count > 0) {
			Toast toast = Toast.makeText(context, "网络已恢复", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 135);
			toast.show();
		}

	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.i("NetWorkState", "Unavailabel");
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						Log.i("NetWorkState", "Availabel");
						return true;
					}
				}
			}
		}
		return false;
	}
}
