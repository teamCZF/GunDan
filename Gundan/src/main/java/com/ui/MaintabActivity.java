package com.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.Data.UserData;
import com.utils.DbUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class MaintabActivity extends Activity{
    private String DB_NAME = "GunDan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbUtils.createDb(this,DB_NAME);
        SharedPreferences sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        String name=sp.getString("USER_NAME","");
        String passwd=sp.getString("PASSWORD","");
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(passwd))
        {
            Intent intent=new Intent(MaintabActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        else
        {
            List<UserData> list=DbUtils.getQueryByWhere(UserData.class,"userName",new String[]{name});
            Intent intent=new Intent(MaintabActivity.this,MainActivity.class);
            intent.putExtra("user_data",list.get(0));
            startActivity(intent);
            finish();
        }
    }
}
