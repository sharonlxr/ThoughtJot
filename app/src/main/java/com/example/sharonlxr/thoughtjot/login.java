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

public class login extends AppCompatActivity {
    int LOGIN = 1;
    int SIGNUP =2;
    String FILENAME = "ID";
    String ret = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ActionBar mActionBar = getSupportActionBar();
//        mActionBar.setDisplayShowHomeEnabled(false);
//        mActionBar.setDisplayShowTitleEnabled(false);
//        LayoutInflater mInflater = LayoutInflater.from(this);
//
//        View mCustomView = mInflater.inflate(R.layout.action_bar, null);
//        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
//        mTitleTextView.setText("ThoughtJot");
//        mActionBar.setCustomView(mCustomView);
//        mActionBar.setDisplayShowCustomEnabled(true);

        Button login = (Button)findViewById(R.id.loginbutton);
        final EditText user= (EditText)findViewById(R.id.user);
        final EditText pw = (EditText)findViewById(R.id.pw);
        final account act = new account(this,getApplicationContext());
        final TextView signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(login.this,SignupActivity.class);
                startActivity(it);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString().trim();
                int hc = username.hashCode();
                if(hc<0){
                    hc*=-1;
                }
                String password = pw.getText().toString().trim();
                act.execute(new String[]{String.valueOf(LOGIN),String.valueOf(hc),username,password});

//                System.out.println(hc);
            }
        });
    }
}
