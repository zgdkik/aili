package org.hbhk.aili.mobile.activity;

import org.hbhk.aili.mobile.R;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 主页
 */
public class DakaActivity extends BaseActivity {
	private WebView webview;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_daka);
		webview = (WebView) findViewById(R.id.webview);
		// 设置WebView属性，能够执行Javascript脚本
		webview.getSettings().setJavaScriptEnabled(true);
		// 加载需要显示的网页
		webview.loadUrl("http://hr.deppon.com:9080/eos-default/dip.integrateorg.oaAttence.oaAttence.flow");
		// 设置Web视图
		webview.setWebViewClient(new DakaWebViewClient());
	}

	private class DakaWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}
