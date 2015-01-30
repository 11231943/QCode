package com.wheat.qcode;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.wheat.qcode.fragment.BaseFragment;
import com.wheat.qcode.fragment.HistoryScanFragment;
import com.wheat.qcode.fragment.MakeScanFragment;
import com.wheat.qcode.fragment.ScanFragmet;
import com.wheat.qcode.fragment.SettingFragment;

public class MainActivity extends FragmentActivity implements
        OnCheckedChangeListener
{

    private BaseFragment curInfoFragment;

    public RadioGroup radioMenu;

    /**
     * 交友页面
     */
    private HistoryScanFragment historyScanFragment;

    /**
     * 照片页面
     */
    // private Feed1Fragment feed1Fragment;
    /**
     * 活动和群组页面
     */
    private MakeScanFragment makeScanFragment;

    /**
     * 信息页面
     */
    private ScanFragmet scanFragmet;

    private SettingFragment settingFragment;

    /**
     * 底部切换标签
     */
    public RadioButton rb_scan, rb_make, rb_history, rb_msg, rb_setting;

    private int curSelectRadioId = R.id.rb_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        findViews();
        initEvent();
        init();
        loadData();

    }

    protected int setView()
    {
        // TODO Auto-generated method stub
        return R.layout.activity_main;
    }

    protected void findViews()
    {
        // TODO Auto-generated method stub
        radioMenu = (RadioGroup) findViewById(R.id.radioMenu);
        rb_scan = (RadioButton) findViewById(R.id.rb_scan);
        rb_make = (RadioButton) findViewById(R.id.rb_make);
        rb_history = (RadioButton) findViewById(R.id.rb_history);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);

    }

    protected void initEvent()
    {
        // TODO Auto-generated method stub
        radioMenu.setOnCheckedChangeListener(this);
        rb_scan.setChecked(true);
    }

    protected void init()
    {
        // TODO Auto-generated method stub
    }

    protected void loadData()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int checkedId)
    {
        {
            // TODO Auto-generated method stub
            {
                BaseFragment fragment = null;
                String id = "";
                switch (checkedId)
                {
                    case R.id.rb_scan:
                        if (this.scanFragmet == null)
                        {
                            this.scanFragmet = new ScanFragmet();
                        }
                        fragment = this.scanFragmet;
                        id = String.valueOf(R.id.rb_scan);
                        break;

                    case R.id.rb_history:
                        if (this.historyScanFragment == null)
                        {
                            this.historyScanFragment = new HistoryScanFragment();
                        }
                        fragment = this.historyScanFragment;
                        id = String.valueOf(R.id.rb_history);
                        break;
                    case R.id.rb_make:
                        if (this.makeScanFragment == null)
                        {
                            this.makeScanFragment = new MakeScanFragment();
                        }
                        fragment = this.makeScanFragment;

                        id = String.valueOf(R.id.rb_make);
                        break;

                   
                    case R.id.rb_setting:
                        if (this.settingFragment == null)
                        {
                            this.settingFragment = new SettingFragment();
                        }
                        fragment = this.settingFragment;

                        id = String.valueOf(R.id.rb_setting);

                        break;

                }
                curSelectRadioId = checkedId;
//                Log.d("=======================================",
//                        fragment.getClass().getName()
//                                + "========================================================="
//                                + checkedId);
                setActivityGroupContent(fragment, id);

            }
        }
    }

    /**
     * 启动 Fragment
     * 
     * @param fragment
     * @param id
     */
    public void setActivityGroupContent(BaseFragment fragment, String id)
    {
        if (fragment != null)
        {
            setShowContent(fragment, id);
        }
        else
        {
            hideFragment(curInfoFragment);
            curInfoFragment = null;
        }
    }

    /**
     * 隐藏Fragment
     * 
     * @param fragment
     */
    public void hideFragment(BaseFragment fragment)
    {
        if (fragment == null)
        {
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (ft == null)
        {
            return;
        }
        ft.hide(fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 显示Content内容
     * 
     * @param fragment
     * @param tag
     */
    public void setShowContent(BaseFragment fragment, String tag)
    {
        FragmentManager fm = getSupportFragmentManager();
        // FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (ft == null)
        {
            return;
        }
        BaseFragment mTPBaseFragment = (BaseFragment) fm.findFragmentByTag(tag);
        if (mTPBaseFragment == null)
        {
            ft.add(R.id.continer, fragment, tag);
        }
        else
        {
            ft.show(mTPBaseFragment);
        }
        if (curInfoFragment != null && curInfoFragment != fragment)
        {
            ft.hide(curInfoFragment);
        }
        curInfoFragment = fragment;
        ft.commitAllowingStateLoss();
    }

}
