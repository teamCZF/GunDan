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
    private EditText etname;
    private EditText etsex;
    private EditText etage;
    private EditText etheight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setinfo);
        backToUser =(Button)findViewById(R.id.backToUser);
        backToUser.setOnClickListener(this);
        saveInfo =(Button)findViewById(R.id.save_info);
        saveInfo.setOnClickListener(this);
        user=(UserData)getIntent().getSerializableExtra("user_data");
        etname=(EditText)findViewById(R.id.text_my_name);
        etsex=(EditText)findViewById(R.id.text_my_sex);
        etage=(EditText)findViewById(R.id.text_my_age);
        etheight=(EditText)findViewById(R.id.text_my_height);
        EditText en=(EditText)findViewById(R.id.text_my_name);
        en.setHint(user.getNickName());

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backToUser:
                finish();
                break;
            case R.id.save_info:
                String name =etage.getText().toString();
                user.setNickName(name);
                String sex =etage.getText().toString();
                user.setSex(sex);
                String age =etage.getText().toString();
                user.setAge(age);
                String height =etage.getText().toString();
                user.setHeight(height);
                DbUtils.update(user);
                finish();
                break;
            default:
                break;
        }

    }
}
