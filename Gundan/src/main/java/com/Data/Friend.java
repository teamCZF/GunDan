package com.Data;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class Friend {
    private String name;
    private int imageId;
    private String step;
    public Friend(String name,int imageId,String step){
        this.name=name;
        this.imageId=imageId;
        this.step=step;
    }
    public String getName(){return  name;}

    public int getImageId() {
        return imageId;
    }
    public String getStep() {
        return step;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
