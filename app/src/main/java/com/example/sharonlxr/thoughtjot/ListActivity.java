package com.example.sharonlxr.thoughtjot;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    final String PK = "PRIMARYKEYS";

    int ENABLE  = 1 ;
    int UNABLE = 0;

    String KEYEN ="KEYSENABLE";
    String RANGEN = "RANGENBLE";
    String TAGEN ="TAGENBLE";
    String START ="START";
    String END = "END";
    String MODE = "MODE";
    String KEY = "KEYS";
    String TAG = "TAGS";
    String TITLE = "TITLEONLY";
    String DATE = "DATE";
    final int DETAILMODE = 1;
    final int TAGSMODE = 2;
    final int DATEMODE = 3;

    final  String DATEM ="DATE";
    final String TITLEM = "TITLE";
    final String DATERANGE = "DATERANGE";
    final String TAGSM = "TAGS";
    final String KEYM = "KEYS";
//    final String TITLEM = "TITLE";


    String FIRST_COLUMN = "TITLE";
    String SECOND_COLUMN = "DATE";
    String THIRD_COLUMN = "CONTENT";
    String FOURTH_COLUMN = "TAGS";

    final String RANGETITLE= "RangeTitle";
    final String RANGETAG = "RANGETAG";
    final String RANGEKEY ="RANGEKEY";
    final String RANGEKEYTAG = "RANGEKEYTAG";
    final String RANGETITLETAG = "RANGETITLETAG";
    final String KEYTAG = "KEYTAG";
    final String TITLETAG = "TITLETAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        TextView tv = (TextView)findViewById(R.id.debug);

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


        Intent it = getIntent();
        int mode = it.getIntExtra(MODE,-1);
        dbTask dbt = new dbTask(getApplicationContext(),this);
//        System.out.println("Debug "+mode);
        switch (mode){
            case DATEMODE:
                System.out.println("date");
                String date = it.getStringExtra(DATE);
                String[] inputs = new String[]{DATEM,date};
                dbt.execute(inputs);
                return;


            case DETAILMODE:
                int range = it.getIntExtra(RANGEN,-1);
                int tag = it.getIntExtra(TAGEN,-1);
                int key = it.getIntExtra(KEYEN,-1);
                int title = it.getIntExtra(TITLE,-1);
                String st = it.getStringExtra(START);
                String end = it.getStringExtra(END);
                String tg = it.getStringExtra(TAG);
                String tt = it.getStringExtra(KEY);
                String[] input;
                if(range==ENABLE){


                    if(key==ENABLE){
                        if(title==ENABLE){
                            //range and title
                            if(tag==ENABLE){
                                input = new String[]{RANGETITLETAG,tt,tg,st,end};
                                dbt.execute(input);

                            }else{
                                input = new String[]{RANGETITLE,tt,st,end};
                                dbt.execute(input);
                            }

                        }else{
                            //range and keywords
                            if(tag==ENABLE){
                                input = new String[]{RANGEKEYTAG,tt,tg,st,end};
                                dbt.execute(input);

                            }else{
                                input = new String[]{RANGEKEY,tt,st,end};
                                dbt.execute(input);

                            }

                        }
                    }else{
                        if(tag==ENABLE){
                            input = new String[]{RANGETAG,tg,st,end};
                            dbt.execute(input);

                        }else{
                            System.out.println(st+end);
                            input = new String[]{DATERANGE,st,end};
                            dbt.execute(input);

                        }

                    }

                }else{
                    if(key==ENABLE){
                        if(title==ENABLE){
                            //range and title
                            if(tag==ENABLE){
                                input = new String[]{TITLETAG,tt,tg,st,end};
                                dbt.execute(input);

                            }else{
                                input = new String[]{TITLEM,tt,st,end};
                                dbt.execute(input);
                            }

                        }else{
                            //range and keywords
                            if(tag==ENABLE){
                                input = new String[]{KEYTAG,tt,tg,st,end};
                                dbt.execute(input);

                            }
//                            else{
//                                input = new String[]{KEYM,tt,st,end};
//                                dbt.execute(input);
//
//                            }

                        }
                    }else{
                        if(tag==ENABLE){
                            input = new String[]{TAGSM,tg,st,end};
                            dbt.execute(input);

                        }

                    }

                }


                return;
            case TAGSMODE:
                String tgs = it.getStringExtra(TAG);
                dbt.execute(new String[]{TAGSM,tgs});
                return;
            default:

                int test = it.getIntExtra(MODE, -1);
                final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> temp1 = new HashMap<String, String>();
                temp1.put(FIRST_COLUMN, "Title");
                temp1.put(SECOND_COLUMN, "Time");
                temp1.put(THIRD_COLUMN, "Content");
                temp1.put(FOURTH_COLUMN, "Tags");
                list.add(temp1);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(FIRST_COLUMN, "Good morning");
                temp.put(SECOND_COLUMN, "2017/03/01");
                temp.put(THIRD_COLUMN, "I am happy");
                temp.put(FOURTH_COLUMN, "Happy");
                list.add(temp);

                ListView lv = (ListView) findViewById(R.id.list);
                ListViewAdapter adapter = new ListViewAdapter(this, list);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println("debug " + i);
                        if (i == 0) {
                            return;
                        }
                        HashMap<String, String> target = list.get(i);
                        System.out.println(i);
                        Intent it = new Intent((ListActivity.this), displayItemActivity.class);
                        it.putExtra(FIRST_COLUMN, target.get(FIRST_COLUMN));
                        it.putExtra(SECOND_COLUMN, target.get(SECOND_COLUMN));
                        it.putExtra(THIRD_COLUMN, target.get(THIRD_COLUMN));
                        it.putExtra(FOURTH_COLUMN, target.get((FOURTH_COLUMN)));
                        startActivity(it);

                    }
                });




