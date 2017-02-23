package com.gnac.vidServ.models;

/**
 * Created by fidelity on 2017/02/23.
 */
public class RequestDescription {
    private int viewers;
    private Video video;

    public RequestDescription(int viewers, Video video) {
        this.viewers = viewers;
        this.video = video;
    }

    public int getViewers() {
        return viewers;
    }

    public Video getVideo() {
        return video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestDescription that = (RequestDescription) o;

        if (viewers != that.viewers) return false;
        return video.equals(that.video);
    }

    @Override
    public int hashCode() {
        int result = viewers;
        result = 31 * result + video.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RequestDescription{" +
                "viewers=" + viewers +
                ", video=" + video +
                '}';
    }
}
