package org.hbhk.aili.mobile.exp.frag;

import org.hbhk.aili.mobile.R;
import org.hbhk.aili.mobile.activity.camera.CameraActivity;
import org.hbhk.aili.mobile.exp.activity.FlashlightActivity;
import org.hbhk.aili.mobile.exp.activity.SpringActivity;
import org.hbhk.aili.mobile.exp.util.OpenActivityUtil;
import org.hbhk.aili.mobile.fragment.MyFragment;
import org.hbhk.aili.mobile.qr.QrActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableRow;

@SuppressLint("ValidFragment")
public class FragmentSetting extends MyFragment {
	private Context context;
	private Activity activity;

	public FragmentSetting(Context context) {
		this.context = context;
		this.activity = (Activity) context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (container == null) {
			// Currently in a layout without a container, so no
			// reason to create our view.
			return null;
		}

		LayoutInflater myInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = myInflater.inflate(R.layout.frag_setting, container,
				false);
		TableRow tableRow = (TableRow) layout
				.findViewById(R.id.table_row_about);
		tableRow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		TableRow dt = (TableRow) layout.findViewById(R.id.table_row_dt);
		dt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				OpenActivityUtil.openNewActivity(FlashlightActivity.class,
						context);
			}
		});
		

		TableRow txm = (TableRow) layout.findViewById(R.id.table_row_txm);
		txm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				OpenActivityUtil.openNewActivity(QrActivity.class,
						context);
			}
		});
		TableRow spring = (TableRow) layout.findViewById(R.id.table_row_hqwz);
		spring.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				OpenActivityUtil.openNewActivity(SpringActivity.class,
						context);
			}
		});
		TableRow takePhoto = (TableRow) layout.findViewById(R.id.table_row_version_check);
		takePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				OpenActivityUtil.openNewActivity(CameraActivity.class,
						context);
			}
		});
		
		

		return layout;
	}

	@Override
	protected void onVisible(boolean isInit) {
		
	}

}
