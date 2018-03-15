package org.hbhk.aili.mobile.exp.wheel;

import org.hbhk.aili.mobile.R;
import org.hbhk.aili.mobile.exp.wheel.kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
import org.hbhk.aili.mobile.exp.wheel.kankan.wheel.widget.adapters.ArrayWheelAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class SelectWheelWindow extends PopupWindow {
	private View mMenuView;
	private boolean scrolling = false;
	private int sourceCompentId;
	private Activity sourceContext;
	String[] provincesArray= new String[] { "���", "�Ϻ�" ,"����" };
	String[][] cities = new String[][] {
			new String[] { "����ر�������01", "����ر�������02" ,"����ر�������03"},
			new String[] { "����", "բ��" ,"����" } ,
			new String[] {"����","����"} 
	};
	
	/**
	 * ����Window����Ĺ���
	 * @param context ��ǰʹ�ô����Activity��������
	 * @param sourceId ��Ҫ��ֵ�Ŀؼ���ԴID
	 */
	public SelectWheelWindow(final Activity context,int sourceId) {
		super(context);
		/*����֮ǰ�����뷨���̼����Ǵ���*/
		dismiss();
		InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

		
		this.sourceContext = context;
		this.sourceCompentId = sourceId;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.wheel_citydialog, null);
		mMenuView.setFocusable(true);
		mMenuView.setFocusableInTouchMode(true);
		mMenuView.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				 if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK){
					 SelectWheelWindow.this.dismiss();
					 return true;
				 }
				return false;
			}
		});
		
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);// ��������Ŀ�
		this.setHeight(LayoutParams.WRAP_CONTENT);// ��������ĸ�
		this.setFocusable(true);// ��������ɵ��
		this.setAnimationStyle(R.style.PopupAnimation);// ���嶯��Ч��
		this.setBackgroundDrawable(new PaintDrawable());
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				/* ������������������� */
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return false;
			}
		}); 
		
		
		
		

		final WheelView provinces = (WheelView) mMenuView.findViewById(R.id.provinces);
		provinces.setVisibleItems(3);
		provinces.setViewAdapter(new ProvincesAdapter(mMenuView.getContext(),provincesArray));
		final WheelView city = (WheelView) mMenuView.findViewById(R.id.city);
		city.setVisibleItems(5);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT,1f);  
	    lp.gravity = Gravity.LEFT;  
		city.setLayoutParams(lp);

		provinces.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!scrolling) {
					updateCities(city, cities, newValue);
					returnCurrItems(provinces,city,context);
				}
			}
		});
		
		/*provinces��������*/
		provinces.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {
				scrolling = true;
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				scrolling = false;
				updateCities(city, cities, provinces.getCurrentItem());
				returnCurrItems(provinces,city,context);
				
			}
		});
		
		/*city�Ĺ�������*/
		city.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingFinished(WheelView wheel) {
				returnCurrItems(provinces,city,context);
			}

			@Override
			public void onScrollingStarted(WheelView wheel) {
				
			}
		});
		provinces.setCurrentItem(1);
	}
	
	/**
	 * Updates the city wheel
	 */
	private void updateCities(WheelView city, String cities[][], int index) {
		ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(
				mMenuView.getContext(), cities[index]);
		adapter.setTextSize(18);
		city.setViewAdapter(adapter);
		city.setCurrentItem(cities[index].length / 2);
	}


	private class ProvincesAdapter extends AbstractWheelTextAdapter {
		//����
		public String[] provinces ;

		/**
		 * Constructor
		 */
		protected ProvincesAdapter(Context context,String[] provinceArray) {
			super(context, R.layout.wheel_country_layout, NO_RESOURCE);
			this.provinces = provinceArray;
			setItemTextResource(R.id.country_name);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return provinces.length;
		}

		@Override
		protected CharSequence getItemText(int index) {
			return provinces[index];
		}
	}
	
	/**
	 * ���ص�ǰWheel�Ľ������
	 */
	public void returnCurrItems(WheelView provinces,WheelView citys,Context context){
		EditText tView = (EditText)((Activity)sourceContext).findViewById(sourceCompentId);
		tView.setInputType(InputType.TYPE_NULL); // disable soft input      

		
		if(provincesArray.length>0 && cities.length>0){
			int provincesIndex = provinces.getCurrentItem();
			int citysIndex = citys.getCurrentItem();
			tView.setText(provincesArray[provincesIndex]+"/"+cities[provincesIndex][citysIndex]);
			//tView.setText(String.valueOf(provinces.getCurrentItem())+","+String.valueOf(citys.getCurrentItem()));
		}
		
	}
}
