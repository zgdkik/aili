package org.hbhk.aili.mobile.exp.util;


import org.hbhk.aili.mobile.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class OpenActivityUtil {

	public static void openNewActivity(Class<?> cls, Bundle bundle,
			Context context) {
		Intent intent;
		intent = new Intent(context, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		Activity activity = (Activity) context;
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}

	public static void openNewActivity(Class<?> cls, Context context) {
		Intent intent;
		intent = new Intent(context, cls);
		Activity activity = (Activity) context;
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}

}
