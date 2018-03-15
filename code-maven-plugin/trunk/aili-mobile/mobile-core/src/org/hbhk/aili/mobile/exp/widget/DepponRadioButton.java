package org.hbhk.aili.mobile.exp.widget;

import org.hbhk.aili.mobile.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class DepponRadioButton extends RadioButton {

	private String key;

	public DepponRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray taArray = context.obtainStyledAttributes(attrs,
				R.styleable.depponRadioButton);
		this.key = taArray.getString(R.styleable.depponRadioButton_key);
		taArray.recycle();

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
