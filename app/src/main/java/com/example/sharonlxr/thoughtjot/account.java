package com.example.sharonlxr.thoughtjot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
//import java.io.openFileOutput

/**
 * Created by sharonlxr on 4/29/17.
 */

public class account extends AsyncTask<String, Void, Boolean > {
    String FILENAME = "ID";
    String USERFILE ="USERNAME";
    Activity mActivity;
    Context mCt;
    final int LOGIN = 1;
    final int SIGNUP =2;
    int ID;
    int md;
    String USERNAME;
    public account(Activity ac, Context ct){
        mActivity = ac;
        mCt = ct;
    }
    @Override
    protected Boolean doInBackground(String... params) {
//        return true;
       String mode =  params[0];
        md = new Integer(mode);
        accountDB db = new accountDB();
        switch ( md){
            case LOGIN:
                ID = new Integer(params[1]);
                USERNAME = params[2];
                return db.login(new Integer(params[1]),params[2],params[3]);
            case SIGNUP:
                ID = new Integer(params[1]);
                USERNAME = params[2];
                String username = params[2];
                String pw = params[3];
                return db.signup(ID,username,pw);



        }
        return false;

    }
    @Override
    protected void onPostExecute(Boolean result) {
        System.out.println(result);
        if(result==true){

            String string = String.valueOf(ID);
            try{
            FileOutputStream fos = mCt.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
            FileOutputStream fos1 = mCt.openFileOutput(USERFILE, Context.MODE_PRIVATE);
            fos1.write(USERNAME.getBytes());
            fos1.close();
                System.out.println("write to file");
            }catch (Exception e){

                Intent it = new Intent(mActivity,login.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCt.startActivity(it);
            }
//            if(md == SIGNUP){
//            System.out.println("dialog "+ID);
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("Please remember you id for Alexa, "+ID)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                Intent it = new Intent(mActivity,MainActivity.class);
                                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mCt.startActivity(it);
                            }
                        });
            AlertDialog alert = builder.create();
            alert.show();
//                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        });

                return;
//            }else{
//            Intent it = new Intent(mActivity,MainActivity.class);
//            it.putExtra("ID",ID);
//            mCt.startActivity(it);
//            }

        }else{
//            Intent it = new Intent(mActivity,mActivity.cl);
//            it.putExtra("ID",ID);
//            mCt.startActivity(it);
//            Toast.makeText()

        }

    }
}
