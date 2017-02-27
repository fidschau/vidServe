package com.gnac.vidServ.models;

import java.util.*;

/**
 * Created by fidelity on 2017/02/23.
 */
public class Endpoint {
    private int id, latencyToDataCenter;
    private HashMap<Cache,Integer> latencyTable = new HashMap<>();
    private ArrayList<RequestDescription> requestDescriptions=new ArrayList<>();


    public Endpoint(int id, int latencyToDataCenter) {
        this.id = id;
        this.latencyToDataCenter = latencyToDataCenter;

    }

    public Endpoint(int endPointID) {
        id=endPointID;
    }

    public void setlatency(Cache cache, int latency){

        latencyTable.put(cache, new Integer(latency));
        latencyTable=getSortedCachesByLatency();

    }
    public int getLatency(Cache cache){

        return latencyTable.get(cache);
    }

    public void addRequestDescription(RequestDescription requestDescription){
        if (requestDescriptions.contains(requestDescription))requestDescriptions.remove(requestDescription);
        requestDescriptions.add(requestDescription);
    }

    public  ArrayList<RequestDescription> getRequestDescriptions(){
        Collections.sort(requestDescriptions, (o1, o2) -> o2.getViewers()-o1.getViewers());

        return requestDescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endpoint endpoint = (Endpoint) o;

        return id == endpoint.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public HashMap<Cache,Integer> getSortedCachesByLatency(){
        List<HashMap.Entry<Cache,Integer>> entryList= new LinkedList<Map.Entry<Cache, Integer>>(latencyTable.entrySet());

        Collections.sort(entryList, Comparator.comparing(o -> (o.getValue())));

        HashMap<Cache,Integer>  returnHashMap=new HashMap<>();
        for (Map.Entry<Cache,Integer> cacheIntegerEntry:entryList){
            returnHashMap.put(cacheIntegerEntry.getKey(),cacheIntegerEntry.getValue());
        }

        return returnHashMap;
    }

    public float getAverageVideoDemand(){
        int totalViews=0;
        for (RequestDescription requestDescription:requestDescriptions){
            totalViews+=requestDescription.getViewers();
        }


        return (totalViews/requestDescriptions.size());
    }

    public boolean isVideoInAnyCache(Video video,ArrayList<Cache>allCaches){
        for (Cache cache:getCaches()){
            System.out.println("Is video in cache:\n");
            System.out.println(cache.toString());
            if (allCaches.get(allCaches.indexOf(cache)).addVideo(video))return true;


        }


        return false;
    }

    private ArrayList<Cache> getCaches(){
        ArrayList<Cache> caches= new ArrayList<>();
       for (Cache cache:getSortedCachesByLatency().keySet()){
           caches.add(cache);
       }

        return caches;
    }

    public String latencyTableString(){
        StringBuilder stringBuilder= new StringBuilder("Latency table: ");

        for (Cache cache:getSortedCachesByLatency().keySet()){
            stringBuilder.append(" Latency: ");
            stringBuilder.append(latencyTable.get(cache));
            stringBuilder.append(" cacheID: ");
            stringBuilder.append(cache.id);
        }

        return stringBuilder.toString();
    }


    @Override
    public String toString() {
        return "Endpoint{" +
                "id=" + id +
                ", latencyToDataCenter=" + latencyToDataCenter +
                ", latencyTable=" + latencyTableString() +
                ", requestDescriptions=" + requestDescriptions +
                ", average video demand= "+getAverageVideoDemand()+
                '}';
    }
}
