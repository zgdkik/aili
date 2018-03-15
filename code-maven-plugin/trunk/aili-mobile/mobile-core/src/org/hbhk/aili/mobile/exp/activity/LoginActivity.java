package org.hbhk.aili.mobile.exp.activity;

import org.hbhk.aili.mobile.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button login = (Button) findViewById(R.id.login_btn);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				openNewActivity(WelcomeActivity.class);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
