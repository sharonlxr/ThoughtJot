package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        TextView tv = (TextView)findViewById(R.id.debug);
        Intent it = getIntent();
        int test = it.getIntExtra(MODE,-1);
        if(test==-1){
        tv.setText("not found");
        }else{
            if(test==DATEMODE){

            String time = it.getStringExtra(DATE);
            tv.setText(time);
            }
            else{
                if(test == TAGSMODE){
                    String tag = it.getStringExtra(TAG);
                    tv.setText(tag);
                }else{
                    if(test == DETAILMODE){

                        int rangeEn = it.getIntExtra(RANGEN,-1);
                        int keyEn = it.getIntExtra(KEYEN,-1);
                        int tagEn = it.getIntExtra(TAGEN,-1);
                        int title = it.getIntExtra(TITLE,-1);



                    }else{

                        Toast.makeText(getApplicationContext(),"no such mode",Toast.LENGTH_LONG);
                        return;
                    }
                }
            }
        }
    }
}
