package com.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Data.UserData;
import com.base.basepedo.R;
import com.utils.DbUtils;

import static com.base.basepedo.R.id.button_myHonor;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class UserActivity extends Activity implements View.OnClickListener{
    private static UserData user;
    private static SharedPreferences sp;
    private static String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_user);
        user=(UserData)getIntent().getSerializableExtra("user_data");
        sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        name=sp.getString("USER_NAME","");
        init();
    }
    private void init() {
        TextView mn =(TextView)findViewById(R.id.my_name);
        user=DbUtils.getQueryByWhere(UserData.class,"userName",new String[]{name}).get(0);
        mn.setText(user.getNickName());
        Button SetInfo = (Button) findViewById(R.id.button_myInfo);
        Button historyStep = (Button) findViewById(R.id.button_historyStep);
        Button myHonor = (Button) findViewById(button_myHonor);

        Button logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);
        SetInfo.setOnClickListener(this);
        historyStep.setOnClickListener(this);
        myHonor.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_myInfo:
                Intent intent1=new Intent(this,SetInfoActivity.class);
                intent1.putExtra("user_data",user);
                startActivity(intent1);
                break;
            case R.id.button_historyStep:
                break;
            case button_myHonor:
                break;
            /*case  R.id.user_back:
                finish();
                break;*/
            case R.id.logout:
                sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }


    }
    @Override
    protected void onResume(){
        super.onResume();
        init();
    }
}
