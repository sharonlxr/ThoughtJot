package com.example.sharonlxr.thoughtjot;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailSearchActivity extends AppCompatActivity {
    String fromSt;
    String toSt;
    String format = "yyyy/MM/dd";
    boolean range;
    boolean keysword;
    boolean tagsenable;
    int ENABLE  = 1 ;
    int UNABLE = 0;
    String DATE = "DATE";
    String KEYEN ="KEYSENABLE";
    String RANGEN = "RANGENBLE";
    String TAGEN ="TAGENBLE";
    String START ="START";
    String END = "END";
    String MODE = "MODE";
    String KEY = "KEYS";
    String TAG = "TAGS";
    String TITLE = "TITLEONLY";
    int DETAILMODE = 1;
    int TAGSMODE = 2;
    int DATEMODE = 3;

    public boolean checkValidRange(String fy,String fm, String fd, String ty,String tm,String td ){
        try {
            int y1 = new Integer(fy);
            int y2 = new Integer(ty);
            int m1 = new Integer(fm);
            int m2 = new Integer(tm);
            int d1 = new Integer(fd);
            int d2 = new Integer(td);
            String fromSt= fy+"/"+fm+"/"+fd;
            String toSt = ty+"/"+tm+"/"+td;
            DateFormat formatter = new SimpleDateFormat(format);
            Date startDate = (Date)formatter.parse(fromSt);
            Date endDate = (Date)formatter.parse(toSt);
            if(startDate.before(endDate)){

                if(startDate.getMonth()!=m1-1||startDate.getDate()!=d1||startDate.getYear()!=y1-1900){

                    return false;
                }
                if(endDate.getYear()!=y2-1900||endDate.getMonth()!=m2-1||endDate.getDate()!=d2){

                    return false;
                }
                System.out.println("valid range check");
                return true;
            }
            return false;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_search);
        final Button search = (Button)findViewById(R.id.deSearchButton);

        //set action bar
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("ThoughtJot");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);


        final RadioButton title = (RadioButton)findViewById(R.id.titleonly);
        final EditText keys = (EditText)findViewById(R.id.keyinput);
        final EditText tags = (EditText)findViewById(R.id.tagsinput);
        final EditText fromy = (EditText)findViewById(R.id.fromyear);
        final EditText fromm = (EditText)findViewById(R.id.frommonth);
        final EditText fromd = (EditText)findViewById(R.id.fromdate);
        final EditText toy = (EditText)findViewById(R.id.toyear);
        final EditText tom = (EditText)findViewById(R.id.tomonth);
        final EditText tod = (EditText)findViewById(R.id.todate);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromyear = fromy.getText().toString().trim();
                String frommonth = fromm.getText().toString().trim();
                String fromdate = fromd.getText().toString().trim();
                String toyear = toy.getText().toString().trim();
                String tomonth = tom.getText().toString().trim();
                String todate = tod.getText().toString().trim();
                String fromSt= fromyear+"/"+frommonth+"/"+fromdate;
                String toSt = toyear+"/"+tomonth+"/"+todate;
                boolean allempty = fromyear.isEmpty()&&frommonth.isEmpty()&&fromdate.isEmpty()
                        &&toyear.isEmpty()&&tomonth.isEmpty()&&todate.isEmpty();
                boolean anyempty = fromyear.isEmpty()||frommonth.isEmpty()||fromdate.isEmpty()
                        ||toyear.isEmpty()||tomonth.isEmpty()||todate.isEmpty();
                if(anyempty&&!allempty){

                    Toast.makeText(getApplicationContext(),"not enough range",Toast.LENGTH_LONG);

                    return;
                }
                if(!allempty){
                    if(checkValidRange(fromyear,frommonth,fromdate,toyear,tomonth,todate)){
                        range = true;

                    }else{

                        Toast.makeText(getApplicationContext(),"Invalid range",Toast.LENGTH_LONG);
                        return;
                    }
                }else{range = false;}
                String keyw = keys.getText().toString().trim();
                if(keyw.trim().isEmpty()||keyw.equals("Seperated by ;")){
                    keysword= false;
                }else{
                    keysword=true;
                }
                String tagsstr = tags.getText().toString().trim();
                if(tagsstr.isEmpty()||tagsstr.equals("Seperated by ;")){
                    tagsenable=false;
                }else{
                    tagsenable = true;
                }
                if(!tagsenable&&!keysword&&!range){
                    Toast.makeText(getApplicationContext(),"Empty input",Toast.LENGTH_LONG);
                    return;
                }
                Intent newIntent = new Intent(DetailSearchActivity.this,ListActivity.class);
                newIntent.putExtra(MODE,DETAILMODE);
                if(range){
                    System.out.println("inner  "+fromSt+toSt);
                    newIntent.putExtra(RANGEN,ENABLE);
                    newIntent.putExtra(START,fromSt);
                    newIntent.putExtra(END,toSt);
                }else{
                    newIntent.putExtra(RANGEN,UNABLE);
                }
                if(tagsenable){
                    newIntent.putExtra(TAGEN,ENABLE);
                    newIntent.putExtra(TAG,tagsstr);
                }else{
                    newIntent.putExtra(TAGEN,UNABLE);
                }
                if(keysword){
                    newIntent.putExtra(KEYEN,ENABLE);
                    newIntent.putExtra(KEY,keyw);

                }else{
                    newIntent.putExtra(KEYEN,UNABLE);
                }
                if(title.isChecked()){
                    newIntent.putExtra(TITLE,ENABLE);

                }else{
                    newIntent.putExtra(TITLE,UNABLE);
                }
                startActivity(newIntent);



            }

        });
        keys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = keys.getText().toString();
                System.out.println(check);
                if(check.equals("Seperated by ;")){
                    keys.setText("");
                }
            }
        });
        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = tags.getText().toString();
                if(check.equals("Seperated by ;")){
                    tags.setText("");
                }
            }
        });


    }
}
