package com.example.movie_h_b.moviemood.models;

import com.google.gson.annotations.SerializedName;

public class VideoTrailer {

    @SerializedName("key")
    private String key;

    public VideoTrailer(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
