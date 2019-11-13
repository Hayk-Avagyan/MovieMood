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
import com.example.movie_h_b.moviemood.fragments.TVSearialsFragment;
import com.example.movie_h_b.moviemood.models.TVTopRatedMovies;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class TVTopRatedMovieAdapter extends RecyclerView.Adapter<TVTopRatedMovieAdapter.MyViewHolder> {

    private List<TVTopRatedMovies> movieList;
    private Context context;
    private TVSearialsFragment tvSearialsFragment;

    public TVTopRatedMovieAdapter(Context context, TVSearialsFragment tvSearialsFragment) {
        this.movieList = new ArrayList<>();
        this.context = context;
        this.tvSearialsFragment = tvSearialsFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_serials_movie, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public void addAllTVTopRated(List<TVTopRatedMovies> movieList) {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Uri imageUri = Uri.parse(movieList.get(position).getPoster_path());
        holder.imageView.setImageURI(imageUri);
        holder.textView.setText(movieList.get(position).getName());

        final int id = movieList.get(position).getId();

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedMovieFragment selectedMovieFragment = new SelectedMovieFragment();
                FragmentManager fragmentManager = tvSearialsFragment.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("movie_id_TV", id);
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

            imageView = itemView.findViewById(R.id.tv_top_rated_poster);
            textView = itemView.findViewById(R.id.tv_top_rated_title);
        }
    }
}
