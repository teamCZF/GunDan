package com.ui;

/**
 * Created by Administrator on 2016/11/22 0022.
 */


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.base.basepedo.R;

public class MainTabActivity extends TabActivity implements OnCheckedChangeListener{

    private TabHost mTabHost;
    private Intent mAIntent;
    private Intent mBIntent;
    private Intent mCIntent;
    private Intent mDIntent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.maintabs);

        this.mAIntent = new Intent(this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.mBIntent = new Intent(this,StoreActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.mCIntent = new Intent(this,RankActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.mDIntent = new Intent(this,UserActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        ((RadioButton) findViewById(R.id.radio_button0))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button3))
                .setOnCheckedChangeListener(this);

        setupIntent();
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            switch (buttonView.getId()) {
                case R.id.radio_button0:
                    this.mTabHost.setCurrentTabByTag("Main_TAB");

                    break;
                case R.id.radio_button1:
                    this.mTabHost.setCurrentTabByTag("B_TAB");
                    break;
                case R.id.radio_button2:
                    this.mTabHost.setCurrentTabByTag("C_TAB");
                    break;
                case R.id.radio_button3:
                    this.mTabHost.setCurrentTabByTag("D_TAB");
                    break;
            }
        }

    }

    private void setupIntent() {
        this.mTabHost = getTabHost();
        TabHost localTabHost = this.mTabHost;

        localTabHost.addTab(buildTabSpec("Main_TAB", R.string.main_home,
                R.drawable.dilan_home, this.mAIntent));

        localTabHost.addTab(buildTabSpec("B_TAB", R.string.main_store,
                R.drawable.dilan_gouwuche, this.mBIntent));

        localTabHost.addTab(buildTabSpec("C_TAB",
                R.string.main_friends, R.drawable.dilan_haoyou,
                this.mCIntent));

        localTabHost.addTab(buildTabSpec("D_TAB", R.string.main_me,
                R.drawable.dilan_geren, this.mDIntent));


    }

    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
                                         final Intent content) {
        return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
                getResources().getDrawable(resIcon)).setContent(content);
    }
}