package com.Data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table("tool")
public class tool {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int toolID;
    @Column("toolName")
    private String toolName;
    @Column("toolImageID")
    private int toolImageID;
    @Column("effect")
    private int effect;

    public int getEffect() {
        return effect;
    }

    public int getToolID() {
        return toolID;
    }

    public int getToolImageID() {
        return toolImageID;
    }

    public String getToolName() {
        return toolName;
    }

}
