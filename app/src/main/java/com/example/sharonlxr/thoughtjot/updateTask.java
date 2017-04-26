package com.example.sharonlxr.thoughtjot;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sharonlxr on 4/25/17.
 */

public class updateTask extends AsyncTask<String, Void, String > {
    Context mContext;
    public  updateTask(Context ct){
        mContext = ct;
    }
    protected String doInBackground(String... params) {
        dbHelper dbh = new dbHelper(mContext);
        dbh.saveModifiedEntry(params[0],params[1],params[2],params[3],params[4]);
        return "updated";
    }
}
