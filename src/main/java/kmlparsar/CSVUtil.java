package kmlparsar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtil {

    // i just changed file name from database.txt to database.csv
    // because i want to write in csv file not text file
    // if you want to write in text file like the video on youtube
    // just change database.csv to database.txt
    public static String filename = "database.csv";

    // main method
    public static void main(String[] args) {

        createNewFileWithHeaders();
    }

    // methods

    public static void createNewFileWithHeaders() {
        File database = new File(filename);

        try {

            try {
                FileWriter writer = new FileWriter(filename, true);
                writer.append("Branch Name" + "," + "Latitude" + "," + "Longitude" + "," + "Polygons");
                writer.append("\n");
                writer.close();
                System.out.println("file created succefully!");
            } catch (IOException e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRecord(List<String> arrayList, String input, String polg) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            System.out.println("please enter any key to update the record");
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(input)) {
                    arrayList.add(line.replace("", polg));

                } else {
                    arrayList.add(line);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < arrayList.size(); i++) {
                writer.append(arrayList.get(i));
                writer.append("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getUserById(String input) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(input)) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createAddNewPerson(KFCBranch outlet) {

        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.append(outlet.getOutletData());
            writer.append("\n");
            writer.close();
            System.out.println("person added succefully!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void readKmFile() {
    }
}
