package com.ui;

/**
 * Created by Administrator on 2016/11/22 0022.
 */


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Toast;

import com.Data.PetData;
import com.Data.Property;
import com.Data.UserData;
import com.base.basepedo.R;
import com.utils.DbUtils;

import java.util.List;

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
    int index = 0;
    final int type[]={1,2,3,4,5};
    private  final Integer[] imagelist =
            { R.drawable.food1,R.drawable.food2, R.drawable.food3, R.drawable.food4,R.drawable.food5 };
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
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Button confirmbtn=(Button) view.findViewById(R.id.confirm_buy);
        //Button canclebtn=(Button)view.findViewById(R.id.cancel_by);
        final ImageView image=(ImageView)view.findViewById(R.id.confirm_foodimage);
        image.setImageResource(imagelist[index]);
        //confirmbtn.setOnClickListener(this);
        confirmbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                List<Property> myfood1=DbUtils.getQueryByWhere(Property.class,"ptype",new String[]{String.valueOf(type[index])});
                if(user.getMoney()>=200)
                {
                    if (myfood1.size() == 0) {
                        prop = new Property(user.getUserID(), index + 1, 1, 1);
                        DbUtils.insert(prop);
                        Toast.makeText(StoreActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                        user.setMoney(user.getMoney() - 200);
                        DbUtils.update(user);
                    } else {
                        int i = myfood1.get(0).getNumber();
                        myfood1.get(0).setNumber(i + 1);
                        DbUtils.update(myfood1.get(0));
                        Log.d("db", "" + myfood1.get(0).getNumber());
                    }
                }
                else Toast.makeText(StoreActivity.this, "金币不足", Toast.LENGTH_SHORT).show();

            }
        });
        //canclebtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.foodbutton1:
                index=0;
                confirmshow(v);
                break;
            case R.id.foodbutton2:
                index=1;
                confirmshow(v);
                break;
            case R.id.foodbutton3:
                index=2;
                confirmshow(v);
                break;
            case R.id.foodbutton4:
                index=3;
                confirmshow(v);
                break;
            case R.id.foodbutton5:
                index=4;
                confirmshow(v);
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