//        }
//        if(false) {
//            int test = it.getIntExtra(MODE, -1);
//            final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//            HashMap<String, String> temp1 = new HashMap<String, String>();
//            temp1.put(FIRST_COLUMN, "Title");
//            temp1.put(SECOND_COLUMN, "Time");
//            temp1.put(THIRD_COLUMN, "Content");
//            temp1.put(FOURTH_COLUMN, "Tags");
//            list.add(temp1);
//            HashMap<String, String> temp = new HashMap<String, String>();
//            temp.put(FIRST_COLUMN, "Good morning");
//            temp.put(SECOND_COLUMN, "2017/03/01");
//            temp.put(THIRD_COLUMN, "I am happy");
//            temp.put(FOURTH_COLUMN, "Happy");
//            list.add(temp);
//
//            ListView lv = (ListView) findViewById(R.id.list);
//            ListViewAdapter adapter = new ListViewAdapter(this, list);
//            lv.setAdapter(adapter);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    System.out.println("debug " + i);
//                    if (i == 0) {
//                        return;
//                    }
//                    HashMap<String, String> target = list.get(i);
//                    System.out.println(i);
//                    Intent it = new Intent((ListActivity.this), displayItemActivity.class);
//                    it.putExtra(FIRST_COLUMN, target.get(FIRST_COLUMN));
//                    it.putExtra(SECOND_COLUMN, target.get(SECOND_COLUMN));
//                    it.putExtra(THIRD_COLUMN, target.get(THIRD_COLUMN));
//                    it.putExtra(FOURTH_COLUMN, target.get((FOURTH_COLUMN)));
//                    startActivity(it);
//
//                }
//            });
//        }
//        if(test==-1){
//        tv.setText("not found");
//        }else{
//            if(test==DATEMODE){
//
//            String time = it.getStringExtra(DATE);
//            tv.setText(time);
//            }
//            else{
//                if(test == TAGSMODE){
//                    String tag = it.getStringExtra(TAG);
//                    tv.setText(tag);
//                }else{
//                    if(test == DETAILMODE){
//
//                        int rangeEn = it.getIntExtra(RANGEN,-1);
//                        int keyEn = it.getIntExtra(KEYEN,-1);
//                        int tagEn = it.getIntExtra(TAGEN,-1);
//                        int title = it.getIntExtra(TITLE,-1);
//
//
//
//                    }else{
//
//                        Toast.makeText(getApplicationContext(),"no such mode",Toast.LENGTH_LONG);
//                        return;
//                    }
//                }
//            }
//        }
    }
