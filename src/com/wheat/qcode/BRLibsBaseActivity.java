/*
 * File Name：CWBaseActivity.java
 * Copyright：Copyright 2013-2014 BlueRhion.Inc. All Rights Reserved.
 * Description： CWBaseActivity.java
 * Modify By：Administrator
 * Modify Date：2014-3-17
 * Modify Type：Add
 */
package com.wheat.qcode;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 基类
 * @author     Administrator
 * @version    BlueRhion v.1.0 2014-3-17
 * @since      BlueRhion v.1.0
 */
public abstract class BRLibsBaseActivity  extends Activity
{
    private boolean hasAddActivity = true;

    /**
     * 是否需要添加Activity
     * 
     * @param hasAdd
     *            是否需要添加
     */
    public void setHasAddActivity(boolean hasAdd)
    {
        this.hasAddActivity = hasAdd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        preCreate();
        super.onCreate(savedInstanceState);
        setContentView(setView());
        if (hasAddActivity)
        {
//            DCApplication application = (DCApplication) getApplication();
//            application.addActivity(this);
        }
        findViews();
        initEvent();
        init();
        loadData();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
//        DCApplication application = (DCApplication) getApplication();
//        application.removeActivity(this);
    }

    /**
     * 预创建,运行在{@link #onCreate(Bundle)}前
     * 
     */
    public void preCreate()
    {

    }

    @Override
    protected void onStart()
    {
        super.onStart();
//        if (DCApplication.instance() == null)
//        {
//            DCApplication.setInstance((DCApplication) getApplication());
//        }
    }

    /**
     * 设置内容
     * 
     * @return 返回一个View
     */
    protected abstract int setView();

    /**
     * 查找元素(控件)
     */
    protected abstract void findViews();

    /**
     * 初始化操作(如初始化事件)
     * 
     */
    protected abstract void initEvent();

    /**
     * 初始化(赋值、显示、隐藏等)
     */
    protected abstract void init();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 隐藏软件盘
     * 
     * @param view
     *            视图
     */
    protected void hideSoftInput(View view)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
