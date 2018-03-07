package com.example.bokoch.maps;


public class MarkerMaps {
    private double mLat;
    private double mLng;
    private String mNamePlaces;
    private int mId;

    public MarkerMaps(int id, String namePlaces, double lat, double lng) {
        mLat = lat;
        mLng = lng;
        mNamePlaces = namePlaces;
        mId = id;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public String getNamePlaces() {
        return mNamePlaces;
    }

    public void setNamePlaces(String namePlaces) {
        mNamePlaces = namePlaces;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }
}
