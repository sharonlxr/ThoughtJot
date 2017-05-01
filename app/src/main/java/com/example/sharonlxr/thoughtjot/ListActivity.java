package com.example.sharonlxr.thoughtjot;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    final String PK = "PRIMARYKEYS";
    String ID = "ID";
    String id;

    int ENABLE  = 1 ;
    int UNABLE = 0;

    String KEYEN ="KEYSENABLE";
    String RANGEN = "RANGENBLE";
    String TAGEN ="TAGENBLE";
    String START ="START";
    String END = "END";
    String MODE = "MODE";
    String KEY = "KEYS";
    String TAG = "TAGS";
    String TITLE = "TITLEONLY";
    String DATE = "DATE";
    final int DETAILMODE = 1;
    final int TAGSMODE = 2;
    final int DATEMODE = 3;

    final  String DATEM ="DATE";
    final String TITLEM = "TITLE";
    final String DATERANGE = "DATERANGE";
    final String TAGSM = "TAGS";
    final String KEYM = "KEYS";
//    final String TITLEM = "TITLE";


    String FIRST_COLUMN = "TITLE";
    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";

    final String RANGETITLE= "RangeTitle";
    final String RANGETAG = "RANGETAG";
    final String RANGEKEY ="RANGEKEY";
    final String RANGEKEYTAG = "RANGEKEYTAG";
    final String RANGETITLETAG = "RANGETITLETAG";
    final String KEYTAG = "KEYTAG";
    final String TITLETAG = "TITLETAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        TextView tv = (TextView)findViewById(R.id.debug);

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
        id = it.getStringExtra(ID);
        int mode = it.getIntExtra(MODE,-1);
        dbTask dbt = new dbTask(getApplicationContext(),this);
//        System.out.println("Debug "+mode);
        switch (mode){
            case DATEMODE:
                System.out.println("date");
                String date = it.getStringExtra(DATE);
                String[] inputs = new String[]{DATEM,date};
                dbt.execute(inputs);
                return;


            case DETAILMODE:
                int range = it.getIntExtra(RANGEN,-1);
                int tag = it.getIntExtra(TAGEN,-1);
                int key = it.getIntExtra(KEYEN,-1);
                int title = it.getIntExtra(TITLE,-1);
                String st = it.getStringExtra(START);
                String end = it.getStringExtra(END);
                String tg = it.getStringExtra(TAG);
                String tt = it.getStringExtra(KEY);
                String[] input;
                if(range==ENABLE){


                    if(key==ENABLE){
                        if(title==ENABLE){
                            //range and title
                            if(tag==ENABLE){
                                input = new String[]{RANGETITLETAG,tt,tg,st,end};
                                dbt.execute(input);

                            }else{
                                input = new String[]{RANGETITLE,tt,st,end};
                                dbt.execute(input);
                            }

                        }else{
                            //range and keywords
                            if(tag==ENABLE){
                                input = new String[]{RANGEKEYTAG,tt,tg,st,end};
                                dbt.execute(input);

                            }else{
                                input = new String[]{RANGEKEY,tt,st,end};
                                dbt.execute(input);

                            }

                        }
                    }else{
                        if(tag==ENABLE){
                            input = new String[]{RANGETAG,tg,st,end};
                            dbt.execute(input);

                        }else{
                            System.out.println(st+end);
                            input = new String[]{DATERANGE,st,end};
                            dbt.execute(input);

                        }

                    }

                }else{
                    if(key==ENABLE){
                        if(title==ENABLE){
                            //range and title
                            if(tag==ENABLE){
                                input = new String[]{TITLETAG,tt,tg,st,end};
                                dbt.execute(input);

                            }else{
                                input = new String[]{TITLEM,tt,st,end};
                                dbt.execute(input);
                            }

                        }else{
                            //range and keywords
                            if(tag==ENABLE){
                                input = new String[]{KEYTAG,tt,tg,st,end};
                                dbt.execute(input);

                            }
//                            else{
//                                input = new String[]{KEYM,tt,st,end};
//                                dbt.execute(input);
//
//                            }

                        }
                    }else{
                        if(tag==ENABLE){
                            input = new String[]{TAGSM,tg,st,end};
                            dbt.execute(input);

                        }

                    }

                }


                return;
            case TAGSMODE:
                String tgs = it.getStringExtra(TAG);
                dbt.execute(new String[]{TAGSM,tgs});
                return;
            default:

                int test = it.getIntExtra(MODE, -1);
                final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> temp1 = new HashMap<String, String>();
                temp1.put(FIRST_COLUMN, "Title");
                temp1.put(SECOND_COLUMN, "Time");
                temp1.put(THIRD_COLUMN, "Content");
                temp1.put(FOURTH_COLUMN, "Tags");
                list.add(temp1);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(FIRST_COLUMN, "Good morning");
                temp.put(SECOND_COLUMN, "2017/03/01");
                temp.put(THIRD_COLUMN, "I am happy");
                temp.put(FOURTH_COLUMN, "Happy");
                list.add(temp);

                ListView lv = (ListView) findViewById(R.id.list);
                ListViewAdapter adapter = new ListViewAdapter(this, list);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println("debug " + i);
                        if (i == 0) {
                            return;
                        }
                        HashMap<String, String> target = list.get(i);
                        System.out.println(i);
                        Intent it = new Intent((ListActivity.this), displayItemActivity.class);
                        it.putExtra(FIRST_COLUMN, target.get(FIRST_COLUMN));
                        it.putExtra(SECOND_COLUMN, target.get(SECOND_COLUMN));
                        it.putExtra(THIRD_COLUMN, target.get(THIRD_COLUMN));
                        it.putExtra(FOURTH_COLUMN, target.get((FOURTH_COLUMN)));
                        startActivity(it);

                    }
                });

    }

    }
}
