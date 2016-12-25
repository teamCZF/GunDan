package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.base.basepedo.R;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class SetInfoActivity extends Activity implements View.OnClickListener{
    private Button backToUser;
    private Button saveInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setinfo);
        backToUser =(Button)findViewById(R.id.backToUser);
        backToUser.setOnClickListener(this);
        saveInfo =(Button)findViewById(R.id.save_info);
        saveInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backToUser:
                finish();
                break;
            case R.id.save_info:
                break;
            default:
                break;
        }

    }
}
