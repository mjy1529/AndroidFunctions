package com.juyoung.recyclerviewscroll;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Movie> movieList;

    public Adapter(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Adapter.MovieViewHolder holder = (Adapter.MovieViewHolder) viewHolder;
        holder.title.setText(movieList.get(i).title);
        holder.subTitle.setText(movieList.get(i).subTitle);
        holder.pubDate.setText(movieList.get(i).pubDate);
    }

    @Override
    public int getItemCount() {
        if(movieList != null) {
            return movieList.size();
        }
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;
        TextView pubDate;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subTitle);
            pubDate = (TextView) itemView.findViewById(R.id.pubDate);
        }
    }
}
