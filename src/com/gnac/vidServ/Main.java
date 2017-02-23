package com.gnac.vidServ;

import java.io.*;

/**
 * Created by fidelity on 2017/02/23.
 */
public class Main {

    public static void main(String[] args){
        String[][] test= getRawArrayFromFile("me_at_the_zoo.in");
        System.out.println(test[0][3]);
    }



    public static String[][] getRawArrayFromFile(String fileName){
        try {
            int count = 0;
            String path="./input_files/";
            BufferedReader reader = new BufferedReader(new FileReader(path+fileName));
            String[] imageLine = reader.readLine().split("\\s");
            int row = Integer.parseInt(imageLine[0]);
            int column = Integer.parseInt(imageLine[1]);

            String[][] image = new String[row][column];
            while (count < column) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split("(?!^)");
                image[count] = temp;
                count++;
            }
            return image;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Logic to be done by Bongani, the file created will be a text file with .out at the end. The string will be provided
     * @param fileName
     * @param submissionString
     */
    public static void createSubmissionFile(String fileName, String submissionString){
        //Todo

        try {
            String path="./output_files/";
            File outputFile;
            outputFile = new File(path, fileName + ".out");

            OutputStream fou = new FileOutputStream(outputFile);
            byte[] data = submissionString.getBytes();
            fou.write(data);
            fou.flush();
            fou.close();



        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
