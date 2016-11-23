package com.Data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table("Appearance")
public class Appearance {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int apprID;
    @Column("apprImageID")
    private int apprImageID;
    @Column("apprType")
    private int apprType;
    @Column("apprDescribe")
    private String apprDescribe;

    public int getApprID() {
        return apprID;
    }

    public int getApprImageID() {
        return apprImageID;
    }

    public int getApprType() {
        return apprType;
    }

    public String getApprDescribe() {
        return apprDescribe;
    }

}
