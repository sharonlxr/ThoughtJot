package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity {
    int month;
    int year;
    int maxMonth;
    int maxYear;
    int TAGSMODE = 2;
    int DATEMODE = 3;
    String MODE = "MODE";
    String TAG = "TAGS";
    String DATE = "DATE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();

        long daysInMonth =calendar.getTimeInMillis();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        maxMonth = month;
        maxYear = year;
        updateCal();

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Intent newIntent = new Intent(SearchActivity.this,ListActivity.class);
                newIntent.putExtra(MODE,DATEMODE);
                newIntent.putExtra(DATE,i+"/"+i1+"/"+i2);
                System.out.println("Debug" +i2);
                startActivity(newIntent);
            }
        });
        final EditText et = (EditText)findViewById(R.id.queryInput);
        Button detail = (Button)findViewById(R.id.detailed);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(SearchActivity.this,DetailSearchActivity.class);
                startActivity(newIntent);
//                return;
            }
        });
        Button search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               String input = et.getText().toString().trim();
                Intent newIntent = new Intent(SearchActivity.this,ListActivity.class);
                String tag =  et.getText().toString().trim();
                if(tag.isEmpty()){
                    Toast.makeText(getApplicationContext(),"empty input",Toast.LENGTH_LONG);
                    return;
                }else{
                    newIntent.putExtra(MODE,TAGSMODE);
                    newIntent.putExtra(TAG,tag);
                    startActivity(newIntent);
                }
                //move to search

            }
        });
        Button last = (Button)findViewById(R.id.pre);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(month ==0 ){
                    month =11;
                    year -=1;
                }else{
                    month-=1;
                }
                updateCal();
            }
        });

        Button next = (Button)findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(month==maxMonth&&year==maxYear){
//                    Toast.makeText(getApplicationContext(),"End of Calendar",Toast.LENGTH_LONG);
                    return;
                }
                if(month ==11 ){
                    month =0;
                    year +=1;
                }else{
                    month+=1;
                }
                updateCal();
            }
        });
    }
    public void updateCal(){

        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();
        Button next = (Button)findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);
        if(month==maxMonth&&year==maxYear){

            long daysInMonth =calendar.getTimeInMillis();
            cal.setMaxDate(daysInMonth);
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            daysInMonth =calendar.getTimeInMillis();

            cal.setMinDate(daysInMonth);
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,1);
            daysInMonth = calendar.getTimeInMillis();
            cal.setMaxDate(daysInMonth);


            return;

        }

        next.setVisibility(View.VISIBLE);
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        long daysInMonth = calendar.getTimeInMillis();
//        calendar.add(Calendar.DATE,1);
        long daysInMonth = calendar.getTimeInMillis();
        cal.setMaxDate(daysInMonth);
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        daysInMonth = calendar.getTimeInMillis();
        cal.setMinDate(daysInMonth);
    }
}
