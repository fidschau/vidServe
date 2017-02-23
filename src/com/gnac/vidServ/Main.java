package com.gnac.vidServ;

import java.util.ArrayList;
import java.util.List;


import java.io.*;

/**
 * Created by fidelity on 2017/02/23.
    //1. Every endpoint needs a list of cache_servers ordered by increasing latency
    //2. Every endpoint needs a list of videos ordered by decreasing demand
    //3. Every server needs a list of videos stored on it, without compromising its size
    //4. the algorithm serves videos from the endpoints to the servers following these lists, until the server is full
 */

public class Main {

    //Global Variables
    int no_of_videos;
    int video_size[];
    int no_of_endpoints;
    int request_descriptions;
    int no_of_caches;
    int cache_size;
    int endpoint_to_datacenter_latency[];
    List<int[]> endpoint_to_cache_latency = new ArrayList<int[]>();

    //For each endpoint have a list of requests
    int end_point_video_requests;
    List<int[]> endpoint_video_requests = new ArrayList<int[]>();


    public void readInputFile(){
      //assign classes and variables from input
        no_of_videos = 5;
        video_size = new int [no_of_videos];

    }

    public static void main(String[] args) {
        // write your code here
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
