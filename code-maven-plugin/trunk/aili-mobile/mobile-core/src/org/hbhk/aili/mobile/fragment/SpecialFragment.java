package org.hbhk.aili.mobile.fragment;
import org.hbhk.aili.mobile.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * 特效页面
 */
public class SpecialFragment extends MyFragment  {

    private View view;


    private boolean isRefresh = true;// 获取数据成功还是失败 为后面执行刷新还是加载 成功或者失败


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_list1, container, false);

        // 缓存的view需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }


    @Override
    protected void onVisible(boolean isInit) {

        if(isInit){
            initView();//初始化控件
        }
    }

    /**
     * 适配器填充listView数据
     */
    private void initView() {


    }
}
