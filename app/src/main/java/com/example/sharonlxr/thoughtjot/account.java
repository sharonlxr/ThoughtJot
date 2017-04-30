package com.example.sharonlxr.thoughtjot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sharonlxr on 4/29/17.
 */

public class account extends AsyncTask<String, Void, Boolean > {
    Activity mActivity;
    Context mCt;
    final int LOGIN = 1;
    final int SIGNUP =2;
    int ID;
    public account(Activity ac, Context ct){
        mActivity = ac;
        mCt = ct;
    }
    @Override
    protected Boolean doInBackground(String... params) {
       String mode =  params[0];
        int md = new Integer(mode);
        accountDB db = new accountDB();
        switch ( md){
            case LOGIN:
                ID = new Integer(params[1]);
                return db.login(new Integer(params[1]),params[2]);


        }
        return false;

    }
    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            Intent it = new Intent(mActivity,MainActivity.class);
            it.putExtra("ID",ID);
            mCt.startActivity(it);

        }else{

        }

    }
}
