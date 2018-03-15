package org.hbhk.aili.mobile.exp.activity;

import org.hbhk.aili.mobile.R;
import org.hbhk.aili.mobile.exp.bc.NetworkBroadcast;
import org.hbhk.aili.mobile.exp.frag.FragmentExecute;
import org.hbhk.aili.mobile.exp.frag.FragmentLaunch;
import org.hbhk.aili.mobile.exp.frag.FragmentSearch;
import org.hbhk.aili.mobile.exp.frag.FragmentSetting;
import org.hbhk.aili.mobile.exp.frag.FragmentTeam;
import org.hbhk.aili.mobile.exp.util.ExitApplication;
import org.hbhk.aili.mobile.exp.widget.BottomBar;
import org.hbhk.aili.mobile.exp.widget.BottomBar.OnItemChangedListener;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends FragmentActivity {

	private PopupWindow  popupWindow;
	
	BroadcastReceiver networkAvailable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// // ʵ��SelectPicPopupWindow
		// // ��ʾWHEEL���
		// SelectWheelWindow menuWindow = new SelectWheelWindow(
		// MainActivity.this, R.id.et1);
		// View thisView = View.inflate(MainActivity.this,
		// R.layout.activity_main, null);
		// // ����layout��PopupWindow����ʾ��λ��
		// menuWindow.showAtLocation(thisView, Gravity.BOTTOM
		// | Gravity.CENTER_HORIZONTAL, 0, 0);
		final BottomBar bottomBar = (BottomBar) findViewById(R.id.ll_bottom_bar);
		bottomBar.setOnItemChangedListener(new OnItemChangedListener() {

			@Override
			public void onItemChanged(int index) {

				showDetails(index);
			}
		});
		bottomBar.setSelectedState(0);
		bottomBar.showIndicate(12);
		
		//注册广播
		networkAvailable = new  NetworkBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(networkAvailable, filter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showDetails(int index) {
		Fragment details = (Fragment) getSupportFragmentManager()
				.findFragmentById(R.id.details);
		switch (index) {
		case 0:
			details = new FragmentExecute();
			break;
		case 1:
			details = new FragmentLaunch();
			break;
		case 2:
			details = new FragmentTeam();
			break;
		case 3:
			details = new FragmentSearch();
			break;
		case 4:
			details = new FragmentSetting(this);
			break;
		}
		// Execute a transaction, replacing any existing
		// fragment with this one inside the frame.
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.details, details);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		// ft.addToBackStack(null);//���д�����Է���֮ǰ�Ĳ���������������£������߶���ʾ������£�
		ft.commit();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage("是否退出?");
			builder.setTitle("系统提示");
			builder.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					ExitApplication.getInstance().exit();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();

		}
		return false;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(networkAvailable);
	}

	public void inipw(){
		 if (popupWindow != null) {
				 popupWindow.dismiss();
				 return;
				 } else {
				 View popupWindow_view = getLayoutInflater().inflate(
				 R.layout.dialog, null, false);
				 // 创建PopupWindow实例
				 popupWindow = new PopupWindow(popupWindow_view, 200, 150, true);
				 // dialog.xml视图里面的控件
				 Button bt_ok = (Button) popupWindow_view.findViewById(R.id.bt_ok);
				 bt_ok.setOnClickListener(new 	 android.view.View.OnClickListener() {
				 @Override
				 public void onClick(View v) {
				 popupWindow.dismiss(); // 对话框消失
				 			finish();
				 				System.exit(0);
				 }
				 });
				 popupWindow.showAtLocation(findViewById(R.layout.activity_main),
				 Gravity.CENTER, 0, 0);
				
				 }

		
	}
	
}
