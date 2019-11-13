package com.example.movie_h_b.moviemood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_h_b.moviemood.R;
import com.example.movie_h_b.moviemood.RESTAPI.RetrofitClient;
import com.example.movie_h_b.moviemood.ServiceInterfaces.ServiceInterface;
import com.example.movie_h_b.moviemood.adapters.TVTopRatedMovieAdapter;
import com.example.movie_h_b.moviemood.models.ResponseTVTopRatedMovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TVSearialsFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private TVTopRatedMovieAdapter adapter;
    private int page = 1;
    private boolean isLoading;
    private boolean isLastPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_serials_fragment, container, false);

        recyclerView = view.findViewById(R.id.tv_top_rated_recycler);
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new TVTopRatedMovieAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        loadMoves();

        return view;
    }

    private void loadMoves() {
        isLoading = true;
        ServiceInterface service = RetrofitClient.getInstance().create(ServiceInterface.class);

        Call<ResponseTVTopRatedMovies> call = service.getTVTopRatedMovies(HomeFragment.API_KEY, page);
        call.enqueue(new Callback<ResponseTVTopRatedMovies>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ResponseTVTopRatedMovies> call, Response<ResponseTVTopRatedMovies> response) {

                isLoading = false;
                ResponseTVTopRatedMovies movie = response.body();
                if (movie == null) {
                    return;
                }
                adapter.addAllTVTopRated(movie.getResults());
                page = movie.getPage() + 1;

                isLastPage = movie.getTotal_pages() <= page;
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ResponseTVTopRatedMovies> call, Throwable t) {
                isLoading = false;
            }
        });
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = gridLayoutManager.getChildCount();
            int totalItemCount = gridLayoutManager.getItemCount();
            int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoves();
                }
            }
        }
    };
}
