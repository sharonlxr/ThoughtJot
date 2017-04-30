package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EditActivity extends AppCompatActivity {
    final String PK = "PRIMARYKEYS";

    String FIRST_COLUMN = "TITLE";
    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";
    String titt;
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
        setContentView(R.layout.activity_edit);

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



        Intent it = getIntent();
        String ts = it.getStringExtra(PK);
//        System.out.println("in edit "+ts);
        final long time = new Long(it.getStringExtra(PK));
        final String tt = it.getStringExtra(FIRST_COLUMN);
        titt=tt;
        final String ct = it.getStringExtra(THIRD_COLUMN);
        final String tg = it.getStringExtra(FOURTH_COLUMN);

        final EditText title = (EditText)findViewById(R.id.editTitle);
        final EditText cont = (EditText)findViewById(R.id.cont);
        final EditText tgs = (EditText)findViewById(R.id.editTags);
        title.setText(tt);
        cont.setText(ct);
        tgs.setText(tg);
        Button save =(Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask ut = new updateTask(getApplicationContext(),EditActivity.this);
                String newt = title.getText().toString();
                String newtag = tgs.getText().toString();
                String newCont = cont.getText().toString();
                ut.execute(new String[]{titt,newCont,newtag,newt,tg,String.valueOf(time)});

            }
        });

    }
}
