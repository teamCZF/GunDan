package com.Data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table("Background")
public class Background {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int bgID;
    @Column("bgImageID")
    private int bgImageID;

    public int getBgID() {
        return bgID;
    }

    public int getBgImageID() {
        return bgImageID;
    }
}
