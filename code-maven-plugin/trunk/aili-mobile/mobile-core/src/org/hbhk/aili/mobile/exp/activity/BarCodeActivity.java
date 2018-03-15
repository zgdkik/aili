package org.hbhk.aili.mobile.exp.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hbhk.aili.mobile.R;
import org.hbhk.aili.mobile.exp.actionbar.ActionBar;
import org.hbhk.aili.mobile.exp.service.DownloadService;
import org.hbhk.aili.mobile.qr.MipcaActivityCapture;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BarCodeActivity extends BaseActivity {

	private EditText resultText;
	private Button orderQuery;

	private ProgressBar pb;
	public static int loading_process;
	private String filename = "hbhk.apk";
	private String downloadurl;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar_code_query);
		ActionBar actionBar = (ActionBar) findViewById(R.id.action_bar_barcode_query);
		actionBar.setTitle("条形码扫描");
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
		resultText = (EditText) this.findViewById(R.id.et_qr_string);
		ImageView scanBarCodeButton = (ImageView) this.findViewById(R.id.dxm);
		scanBarCodeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openCameraIntent = new Intent(BarCodeActivity.this,
						MipcaActivityCapture.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});

		orderQuery = (Button) this.findViewById(R.id.order_query);
		loading_process = 0;

		orderQuery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				downloadurl = getEditTextStr(resultText);
				if (downloadurl.equals("")) {
					toast("输入条形码编号");
					return;
				}
				new Thread() {
					public void run() {
						Message msg = BroadcastHandler.obtainMessage();
						BroadcastHandler.sendMessage(msg);
						// 线程启动下载任务，通过handler传递消息
					}
				}.start();
			}
		});

	}

	private Handler BroadcastHandler = new Handler() {
		public void handleMessage(Message msg) {

			Dialog dialog = new AlertDialog.Builder(BarCodeActivity.this)
					.setTitle("确认下载该软件吗？")
					.setPositiveButton("确认下载",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Beginning();
									dialog.dismiss();
								}
							})
					.setNegativeButton("以后再说",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									dialog.dismiss();
								}
							}).create();
			dialog.show();
		}
	};

	public void Beginning() {
		LinearLayout ll = (LinearLayout) LayoutInflater.from(
				BarCodeActivity.this).inflate(R.layout.layout_loadapk, null);
		pb = (ProgressBar) ll.findViewById(R.id.down_pb);
		tv = (TextView) ll.findViewById(R.id.tv);
		Builder builder = new Builder(BarCodeActivity.this);
		builder.setView(ll);
		builder.setTitle("下载进度提示");
		builder.setNegativeButton("后台下载",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(BarCodeActivity.this,
								DownloadService.class);
						// 由intent启动service，后台运行下载进程，在服务中调用notifycation状态栏显示进度条
						startService(intent);
						dialog.dismiss();
					}
				});

		builder.show();

		new Thread() {
			public void run() {
				loadFile(downloadurl);
			}
		}.start();
		// if(TestDownLoadActivity.loading_process>99) ;
	}

	public void Beginning2() {
		LinearLayout ll = (LinearLayout) LayoutInflater.from(
				BarCodeActivity.this).inflate(R.layout.layout_loadapk, null);
		pb = (ProgressBar) ll.findViewById(R.id.down_pb);
		tv = (TextView) ll.findViewById(R.id.tv);
		Builder builder = new Builder(BarCodeActivity.this);
		builder.setView(ll);
		builder.setTitle("下载进度提示");
		builder.setNegativeButton("后台下载",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(BarCodeActivity.this,
								DownloadService.class);
						// 由intent启动service，后台运行下载进程，在服务中调用notifycation状态栏显示进度条
						startService(intent);
						dialog.dismiss();
					}
				});

		builder.show();

		new Thread() {
			public void run() {
				loadFile(downloadurl);
			}
		}.start();
		// if(TestDownLoadActivity.loading_process>99) ;
	}

	// 从IP地址下载文件到本地
	public void loadFile(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(get);

			HttpEntity entity = response.getEntity();
			float length = entity.getContentLength();

			InputStream is = entity.getContent();
			FileOutputStream fileOutputStream = null;
			if (is != null) {
				String outpath = Environment.getExternalStorageDirectory()
						+ "/hbhk";
				File file = new File(outpath, filename);
				fileOutputStream = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				int ch = -1;
				float count = 0;
				while ((ch = is.read(buf)) != -1) {
					fileOutputStream.write(buf, 0, ch);
					count += ch;
					sendMsg(1, (int) (count * 100 / length));
				}
			}
			sendMsg(2, 0);
			fileOutputStream.flush();
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		} catch (Exception e) {
			sendMsg(-1, 0);
		}
	}

	private void sendMsg(int flag, int c) {
		Message msg = new Message();
		msg.what = flag;
		msg.arg1 = c;
		handler.sendMessage(msg);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {// 定义一个Handler，用于处理下载线程与UI间通讯
			if (!Thread.currentThread().isInterrupted()) {
				switch (msg.what) {
				case 1:
					pb.setProgress(msg.arg1);
					loading_process = msg.arg1;
					tv.setText("已为您下载了：" + loading_process + "%");
					break;
				case 2:
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setDataAndType(
							Uri.fromFile(new File(Environment
									.getExternalStorageDirectory() + "/hbhk",
									filename)),
							"application/vnd.android.package-archive");
					startActivity(intent);
					break;
				case -1:
					String error = msg.getData().getString("error");
					Toast.makeText(BarCodeActivity.this, error, 1).show();
					break;
				}
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				String scanResult = bundle.getString("result");
				resultText.setText(scanResult);
			}

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
