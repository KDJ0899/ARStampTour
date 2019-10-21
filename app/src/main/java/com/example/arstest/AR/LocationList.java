package com.example.arstest.AR;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class LocationList {
    private List<LatLng> locations;

    public LocationList(){}
    public LocationList(List<LatLng> locations){
        this.locations=locations;
    }

    public List<LatLng> getLocations() {
        return locations;
    }
    public void setLocations(List<LatLng> locations) {
        this.locations = locations;
    }
}
