/*
 * File Name：ShowScanActivity.java
 * Copyright：Copyright 2013-2015 BEIJING. ©2013 ZANK.MOBI.Inc. All Rights Reserved.
 * Description： ShowScanActivity.java
 * Modify By：wheat
 * Modify Date：2015-1-29
 * Modify Type：Add
 */
package com.wheat.qcode.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wheat.qcode.BRBaseActivity;
import com.wheat.qcode.R;

/**
 * TODO Description of class。
 * <p>
 * TODO Detail Description
 * <p>
 * TODO Sample Code
 * 
 * <pre>
 * </pre>
 * 显示二维码结果
 * 
 * @author wheat
 * @version feizan v.3.2 2015-1-29
 * @since feizan v.3.2
 */
public class ShowScanActivity extends BRBaseActivity
{

    private TextView mTextView;

    private ImageView mImageView;

    @Override
    public void preCreate()
    {
        // TODO Auto-generated method stub
        super.preCreate();
        setTitleBarText("扫描结果");
    }

    @Override
    protected int setView()
    {
        // TODO Auto-generated method stub
        return R.layout.show_scan;
    }

    @Override
    protected void findViews()
    {
        // TODO Auto-generated method stub
        super.findViews();
        mTextView = (TextView) findViewById(R.id.result);
        mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
    }

    @Override
    protected void initEvent()
    {
        // TODO Auto-generated method stub
        super.initEvent();
    }

    @Override
    protected void init()
    {
        // TODO Auto-generated method stub
        super.init();
        Bundle bundle = getIntent().getExtras();
        mTextView.setText(bundle.getString("result"));
        mImageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra(
                "bitmap"));

    }

    @Override
    protected void loadData()
    {
        // TODO Auto-generated method stub
        super.loadData();
    }

    @Override
    public void onClick(View arg0)
    {
        // TODO Auto-generated method stub

    }

}
