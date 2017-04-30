package com.example.sharonlxr.thoughtjot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;

public class accountManActivity extends AppCompatActivity {
    String IDIT = "ID";
    String UN = "USERNAME";
    String FILENAME = "ID";
    String USERFILE ="USERNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_man);
        Intent it = getIntent();
        String id = it.getStringExtra(IDIT);
        String us = it.getStringExtra(UN);
        TextView idtv = (TextView)findViewById(R.id.IDdisplay);
        TextView untv= (TextView)findViewById(R.id.undisplay);
        idtv.setText("ID: "+id);
        untv.setText("UserName: "+us);
        Button bt = (Button)findViewById(R.id.signout);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraseAccount();
                Intent it = new Intent(accountManActivity.this,login.class);
                startActivity(it);
            }
        });
    }
    public void eraseAccount(){
        try{
        String string ="";
        FileOutputStream fos = getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write(string.getBytes());
        fos.close();
        FileOutputStream fos1 = getApplicationContext().openFileOutput(USERFILE, Context.MODE_PRIVATE);
        fos1.write(string.getBytes());
        fos1.close();}catch (Exception e){
            return;
        }
    }
}
