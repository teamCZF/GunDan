package com.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void  onReceive(Context context, Intent intent){
        Intent i=new Intent(context,EggService.class);
        context.startService(i);
    }
}
