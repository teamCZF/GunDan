package com.Data;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

import com.base.basepedo.R;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

@Table("pet")
public class PetData implements Serializable {
    @PrimaryKey(AssignType.BY_MYSELF)
    private int userID;
    @Column("petName")
    private String petName;
    @Column("petLevel")//等级
    private int petLevel;
    @Column("healthPoint")
    private int healthPoint;
    @Column("moodPoint")
    private int moodPoint;
    @Column("exp")
    private int exp;
    @Column("petImageID")
    private int petImageID;
    public PetData(){
        this.petName="滚滚";
        this.petLevel=0;
        this.exp=0;
        this.healthPoint=100;
        this.moodPoint=100;
        this.petImageID= R.mipmap.egg0;
    }
    public PetData(int userID){
        this.userID=userID;
        this.petName="滚滚";
        this.petLevel=0;
        this.exp=0;
        this.healthPoint=100;
        this.moodPoint=100;
        this.petImageID= R.mipmap.egg0;
    }
    public void  init(){
        this.petName="滚滚";
        this.petLevel=0;
        this.exp=0;
        this.healthPoint=100;
        this.moodPoint=100;
        this.petImageID= R.mipmap.egg0;
    }
    private static String[] mHungerDialog={
            "要不要一起吃？","....还想吃",
            "好饿....","这是什么鬼东西！"
    };
    private static int[] hungerHeadImage = {
            R.mipmap.egg_eat,R.mipmap.egg_blink1
    };
    private static String[] mTouchDialog = {
            "舒服多了",                  "……（愉悦）",
            "你看见我的小黄鸭了么",       "……（心情不错）",
            "肥皂找不到了",              "快去给本小姐烧水！",
            "干的好，这一鞭是赏你的！！"
    };
    private static int[] onTouchImage = {
            R.mipmap.egg_glass1,R.mipmap.egg_glass2
    };
    public static String getHungerDialog(int index) {
        return mHungerDialog[index];
    }
    public static String getTouchDialog(int index) {
        return mTouchDialog[index];
    }
    public static int getHungerHeadImage(int index) {
        return hungerHeadImage[index];
    }
    public static int getOnTouchImage(int index) {
        return onTouchImage[index];
    }

    public  String getPetName(){return petName;}
    public int getUserID(){return userID;}
    public void setUserID(int UID){userID=UID;}
    public  void setPetName(String name){this.petName=name;}

    public int getPetLevel(){return petLevel;}

    public void setPetLevel(int level){this.petLevel=level;}

    public int getHealthPoint(){return healthPoint;}

    public  void setHealthPoint(int hp){this.healthPoint=hp;}

    public  int getMoodPoint(){return  moodPoint;}

    public void setMoodPoint(int moodPoint) {
        this.moodPoint = moodPoint;
    }

    public void addMoodPoint(int mp){this.moodPoint+=mp;}

    public int getExp(){return exp;}

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void addExp(int exp){this.exp+=exp;}

    public int getPetImageID(){return petImageID;}

    public void setPetImageID(int imageID){this.petImageID=imageID;}

    public static String[] getmTouchDialog() {
        return mTouchDialog;
    }

    public static int[] getHungerHeadImage() {
        return hungerHeadImage;
    }

    public static int[] getOnTouchImage() {
        return onTouchImage;
    }

    public static String[] getmHungerDialog() {
        return mHungerDialog;
    }
}

