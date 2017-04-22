package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
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
    int DETAILMODE = 1;
    int TAGSMODE = 2;
    int DATEMODE = 3;


    String FIRST_COLUMN = "TITLE";
    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";
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
        int test = it.getIntExtra(MODE,-1);
        final ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> temp1=new HashMap<String, String>();
        temp1.put(FIRST_COLUMN, "Title");
        temp1.put(SECOND_COLUMN, "Time");
        temp1.put(THIRD_COLUMN, "Content");
        temp1.put(FOURTH_COLUMN, "Tags");
        list.add(temp1);
        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put(FIRST_COLUMN, "Good morning");
        temp.put(SECOND_COLUMN, "2017/03/01");
        temp.put(THIRD_COLUMN, "I am happy");
        temp.put(FOURTH_COLUMN, "Happy");
        list.add(temp);

//        HashMap<String,String> temp2=new HashMap<String, String>();
//        temp2.put(FIRST_COLUMN, "Rajat Ghai");
//        temp2.put(SECOND_COLUMN, "Male");
//        temp2.put(THIRD_COLUMN, "25");
//        temp2.put(FOURTH_COLUMN, "Unmarried");
//        list.add(temp2);
//
//        HashMap<String,String> temp3=new HashMap<String, String>();
//        temp3.put(FIRST_COLUMN, "Karina Kaif");
//        temp3.put(SECOND_COLUMN, "Female");
//        temp3.put(THIRD_COLUMN, "31");
//        temp3.put(FOURTH_COLUMN, "Unmarried");
//        list.add(temp3);
        ListView lv = (ListView)findViewById(R.id.list);
        ListViewAdapter adapter=new ListViewAdapter(this, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("debug "+i);
                if(i==0){return;}
                HashMap<String,String> target = list.get(i);
                System.out.println(i);
                Intent it =  new Intent((ListActivity.this),displayItemActivity.class);
                it.putExtra(FIRST_COLUMN,target.get(FIRST_COLUMN));
                it.putExtra(SECOND_COLUMN,target.get(SECOND_COLUMN));
                it.putExtra(THIRD_COLUMN,target.get(THIRD_COLUMN));
                it.putExtra(FOURTH_COLUMN,target.get((FOURTH_COLUMN)));
                startActivity(it);

            }
        });
//        if(test==-1){
//        tv.setText("not found");
//        }else{
//            if(test==DATEMODE){
//
//            String time = it.getStringExtra(DATE);
//            tv.setText(time);
//            }
//            else{
//                if(test == TAGSMODE){
//                    String tag = it.getStringExtra(TAG);
//                    tv.setText(tag);
//                }else{
//                    if(test == DETAILMODE){
//
//                        int rangeEn = it.getIntExtra(RANGEN,-1);
//                        int keyEn = it.getIntExtra(KEYEN,-1);
//                        int tagEn = it.getIntExtra(TAGEN,-1);
//                        int title = it.getIntExtra(TITLE,-1);
//
//
//
//                    }else{
//
//                        Toast.makeText(getApplicationContext(),"no such mode",Toast.LENGTH_LONG);
//                        return;
//                    }
//                }
//            }
//        }
    }
}
