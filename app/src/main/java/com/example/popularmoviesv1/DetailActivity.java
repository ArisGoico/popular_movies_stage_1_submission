package com.example.popularmoviesv1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    //Variables with the Movie information
    private String title;
    private String synopsis;
    private String releaseDate;
    private String score;
    private Uri posterUri;

    //UI Variables
    private ImageView mPoster;
    private TextView mTitle;
    private TextView mSynopsis;
    private TextView mReleaseDate;
    private TextView mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                title = extras.getString("Title");
                synopsis = extras.getString("Synopsis");
                releaseDate = extras.getString("ReleaseDate");
                score = extras.getString("Score");
                posterUri = Uri.parse(extras.getString("PosterUri"));
            }
            else {
                String error = "Error while recovering the extras from the bundle!";
                Log.e(this.getClass().toString(), error);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                return;
            }
        }

        //DONE Display the appropriate information in each View
        mPoster = (ImageView) findViewById(R.id.iv_detail_image);
        Picasso.get().load(posterUri).into(mPoster);

        mTitle = (TextView) findViewById(R.id.tv_detail_title);
        mTitle.setText(title);

        mSynopsis = (TextView) findViewById(R.id.tv_detail_synopsis);
        mSynopsis.setText(synopsis);

        mReleaseDate = (TextView) findViewById(R.id.tv_detail_release_date);
        mReleaseDate.setText(releaseDate);

        mScore = (TextView) findViewById(R.id.tv_detail_score);
        mScore.setText(score);
    }
}