package com.wheat.qcode;

/*
 * File Name：CWBaseActivity.java
 * Copyright：Copyright 2008-2013 CiWong.Inc. All Rights Reserved.
 * Description： CWBaseActivity.java
 * Modify By：PLA-ZJLIU
 * Modify Date：2013-12-5
 * Modify Type：Add
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wheat.qcode.i.GoBackListener;

public abstract class BRBaseActivity extends BRLibsBaseActivity implements
        OnTouchListener ,OnClickListener
{

    private final static String TAG = "CWBaseActivity";

    /**
     * Intent键值,存储标题栏的返回按钮显示字符
     */
    public static final String GO_BACK = "GO_BACK";

    /**
     * 显示返回按钮，默认不显示
     */
    public static final int DEF_GO_BACK = -1;

    private boolean isForbiddenTitleBar;// 是否禁用标题栏

    private GoBackListener mGoBackListener;

    private ImageButton goBack;

    private TextView title;

    private CharSequence titleBarText;

    // 手指向右滑动时的最小速度
    private static final int XSPEED_MIN = 200;

    // 手指向右滑动时的最小距离
    private static final int XDISTANCE_MIN = 150;

    // 记录手指按下时的横坐标。
    private float xDown;

    // 记录手指移动时的横坐标。
    private float xMove;

    // 用于计算手指滑动的速度。
    private VelocityTracker mVelocityTracker;

    /**
     * 是否禁用标题栏
     * 
     * @param isForbiddenTitleBar
     */
    public void setForbiddenTitleBar(boolean isForbiddenTitleBar)
    {
        this.isForbiddenTitleBar = isForbiddenTitleBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // CWLog.d(TAG, "onCreate");
        if (!isForbiddenTitleBar)
        {
            baseFindViews();
            baseInitEvent();
            baseInit();
        }
    }

    @Override
    public void preCreate()
    {

        super.preCreate();
        // CWLog.d(TAG, "preCreate");

    }
 
    @Override
    protected int setView()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected void findViews()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initEvent()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void loadData()
    {
        // TODO Auto-generated method stub

    }

    private void baseFindViews()
    {
        goBack = (ImageButton) findViewById(R.id.goBack);
        title = (TextView) findViewById(R.id.title);
    }

    private void baseInitEvent()
    {
        if (goBack != null)
        {
            goBack.setOnClickListener(new OnClick());
        }
    }

    /**
     * 设置标题栏中返回按钮事件,若不设置默认操作是finish当前Activity
     * 
     * @param listener
     *            {@link GoBackListener}
     */
    public void setGoBackListener(GoBackListener listener)
    {
        mGoBackListener = listener;
    }

    /**
     * 隐藏键盘 <br /> {@inheritDoc}
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            View v = getWindow().getDecorView();
            View focus = v.findFocus();
            int[] location = new int[4];
            if (focus != null)
            {
                focus.getLocationInWindow(location);
                location[2] = location[0] + focus.getMeasuredWidth();
                location[3] = location[1] + focus.getMeasuredHeight();
                float x = event.getX();
                float y = event.getY();
                if (focus instanceof EditText && x > location[0]
                        && y > location[1] && x < location[2]
                        && y < location[3])
                {
                    return super.dispatchTouchEvent(event);
                }
            }
            hideSoftInput(getWindow().getDecorView());
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 设置标题
     * 
     * @param text
     *            标题
     */
    public void setTitleBarText(CharSequence text)
    {
        if (title != null)
        {
            title.setText(text);
        }
        else
        {
            titleBarText = text;
        }
    }

    /**
     * 
     * 设置标题
     * 
     * @param resId
     *            标题资源ID
     */
    public void setTitleBarText(int resId)
    {
        if (title != null)
        {
            title.setText(resId);
        }
        else
        {
            titleBarText = getString(resId);
        }
    }

    private void baseInit()
    {
        if ((title == null || goBack == null) && !isForbiddenTitleBar)
        {
            Log.e("debug", "没有引入布局title_bar.xml");
        }
        Intent mIntent = getIntent();
        if (mIntent != null && goBack != null)
        {
            int goBackResId = mIntent.getIntExtra(GO_BACK, -2);
            if (goBackResId == -2)
            {
                goBack.setVisibility(View.GONE);
            }
            // else if (goBackResId == -1)
            // {
            // goBack.setText(R.string.go_back);
            // }
            // else
            // {
            // goBack.setText(goBackResId);
            // }
        }
        if (titleBarText != null)
        {
            title.setText(titleBarText);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        // CWLog.d(TAG, "onStart");
        
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

    private class OnClick implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            int id = v.getId();
            if (id == R.id.goBack)
            {
                if (mGoBackListener != null)
                {
                    mGoBackListener.goBack();
                }
                else
                {
                    finish();
                }
            }
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        createVelocityTracker(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                // 活动的距离
                int distanceX = (int) (xMove - xDown);
                // 获取顺时速度
                int xSpeed = getScrollVelocity();
                // 当滑动的距离大于我们设定的最小距离且滑动的瞬间速度大于我们设定的速度时，返回到上一个activity
                if (distanceX > XDISTANCE_MIN && xSpeed > XSPEED_MIN)
                {
                    finish();
                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中。
     * 
     * @param event
     * 
     */
    private void createVelocityTracker(MotionEvent event)
    {
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 回收VelocityTracker对象。
     */
    private void recycleVelocityTracker()
    {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    /**
     * 获取手指在content界面滑动的速度。
     * 
     * @return 滑动速度，以每秒钟移动了多少像素值为单位。
     */
    private int getScrollVelocity()
    {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }
}
