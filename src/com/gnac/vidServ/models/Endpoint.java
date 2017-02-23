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


    @Override
    public String toString() {
        return "Endpoint{" +
                "id=" + id +
                ", latencyToDataCenter=" + latencyToDataCenter +
                ", latencyTable=" + latencyTable +
                ", requestDescriptions=" + requestDescriptions +
                '}';
    }
}
