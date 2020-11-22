package com.example.popularmoviesv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.popularmoviesv1.data.Movie;
import com.example.popularmoviesv1.utils.JsonNetworkUtils;


import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //UI Variables
    private TextView mTvErrorMessage;
    private ProgressBar mPbLoading;
    private RecyclerView mRecyclerView;

    //Adapter
    private MovieItemAdapter mAdapter;

    //Internal variables
    private boolean orderPopular = true;

    //Constants
    private final int NUM_COLUMNS = 3;

    //MovieDB API calls URLs
    private final String SCHEMA = "https";
    private final String MOVIEDB_BASE = "api.themoviedb.org";
    private final String MOVIEDB_BASE_2 = "3";
    private final String MOVIE_PATH = "movie";
    private final String POPULAR_PATH = "popular";
    private final String TOPRATED_PATH = "top_rated";
    private final String API_KEY_PARAM = "api_key";
    private final String API_KEY = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loading of UI variables
        mTvErrorMessage = (TextView) findViewById(R.id.tv_main_error);
        mPbLoading = (ProgressBar) findViewById(R.id.pb_main_loading);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_recycle);

        mAdapter = new MovieItemAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, NUM_COLUMNS));
        mRecyclerView.setHasFixedSize(true);

        displayLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Call method to launch the AsyncTask
        downloadMoviePostersPopular();
    }

    //------FUNCTIONS------
    private void downloadMoviePostersPopular() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEMA)
            .authority(MOVIEDB_BASE)
            .appendPath(MOVIEDB_BASE_2)
            .appendPath(MOVIE_PATH)
            .appendPath(POPULAR_PATH)
            .appendQueryParameter(API_KEY_PARAM, API_KEY);
        Uri uri = builder.build();
        new AsyncMoviesFetch().execute(uri);
    }

    private void downloadMoviePostersTopRated() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEMA)
                .authority(MOVIEDB_BASE)
                .appendPath(MOVIEDB_BASE_2)
                .appendPath(MOVIE_PATH)
                .appendPath(TOPRATED_PATH)
                .appendQueryParameter(API_KEY_PARAM, API_KEY);
        Uri uri = builder.build();
        new AsyncMoviesFetch().execute(uri);
    }

    private void displayLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mTvErrorMessage.setVisibility(View.INVISIBLE);
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void displayError(String text) {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mPbLoading.setVisibility(View.INVISIBLE);
        mTvErrorMessage.setVisibility(View.VISIBLE);
        mTvErrorMessage.setText(text);
    }

    private void displayRecycleView() {
        mPbLoading.setVisibility(View.INVISIBLE);
        mTvErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    //------MENU------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_change_sorting) {
            //DONE Change text on the button
            if (orderPopular) {
                downloadMoviePostersTopRated();
                item.setTitle(R.string.main_menu_change_sorting_popular);
                orderPopular = false;
            }
            else {
                downloadMoviePostersPopular();
                item.setTitle(R.string.main_menu_change_sorting_rated);
                orderPopular = true;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //------ASYNCTASK------
    private class AsyncMoviesFetch extends AsyncTask<Uri, Void, Movie[]> {
        protected void onPreExecute() {
            super.onPreExecute();
            displayLoading();
        }

        protected Movie[] doInBackground(Uri... uris) {
            Uri address = uris[0];
            URL objective = JsonNetworkUtils.convertUriToURL(address);
            Movie[] movies = null;
            String jsonString;
            if (objective != null) {
                try {
                    jsonString = JsonNetworkUtils.getResponseFromHttpUrl(objective);
                }
                catch (Exception e) {
                    //displayError("Unknown error while accessing the URL.");
                    Log.e(this.getClass().toString(), "Unknown error while accessing the URL.");
                    e.printStackTrace();
                    return null;
                }
                movies = JsonNetworkUtils.parseMovieJson(jsonString);
            }

            return movies;
        }

        protected void onPostExecute(Movie[] result) {
            if (result == null) {
                displayError("Movie array retrieved was empty after executing the AsyncTask.");
                Log.e(this.getClass().toString(), "Movie array retrieved was empty after executing the AsyncTask.");
                return;
            }
            displayRecycleView();
            mAdapter.setArrayMoviesData(result);
        }
    }
}