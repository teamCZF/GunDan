package com.ui;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.Data.UserData;
import com.base.basepedo.R;

/**
 * Created by chen on 2016/12/19.
 */

public class StoreFoodFragment extends Fragment //implements View.OnClickListener
{
    /*private Button btnf1;
    private Button btnf2;
    private Button btnf3;
    private Button btnf4;
    private Button btnf5;
    private UserData user;*/

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState)
    {

        View view =inflater.inflate(R.layout.store_food_fragment,container,false);
        /*btnf1 = (Button)getActivity().findViewById(R.id.foodbutton1);
        btnf2 = (Button)getActivity().findViewById(R.id.foodbutton2);
        btnf3 = (Button)getActivity().findViewById(R.id.foodbutton3);
        btnf4 = (Button)getActivity().findViewById(R.id.foodbutton4);
        btnf5 = (Button)getActivity().findViewById(R.id.foodbutton5);
        btnf1.setOnClickListener(this);
        btnf2.setOnClickListener(this);
        btnf3.setOnClickListener(this);
        btnf4.setOnClickListener(this);
        btnf5.setOnClickListener(this);*/
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
    /*private void showconfirm(View v)
    {
        LayoutInflater layoutInflater=(LayoutInflater)getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.food_confirm,null);
        PopupWindow popupWindow=new PopupWindow(view,
                WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();    //  获取测量后的宽度
        int popupHeight = view.getMeasuredHeight();  //获取测量后的高度
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
        Button confirmbtn=(Button) view.findViewById(R.id.confirm_buy);
        Button canclebtn=(Button)view.findViewById(R.id.cancel_buy);
        confirmbtn.setOnClickListener(this);
        canclebtn.setOnClickListener(this);
    }
    public void onClick(View v)
        {
            switch (getView().getId())
            {
                case R.id.foodbutton1:
                    showconfirm(v);

                    break;
                case R.id.foodbutton2:

                    break;
                case R.id.foodbutton3:

                    break;
                case R.id.foodbutton4:

                    break;
                case R.id.foodbutton5:

                    break;
                default:
                    break;
            }
        }*/

}


