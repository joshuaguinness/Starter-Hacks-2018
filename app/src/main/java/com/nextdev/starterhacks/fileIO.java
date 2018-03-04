package com.nextdev.starterhacks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * FileIO contains input and output functions for an arraylist of location objects
 */

public class fileIO {
    ArrayList<location> readIn() {

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
        return myList;
    }
    void printOut(ArrayList<location> myList) {
        try {
        File file = new File("pastLocations.txt"); //will overwrite
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < myList.size(); i++) {
            String s = myList.get(i).addr + "," + Integer.toString(myList.get(i).haz) + ',' + myList.get(i).desc;
            bw.write(s);
        }
        bw.close();
    } catch(Exception e) {
            System.out.println(e);
        }
    }
}
