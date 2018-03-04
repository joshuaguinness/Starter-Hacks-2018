package com.nextdev.starterhacks;

/**
 * Location class stores the information about a particular location
 */


public class location {
    public String addr = "";
    public int haz = 0;
    public String desc = "";
    public double dist = 1000;
    public double lat = 0;
    public double lon = 0;

    public location(String address, int hazard, String description) {
        addr = address;
        haz = hazard;
        desc = description;
    }

    public location(String address, int hazard, String description, double distance) {
        addr = address;
        haz = hazard;
        desc = description;
        dist = distance;
    }

    // new object call type for just coord purposes
    public location(double latitude, double longitude) {
        lat = latitude;
        lon = longitude;
    }
}
