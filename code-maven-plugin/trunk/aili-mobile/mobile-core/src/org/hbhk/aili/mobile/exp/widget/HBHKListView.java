package org.hbhk.aili.mobile.exp.widget;

import org.hbhk.aili.mobile.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Բ��ListView
 */

public class HBHKListView extends ListView {

	public HBHKListView(Context context) {
		super(context);
	}

	public HBHKListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public HBHKListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			int itemnum = pointToPosition(x, y);

			if (itemnum == AdapterView.INVALID_POSITION)
				break;
			else {
				if (itemnum == 0) {
					if (itemnum == (getAdapter().getCount() - 1)) {
						setSelector(R.drawable.list_corner_round);
					} else {
						setSelector(R.drawable.list_corner_round_top);
					}
				} else if (itemnum == (getAdapter().getCount() - 1))
					setSelector(R.drawable.list_corner_round_bottom);
				else {
					setSelector(R.drawable.list_corner_shape);
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			break;
		}

		return super.onInterceptTouchEvent(ev);
	}
}