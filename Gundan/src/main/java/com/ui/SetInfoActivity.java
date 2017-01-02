package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Data.UserData;
import com.base.basepedo.R;
import com.utils.DbUtils;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class SetInfoActivity extends Activity implements View.OnClickListener{
    private Button backToUser;
    private Button saveInfo;
    private UserData user;
    private static EditText etnick;
    private static EditText etsex;
    private static EditText etage;
    private static EditText etheight;
    private static EditText etweight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setinfo);
        backToUser =(Button)findViewById(R.id.backToUser);
        backToUser.setOnClickListener(this);
        saveInfo =(Button)findViewById(R.id.save_info);
        saveInfo.setOnClickListener(this);
        user=(UserData)getIntent().getSerializableExtra("user_data");
        etnick=(EditText)findViewById(R.id.text_my_name);
        etsex=(EditText)findViewById(R.id.text_my_sex);
        etage=(EditText)findViewById(R.id.text_my_age);
        etweight=(EditText)findViewById(R.id.text_my_weight);
        etheight=(EditText)findViewById(R.id.text_my_height);

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backToUser:
                finish();
                break;
            case R.id.save_info:
                String name =etnick.getText().toString();
                user.setNickName(name);
                String sex =etsex.getText().toString();
                user.setSex(sex);
                String age =etage.getText().toString();
                user.setAge(age);
                String height =etheight.getText().toString();
                user.setHeight(height);
                String weight =etweight.getText().toString();
                user.setWeight(weight);
                DbUtils.update(user);
                finish();
                break;
            default:
                break;
        }

    }
}
