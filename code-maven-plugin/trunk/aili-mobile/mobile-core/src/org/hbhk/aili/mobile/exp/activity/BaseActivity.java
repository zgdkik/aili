package org.hbhk.aili.mobile.exp.activity;

import org.hbhk.aili.mobile.R;
import org.hbhk.aili.mobile.exp.actionbar.ActionBar;
import org.hbhk.aili.mobile.exp.util.ExitApplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity {

	Resources res;
	boolean success = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		// 通用资源缩写
		res = getResources();
		// 优化输入法模式
		int inputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
		getWindow().setSoftInputMode(inputMode);
		ExitApplication.getInstance().addActivity(this);
	}

	/**
	 * 从当前activity跳转到目标activity,<br>
	 * 如果目标activity曾经打开过,就重新展现,<br>
	 * 如果从来没打开过,就新建一个打开
	 * 
	 */
	public void openExistActivity(Class<?> cls, Bundle bundle) {
		Intent intent;
		intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}

	public void openExistActivity(Class<?> cls) {
		Intent intent;
		intent = new Intent(this, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}

	public void exitActivity(Activity activity) {
		activity.finish();
	}

	/**
	 * 新建一个activity打开
	 * 
	 * @param cls
	 */
	public void openNewActivity(Class<?> cls, Bundle bundle) {
		Intent intent;
		intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openNewActivity(Class<?> cls) {
		Intent intent;
		intent = new Intent(this, cls);
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void backActivity(Class<?> cls) {
		Intent intent;
		intent = new Intent(this, cls);
		startActivity(intent);
		overridePendingTransition(R.anim.back_right_in, R.anim.back_right_out);
	}

	public void backActivity(Class<?> cls, Bundle bundle) {
		Intent intent;
		intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.back_right_in, R.anim.back_right_out);
	}

	public void openNewActivityForResult(Class<?> cls, int requestCode) {
		Intent intent;
		intent = new Intent(this, cls);
		startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openNewActivityForResult(Class<?> cls, Bundle bundle,
			int requestCode) {
		Intent intent;
		intent = new Intent(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode, bundle);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/**
	 * 通用消息提示
	 * 
	 * @param resId
	 */
	public void toast(int resId) {
		Toast toast = Toast.makeText(this, resId, Toast.LENGTH_SHORT);
		// 设置Toast显示位置(起点位置,水平向右位移,垂直向下位移)
		toast.setGravity(Gravity.TOP, 0, 30);
		toast.show();
	}

	/**
	 * 通用消息提示
	 * 
	 * @param resId
	 */
	public void toast(String msg) {
		Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		// 设置Toast显示位置(起点位置,水平向右位移,垂直向下位移)
		toast.setGravity(Gravity.TOP, 0, 30);
		toast.show();

	}

	/**
	 * 从资源获取字符串
	 * 
	 * @param resId
	 * @return
	 */
	public String getStr(int resId) {
		return res.getString(resId);
	}

	/**
	 * 从EditText 获取字符串
	 * 
	 * @param editText
	 * @return
	 */
	public String getEditTextStr(EditText editText) {
		String editStr = "";
		if (editText != null) {
			if (editText.getText() != null) {
				editStr = editText.getText().toString().trim();
			}
		}
		return editStr;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			this.finish();
			overridePendingTransition(R.anim.back_right_in,
					R.anim.back_right_out);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void returnLastActivity() {
		this.finish();
		overridePendingTransition(R.anim.back_right_in, R.anim.back_right_out);
	}

	public int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		return dm.widthPixels;
	}

	public int getScreenheight() {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		return dm.heightPixels;
	}

	public int getScreenWidthPercent(Double pre) {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		return (int) (dm.widthPixels * pre);
	}

	public int getScreenHeightPercent(Double pre) {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		return (int) (dm.heightPixels * pre);
	}

	public void setViewLaction(View view, double prew, double preh) {
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if (prew > 0) {
			layoutParams.width = getScreenWidthPercent(prew);
		}

		if (preh > 0) {
			layoutParams.height = getScreenHeightPercent(preh);
		}

		view.setLayoutParams(layoutParams);
	}

	public void setViewLaction(View view, int w, int h) {
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if (w > 0) {
			layoutParams.width = w;
		}
		if (h > 0) {
			layoutParams.height = h;
		}

		view.setLayoutParams(layoutParams);
	}

	/*
	 * 发送消息通知到Handler
	 */
	public void putHandlerMessage(int arg1, int arg2, Handler handler) {
		Message msg = new Message();
		msg.arg1 = arg1;
		msg.arg2 = arg2;
		handler.sendMessage(msg);
	}

	public void addActionBar(String title, int viewId) {

		ActionBar actionBar = (ActionBar) findViewById(viewId);
		actionBar.setTitle(title);
		actionBar.setHomeAction(new ActionBar.Action() {
			@Override
			public void performAction(View view) {
				returnLastActivity();
			}

			@Override
			public int getDrawable() {
				return R.drawable.action_bar_back;
			}
		});
	}
}
