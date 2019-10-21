package com.example.arstest.DTO;

public class Reward {
    public int Reward_Id;
    public String Name;
    public int No_Stamp;
    public String Image;
    public int BRAND;
    public int CATEGORY;

    public int getReward_Id() {
        return Reward_Id;
    }

    public void setReward_Id(int reward_Id) {
        Reward_Id = reward_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNo_Stamp() {
        return No_Stamp;
    }

    public void setNo_Stamp(int no_Stamp) {
        No_Stamp = no_Stamp;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        Image = Image;
    }

    public int getBRAND() {
        return BRAND;
    }

    public void setBRAND(int BRAND) {
        this.BRAND = BRAND;
    }

    public int getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(int CATEGORY) {
        this.CATEGORY = CATEGORY;
    }
}
