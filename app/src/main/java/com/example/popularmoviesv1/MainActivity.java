package com.example.popularmoviesv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //UI Variables
    TextView mTvErrorMessage;
    ProgressBar mPbLoading;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loading of UI variables
        mTvErrorMessage = (TextView) findViewById(R.id.tv_main_error);
        mPbLoading = (ProgressBar) findViewById(R.id.pb_main_loading);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_recycle);



    }
}