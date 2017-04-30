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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.sharonlxr.thoughtjot.R.id.searchButton;

public class MainActivity extends AppCompatActivity {


    String FILENAME = "ID";
    String ID;
    String USERFILE ="USERNAME";
    String IDIT = "ID";
    String UN = "USERNAME";
    public String readUserName(){
        try {
            InputStream inputStream = this.getApplicationContext().openFileInput(USERFILE);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                String nm = stringBuilder.toString();
                if(nm.trim().isEmpty()||nm==null){
                   return null;
                }
                System.out.println(nm);
                return nm;
            }else{
                return  null;
            }
        }
        catch (Exception e)

        {
           return null;
//        Log.e("login activity", "File not found: " + e.toString());
        }
    }
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
        TextView tv =(TextView) findViewById(R.id.welcomeMsg);
        final String userName = readUserName();
        if(userName ==null){
            Intent it = new Intent(MainActivity.this,login.class);
            startActivity(it);
        }
        tv.setText("Welcome, "+ userName);
        View mCustomView = mInflater.inflate(R.layout.action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("ThoughtJot");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        Button ac = (Button)findViewById(R.id.acc);
        try {
            InputStream inputStream = this.getApplicationContext().openFileInput(FILENAME);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ID = stringBuilder.toString();
                if(ID.trim().isEmpty()||ID==null){
                    Intent it = new Intent(MainActivity.this,login.class);
                    startActivity(it);
                }
            }else{
                Intent it = new Intent(MainActivity.this,login.class);
                startActivity(it);
            }
        }

        catch (Exception e)

        {
            Intent it = new Intent(MainActivity.this,login.class);
            startActivity(it);
//        Log.e("login activity", "File not found: " + e.toString());
        }

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(MainActivity.this,accountManActivity.class);
                it.putExtra(IDIT,ID);
                it.putExtra(UN,userName);
                startActivity(it);
            }
        });

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
                newIntent.putExtra("ID",ID);
                System.out.println(ID);
                startActivity(newIntent);
            }
        });
    }

}
