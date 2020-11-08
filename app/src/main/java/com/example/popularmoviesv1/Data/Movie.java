package com.example.popularmoviesv1.Data;

import android.net.Uri;

public class Movie {
    public String title;
    public String synopsis;
    public String releaseDate;
    public String score;
    public Uri posterUri;

    public Movie(String inTitle, String inSynopsis, String inReleaseDate, String inScore, Uri inUri) {
        title = inTitle;
        synopsis = inSynopsis;
        releaseDate = inReleaseDate;
        score = inScore;
        posterUri = inUri;
    }

}
