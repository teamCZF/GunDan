package com.Data;

import com.base.basepedo.R;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table("food")
public class Food {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int foodID;
    @Column("foodName")
    private  String foodName;
    @Column("foodImageID")
    private int foodImageID;
    @Column("addedExp")
    private long addedExp;
    @Column("addedHP")
    private int addedHP;
    @Column("addedMP")
    private int addedMP;
    @Column("foodDescribe")

    private String foodDescribe;

    public int getFoodID() {
        return foodID;
    }
    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public int getFoodImageID() {
        return foodImageID;
    }
    public void setFoodImageID(int foodImageID) {
        this.foodImageID = foodImageID;
    }
    public int getAddedHP() {
        return addedHP;
    }
    public void setAddedHP(int addedHP) {
        this.addedHP = addedHP;
    }
    public long getAddedExp() {
        return addedExp;
    }
    public void setAddedExp(long addedExp) {
        this.addedExp = addedExp;
    }
    public int getAddedMP() {
        return addedMP;
    }
    public void setAddedMP(int addedMP) {
        this.addedMP = addedMP;
    }

    public String getFoodDescribe() {
        return foodDescribe;
    }


}
