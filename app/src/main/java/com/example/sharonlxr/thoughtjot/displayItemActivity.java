package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayItemActivity extends AppCompatActivity {
    String FIRST_COLUMN = "TITLE";
    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);
        TextView title = (TextView)findViewById(R.id.titleItem);
        TextView date = (TextView)findViewById(R.id.DateItem);
        TextView cont = (TextView)findViewById(R.id.content);
        TextView tags = (TextView)findViewById(R.id.tagsItem);
        Intent it = getIntent();
        final String tt = it.getStringExtra(FIRST_COLUMN);
        final String tm = it.getStringExtra(SECOND_COLUMN);
        final String ct = it.getStringExtra(THIRD_COLUMN);
        final String tg = it.getStringExtra(FOURTH_COLUMN);
        title.setText(tt);
        date.setText(tm);
        cont.setText(ct);
        tags.setText(tg);
        Button edit = (Button)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(displayItemActivity.this,EditActivity.class);
                it.putExtra(FIRST_COLUMN,tt);
                it.putExtra(THIRD_COLUMN,ct);
                it.putExtra(FOURTH_COLUMN,tg);
                startActivity(it);

            }
        });



    }
}
