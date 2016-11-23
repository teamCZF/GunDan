package com.Data;

/**
 * Created by Administrator on 2016/11/19 0019.
 */
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
@Table("pet")
public class PetData{
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int petID;
    @Column("petName")
    private String petName;
    @Column("petLevel")//等级
    private int petLevel;
    @Column("healthPoint")
    private int healthPoint;
    @Column("moodPoint")
    private int moodPoint;
    @Column("exp")
    private long exp;
    @Column("petImageID")
    private int petImageID;

    public int getPetID(){return  petID;}

    public void setPetID(int id){this.petID=id;}

    public  String getPetName(){return petName;}

    public  void setPetName(String name){this.petName=name;}

    public int getPetLevel(){return petLevel;}

    public void setPetLevel(int level){this.petLevel=level;}

    public int getHealthPoint(){return healthPoint;}

    public  void addHealthPoint(int hp){this.healthPoint+=hp;}

    public  int getMoodPoint(){return  moodPoint;}

    public void addMoodPoint(int mp){this.moodPoint+=mp;}

    public long getExp(){return exp;}

    public void addExp(long exp){this.exp+=exp;}

    public int getPetImageID(){return petImageID;}

    public void setPetImageID(int imageID){this.petImageID=imageID;}

    public void petInit(){}

}
