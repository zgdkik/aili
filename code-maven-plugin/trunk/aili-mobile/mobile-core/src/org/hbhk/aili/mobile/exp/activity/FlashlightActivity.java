package org.hbhk.aili.mobile.exp.activity;

import org.hbhk.aili.mobile.R;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class FlashlightActivity extends BaseActivity {
	ImageButton btn_light;
	boolean isOff = false;
	private Camera camera;
	private Parameters parameters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);
		btn_light = (ImageButton) findViewById(R.id.btn_light);
		btn_light.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isOff == true) {
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(parameters);
					// �ͷ��ֻ�����ͷ
					camera.release();
					camera = null;
					isOff = false;
					btn_light.setBackgroundResource(R.drawable.shou_off);
					return;
				}

				camera = Camera.open();
				parameters = camera.getParameters();
				parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
				camera.setParameters(parameters);
				camera.startPreview();
				isOff = true;
				btn_light.setBackgroundResource(R.drawable.shou_on);

			}
		});

	}

}
