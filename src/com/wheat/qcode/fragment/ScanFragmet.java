/*
 * File Name：ScanFragmet.java
 * Copyright：Copyright 2013-2015 BEIJING. ©2013 ZANK.MOBI.Inc. All Rights Reserved.
 * Description： ScanFragmet.java
 * Modify By：wheat
 * Modify Date：2015-1-29
 * Modify Type：Add
 */
package com.wheat.qcode.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wheat.qcode.R;
import com.wheat.qcode.activity.MipcaActivityCapture;


/**
 * TODO Description of class。
 * <p>
 * TODO Detail Description
 * <p>
 * TODO Sample Code
 * <pre>
 * </pre>
 * 扫描页面
 * @author     wheat
 */
public class ScanFragmet extends BaseFragment
{

    private Button star_scan;
    @Override
    public int setView()
    {
        // TODO Auto-generated method stub
        return R.layout.scan_fragmnet;
    }

    @Override
    public void preCreate()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void findViews()
    {
        star_scan=(Button)findViewById(R.id.star_scan);
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initEvent()
    {
        // TODO Auto-generated method stub
        star_scan.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View arg0)
            {
                // TODO Auto-generated method stub
                Intent intent =new Intent(getActivity(),MipcaActivityCapture.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData()
    {
        // TODO Auto-generated method stub
        
    }
    
    
        
}
