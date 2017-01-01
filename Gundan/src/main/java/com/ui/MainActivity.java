package com.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
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
import android.widget.ImageView;
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

import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends Activity implements Handler.Callback,View.OnClickListener{
    //循环取当前时刻的步数中间的间隔时间
    private long TIME_INTERVAL = 500;

    private static UserData user;
    private static PetData myEgg;
    private static int lastStep=0;
    private static TextView text_step;
    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Handler delayHandler;
    private GifImageView egg_gif;
    private GifImageView hatch_egg;
    private TextView petMoney;
    private TextView petName;
    private TextView petLevel;
    private Timer timer=new Timer(true);
    //进度条
    private static ProgressBar hpProgress;
    private static ProgressBar mpProgress;
    private static ProgressBar expProgress;
    //
    private static int baseEXP=1000;
    private static int maxPetLevel=3;
    private static int currentLevel;
    private static int maxExp=1000;
    private static int egghp;
    private static int eggmp;
    private static int exp;

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
                    lastStep=currentStep;
                    user.setMoney(user.getMoney()+20);
                    DbUtils.update(user);
                    petMoney.setText(user.getMoney()+"");
                }
                if(currentStep-lastStep<0)
                    lastStep=0;
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
            //喂食
            case 3:
                //玩耍
            case 4:
                egg_gif.setImageResource(myEgg.getPetImageID());
                break;
            case 5://孵蛋
                hatch_egg.setVisibility(View.GONE);
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(UserData)getIntent().getSerializableExtra("user_data");
        myEgg=DbUtils.getQueryAll(PetData.class).get(0);
        init();
    }

    //界面初始化
    private void init() {
        text_step = (TextView) findViewById(R.id.text_step);
        delayHandler = new Handler(this);
        //初始化界面
        hpProgress=(ProgressBar)findViewById(R.id.hp_progressbar);
        mpProgress=(ProgressBar)findViewById(R.id.mp_progressbar);
        expProgress=(ProgressBar)findViewById(R.id.exp_progressbar);
        initProgressBar();
        Button feedButton=(Button)findViewById(R.id.feed_button);
        feedButton.setOnClickListener(this);
        Button storeButton=(Button)findViewById(R.id.button_store);
        storeButton.setOnClickListener(this);
        Button rankButton=(Button)findViewById(R.id.button_rank);
        rankButton.setOnClickListener(this);
        Button setButton=(Button)findViewById(R.id.button_user);
        setButton.setOnClickListener(this);
        Button collectionButton=(Button)findViewById(R.id.button_collection);
        collectionButton.setOnClickListener(this);
        //宠物名
        petName=(TextView)findViewById(R.id.pet_name);
        petName.setText(myEgg.getPetName()+"");
        petName.setOnClickListener(this);
        //宠物等级
        petLevel=(TextView)findViewById(R.id.pet_level);
        petLevel.setText(myEgg.getPetLevel()+"");
        petLevel.setOnClickListener(this);
        petMoney=(TextView)findViewById(R.id.pet_money);
        petMoney.setText(user.getMoney()+"");
        egg_gif=(GifImageView)findViewById(R.id.egg);
        hatch_egg=(GifImageView)findViewById(R.id.egg_hatch);
        egg_gif.setImageResource(myEgg.getPetImageID());
        egg_gif.setOnClickListener(this);

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
            case R.id.button_collection:
                Intent intent4=new Intent(MainActivity.this,collection.class);
                intent4.putExtra("user_data",user);
                startActivity(intent4);
                break;
            case R.id.feed_button:
                popShowUp(v);
                //Toast.makeText(MainActivity.this, "喂食成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clothes_button:
                Toast.makeText(MainActivity.this, "换装成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.egg:
                onTouch();
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
        ImageView feedButton1=(ImageView) view.findViewById(R.id.eat_food1);
        ImageView feedButton2=(ImageView) view.findViewById(R.id.eat_food2);
        feedButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFeed();
            }
        });
        feedButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFeed();
            }
        });
    }
    private void onFeed(){
        int egghp=myEgg.getHealthPoint()+60;
        int eggmp=myEgg.getMoodPoint()+20;
        int nexp=myEgg.getExp()+100;
        if(eggmp>100)eggmp=100;
        if(egghp>100)egghp=100;
        if(nexp>=expProgress.getMax())upgrade();
        updateProgressBar(egghp,eggmp,nexp);
        Toast.makeText(MainActivity.this,"喂食成功\n+60HP\t+20mp\t+100exp！！",Toast.LENGTH_SHORT).show();
        egg_gif.setImageResource(R.mipmap.egg_eat);
        TimerTask task = new TimerTask(){
            public void run(){
                // 在此处添加执行的代码
                Message msg=new Message();
                msg.what=3;
                delayHandler.sendMessage(msg);

            }
        };
        timer = new Timer();
        timer.schedule(task, 3000);//开启定时器，delay 1s后执行task
    }
    private void onTouch(){
        int eggmp=myEgg.getMoodPoint()+10;
        int nexp=myEgg.getExp()+10;
        if(eggmp>100)eggmp=100;
        if(nexp>=expProgress.getMax())upgrade();
        updateProgressBar(egghp,eggmp,nexp);
        Toast.makeText(MainActivity.this,"好无聊\n+2mp\t+10exp",Toast.LENGTH_SHORT).show();
        egg_gif.setImageResource(R.mipmap.egg_blink1);
        TimerTask task = new TimerTask(){
            public void run(){
                // 在此处添加执行的代码
                Message msg=new Message();
                msg.what=4;
                delayHandler.sendMessage(msg);

            }
        };
        timer = new Timer();
        timer.schedule(task, 3000);//开启定时器，delay 1s后执行task
    }

    private void upgrade(){
        currentLevel++;
        //满级
        if(currentLevel>maxPetLevel){
            myEgg.init();
            DbUtils.update(myEgg);
            getEggState();
            maxExp=baseEXP;
            expProgress.setProgress(0);
            expProgress.setMax(maxExp);
            petLevel.setText(currentLevel+"");
            updateProgressBar(100,100,0);
            hatch_egg.setVisibility(View.VISIBLE);
            TimerTask task = new TimerTask(){
                public void run(){
                    // 在此处添加执行的代码
                    Message msg=new Message();
                    msg.what=5;//孵蛋
                    delayHandler.sendMessage(msg);
                }
            };
            timer = new Timer();
            timer.schedule(task, 5000);//开启定时器，delay 10s后执行task
        }
        else {
            int m=1;
            for(int j=0;j<currentLevel;j++){
                m=m*2;
            }
            maxExp=m*baseEXP;
            expProgress.setProgress(0);
            expProgress.setMax(maxExp);
            petLevel.setText(currentLevel+"");
            updateProgressBar(100,100,0);

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        setupStepService();
        setupEggService();
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
    public class AlarmReceiver extends BroadcastReceiver {
        @Override
        public void  onReceive(Context context, Intent intent){

            Intent i=new Intent(context,EggService.class);
            context.startService(i);
        }
    }

    //更新进度条
    public static void setProgress(int egghp, int eggmp, int exp) {
        hpProgress.setProgress(egghp);
        mpProgress.setProgress(eggmp);
        expProgress.setProgress(exp);
    }
    //获取宠物状态
    public static void getEggState(){
        myEgg=DbUtils.getQueryAll(PetData.class).get(0);
        egghp=myEgg.getHealthPoint();
        eggmp=myEgg.getMoodPoint();
        currentLevel=myEgg.getPetLevel();
        exp=myEgg.getExp();
    }
    //设置宠物状态
    public static void setEggState(int hp,int mp,int exp){
        myEgg.setHealthPoint(hp);
        myEgg.setMoodPoint(mp);
        myEgg.setExp(exp);
        DbUtils.update(myEgg);
    }
    //初始化进度条
    public static void initProgressBar(){
        hpProgress.setMax(100);
        mpProgress.setMax(100);
        currentLevel=myEgg.getPetLevel();
        int m=1;
        for(int j=0;j<currentLevel;j++){
            m=m*2;
        }
        maxExp=m*baseEXP;
        expProgress.setMax(maxExp);
        getEggState();
        setEggState(egghp,eggmp,exp);
        setProgress(egghp,eggmp,exp);
    }
    //更新进度条
    public static void updateProgressBar(int hp,int mp,int nexp){
        egghp=hp;
        eggmp=mp;
        exp=nexp;
        setEggState(egghp,eggmp,exp);
        setProgress(egghp,eggmp,exp);
    }

}
