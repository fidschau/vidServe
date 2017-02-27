package com.gnac.vidServ;

import com.gnac.vidServ.models.Cache;
import com.gnac.vidServ.models.Endpoint;
import com.gnac.vidServ.models.RequestDescription;
import com.gnac.vidServ.models.Video;

import java.nio.file.Files;
import java.util.*;


import java.io.*;
import java.util.stream.Stream;

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

        processVideos("me_at_the_zoo");

        processVideos("trending_today");
        processVideos("videos_worth_spreading");
        processVideos("kittens");

    }


    private static void processVideos(String fileName){


        String[][] rawArray= getRawArrayFromFile(fileName);
        System.out.println(rawArray[0][0]);
        ArrayList<Cache>allCaches=new ArrayList<>();

       /* try {
            int [][] testArray=create2DIntMatrixFromFile("me_at_the_zoo.in");


            System.out.println(""+testArray[0][0]);


        } catch (Exception e) {
            e.printStackTrace();
        }*/
        ArrayList<Video>videos=new ArrayList<>();
        ArrayList<Endpoint>endpoints=new ArrayList<>();
        if (rawArray!=null){
            String[] description=rawArray[0];
            String[] videoSizes=rawArray[1];
            int maxCacheSize = Integer.parseInt(description[4]);
            int numberOfVideos=Integer.parseInt(description[0]);
            int numberOfRequests=Integer.parseInt(description[2]);
            int totalNumberOfCaches=Integer.parseInt(description[3]);


            for (int i=0;i<totalNumberOfCaches;i++){
                allCaches.add(new Cache(i,maxCacheSize));
            }

            for(int id=0;id<numberOfVideos;id++){
                int size=Integer.parseInt(videoSizes[id]);
                videos.add(new Video(id,size));
            }
            int numberOfEndpoints=Integer.parseInt(description[1]);
            //endpoints= new Endpoint[numberOfEndpoints];

            int lineCount=1;
            for (int i=0;i<numberOfEndpoints;i++){
                lineCount++;
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
            System.out.println(videos.toString());
            System.out.println(endpoints.toString());
            Collections.sort(endpoints, (o1, o2) -> Float.compare(o2.getAverageVideoDemand(),o1.getAverageVideoDemand()));
            System.out.println("Sorted: "+endpoints.toString());



            for (Endpoint endpoint:endpoints){
                for (RequestDescription requestDescription:endpoint.getRequestDescriptions()){
                    Video video = videos.get(videos.indexOf(requestDescription.getVideo()));
                    if (!endpoint.isVideoInAnyCache(video,allCaches)){
                        for (Cache cache:endpoint.getSortedCachesByLatency().keySet()){
                            Cache globalCache=allCaches.get(allCaches.indexOf(cache));
                            if (globalCache.addVideo(video)){
                                allCaches.remove(cache);
                                allCaches.add(cache);
                                return;
                            }
                        }
                    }
                }

            }

            String submissionString = createSubmissionString(allCaches);
            createSubmissionFile(fileName,submissionString);

        }


        else System.out.println("raw array is null");




    }

    private static String createSubmissionString(ArrayList<Cache> allCaches){
        int totalCaches=0;
        for (Cache cache:allCaches){
            if (cache.isInUse())totalCaches++;
        }
        System.out.println("Total caches = "+totalCaches);

        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(totalCaches);
        for (Cache cache:allCaches){

            if (cache.isInUse()){

                stringBuilder.append(cache.stringForSubmission());
            }
        }


        return stringBuilder.toString();
    }





    public static String[][] getRawArrayFromFile(String fileName){
        try {
            int count = 0;

            StringBuilder inputStringBuilder=new StringBuilder().append(fileName).append(".in");
            String path="./input_files/";
            File inputFile = new File(path, inputStringBuilder.toString());
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            ArrayList<String []> strings= new ArrayList<>();

            String[][] image;// = new String[row][column];


            String line;

            while ((line = reader.readLine())!=null) {

                String[] temp = line.split(" ");
                strings.add(temp);
            }
            image=new String[strings.size()][];
            image=strings.toArray(image);

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


    public static int[][] create2DIntMatrixFromFile(String filename) throws Exception {
        int[][] matrix = null;

        String path="./input_files/";
        File inputFile = new File(path, filename);

        // If included in an Eclipse project.
       // InputStream stream = ClassLoader.getSystemResourceAsStream(inputFile.getPath());
       // BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

        // If in the same directory - Probably in your case...
        // Just comment out the 2 lines above this and uncomment the line
        // that follows.
        BufferedReader buffer = new BufferedReader(new FileReader(inputFile));

        String line;
        int row = 0;
        int size = 0;

        while ((line = buffer.readLine()) != null) {
            String[] vals = line.trim().split("\\s+");

            // Lazy instantiation.
            if (matrix == null) {
                size = vals.length;
                matrix = new int[size][size];
            }

            for (int col = 0; col < size; col++) {
                matrix[row][col] = Integer.parseInt(vals[col]);
            }

            row++;
        }

        return matrix;
    }

}
