package com.example.popularmoviesv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesv1.Data.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {

    //------VARIABLES AND DECLARATIONS------
    private Movie[] mArrayMovies;

    //ViewHolder class needed for the adapter
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mPoster = (ImageView) itemView.findViewById(R.id.iv_item_movie_poster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //TODO launch the Activity of the detail
        }
    }

    //------CONSTRUCTOR------
    public MovieItemAdapter(Movie[] inputMovies) {
        mArrayMovies = inputMovies;
    }


    //------FUNCTIONS------

    //Method overrides for the adapter
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO maybe do some checks for valid position?
        Movie movie = mArrayMovies[position];
        Picasso.get().load(movie.posterUri).into(holder.mPoster);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieItem = inflater.inflate(R.layout.item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(movieItem);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mArrayMovies.length;
    }
}
