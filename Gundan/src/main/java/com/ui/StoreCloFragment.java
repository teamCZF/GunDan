package com.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.basepedo.R;

/**
 * Created by chen on 2016/12/19.
 */

public class StoreCloFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.store_clothes_fragment,container,false);
        return view;
    }

}