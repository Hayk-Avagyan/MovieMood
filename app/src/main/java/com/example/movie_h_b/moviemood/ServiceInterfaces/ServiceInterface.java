package com.example.movie_h_b.moviemood.ServiceInterfaces;

import com.example.movie_h_b.moviemood.models.ResponseComingSoonMovies;
import com.example.movie_h_b.moviemood.models.ResponseSearchMovies;
import com.example.movie_h_b.moviemood.models.ResponseTVTopRatedMovies;
import com.example.movie_h_b.moviemood.models.ResponseTopRatedMovies;
import com.example.movie_h_b.moviemood.models.SelectedMovies;
import com.example.movie_h_b.moviemood.models.VideoTrailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceInterface {

    @GET("movie/top_rated")
    Call<ResponseTopRatedMovies> getTopRatedMovies(@Query("api_key") String api_key, @Query("page") int page);

    @GET("movie/upcoming")
    Call<ResponseComingSoonMovies> getComingSoonMovies(@Query("api_key") String api_key, @Query("page") int page);

    @GET("tv/top_rated")
    Call<ResponseTVTopRatedMovies> getTVTopRatedMovies(@Query("api_key") String api_key, @Query("page") int page);

    @GET("search/movie")
    Call<ResponseSearchMovies> getSearchMovies(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<SelectedMovies> getSelectedMovies(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}/videos")
    Call<VideoTrailer> getVideoTrailer(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

}
