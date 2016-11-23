package com.Data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table("Property")
public class Property {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int pUesrID;
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int pGoodsID;
    @Column("number")
    private int number;

    public int getpGoodsID() {
        return pGoodsID;
    }

    public void setpGoodsID(int pGoodsID) {
        this.pGoodsID = pGoodsID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getpUesrID() {
        return pUesrID;
    }

    public void setpUesrID(int pUesrID) {
        this.pUesrID = pUesrID;
    }
}

