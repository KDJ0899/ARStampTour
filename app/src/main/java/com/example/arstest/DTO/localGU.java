package com.example.arstest.DTO;

public class localGU {
    int Gu_Id;
    String Name;
    String Info;
    String Latitude;
    String Longitude;
    String Image;
    String MainAttraction;
    int Local_Si;

    public localGU(int Gu_Id, String name, String info, String latitude, String longitude, String image, String MainAttraction, int local_Si) {
        this.Gu_Id = Gu_Id;
        this.Name = name;
        this.Info = info;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.Image = image;
        this.MainAttraction = MainAttraction;
        Local_Si = local_Si;
    }

    public int getGu_Id() {
        return Gu_Id;
    }

    public String getName() {
        return Name;
    }

    public String getInfo() {
        return Info;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getImage() {
        return Image;
    }

    public String getMainAttraction() {
        return MainAttraction;
    }

    public int getLocal_Si() {
        return Local_Si;
    }
}
