package com.example.popularmoviesv1.utils;

import android.net.Uri;
import android.util.Log;

import com.example.popularmoviesv1.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JsonNetworkUtils {

    private static final String TAG = "JsonUtils";

    //MovieDB Image calls
    private static final String SCHEMA = "https";
    private static final String IMAGE_BASE = "image.tmdb.org";
    private static final String IMAGE_BASE_2 = "t";
    private static final String IMAGE_BASE_3 = "p";
    private static final String IMAGE_SIZE = "w342";

    public static Movie[] parseMovieJson(String json) {
        JSONObject fullJSON;

        String title;
        String synopsis;
        String releaseDate;
        String score;
        Uri posterUri;

        Movie[] movieArray;

        try {
            fullJSON = new JSONObject(json);
            //Parse JSON
            JSONArray results = fullJSON.getJSONArray("results");
            movieArray = new Movie[results.length()];
            for (int i = 0; i < results.length(); i++) {
                JSONObject tempMovie = results.getJSONObject(i);
                title = tempMovie.getString("title");
                synopsis = tempMovie.getString("overview");
                releaseDate = tempMovie.getString("release_date");
                score = tempMovie.getString("vote_average");
                String posterPathString = tempMovie.getString("poster_path");
                if (posterPathString.startsWith("/")) {
                    posterPathString = posterPathString.substring(1);
                }
                posterUri = buildImageUri(posterPathString);
                movieArray[i] = new Movie(title,synopsis,releaseDate,score,posterUri);
            }

        }
        catch (JSONException e) {
            Log.w(TAG, "Error while parsing the JSON.");
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            Log.w(TAG, "Unknown error while parsing the JSON.");
            e.printStackTrace();
            return null;
        }

        return movieArray;
    }

    public static Uri buildImageUri(String posterPath) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEMA)
                .authority(IMAGE_BASE)
                .appendPath(IMAGE_BASE_2)
                .appendPath(IMAGE_BASE_3)
                .appendPath(IMAGE_SIZE)
                .appendPath(posterPath);
        Uri uri = builder.build();
        return uri;
    }

    public static URL convertUriToURL(Uri inputUri) {
        try {
            URL url = new URL(inputUri.toString());
            return url;
        }
        catch(Exception e) {
            Log.e(TAG, "Unknown error while converting the Uri to URL.");
            e.printStackTrace();
            return null;
        }
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
