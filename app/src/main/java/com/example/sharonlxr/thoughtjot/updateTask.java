package com.example.sharonlxr.thoughtjot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by sharonlxr on 4/25/17.
 */

public class updateTask extends AsyncTask<String, Void, String > {
    Context mContext;
    String title;
    String time;
    String tag;
    String entry;
    Activity mActivity;
    String FIRST_COLUMN = "TITLE";
    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";
    final String PK = "PRIMARYKEYS";
    public  updateTask(Context ct,Activity ac){
        mContext = ct;
        mActivity =ac;
    }
    protected String doInBackground(String... params) {
        dbHelper dbh = new dbHelper(mContext);
         time = params[5];
        entry = params[1];
       title = params[3];
        tag = params[2];
        dbh.saveModifiedEntry(params[0],params[1],params[2],params[3],params[4],new Long(params[5]));
        return "updated";
    }
    @Override
    protected void onPostExecute(final String result) {
        if(result=="updated"){
        Intent it = new Intent(mActivity,displayItemActivity.class);
        it.putExtra(FIRST_COLUMN,title);
        it.putExtra(PK,time);
        it.putExtra(THIRD_COLUMN,entry);
        it.putExtra(FOURTH_COLUMN,tag);
            Calendar cl = Calendar.getInstance();
            cl.setTimeInMillis(new Long(time));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
            String timest = df.format(cl.getTime());
        it.putExtra(SECOND_COLUMN,timest);
            mActivity.startActivity(it);

        }else{
            Toast.makeText(mContext,"Fail to update",Toast.LENGTH_LONG);
        }


    }
}
