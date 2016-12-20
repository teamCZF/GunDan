package com.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class EggService extends Service {
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startID){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("EGGSERVICE", "executed at: "+new Date().toString());
            }
        }).start();
        AlarmManager manager =(AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour =60*60*1000;//这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i=new Intent(this,AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent,flags,startID);
    }
}
