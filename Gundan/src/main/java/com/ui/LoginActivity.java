package com.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Data.UserData;
import com.base.basepedo.R;
import com.utils.DbUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class LoginActivity extends Activity {
    private static final String TAG = "用户登录";
    private EditText username;
    private EditText userpassword;
    private Button login;
    private Button register;
    private String userNameValue,passwordValue;
    private SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //初始化
        sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        username=(EditText)findViewById(R.id.login_username);
        userpassword=(EditText)findViewById(R.id.login_password);
        login=(Button)findViewById(R.id.button_login);
        register=(Button)findViewById(R.id.login_register);

        // 登录监听事件  现在默认为用户名为：liu 密码：123
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                userNameValue = username.getText().toString();
                passwordValue = userpassword.getText().toString();
                List<UserData> list=DbUtils.getQueryByWhere(UserData.class,"userName",new String[]{userNameValue});
                if(list.size()==0){
                    Toast.makeText(LoginActivity.this,"用户不存在", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(passwordValue.equals(list.get(0).getPassword()))
                    {
                        Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_NAME", userNameValue);
                        editor.putString("PASSWORD",passwordValue);
                        editor.commit();
                        //跳转界面
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("user_data",list.get(0));
                        LoginActivity.this.startActivity(intent);
                        //finish();

                    }
                    else{
                        Toast.makeText(LoginActivity.this,"密码错误，请重新登录", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
