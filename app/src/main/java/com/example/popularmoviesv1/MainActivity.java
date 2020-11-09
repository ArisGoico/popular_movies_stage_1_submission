package com.example.popularmoviesv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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


        //TODO Add RecyclerView structure with adapter and everything
    }


    //----------
    //---MENU---
    //----------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_change_sorting) {
            //TODO Change sorting
            Toast.makeText(MainActivity.this, "Menu item clicked.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}