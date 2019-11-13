package com.example.movie_h_b.moviemood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_h_b.moviemood.R;
import com.example.movie_h_b.moviemood.RESTAPI.RetrofitClient;
import com.example.movie_h_b.moviemood.ServiceInterfaces.ServiceInterface;
import com.example.movie_h_b.moviemood.adapters.SelectedMovieAdapter;
import com.example.movie_h_b.moviemood.models.SelectedMovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SelectedMovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SelectedMovieAdapter adapter;
    private boolean isLoading;
    private int movieIdTop;
    private int movieIdSoon;
    private int movieIdTVSerials;
    private int movieIdSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_selected_fragment, container, false);

        movieIdTop = getArguments().getInt("movie_id_top");
        movieIdSearch = getArguments().getInt("movie_id_search");
        movieIdTVSerials = getArguments().getInt("movie_id_TV");
        movieIdSoon = getArguments().getInt("movie_id_soon");

        recyclerView = view.findViewById(R.id.movie_selected_recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SelectedMovieAdapter(getContext());
        recyclerView.setAdapter(adapter);

        loadMovesTopRated();
        loadMovesSearchMovie();
        loadMovesComingSoon();
        loadTVSerials();

        return view;
    }

    private void loadMovesTopRated() {
        isLoading = true;
        ServiceInterface service = RetrofitClient.getInstance().create(ServiceInterface.class);

        Call<SelectedMovies> call = service.getSelectedMovies(movieIdTop, HomeFragment.API_KEY);
        call.enqueue(new Callback<SelectedMovies>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<SelectedMovies> call, Response<SelectedMovies> response) {

                isLoading = false;
                SelectedMovies movie = response.body();
                if (movie == null) {
                    return;
                }
                adapter.addAllSelectedMovies(movie);

            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<SelectedMovies> call, Throwable t) {
                isLoading = false;
            }
        });
    }

    private void loadMovesComingSoon() {
        isLoading = true;
        ServiceInterface service = RetrofitClient.getInstance().create(ServiceInterface.class);

        Call<SelectedMovies> call = service.getSelectedMovies(movieIdSoon, HomeFragment.API_KEY);
        call.enqueue(new Callback<SelectedMovies>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<SelectedMovies> call, Response<SelectedMovies> response) {

                isLoading = false;
                SelectedMovies movie = response.body();
                if (movie == null) {
                    return;
                }
                adapter.addAllSelectedMovies(movie);

            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<SelectedMovies> call, Throwable t) {
                isLoading = false;
            }
        });
    }

    private void loadTVSerials() {
        isLoading = true;
        ServiceInterface service = RetrofitClient.getInstance().create(ServiceInterface.class);

        Call<SelectedMovies> call = service.getSelectedMovies(movieIdTVSerials, HomeFragment.API_KEY);
        call.enqueue(new Callback<SelectedMovies>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<SelectedMovies> call, Response<SelectedMovies> response) {

                isLoading = false;
                SelectedMovies movie = response.body();
                if (movie == null) {
                    return;
                }
                adapter.addAllSelectedMovies(movie);

            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<SelectedMovies> call, Throwable t) {
                isLoading = false;
            }
        });
    }

    private void loadMovesSearchMovie() {
        isLoading = true;
        ServiceInterface service = RetrofitClient.getInstance().create(ServiceInterface.class);

        Call<SelectedMovies> call = service.getSelectedMovies(movieIdSearch, HomeFragment.API_KEY);
        call.enqueue(new Callback<SelectedMovies>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<SelectedMovies> call, Response<SelectedMovies> response) {

                isLoading = false;
                SelectedMovies movie = response.body();
                if (movie == null) {
                    return;
                }
                adapter.addAllSelectedMovies(movie);

            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<SelectedMovies> call, Throwable t) {
                isLoading = false;
            }
        });
    }
}
