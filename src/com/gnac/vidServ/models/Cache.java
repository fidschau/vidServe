package com.gnac.vidServ.models;

import java.util.ArrayList;

/**
 * Created by fidelity on 2017/02/23.
 */
public class Cache {
    int id, size;
    private ArrayList<Video> videos= new ArrayList<>();


    public Cache(int id) {
        this.id = id;
    }

    public Cache(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public boolean addVideo(Video video){
        if (canAddVideo(video)){
            if (videos.contains(video))videos.remove(video);
            videos.add(video);
            return true;
        }
        return false;
    }

    public  boolean canAddVideo(Video video){

        return (usedUpSpace()+video.getSize())<=size;
    }

    public int usedUpSpace(){
        int usedUpSpace=0;
        for (Video video:videos){
            usedUpSpace+=video.getSize();
        }
        return usedUpSpace;
    }

    public int getFreeSpace(){

        return size-usedUpSpace();
    }

    public boolean hasVideo(Video video){

        if (videos.contains(video))return true;
        return false;
    }

    public int getNumberOfVideos(){
        return videos.size();
    }

    public String stringForSubmission(){
        StringBuilder stringBuilder= new StringBuilder();
        if (videos.size()>0){
            stringBuilder.append("\n").append(id);
        }

        for (Video video:videos){
            stringBuilder.append(" ").append(video.getId());
        }
        return stringBuilder.toString();
    }

    public boolean isInUse(){

        return videos.size()>0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cache cache = (Cache) o;

        return id == cache.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Cache{" +
                "id=" + id +
                ", size=" + size +
                ", videos=" + videos +
                '}';
    }
}
