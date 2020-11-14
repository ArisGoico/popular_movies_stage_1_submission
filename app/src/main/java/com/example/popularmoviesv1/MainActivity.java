package com.example.popularmoviesv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmoviesv1.Data.Movie;

import org.w3c.dom.Text;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    //UI Variables
    private TextView mTvErrorMessage;
    private ProgressBar mPbLoading;
    private RecyclerView mRecyclerView;

    private boolean orderPopular = true;

    //Constants
    private final int NUM_COLUMNS = 3;

    //MovieDB URLs
    private final String SCHEMA = "https";
    private final String MOVIEDB_BASE = "api.themoviedb.org/3/";
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

        displayLoading();

        //DONE Fetch data from the web by creating an AsynctTask
        //Call method to launch the AsyncTask
        downloadMoviePostersPopular();
    }

    //------FUNCTIONS------
    private void downloadMoviePostersPopular() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEMA)
            .authority(MOVIEDB_BASE)
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

    private void displayError(String text) {
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
            //TODO Change text on the button
            if (orderPopular) {
                downloadMoviePostersTopRated();
                orderPopular = false;
            }
            else {
                downloadMoviePostersPopular();
                orderPopular = true;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //------ASYNCTASK------
    private class AsyncMoviesFetch extends AsyncTask<Uri, Void, Movie[]> {
        protected void onPreExecute() {
            mRecyclerView.setAdapter(null);
            displayLoading();
        }

        protected Movie[] doInBackground(Uri... uris) {
            //TODO fetch images using the uri
            Uri address = uris[0];

            //TODO parse the information from the JSON into the Movie[] structure
        }

        protected void onPostExecute(Movie[] result) {
            MovieItemAdapter adapter = new MovieItemAdapter(result);
            adapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, NUM_COLUMNS));
            mRecyclerView.setHasFixedSize(true);
            displayRecycleView();
        }
    }
}