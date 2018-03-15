package org.hbhk.aili.mobile.qr;


import org.hbhk.aili.mobile.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QrActivity extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	/**
	 * 锟斤拷示扫锟斤拷锟斤拷
	 */
	private TextView mTextView ;
	/**
	 * 锟斤拷示扫锟斤拷锟侥碉拷图片
	 */
	private ImageView mImageView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr);
		
		mTextView = (TextView) findViewById(R.id.result); 
		mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
		
		//锟斤拷锟斤拷锟脚ワ拷锟阶拷锟斤拷锟轿拷锟缴拷锟斤拷锟芥，锟斤拷锟斤拷锟矫碉拷锟斤拷startActivityForResult锟斤拷转
		//扫锟斤拷锟斤拷锟斤拷之锟斤拷锟斤拷锟斤拷媒锟斤拷锟�
		Button mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(QrActivity.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				//锟斤拷示扫锟借到锟斤拷锟斤拷锟斤拷
				mTextView.setText(bundle.getString("result"));
				//锟斤拷示
				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
			}
			break;
		}
    }	

}
