package com.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Data.UserData;
import com.base.basepedo.R;
import com.utils.DbUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class RegisterActivity extends Activity {
    private EditText username;
    private EditText passwd1;
    private EditText passwd2;
    String uname,pswd1,pswd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button regist=(Button)findViewById(R.id.button_register);
        username=(EditText)findViewById(R.id.register_username);
        passwd1=(EditText)findViewById(R.id.register_password);
        passwd2=(EditText)findViewById(R.id.register_password2);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname=username.getText().toString();
                pswd1=passwd1.getText().toString();
                pswd2=passwd2.getText().toString();
                if(TextUtils.isEmpty(uname)||TextUtils.isEmpty(pswd1)||TextUtils.isEmpty(pswd2))
                {
                    Toast.makeText(RegisterActivity.this,"请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
                else{
                    List<UserData> list= DbUtils.getQueryByWhere(UserData.class,"userName",new String[]{uname});
                    if(list.size()!=0){

                        Toast.makeText(RegisterActivity.this,"用户名已存在", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(!pswd1.equals(pswd2)){
                            Toast.makeText(RegisterActivity.this,"密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            UserData user=new UserData(uname,pswd1);
                            user.setUserImageID(R.mipmap.egg0);
                            DbUtils.insert(user);
                            Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                            intent.putExtra("user_data",user);
                            startActivity(intent);
                            finish();
                        }
                    }

                }
            }
        });


    }
}