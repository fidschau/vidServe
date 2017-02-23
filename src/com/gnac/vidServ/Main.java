package com.gnac.vidServ;

import java.util.ArrayList;
import java.util.List;


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

    }
}
