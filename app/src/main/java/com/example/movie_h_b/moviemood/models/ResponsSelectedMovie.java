package com.example.movie_h_b.moviemood.models;

import java.util.List;

public class ResponsSelectedMovie {

    private List<SelectedMovies> results;

    public List<SelectedMovies> getResults() {
        return results;
    }

    public void setResults(List<SelectedMovies> results) {
        this.results = results;
    }

    public class MovieGenre {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


