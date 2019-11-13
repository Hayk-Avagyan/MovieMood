package com.example.movie_h_b.moviemood.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVTopRatedMovies {

    @SerializedName("vote_count")
    private Integer vote_count;

    @SerializedName("id")
    private Integer id;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_name")
    private String original_name;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String first_air_date;

    public TVTopRatedMovies(Integer vote_count, Integer id, double vote_average, String name,
                            double popularity, String poster_path, String original_language, String original_name,
                            List<Integer> genre_ids, String backdrop_path, String overview, String first_air_date) {
        this.vote_count = vote_count;
        this.id = id;
        this.vote_average = vote_average;
        this.name = name;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_name = original_name;
        this.genre_ids = genre_ids;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.first_air_date = first_air_date;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

}
