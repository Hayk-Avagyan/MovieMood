package com.example.movie_h_b.moviemood.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_h_b.moviemood.R;
import com.example.movie_h_b.moviemood.fragments.SelectedMovieFragment;
import com.example.movie_h_b.moviemood.fragments.TopRatedFragment;
import com.example.movie_h_b.moviemood.models.TopRatedMovies;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class TopRatedMovieAdapter extends RecyclerView.Adapter<TopRatedMovieAdapter.MyViewHolder> {

    private List<TopRatedMovies> movieList;
    private Context context;
    private TopRatedFragment topRatedFragment;

    public TopRatedMovieAdapter(Context context, TopRatedFragment topRatedFragment) {
        this.movieList = new ArrayList<>();
        this.context = context;
        this.topRatedFragment = topRatedFragment;
    }

    @NonNull
    @Override
    public TopRatedMovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_rated_movie, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public void addAllTopRated(List<TopRatedMovies> movieList) {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedMovieAdapter.MyViewHolder holder, final int position) {

        Uri imageUri = Uri.parse(movieList.get(position).getPoster_path());
        holder.imageView.setImageURI(imageUri);
        holder.textView.setText(movieList.get(position).getTitle());

        final int id = movieList.get(position).getId();

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedMovieFragment selectedMovieFragment = new SelectedMovieFragment();
                FragmentManager fragmentManager = topRatedFragment.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("movie_id_top", id);
                selectedMovieFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, selectedMovieFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView imageView;
        TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Fresco.initialize(context);

            imageView = itemView.findViewById(R.id.top_rated_poster);
            textView = itemView.findViewById(R.id.top_rated_title);
        }
    }
}