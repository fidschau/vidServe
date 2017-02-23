package com.gnac.vidServ;

import com.gnac.vidServ.models.Cache;
import com.gnac.vidServ.models.Endpoint;
import com.gnac.vidServ.models.RequestDescription;
import com.gnac.vidServ.models.Video;

import java.util.ArrayList;
import java.util.List;


import java.io.*;
import java.util.StringJoiner;

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
        String[][] rawArray= getRawArrayFromFile("me_at_the_zoo.in");
        System.out.println(rawArray[0][3]);

        /*ArrayList<Video>videos=new ArrayList<>();
        ArrayList<Endpoint>endpoints=new ArrayList<>();
        if (rawArray!=null){
            String[] description=rawArray[0];
            String[] videoSizes=rawArray[1];
            int maxCacheSize = Integer.parseInt(description[4]);
            int numberOfVideos=Integer.parseInt(description[0]);
            int numberOfRequests=Integer.parseInt(description[2]);

            for(int id=0;id<numberOfVideos;id++){
                int size=Integer.parseInt(videoSizes[id]);
                videos.add(new Video(id,size));
            }
            int numberOfEndpoints=Integer.parseInt(description[1]);
            //endpoints= new Endpoint[numberOfEndpoints];

            int lineCount=2;
            for (int i=0;i<numberOfEndpoints;i++){
                String[] endpointDescription =rawArray[lineCount];

                int dataCenterLatency=Integer.parseInt(endpointDescription[0]);
                Endpoint endpoint= new Endpoint(i,dataCenterLatency);
                endpoints.add(endpoint);
                int numberOfCaches=Integer.parseInt(endpointDescription[1]);
                for (int j=0;j<numberOfCaches;j++){
                    lineCount++;
                    String [] latency=rawArray[lineCount];
                    int cacheID=Integer.parseInt(latency[0]);
                    int latencyToCache=Integer.parseInt(latency[1]);
                    Cache cache=new Cache(cacheID,maxCacheSize);
                    endpoint.setlatency(cache,latencyToCache);

                }
            }
            lineCount++;
            for (int i=0;i<numberOfRequests;i++){
                String request[]=rawArray[lineCount];
                int views=Integer.parseInt(request[2]);
                int videoID=Integer.parseInt(request[0]);
                int endPointID=Integer.parseInt(request[1]);
                Endpoint endpoint= new Endpoint(endPointID);
                Video  video= new Video(videoID);
                RequestDescription requestDescription= new RequestDescription(views,videos.get(videos.indexOf(video)));
                endpoints.get(endpoints.indexOf(endpoint)).addRequestDescription(requestDescription);
                lineCount++;
            }

        }
        else System.out.println("raw array is null");*/
    }





    public static String[][] getRawArrayFromFile(String fileName){
        try {
            int count = 0;
            String path="./input_files/";
            File inputFile = new File(path, fileName);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String[] imageLine = reader.readLine().split("");
            int row = Integer.parseInt(imageLine[0]);
            int column = Integer.parseInt(imageLine[1]);

            String[][] image = new String[row][column];
            while (count < column) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(" ");
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
