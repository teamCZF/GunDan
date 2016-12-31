package com.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.Data.PetData;
import com.utils.DbUtils;

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
                PetData egg= DbUtils.getQueryAll(PetData.class).get(0);
                int eggHp=egg.getHealthPoint();
                int eggMp=egg.getMoodPoint();
                if(eggHp>0){
                    eggHp--;
                    egg.setHealthPoint(eggHp);

                }
                if(eggMp>0){
                    eggMp--;
                    egg.setMoodPoint(eggMp);
                }
                DbUtils.update(egg);
                Log.i("EGGSERVICE", "executed at: "+new Date().toString()+" EggHP="+Integer.valueOf(egg.getHealthPoint()).toString());
            }
        }).start();
        AlarmManager manager =(AlarmManager) getSystemService(ALARM_SERVICE);
        int minutes =7*60*1000;//这是7分钟的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime()+minutes;
        Intent i=new Intent(this,AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent,flags,startID);
    }
}
