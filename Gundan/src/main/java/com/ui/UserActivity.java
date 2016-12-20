package com.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.base.basepedo.R;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class UserActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_user);
        init();

    }
    private void init() {
        Button SetInfo = (Button) findViewById(R.id.button_myInfo);
        Button historyStep = (Button) findViewById(R.id.button_historyStep);
        Button myHonor = (Button) findViewById(R.id.button_myHonor);
        Button myCollection = (Button) findViewById(R.id.button_myCollection);
        SetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, SetInfoActivity.class);
                startActivity(intent);
            }
        });
        historyStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        myHonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        myCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
