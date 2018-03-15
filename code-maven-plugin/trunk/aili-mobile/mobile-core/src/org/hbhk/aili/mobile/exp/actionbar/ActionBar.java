/*
 * Copyright (C) 2010 Johan Nilsson <http://markupartist.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hbhk.aili.mobile.exp.actionbar;

import java.util.LinkedList;

import org.hbhk.aili.mobile.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ActionBar extends RelativeLayout implements OnClickListener {

    private LayoutInflater mInflater;
    private RelativeLayout mBarView;
    private ImageButton mHomeBtn;
    private RelativeLayout mHomeLayout;
    private TextView mTitleView;
    private LinearLayout mActionsView;
    
    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBarView = (RelativeLayout) mInflater.inflate(R.layout.view_action_bar, null);
        addView(mBarView);
        mHomeLayout = (RelativeLayout) mBarView.findViewById(R.id.actionbar_home_bg);
        mHomeBtn = (ImageButton) mBarView.findViewById(R.id.actionbar_home_btn);
        mTitleView = (TextView) mBarView.findViewById(R.id.actionbar_title);
        mActionsView = (LinearLayout) mBarView.findViewById(R.id.actionbar_actions);
        
        //调用自定义Title属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ActionBar);
        CharSequence title = ta.getString(R.styleable.ActionBar_title1);
        if (title != null) {
            setTitle(title);
        }
        ta.recycle();
    }

    public void setHomeAction(Action action) {
        mHomeBtn.setOnClickListener(this);
        mHomeBtn.setTag(action);
        mHomeBtn.setImageResource(action.getDrawable());
        mHomeLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 清除Home Action
     */
    public void clearHomeAction() {
        mHomeLayout.setVisibility(View.GONE);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(CharSequence title) {
        mTitleView.setText(title);
    }

    /**
     * 设置标题
     * @param resid
     */
    public void setTitle(int resId) {
        mTitleView.setText(resId);
    }

    /**
     * 设置标题点击监听事件
     * @param listener
     */
    public void setOnTitleClickListener(OnClickListener listener) {
        mTitleView.setOnClickListener(listener);
    }

    /**
     * OnClickListener接口实现方法
     */
    @Override
    public void onClick(View view) {
        final Object tag = view.getTag();
        if (tag instanceof Action) {
            final Action action = (Action) tag;
            action.performAction(view);
        }
    }

    /**
     * 增加Action列表
     * @param actionList
     */
    public void addActions(ActionList actionList) {
        int actions = actionList.size();
        for (int i = 0; i < actions; i++) {
            addAction(actionList.get(i));
        }
    }

    /**
     * 在ActionsView的尾部增加Action
     * @param action
     */
    public void addAction(Action action) {
        final int index = mActionsView.getChildCount();
        addAction(action, index);
    }

	/**
	 * 在指定位置上增加Action
	 * @param action
	 * @param index
	 */
    public void addAction(Action action, int index) {
        mActionsView.addView(inflateAction(action), index);
    }

    /**
     * 移除指定位置上的Action
     * @param index
     */
    public void removeActionAt(int index) {
        mActionsView.removeViewAt(index);
    }

    /**
     * 移除某种类别的Action
     * @param action
     */
    public void removeAction(Action action) {
        int childCount = mActionsView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mActionsView.getChildAt(i);
            if (view != null) {
                final Object tag = view.getTag();
                if (tag instanceof Action && tag.equals(action)) {
                    mActionsView.removeView(view);
                }
            }
        }
    }

    /**
     * 移除所有Action
     */
    public void removeAllActions() {
        mActionsView.removeAllViews();
    }

    /**
     * 获取自定义Action Bar的数量
     * @return
     */
    public int getActionCount() {
        return mActionsView.getChildCount();
    }

    /**
     * Inflates a {@link View} with the given {@link Action}.
     * @param action the action to inflate
     * @return a view
     */
    private View inflateAction(Action action) {
        View view = mInflater.inflate(R.layout.view_action_bar_item, mActionsView, false);
        ImageButton labelView = (ImageButton) view.findViewById(R.id.actionbar_item);
        labelView.setImageResource(action.getDrawable());
        view.setTag(action);
        view.setOnClickListener(this);
        return view;
    }

    /**
     * A {@link LinkedList} that holds a list of {@link Action}s.
     */
    @SuppressWarnings("serial")
	public static class ActionList extends LinkedList<Action> {
    
    }

    /**
     * Definition of an action that could be performed, along with a icon to
     * show.
     */
    public interface Action {
    	
        public int getDrawable();
        
        public void performAction(View view);
    }

    public static abstract class AbstractAction implements Action {
    	
        final private int mDrawable;

        public AbstractAction(int drawable) {
            mDrawable = drawable;
        }

        @Override
        public int getDrawable() {
            return mDrawable;
        }
        
    }
    
    // 主页Action
    public static class HomeAction extends AbstractAction {
    	
        private Context mContext;
        private Intent mIntent;

        public HomeAction(Context context, Intent intent) {
            super(R.drawable.ic_title_home_default);
            mContext = context;
            mIntent = intent;
        }
        
        public HomeAction(Context context, Intent intent, int drawable) {
            super(drawable);
            mContext = context;
            mIntent = intent;
        }

        @Override
        public void performAction(View view) {
            mContext.startActivity(mIntent);
        }
        
    }
    
    // 返回Action
    public static class BackAction extends AbstractAction {
    	
        private Activity activity;
        
        public BackAction(Activity activity) {
            super(R.drawable.action_bar_back);
            this.activity = activity;
        }
        
        public BackAction(Activity activity, int drawable) {
            super(drawable);
            this.activity = activity;
        }

        @Override
        public void performAction(View view) {
        	activity.finish();
        }
        
    }

}
