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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

import com.base.basepedo.R;

public class StoreActivity extends Activity{
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        group = (RadioGroup) findViewById(R.id.store_change);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                click(checkedId);
            }
        });
    }

    void click(int id){
        ItemFragment item = new ItemFragment();
        Bundle bun = new Bundle();
        switch (id) {
            case R.id.buttonClo:
                bun.putInt(ItemFragment.LAYOUT, R.layout.store_clothes_fragment);
                break;
            case R.id.buttonFood:
                bun.putInt(ItemFragment.LAYOUT, R.layout.store_food_fragment);
                break;
            case R.id.buttonHome:
                bun.putInt(ItemFragment.LAYOUT, R.layout.store_home_fragment);
                break;
            case R.id.buttonTools:
                bun.putInt(ItemFragment.LAYOUT, R.layout.store_tools_fragment);
                break;
            default :
                return;
        }
        item.setArguments(bun);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.low_fragment, item)
                .commit();
                /*getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.content, item)
            .commit();*/

    }

    public static class ItemFragment extends Fragment {
        static final String LAYOUT = "layout";
        int layout = 0;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            layout = getArguments().getInt(LAYOUT);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = null;
            if(layout == 0){
                v = new View(getActivity());
            }else{
                v = inflater.inflate(layout, container, false);
            }
            return v;
        }
    }
}