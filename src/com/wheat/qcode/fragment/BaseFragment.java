package com.wheat.qcode.fragment;

/*
 * File Name：LSBBaseFragment.java
 * Copyright：Copyright 2008-2013 CiWong.Inc. All Rights Reserved.
 * Description： BaseFragment.java
 * Modify By：PLA-ZJLIU
 * Modify Date：2013-11-7
 * Modify Type：Add
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 碎片基类
 * 
 */
public abstract class BaseFragment extends Fragment implements
        View.OnClickListener
{

    private View rootView;

    /**
     * Intent键值,存储标题栏的返回按钮显示字符
     */
    public static final String GO_BACK = "GO_BACK";

    /**
     * 页码
     */
    protected int page = 1;

    /**
     * 每页数量
     */
    protected int pageSize = 20;

    /**
     * 是否加载完所有数据
     */
    protected boolean isPage;

    /**
     * 设置视图
     * 
     * @return layout resId
     */
    public abstract int setView();

    /**
     * 预创建
     * 
     */
    public abstract void preCreate();

    /**
     * 查找视图控件
     * 
     */
    public abstract void findViews();

    /**
     * 初始化
     * 
     */
    public abstract void init();

    /**
     * 初始化事件
     * 
     */
    public abstract void initEvent();

    /**
     * 加载数据
     * 
     */
    public abstract void loadData();

    /**
     * 根据ID查找相应的View
     * 
     * @param id
     *            View的ID
     * @return 对应控件
     */
    public View findViewById(int id)
    {
        if (rootView != null)
        {
            return rootView.findViewById(id);
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        // CWLog.d(TAG, "onCreateView");
        rootView = inflater.inflate(setView(), container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        // CWLog.d(TAG, "onActivityCreated");
        preCreate();
        super.onActivityCreated(savedInstanceState);
        findViews();
        initEvent();
        init();
        loadData();
    }

    @Override
    public void onClick(View v)
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // CWLog.d(TAG, "onCreate");
        // UserInfo userInfo = getUserInfo();
        // if (userInfo != null && AsyncHttpRequest.getVerifyInfo() == null)
        // {
        // // AsyncHttpRequest.setVerifyInfo(userInfo.getToken());
        // }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    /**
     * 获取View的boolean类型的tag
     * 
     * @param view
     *            {@link View}
     * @return tag
     */
    public boolean getBooleanTag(View view)
    {
        boolean tag = false;
        try
        {
            tag = Boolean.parseBoolean(view.getTag().toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tag;
    }

    /**
     * 启动Activity,如标题栏不需要返回按钮(即隐藏)则调用 {@link #startActivity(Intent)}<br />
     * 如需使用默认值则参数goBackResId什为{@link #DEF_GO_BACK}
     * 
     * @param mIntent
     *            {@link Intent}
     * @param goBackResId
     *            标题栏返回按钮显示字符(如:拍一拍、学习圈)
     */
    public void startActivity(Intent mIntent, int goBackResId)
    {
        mIntent.putExtra(GO_BACK, goBackResId);
        startActivity(mIntent);
    }

    /**
     * 启动Activity,如标题栏不需要返回按钮(即隐藏)则调用 {@link #startActivity(Intent)}<br />
     * 如需使用默认值则参数goBackResId什为{@link #DEF_GO_BACK}
     * 
     * @param mIntent
     *            {@link Intent}
     * @param goBackString
     *            标题栏返回按钮显示字符(如:拍一拍、学习圈)
     */
    public void startActivity(Intent mIntent, String goBackString)
    {
        mIntent.putExtra(GO_BACK, goBackString);
        startActivity(mIntent);
    }

    /**
     * 
     * 启动Activity,如标题栏不需要返回按钮(即隐藏)则调用 {@link #startActivity(Intent)}<br />
     * 如需使用默认值则参数goBackResId什为{@link #DEF_GO_BACK}
     * 
     * @param intent
     *            {@link Intent}
     * @param requestCode
     *            If >= 0, this code will be returned in onActivityResult() when
     *            the activity exits.
     * @param goBackResId
     *            标题栏返回按钮显示字符(如:拍一拍、学习圈)
     */
    public void startActivityForResult(Intent intent, int requestCode,
            int goBackResId)
    {
        intent.putExtra(GO_BACK, goBackResId);
        super.startActivityForResult(intent, requestCode);
    }

    /**
     * 隐藏当前碎片
     * 
     */
    public void onHide()
    {

    }
}
