package com.nextdev.starterhacks;

import java.util.ArrayList;

/**
 * Created by Home on 04/03/2018.
 */

public class sortLocations {
    public ArrayList<location> sort(ArrayList<location> myList) {

        for (int i = 0; i < myList.size(); i++) {
            myList.get(i).dist = getDistance(myList.get(i));
        }
        for (int i = 0; i < myList.size(); i++) {
            for (int j = 1; j <= 1; j++) {
                if (myList.get(j-1).dist > myList.get(j).dist) {
                    double temp = myList.get(j-1).dist;
                    myList.get(j-1).dist = myList.get(j).dist;
                    myList.get(j).dist = temp;
                }
            }
        }
        return myList;
    }

    // This function is where the main google maps implimentation must be done:
    private double getDistance(location L) {
        return 5.2;




    }

    private double haversine(double lon1, double lat1, double lon2, double lat2) {
        int R = 6371;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
