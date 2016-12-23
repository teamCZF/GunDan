package com.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.base.basepedo.R;
import com.config.Constant;
import com.service.EggService;
import com.service.StepService;

public class MainActivity extends Activity implements Handler.Callback,View.OnClickListener{
    //循环取当前时刻的步数中间的间隔时间
    private long TIME_INTERVAL = 500;
    private TextView text_step;
    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Handler delayHandler;

    //

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.MSG_FROM_SERVER:
                // 更新界面上的步数
                text_step.setText(msg.getData().getInt("step") + "");
                delayHandler.sendEmptyMessageDelayed(Constant.REQUEST_SERVER, TIME_INTERVAL);
                break;
            case Constant.REQUEST_SERVER:
                try {
                    Message msg1 = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                    msg1.replyTo = mGetReplyMessenger;
                    messenger.send(msg1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }
    private void init() {
        text_step = (TextView) findViewById(R.id.text_step);
        delayHandler = new Handler(this);
        Button menuButton=(Button)findViewById(R.id.menu_button);
        menuButton.setOnClickListener(this);
        Button storeButton=(Button)findViewById(R.id.button_store);
        storeButton.setOnClickListener(this);
        Button rankButton=(Button)findViewById(R.id.button_rank);
        rankButton.setOnClickListener(this);
        Button setButton=(Button)findViewById(R.id.button_user);
        setButton.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.menu_button:
                popShowUp(v);
                break;
            case R.id.button_store:
                Intent intent1=new Intent(MainActivity.this,StoreActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_rank:
                Intent intent2=new Intent(MainActivity.this,RankActivity.class);
                startActivity(intent2);
                break;
            case R.id.button_user:
                Intent intent3=new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent3);
                break;
            case R.id.feed_button:
                Toast.makeText(MainActivity.this, "喂食成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clothes_button:
                Toast.makeText(MainActivity.this, "换装成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
    private void popShowUp(View v){
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View view=layoutInflater.inflate(R.layout.poplayout,null);
        PopupWindow popupWindow=new PopupWindow(view,
                WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();    //  获取测量后的宽度
        int popupHeight = view.getMeasuredHeight();  //获取测量后的高度
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //popupWindow.showAtLocation(v,Gravity.NO_GRAVITY, (location[0]) - popupWidth / 2, location[1] - popupHeight);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
        Button feedButton=(Button) view.findViewById(R.id.feed_button);
        Button dressButton=(Button)view.findViewById(R.id.clothes_button);
        feedButton.setOnClickListener(this);
        dressButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupStepService();
    }

    private void setupStepService() {
        Intent intent = new Intent(this, StepService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }
    private void setupEggService(){
        Intent intent= new Intent(this, EggService.class);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

}
