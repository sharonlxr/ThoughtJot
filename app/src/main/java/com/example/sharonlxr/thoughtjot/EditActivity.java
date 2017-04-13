package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    String FIRST_COLUMN = "TITLE";
//    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent it = getIntent();
        final String tt = it.getStringExtra(FIRST_COLUMN);
        final String ct = it.getStringExtra(THIRD_COLUMN);
        final String tg = it.getStringExtra(FOURTH_COLUMN);
        EditText title = (EditText)findViewById(R.id.editTitle);
        EditText cont = (EditText)findViewById(R.id.cont);
        EditText tgs = (EditText)findViewById(R.id.editTags);
        title.setText(tt);
        cont.setText(ct);
        tgs.setText(tg);
        Button save =(Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
