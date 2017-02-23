package com.gnac.vidServ.models;

/**
 * Created by fidelity on 2017/02/23.
 */
public class Video {

    private int id;
    private int size;

    public Video(int id) {
        this.id = id;
    }

    public Video(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        return id == video.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }
}
