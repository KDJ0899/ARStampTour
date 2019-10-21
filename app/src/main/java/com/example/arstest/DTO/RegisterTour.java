package com.example.arstest.DTO;

public class RegisterTour {

    int req_id;
    int User_Id;
    int Gu_Id;
    int Complete;

    public int getReq_id() {
        return req_id;
    }

    public void setReq_id(int req_id) {
        this.req_id = req_id;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public int getGu_Id() {
        return Gu_Id;
    }

    public void setGu_Id(int gu_Id) {
        Gu_Id = gu_Id;
    }

    public int isComplete() {
        return Complete;
    }

    public void setComplete(int complete) {
        Complete = complete;
    }
}
