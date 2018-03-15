package org.hbhk.aili.mobile.exp.activity;

import org.hbhk.aili.mobile.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class SpringActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spring);
		final TextView resultTextView = (TextView) findViewById(R.id.result_text);

//		AsyncTask<String, Void, String> simpleGetTask = new AsyncTask<String, Void, String>() {
//			@Override
//			protected String doInBackground(String... params) {
//				// executed by a background thread
//				// 创建一个RestTemplate实例
//				RestTemplate restTemplate = new RestTemplate();
//				// 添加字符串消息转换器
//				restTemplate.getMessageConverters().add(
//						new StringHttpMessageConverter());
//				return restTemplate.getForObject(params[0], String.class);
//			}
//
//			@Override
//			protected void onPostExecute(String result) {
//				resultTextView.setText(result);
//			}
//
//		};
//
//		String url = "http://www.hbhk520.com";
//		// 完成时更新resultTextView
//		simpleGetTask.execute(url);

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


}
