package com.example.sharonlxr.thoughtjot;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sharonlxr on 4/25/17.
 */

public class testTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        testHelper tk = new testHelper();
        return "Done";
    }
}
