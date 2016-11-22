package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class CActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("This is C Activity!");
        tv.setGravity(Gravity.CENTER);
        setContentView(tv);
    }
}
