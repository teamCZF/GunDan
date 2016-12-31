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
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Data.PetData;
import com.Data.UserData;
import com.base.basepedo.R;
import com.config.Constant;
import com.service.EggService;
import com.service.StepService;
import com.utils.DbUtils;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends Activity implements Handler.Callback,View.OnClickListener{
    //循环取当前时刻的步数中间的间隔时间
    private long TIME_INTERVAL = 500;
    private UserData user;
    private PetData myEgg;
    private static int lastStep=0;
    private TextView text_step;
    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Handler delayHandler;
    private GifImageView egg_gif;
    private TextView petMoney;

    private static ProgressBar HungerProgress;
    private static ProgressBar HappinessProgress;
    private static ProgressBar experienceProgress;
    //
    private int [] eggImage=new int []{
            R.mipmap.egg0,
            R.mipmap.egg1

    };
    int currentImage=0;

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
                int currentStep=msg.getData().getInt("step");
                text_step.setText( currentStep+ "");
                if(currentStep-lastStep>200){
                    user.setMoney(user.getMoney()+20);
                    DbUtils.update(user);
                    petMoney.setText(user.getMoney()+"");
                }
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
        user=(UserData)getIntent().getSerializableExtra("user_data");
        myEgg=DbUtils.getQueryAll(PetData.class).get(0);
        init();
    }
    private void init() {
        text_step = (TextView) findViewById(R.id.text_step);
        delayHandler = new Handler(this);
        //初始化界面
        Button feedButton=(Button)findViewById(R.id.feed_button);
        feedButton.setOnClickListener(this);
        Button storeButton=(Button)findViewById(R.id.button_store);
        storeButton.setOnClickListener(this);
        Button rankButton=(Button)findViewById(R.id.button_rank);
        rankButton.setOnClickListener(this);
        Button setButton=(Button)findViewById(R.id.button_user);
        setButton.setOnClickListener(this);
        //宠物名
        TextView petName=(TextView)findViewById(R.id.pet_name);
        petName.setText(myEgg.getPetName()+"");
        petName.setOnClickListener(this);
        //宠物等级
        TextView petLevel=(TextView)findViewById(R.id.pet_level);
        petLevel.setText(myEgg.getPetLevel()+"");
        petLevel.setOnClickListener(this);

        petMoney=(TextView)findViewById(R.id.pet_money);
        petMoney.setText(user.getMoney()+"");
        egg_gif=(GifImageView)findViewById(R.id.egg);
        egg_gif.setImageResource(myEgg.getPetImageID());
        egg_gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImage>=1){
                    currentImage=-1;
                }
                egg_gif.setImageResource(eggImage[++currentImage]);
            }
        });

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.button_store:
                Intent intent1=new Intent(MainActivity.this,StoreActivity.class);
                intent1.putExtra("user_data",user);
                startActivity(intent1);
                break;
            case R.id.button_rank:
                Intent intent2=new Intent(MainActivity.this,RankActivity.class);
                intent2.putExtra("user_data",user);
                startActivity(intent2);
                break;
            case R.id.button_user:
                Intent intent3=new Intent(MainActivity.this,UserActivity.class);
                intent3.putExtra("user_data",user);
                startActivity(intent3);
                break;
            case R.id.feed_button:
                popShowUp(v);
                //Toast.makeText(MainActivity.this, "喂食成功", Toast.LENGTH_SHORT).show();
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
        View view=layoutInflater.inflate(R.layout.main_food_pop,null);
        PopupWindow popupWindow=new PopupWindow(view,
                WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();    //  获取测量后的宽度
        int popupHeight = view.getMeasuredHeight();  //获取测量后的高度
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //让弹窗出现在VIEW上方
        //popupWindow.showAtLocation(v,Gravity.NO_GRAVITY, (location[0]) - popupWidth / 2, location[1] - popupHeight);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
        ImageButton feedButton1=(ImageButton) view.findViewById(R.id.eat_food1);
        ImageButton feedButton2=(ImageButton) view.findViewById(R.id.eat_food2);
        feedButton1.setOnClickListener(this);
        feedButton2.setOnClickListener(this);
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
        //init();
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
