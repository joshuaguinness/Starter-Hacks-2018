package com.nextdev.starterhacks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Home on 03/03/2018.
 */

public class fileIO {
    public static void main(String [] args) {
        ArrayList<location> myList = new ArrayList<location>();

        String file = "pastLocations.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(",");
                myList.add(new location(info[0],Integer.parseInt(info[1]), info[2]));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
