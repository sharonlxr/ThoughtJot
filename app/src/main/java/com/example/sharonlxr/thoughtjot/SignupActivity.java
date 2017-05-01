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
import android.widget.Toast;

import static com.example.sharonlxr.thoughtjot.R.id.link_login;
import static com.example.sharonlxr.thoughtjot.R.id.signup;

public class SignupActivity extends AppCompatActivity {
    final int SIGNUP =2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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

        final EditText usr = (EditText)findViewById(R.id.usrName);
        final EditText pw = (EditText)findViewById(R.id.pwsu);
        final Button bt = (Button)findViewById(R.id.signuobutton);
        final account ac = new account(this,getApplicationContext());

        final TextView login = (TextView) findViewById(link_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SignupActivity.this,login.class);
                startActivity(it);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrst = usr.getText().toString().trim();
                String pwst = pw.getText().toString().trim();
                if(usrst.isEmpty()||pwst.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill out",Toast.LENGTH_LONG);
                    return;
                }
                int hc = usrst.hashCode();
                if(hc<0){
                    hc*=-1;
                }
                ac.execute(new String[]{String.valueOf(SIGNUP),String.valueOf(hc),usrst,pwst});
            }
        });
    }
}
