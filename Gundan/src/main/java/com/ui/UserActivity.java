package com.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.base.basepedo.R;

import static com.base.basepedo.R.id.button_myHonor;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class UserActivity extends Activity implements View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_user);
        init();

    }
    private void init() {
        Button SetInfo = (Button) findViewById(R.id.button_myInfo);
        Button historyStep = (Button) findViewById(R.id.button_historyStep);
        Button myHonor = (Button) findViewById(button_myHonor);
        Button backToMain=(Button)findViewById(R.id.user_back);
        Button logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);
        SetInfo.setOnClickListener(this);
        historyStep.setOnClickListener(this);
        myHonor.setOnClickListener(this);
        backToMain.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_myInfo:
                Intent intent1=new Intent(this,SetInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_historyStep:
                break;
            case button_myHonor:
                break;
            case  R.id.user_back:
                finish();
                break;
            case R.id.logout:
                SharedPreferences sp=getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
            default:
                break;
        }

    }
}
