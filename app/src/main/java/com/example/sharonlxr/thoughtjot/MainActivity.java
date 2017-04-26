package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.sharonlxr.thoughtjot.R.id.searchButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.logo);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        new dbHelper(getApplicationContext());
//        dbTask db = new dbTask(getApplicationContext());
//        db.execute(new String[]{});
//        testTask ts = new testTask(getApplicationContext());
//        ts.execute();

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("ThoughtJot");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);


//        ImageButton imageButton = (ImageButton) mCustomView
//                .findViewById(R.id.imageButton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Refresh Clicked!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });






//        ActionBar actionBar = getSupportActionBar();
//
//        actionBar.setDisplayShowHomeEnabled(false);
////displaying custom ActionBar
//        View mActionBarView = getLayoutInflater().inflate(R.layout.action_bar, null);
//        actionBar.setCustomView(mActionBarView);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);




        Button searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(newIntent);
            }
        });
    }

}
