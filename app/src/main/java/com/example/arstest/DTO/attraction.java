package com.example.arstest.DTO;

public class attraction {
    int Att_Id;
    String Name;
    String Info;
    String Address;
    String Image;
    String Latitude;
    String Longitude;
    int LOCAL_GU;

    public int getAtt_Id() {
        return Att_Id;
    }

    public void setAtt_Id(int att_Id) {
        Att_Id = att_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public int getLOCAL_GU() {
        return LOCAL_GU;
    }

    public void setLOCAL_GU(int LOCAL_GU) {
        this.LOCAL_GU = LOCAL_GU;
    }
}
