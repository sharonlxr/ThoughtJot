package com.example.sharonlxr.thoughtjot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    int LOGIN = 1;
    int SIGNUP =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button)findViewById(R.id.loginbutton);
        final EditText user= (EditText)findViewById(R.id.user);
        final EditText pw = (EditText)findViewById(R.id.pw);
        final account act = new account(this,getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString().trim();
                int hc = username.hashCode();
                String password = pw.getText().toString().trim();
                act.execute(new String[]{String.valueOf(LOGIN),String.valueOf(hc),password});

//                System.out.println(hc);
            }
        });
    }
}
