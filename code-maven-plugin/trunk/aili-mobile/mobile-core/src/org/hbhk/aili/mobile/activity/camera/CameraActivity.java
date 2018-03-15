package org.hbhk.aili.mobile.activity.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hbhk.aili.mobile.R;
import org.hbhk.aili.mobile.exp.activity.BaseActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends BaseActivity {
	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	private Button takePhotoBn;
	private ImageView showImage;
	// 图片路径
	private Uri imageUri;
	// 图片名称
	private String filename;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		takePhotoBn = (Button) findViewById(R.id.takePhotoBn);
		showImage = (ImageView) findViewById(R.id.showImage);
		addActionBar("拍照", R.id.action_bar_camera);
		// 点击Photo Button按钮照相
		takePhotoBn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 图片名称 时间命名
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = new Date(System.currentTimeMillis());
				filename = "hbhk-" + format.format(date);
				// 创建File对象用于存储拍照的图片 SD卡根目录
				// File outputImage = new
				// File(Environment.getExternalStorageDirectory(),test.jpg);
				// 存储至DCIM文件夹
				File path = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
				File outputImage = new File(path, filename + ".jpg");
				try {
					if (outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 将File对象转换为Uri并启动照相程序
				imageUri = Uri.fromFile(outputImage);
				// 照相
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 指定图片输出地址
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				// 启动照相
				startActivityForResult(intent, TAKE_PHOTO);
			}
		});

		if (savedInstanceState == null) {
			// getFragmentManager().beginTransaction()
			// .add(R.id.container, new PlaceholderFragment())
			// .commit();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			toast("");
			return;
		}
		switch (requestCode) {
		case TAKE_PHOTO:
			// 剪裁
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(imageUri, "image");
			intent.putExtra("scale", true);
			// 设置宽高比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// 设置裁剪图片宽高
			intent.putExtra("outputX", 340);
			intent.putExtra("outputY", 340);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			toast("剪裁图片");
			// 广播刷新相册
			Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			intentBc.setData(imageUri);
			this.sendBroadcast(intentBc);
			// 设置裁剪参数显示图片至ImageView
			startActivityForResult(intent, CROP_PHOTO);
			break;
		case CROP_PHOTO:
			try {
				// 图片解析成Bitmap对象
				Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(imageUri));
				Toast.makeText(CameraActivity.this, imageUri.toString(),
						Toast.LENGTH_SHORT).show();
				showImage.setImageBitmap(bitmap); // 将剪裁后照片显示出来
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
