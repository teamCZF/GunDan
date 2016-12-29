package com.Data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/19 0019.
 */
@Table("user")
public class UserData implements Serializable{
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int userID;
    @Column("userName")
    private String userName;
    @Column("height")
    private int height;
    @Column("weight")
    private int weight;
    @Column("password")
    private String password;
    @Column("sex")
    private  String sex;
    @Column("age")
    private int age;
    @Column("money")
    private long money;
    @Column("uerImageID")
    private int userImageID;
    @Column("totalStep")
    private long totalStep;
    @Mapping(Relation.OneToOne)
    public PetData egg;
    @Mapping(Relation.OneToMany)
    public ArrayList<Friend> friends;
    @Mapping(Relation.OneToMany)
    public ArrayList<Food> foods;
    @Mapping(Relation.OneToMany)
    public  ArrayList<Appearance> appearances;
    @Mapping(Relation.OneToMany)
    public ArrayList<tool> tools;


    public  UserData(){}
    public UserData(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getUserImageID() {
        return userImageID;
    }
    public void setUserImageID(int userImageID) {
        this.userImageID = userImageID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getMoney() {
        return money;
    }
    public void setMoney(long money) {
        this.money = money;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public long getTotalStep() {
        return totalStep;
    }
    public void setTotalStep(long totalStep) {
        this.totalStep = totalStep;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
