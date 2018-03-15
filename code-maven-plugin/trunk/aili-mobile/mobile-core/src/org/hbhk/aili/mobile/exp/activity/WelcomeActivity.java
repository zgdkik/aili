package org.hbhk.aili.mobile.exp.activity;

import java.util.ArrayList;

import org.hbhk.aili.mobile.R;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class WelcomeActivity extends BaseActivity {

	ViewPager viewPager;
	ArrayList<View> list;
	ViewGroup main, group;
	TextView textView;
	TextView[] textViews;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȥ����Ϣ��
		LayoutInflater inflater = getLayoutInflater();
		list = new ArrayList<View>();
		list.add(inflater.inflate(R.layout.welcome_item1, null));
		list.add(inflater.inflate(R.layout.welcome_item2, null));
		list.add(inflater.inflate(R.layout.welcome_item3, null));
		list.add(inflater.inflate(R.layout.welcome_item4, null));
		textViews = new TextView[list.size()];
		main = (ViewGroup) inflater.inflate(R.layout.activity_welcome, null);
		// group��R.layou.main�еĸ�����СԲ���LinearLayout.
		group = (ViewGroup) main.findViewById(R.id.viewGroup);
		viewPager = (ViewPager) main.findViewById(R.id.viewPager);
		for (int i = 0; i < list.size(); i++) {
			textView = new TextView(WelcomeActivity.this);
			textView.setLayoutParams(new LayoutParams(20, 20));
			// textView.setPadding(0, 0, 0, 0);
			textViews[i] = textView;
			if (i == 0) {
				// Ĭ�Ͻ��������һ��ͼƬ��ѡ��;
				textViews[i].setBackgroundResource(R.drawable.radio_sel);
			} else {
				textViews[i].setBackgroundResource(R.drawable.radio);
			}
			group.addView(textViews[i]);
		}
		setContentView(main);
		viewPager.setAdapter(new MyAdapter());
		viewPager.setOnPageChangeListener(new MyListener());
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(list.get(position));
		}

		@Override
		public void finishUpdate(ViewGroup container) {
			super.finishUpdate(container);
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return super.getPageTitle(position);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(list.get(position));
			return list.get(position);
		}
	}

	class MyListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			if (arg0 == textViews.length - 1) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				openNewActivity(MainActivity.class);
				WelcomeActivity.this.finish();
			}
		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < textViews.length; i++) {
				textViews[arg0].setBackgroundResource(R.drawable.radio_sel);
				if (arg0 != i) {
					textViews[i].setBackgroundResource(R.drawable.radio);
				}
			}

		}

	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


}
