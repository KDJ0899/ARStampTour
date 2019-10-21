package com.example.arstest.AR;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.arstest.DTO.attraction;
import com.example.arstest.DataStorage;
import com.example.arstest.MainActivity;
import com.example.arstest.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalculateDistance extends Thread {

    private  List<attraction> Attractions;
    public static LatLng currentPosition;

    LocationManager locManager; // 위치 정보 프로바이더
    LocationListener locationListener;

    Context context;



    public CalculateDistance(List<attraction> Attractions){
        this.Attractions = Attractions;
    }

    @Override
    public void run() {
        super.run();
        try{

            for(attraction obj : DataStorage.attractions){
                obj.setDistance((int) LocationDistance.distance(Double.parseDouble(obj.getLatitude()),Double.parseDouble(obj.getLongitude()),currentPosition.latitude,currentPosition.longitude));
            }

//            Collections.sort(Attractions, new Comparator<attraction>() {
//                @Override
//                public int compare(attraction o1, attraction o2) {
//                    return o2.getDistance()-o1.getDistance();
//                }
//            });
            Thread.sleep(10000);
        }
        catch (Exception e){

        }
    }
}
