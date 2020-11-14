package com.example.popularmoviesv1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesv1.data.Movie;
import com.squareup.picasso.Picasso;


public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {

    //------VARIABLES AND DECLARATIONS------
    private Movie[] mArrayMovies;

    //ViewHolder class needed for the adapter
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mPoster = (ImageView) itemView.findViewById(R.id.iv_item_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Context context = v.getContext();
            //DONE launch the Activity of the detail
            Intent intent = new Intent(context, DetailActivity.class);

            //DONE Add the movie information and pass it as parameter of the intent
            intent.putExtra("Title",mArrayMovies[position].title);
            intent.putExtra("Synopsis",mArrayMovies[position].synopsis);
            intent.putExtra("ReleaseDate",mArrayMovies[position].releaseDate);
            intent.putExtra("Score",mArrayMovies[position].score);
            intent.putExtra("PosterUri",mArrayMovies[position].posterUri.toString());

            context.startActivity(intent);
        }
    }

    //------CONSTRUCTOR------
    public MovieItemAdapter() {
    }


    //------FUNCTIONS------
    public void setArrayMoviesData(Movie[] input){
        mArrayMovies = input;
        notifyDataSetChanged();
    }

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
        if (mArrayMovies == null) return 0;
        return mArrayMovies.length;
    }
}
