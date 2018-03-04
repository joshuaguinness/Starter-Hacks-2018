package com.nextdev.starterhacks;

/**
 * Location class stores the information about a particular location
 */


public class location {
    public String addr = "";
    public int haz = 0;
    public String desc = "";
    public double dist = 1000;

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
}
