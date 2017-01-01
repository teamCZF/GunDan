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
    @PrimaryKey(AssignType.BY_MYSELF)
    private int pUesrID;
    @Column("pGoodsID")
    private int pGoodsID;
    @Column("number")
    private int number;
    @Column("ptype")
    private int ptype;
    public Property(int userid,int goodsid,int number,int type)
    {
        this.pUesrID=userid;
        this.pGoodsID=goodsid;
        this.number=userid;
        this.ptype=type;
    }

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

    public int getPtype() {
        return ptype;
    }
    public void setPtype(int ptype)
    {
        this.ptype = ptype;
    }

}

