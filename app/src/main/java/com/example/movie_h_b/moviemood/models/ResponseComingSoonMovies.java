package com.example.movie_h_b.moviemood.models;

import java.util.List;

public class ResponseComingSoonMovies {

    private int page;
    private int total_results;
    private int total_pages;
    private List<ComingSoonMovies> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ComingSoonMovies> getResults() {
        return results;
    }

    public void setResults(List<ComingSoonMovies> results) {
        this.results = results;
    }
}
