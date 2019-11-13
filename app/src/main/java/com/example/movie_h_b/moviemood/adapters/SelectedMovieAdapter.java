package com.example.movie_h_b.moviemood.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_h_b.moviemood.R;
import com.example.movie_h_b.moviemood.models.SelectedMovies;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class SelectedMovieAdapter extends RecyclerView.Adapter<SelectedMovieAdapter.MyViewHolder> {

    private List<SelectedMovies> movieList;
    private Context context;

    public SelectedMovieAdapter(Context context) {
        this.movieList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_selected, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public void addAllSelectedMovies(SelectedMovies movieList) {
        this.movieList.add(movieList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Uri imageUri = Uri.parse(movieList.get(position).getPoster_path());
        holder.imageView.setImageURI(imageUri);
        holder.movieSelectedTitle.setText(movieList.get(position).getTitle());
        holder.movieReleaseDate.setText(movieList.get(position).getRelease_date());
        holder.movieRuntime.setText(String.valueOf(movieList.get(position).getRuntime()));
        String stringDouble = Double.toString(movieList.get(position).getVote_average());
        holder.movieVoteAverage.setText(stringDouble);
        holder.movieOverview.setText(movieList.get(position).getOverview());
        holder.movieBudget.setText(String.valueOf(movieList.get(position).getBudget()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView imageView;
        TextView movieSelectedTitle, movieReleaseDate, movieRuntime, movieVoteAverage, movieOverview, movieBudget;
        Button button;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Fresco.initialize(context);

            imageView = itemView.findViewById(R.id.movie_selected_poster);
            movieSelectedTitle = itemView.findViewById(R.id.movie_selected_title);
            movieReleaseDate = itemView.findViewById(R.id.movie_release_date);
            movieRuntime = itemView.findViewById(R.id.movie_runtime);
            movieVoteAverage = itemView.findViewById(R.id.movie_vote_average);
            movieOverview = itemView.findViewById(R.id.movie_overview);
            movieBudget = itemView.findViewById(R.id.movie_budget);
            button = itemView.findViewById(R.id.trailer_button);
        }
    }
}
