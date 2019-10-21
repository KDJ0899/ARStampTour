package com.example.arstest.DTO;

public class user {
    int User_Id;
    String ID;
    String Password;
    String Name;
    String Birthday;
    String Phone_No;
    String Sex;

    public String getName() {
        return Name;
    }

    public String getPhone_No() {
        return Phone_No;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public void setPhone_No(String phone_No) {
        Phone_No = phone_No;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }
}
