package com.example.arstest;

import com.example.arstest.DTO.RegisterTour;
import com.example.arstest.DTO.Reward;
import com.example.arstest.DTO.attraction;
import com.example.arstest.DTO.localGU;
import com.example.arstest.DTO.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStorage {

    public static List<localGU> guList;
    public static Map<Integer,List<attraction>> guMap;
    public static List<attraction> attractions;
    public static List<Reward> rewards;
    public static List<RegisterTour> registerTours;
    public static user userDetail = new user();
    public static String ipAdress ="http://10.0.102.44:3000";
}