//    public class dbTask extends AsyncTask<String, Void, ArrayList<HashMap<String,String>> > {
//        Context mContext;
//
//        String FIRST_COLUMN = "TITLE";
//        String SECOND_COLUMN = "DATE";
//        String THIRD_COLUMN = "CONTENT";
//        String FOURTH_COLUMN = "TAGS";
//
//        public dbTask (Context context){
//            mContext = context;
//        }
//        public ArrayList<HashMap<String,String>> convertToObject(List<Map<String,AttributeValue>> ins){
//            ArrayList<HashMap<String,String>> re = new ArrayList<>();
//
//            for(Map<String,AttributeValue> mp:ins){
//                HashMap<String,String> temp = new HashMap<>();
//                temp.put(FIRST_COLUMN,mp.get("Title").toString());
//                long time = new Long(mp.get("Date").toString());
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(time);
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
//                String timest = df.format(calendar.getTime());
//                temp.put(SECOND_COLUMN,timest);
//                temp.put(THIRD_COLUMN,mp.get("Entry").toString());
//                temp.put(FOURTH_COLUMN,mp.get("Tags").toString());
//                re.add(temp);
//
//
//            }
//            return re;
//
//        }
//        public ArrayList<HashMap<String,String>> convertToObject(Map<String,AttributeValue> mp){
//            ArrayList<HashMap<String,String>> re = new ArrayList<>();
//
//
//            HashMap<String,String> temp = new HashMap<>();
//            temp.put(FIRST_COLUMN,mp.get("Title").toString());
//            long time = new Long(mp.get("Date").toString());
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(time);
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
//            String timest = df.format(calendar.getTime());
//            temp.put(SECOND_COLUMN,timest);
//            temp.put(THIRD_COLUMN,mp.get("Entry").toString());
//            temp.put(FOURTH_COLUMN,mp.get("Tags").toString());
//            re.add(temp);
//
//
//
//            return re;
//
//        }
//        @Override
//        protected ArrayList<HashMap<String,String>> doInBackground(String... params) {
//            dbHelper dbh = new dbHelper((mContext));
//            List<Map<String,AttributeValue>> re;
//            switch (params[0]){
//
//                case DATEM:
//                    String date = params[1];
//                    String[] seg = date.split("/");
//                    if(seg.length!=3){
//                        return new ArrayList<HashMap<String,String>>(){};
//                    }
//                    int year = new Integer(seg[0]);
//                    int month = new Integer(seg[1]);
//                    int dt = new Integer(seg[2]);
//                    re = dbh.searchByDate(year,month,dt);
//                    return convertToObject(re);
//                case TITLEM:
//                    String tt = (params[1]);
//                    return convertToObject(dbh.searchByTitle(tt));
//                case TAGSM:
//
//                    String tg = params[1];
//                    System.out.println("Debug "+"tg");
//                    return convertToObject(dbh.searchByTag(tg));
//
//                case DATERANGE:
//                    String st = params[1];
//                    String[]sta = st.split("/");
//                    String et = params[2];
//                    String[]eta = et.split("/");
//                    int year1 = new Integer(sta[0]);
//                    int month1 = new Integer(sta[1]);
//                    int dt1 = new Integer(sta[2]);
//                    int year2 = new Integer(eta[0]);
//                    int month2 = new Integer(eta[1]);
//                    int dt2 = new Integer(eta[2]);
//                    return convertToObject(dbh.searchByRangeDate(year1,month1,dt1,year2,month2,dt2));
//                case RANGEKEY:
//                    String st1 = params[2];
//                    String[]sta1 = st1.split("/");
//                    String et1 = params[3];
//                    String[]eta1 = et1.split("/");
//                    int year3 = new Integer(sta1[0]);
//                    int month3 = new Integer(sta1[1]);
//                    int dt3 = new Integer(sta1[2]);
//                    int year4 = new Integer(eta1[0]);
//                    int month4 = new Integer(eta1[1]);
//                    int dt4 = new Integer(eta1[2]);
//                    String key = params[1];
//                    re = dbh.searchByRangeAndKey(key,year3,month3,dt3,year4,month4,dt4);
//                    return convertToObject(re);
//                case RANGEKEYTAG:
//                    String st2 = params[3];
//                    String[]sta2 = st2.split("/");
//                    String et2 = params[4];
//                    String[]eta2 = et2.split("/");
//                    int year5 = new Integer(sta2[0]);
//                    int month5 = new Integer(sta2[1]);
//                    int dt5 = new Integer(sta2[2]);
//                    int year6 = new Integer(eta2[0]);
//                    int month6 = new Integer(eta2[1]);
//                    int dt6 = new Integer(eta2[2]);
//                    String tg1 = params[2];
//                    String tt5 = params[1];
//                    re = dbh.searchByRangeAndKeyAndTag(tt5,tg1,year5,month5,dt5,year6,month6,dt6);
//                    return convertToObject(re);
//
//                case RANGETITLE:
//                    String st3 = params[2];
//                    String[]sta3 = st3.split("/");
//                    String et3 = params[3];
//                    String[]eta3 = et3.split("/");
//                    int year7 = new Integer(sta3[0]);
//                    int month7 = new Integer(sta3[1]);
//                    int dt7 = new Integer(sta3[2]);
//                    int year8 = new Integer(eta3[0]);
//                    int month8 = new Integer(eta3[1]);
//                    int dt8 = new Integer(eta3[2]);
//                    String tt1 = params[1];
//                    re = dbh.searchByRangeAndTitle(tt1,year7,month7,dt7,year8,month8,dt8);
//                    return convertToObject(re);
//
//                case RANGETITLETAG:
//                    String st5 = params[3];
//                    String[]sta5 = st5.split("/");
//                    String et5 = params[4];
//                    String[]eta5 = et5.split("/");
//                    int year9 = new Integer(sta5[0]);
//                    int month9 = new Integer(sta5[1]);
//                    int dt9 = new Integer(sta5[2]);
//                    int year10 = new Integer(eta5[0]);
//                    int month10 = new Integer(eta5[1]);
//                    int dt10 = new Integer(eta5[2]);
//                    String tt2 = params[1];
//                    String tg2 = params[2];
//                    re = dbh.searchByRangeAndTitleAndTag(tt2,tg2,year9,month9,dt9,year10,month10,dt10);
//                    return convertToObject(re);
//                case RANGETAG:
//                    String st6 = params[2];
//                    String[]sta6 = st6.split("/");
//                    String et6 = params[3];
//                    String[]eta6 = et6.split("/");
//                    int year11 = new Integer(sta6[0]);
//                    int month11 = new Integer(sta6[1]);
//                    int dt11 = new Integer(sta6[2]);
//                    int year12 = new Integer(eta6[0]);
//                    int month12 = new Integer(eta6[1]);
//                    int dt12 = new Integer(eta6[2]);
//
//                    String tg3 = params[1];
//                    re = dbh.searchByRangeAndTag(tg3,year11,month11,dt11,year12,month12,dt12);
//                    return convertToObject(re);
//                case TITLETAG:
//                    String tt4 = params[1];
//                    String tg4= params[2];
//                    re = dbh.searchByTitleAndTag(tt4,tg4);
//                    return convertToObject(re);
//                case KEYTAG:
//                    String ky = params[1];
//                    String tg5= params[2];
//                    re = dbh.searchByKeyAndTag(ky,tg5);
//                    return convertToObject(re);
//
//
//
//            }
//        return new ArrayList<>();
//
////        return "Executed";
//        }
//
//        @Override
//        protected void onPostExecute(final ArrayList<HashMap<String,String>> result) {
//            ListView lv = (ListView) findViewById(R.id.list);
//            ListViewAdapter adapter = new ListViewAdapter(ListActivity.this, result);
//            lv.setAdapter(adapter);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    System.out.println("debug " + i);
////                    if (i == 0) {
////                        return;
////                    }
//                    HashMap<String, String> target = result.get(i);
//
//                    Intent it = new Intent((ListActivity.this), displayItemActivity.class);
//                    it.putExtra(FIRST_COLUMN, target.get(FIRST_COLUMN));
//                    it.putExtra(SECOND_COLUMN, target.get(SECOND_COLUMN));
//                    it.putExtra(THIRD_COLUMN, target.get(THIRD_COLUMN));
//                    it.putExtra(FOURTH_COLUMN, target.get((FOURTH_COLUMN)));
//
//                    startActivity(it);
//
//                }
//            });
//
//
////        System.out.println(result);
//
//        }
//
//        @Override
//        protected void onPreExecute() {}
//
//        @Override
//        protected void onProgressUpdate(Void... values) {}
//
//    }
    }
}
