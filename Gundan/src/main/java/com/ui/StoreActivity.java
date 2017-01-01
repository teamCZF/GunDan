package com.ui;

/**
 * Created by Administrator on 2016/11/22 0022.
 */


import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.Data.Property;
import com.Data.UserData;
import com.base.basepedo.R;
import com.utils.DbUtils;

public class StoreActivity extends Activity implements View.OnClickListener
{
    RadioGroup group;
    private Button btnf1;
    private Button btnf2;
    private Button btnf3;
    private Button btnf4;
    private Button btnf5;
    private UserData user;
    private Property prop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        group = (RadioGroup) findViewById(R.id.store_change);
        btnf1 = (Button)findViewById(R.id.foodbutton1);
        btnf2 = (Button)findViewById(R.id.foodbutton2);
        btnf3 = (Button)findViewById(R.id.foodbutton3);
        btnf4 = (Button)findViewById(R.id.foodbutton4);
        btnf5 = (Button)findViewById(R.id.foodbutton5);
        btnf1.setOnClickListener(this);
        btnf2.setOnClickListener(this);
        btnf3.setOnClickListener(this);
        btnf4.setOnClickListener(this);
        btnf5.setOnClickListener(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                click(checkedId);
            }
        });
        user=(UserData)getIntent().getSerializableExtra("user_data");
    }
    private void confirmshow(View v)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View view=layoutInflater.inflate(R.layout.food_confirm,null);
        PopupWindow popupWindow=new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //让弹窗出现在VIEW上方
        //popupWindow.showAtLocation(v,Gravity.NO_GRAVITY, (location[0]) - popupWidth / 2, location[1] - popupHeight);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Button confirmbtn=(Button) view.findViewById(R.id.confirm_buy);
        //Button canclebtn=(Button)view.findViewById(R.id.cancel_by);u
        confirmbtn.setOnClickListener(this);
        //canclebtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.foodbutton1:
                confirmshow(v);
                break;
            case R.id.confirm_buy:
                prop=new Property(user.getUserID(),1,1,1);
                DbUtils.insert(prop);
                Toast.makeText(StoreActivity.this,"购买成功",Toast.LENGTH_SHORT).show();
                user.setMoney(user.getMoney()-200);
                DbUtils.insert(user);
                break;
            case R.id.foodbutton2:

                break;
            case R.id.foodbutton4:

                break;
            case R.id.foodbutton5:

                break;
            default:
                break;
        }
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