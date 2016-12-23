package com.ui;

/**
 * Created by Administrator on 2016/11/22 0022.
 */


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.base.basepedo.R;
public class StoreActivity extends Activity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_store);
        Button buttonc=(Button)findViewById(R.id.buttonClo);
        Button buttonf=(Button)findViewById(R.id.buttonFood);
        Button buttont=(Button)findViewById(R.id.buttonTools);
        Button buttonh=(Button)findViewById(R.id.buttonHome);
        buttonc.setOnClickListener(this);
        buttonf.setOnClickListener(this);
        buttont.setOnClickListener(this);
        buttonh.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.buttonClo:
                StoreCloFragment fs=new StoreCloFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.low_fragment,fs);
                transaction.commit();
                break;
            case R.id.buttonFood:
                StoreFoodFragment ff=new StoreFoodFragment();
                FragmentManager fragmentManagerf=getFragmentManager();
                FragmentTransaction transactionf=fragmentManagerf.beginTransaction();
                transactionf.replace(R.id.low_fragment,ff);
                transactionf.commit();
                break;
            case R.id.buttonHome:
                StoreHomeFragment fh=new StoreHomeFragment();
                FragmentManager fragmentManagerh=getFragmentManager();
                FragmentTransaction transactionh=fragmentManagerh.beginTransaction();
                transactionh.replace(R.id.low_fragment,fh);
                transactionh.commit();
                break;
            case R.id.buttonTools:
                StoreToolsFragment ft=new StoreToolsFragment();
                FragmentManager fragmentManagert=getFragmentManager();
                FragmentTransaction transactiont=fragmentManagert.beginTransaction();
                transactiont.replace(R.id.low_fragment,ft);
                transactiont.commit();
                break;
            default:
                break;


        }
    }
}