package org.hbhk.aili.mobile.fragment;

import org.hbhk.aili.mobile.R;
import org.hbhk.aili.mobile.activity.DakaActivity;
import org.hbhk.aili.mobile.activity.camera.CameraActivity;
import org.hbhk.aili.mobile.exp.activity.FlashlightActivity;
import org.hbhk.aili.mobile.exp.activity.SpringActivity;
import org.hbhk.aili.mobile.exp.util.OpenActivityUtil;
import org.hbhk.aili.mobile.qr.QrActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableRow;


/**
 * 信息
 */
@SuppressLint("ValidFragment")
public class InfomationFragment extends MyFragment implements View.OnClickListener  {
    private View layout;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
			return null;
		}
        context = getActivity();
        LayoutInflater myInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = myInflater.inflate(R.layout.fragment_list, container, false);
		TableRow tableRow = (TableRow) layout.findViewById(R.id.table_row_about);
		tableRow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				OpenActivityUtil.openNewActivity(DakaActivity.class,
						context);
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
		TableRow takePhoto = (TableRow) layout.findViewById(R.id.table_row_takePhoto);
		takePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				OpenActivityUtil.openNewActivity(CameraActivity.class,
						context);
			}
		});
		
        return layout;
    }



    /**
     * onClick事件
     */
    @Override
    public void onClick(View view) {
    }




    @Override
    protected void onVisible(boolean isInit) {

    }
}
