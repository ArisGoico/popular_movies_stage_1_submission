package com.example.popularmoviesv1.data;

import android.net.Uri;

public class Movie {
    private String title;
    private String synopsis;
    private String releaseDate;
    private String score;
    private Uri posterUri;

    public Movie(String inTitle, String inSynopsis, String inReleaseDate, String inScore, Uri inUri) {
        title = inTitle;
        synopsis = inSynopsis;
        releaseDate = inReleaseDate;
        score = inScore;
        posterUri = inUri;
    }

    public Uri getPosterUri() {
        return posterUri;
    }

    public void setPosterUri(Uri posterUri) {
        this.posterUri = posterUri;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
