package com.example.movie_h_b.moviemood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_h_b.moviemood.R;
import com.example.movie_h_b.moviemood.RESTAPI.RetrofitClient;
import com.example.movie_h_b.moviemood.ServiceInterfaces.ServiceInterface;
import com.example.movie_h_b.moviemood.adapters.SearchMovieAdapter;
import com.example.movie_h_b.moviemood.models.ResponseSearchMovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SearchFragment extends Fragment {

    public static final String API_KEY = "bad1aede729018eb5ef310ff78c97a62";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SearchMovieAdapter adapter;
    private EditText editTextSearch;
    private Button button;
    private int page = 1;
    private boolean isLoading;
    private boolean isLastPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        editTextSearch = view.findViewById(R.id.search_movie_editText);
        button = view.findViewById(R.id.search_movie_button);

        recyclerView = view.findViewById(R.id.search_recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SearchMovieAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

                loadMoves();
            }
        });
        return view;
    }

    private void loadMoves() {
        isLoading = true;
        ServiceInterface service = RetrofitClient.getInstance().create(ServiceInterface.class);

        Call<ResponseSearchMovies> call = service.getSearchMovies(API_KEY, editTextSearch.getText().toString(), page);
        call.enqueue(new Callback<ResponseSearchMovies>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ResponseSearchMovies> call, Response<ResponseSearchMovies> response) {

                isLoading = false;
                ResponseSearchMovies movie = response.body();
                if (movie == null) {
                    return;
                }
                adapter.addAllSearchMovies(movie.getResults());
                page = movie.getPage() + 1;

                isLastPage = movie.getTotal_pages() <= page;
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ResponseSearchMovies> call, Throwable t) {
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
            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoves();
                }
            }
        }
    };
}
