package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class displayItemActivity extends AppCompatActivity {
    String FIRST_COLUMN = "TITLE";
    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";
    final String PK = "PRIMARYKEYS";



    String ID ="ID";
    String FILENAME = "ID";

    public String readLogID(){
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
                    return null;
                }
                return ID;
            }else{
                return  null;
            }
        }
        catch (Exception e)

        {return  ID;
//        Log.e("login activity", "File not found: " + e.toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        //set action bar
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("ThoughtJot");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);


        TextView title = (TextView)findViewById(R.id.titleItem);
        TextView date = (TextView)findViewById(R.id.DateItem);
        TextView cont = (TextView)findViewById(R.id.content);
        TextView tags = (TextView)findViewById(R.id.tagsItem);
        Intent it = getIntent();
        final String tt = it.getStringExtra(FIRST_COLUMN);
        final String tm = it.getStringExtra(SECOND_COLUMN);
        final String ct = it.getStringExtra(THIRD_COLUMN);
        final String tg = it.getStringExtra(FOURTH_COLUMN);
        final String pks = it.getStringExtra(PK);
        title.setText(tt);
        date.setText(tm);
        cont.setText(ct);
        tags.setText(tg);
        Button edit = (Button)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(displayItemActivity.this,EditActivity.class);
                it.putExtra(FIRST_COLUMN,tt);
                it.putExtra(THIRD_COLUMN,ct);
                it.putExtra(FOURTH_COLUMN,tg);
                it.putExtra(PK,pks);
                startActivity(it);

            }
        });



    }
}
