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

}
