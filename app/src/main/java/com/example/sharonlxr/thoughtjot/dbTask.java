package com.example.sharonlxr.thoughtjot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sharonlxr on 4/25/17.
 */

public class dbTask extends AsyncTask<String, Void, ArrayList<HashMap<String,String>> > {
    Context mContext;

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
    Activity mActivity;
//    ListViewAdapter listadp;
//    dbHelper dbh;
    public dbTask (Context context, Activity ct){
        mActivity = ct;

        mContext = context;
//        dbh = new dbHelper((mContext));
    }
    public ArrayList<HashMap<String,String>> convertToObject(List<Map<String,AttributeValue>> ins){
        ArrayList<HashMap<String,String>> re = new ArrayList<>();
        if(ins==null){
            return re;
        }
        for(Map<String,AttributeValue> mp:ins){
            HashMap<String,String> temp = new HashMap<>();
            temp.put(FIRST_COLUMN,mp.get("Title").getS().toString());
            if(!mp.containsKey("Date")){

            }else{
            long time = new Long(mp.get("Date").getN().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
            String timest = df.format(calendar.getTime());
            temp.put(SECOND_COLUMN,timest);}
            temp.put(THIRD_COLUMN,mp.get("Entry").getS().toString());
            temp.put(FOURTH_COLUMN,mp.get("Tags").getS().toString());
            re.add(temp);


        }
        return re;

    }
    public ArrayList<HashMap<String,String>> convertToObject(Map<String,AttributeValue> mp){
        ArrayList<HashMap<String,String>> re = new ArrayList<>();


        HashMap<String,String> temp = new HashMap<>();
        if(mp==null){
            return re;
        }
        temp.put(FIRST_COLUMN,mp.get("Title").toString());
        long time = new Long(mp.get("Date").toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
        String timest = df.format(calendar.getTime());
        temp.put(SECOND_COLUMN,timest);
        temp.put(THIRD_COLUMN,mp.get("Entry").toString());
        temp.put(FOURTH_COLUMN,mp.get("Tags").toString());
        re.add(temp);



        return re;

    }
    @Override
    protected ArrayList<HashMap<String,String>> doInBackground(String... params) {
        dbHelper dbh = new dbHelper((mContext));
        System.out.println("haha");

        List<Map<String,AttributeValue>> re;
//        return re;
        switch (params[0]){

            case DATEM:

                String date = params[1];
                System.out.println(date);
                String[] seg = date.split("/");
                if(seg.length!=3){
                    return new ArrayList<HashMap<String,String>>(){};
                }
                int year = new Integer(seg[0]);
                int month = new Integer(seg[1]);
                int dt = new Integer(seg[2]);
                re = dbh.searchByDate(year,month,dt);
                return convertToObject(re);
            case TITLEM:
                String tt = (params[1]);
                return convertToObject(dbh.searchByTitle(tt));
            case TAGSM:

                String tg = params[1];
                System.out.println("Debug "+"tg");
                return convertToObject(dbh.searchByTag(tg));

            case DATERANGE:
                String st = params[1];
                String[]sta = st.split("/");
                String et = params[2];
                String[]eta = et.split("/");
                int year1 = new Integer(sta[0]);
                int month1 = new Integer(sta[1]);
                int dt1 = new Integer(sta[2]);
                int year2 = new Integer(eta[0]);
                int month2 = new Integer(eta[1]);
                int dt2 = new Integer(eta[2]);
                return convertToObject(dbh.searchByRangeDate(year1,month1,dt1,year2,month2,dt2));
            case RANGEKEY:
                String st1 = params[2];
                String[]sta1 = st1.split("/");
                String et1 = params[3];
                String[]eta1 = et1.split("/");
                int year3 = new Integer(sta1[0]);
                int month3 = new Integer(sta1[1]);
                int dt3 = new Integer(sta1[2]);
                int year4 = new Integer(eta1[0]);
                int month4 = new Integer(eta1[1]);
                int dt4 = new Integer(eta1[2]);
                String key = params[1];
                re = dbh.searchByRangeAndKey(key,year3,month3,dt3,year4,month4,dt4);
                return convertToObject(re);
            case RANGEKEYTAG:
                String st2 = params[3];
                String[]sta2 = st2.split("/");
                String et2 = params[4];
                String[]eta2 = et2.split("/");
                int year5 = new Integer(sta2[0]);
                int month5 = new Integer(sta2[1]);
                int dt5 = new Integer(sta2[2]);
                int year6 = new Integer(eta2[0]);
                int month6 = new Integer(eta2[1]);
                int dt6 = new Integer(eta2[2]);
                String tg1 = params[2];
                String tt5 = params[1];
                re = dbh.searchByRangeAndKeyAndTag(tt5,tg1,year5,month5,dt5,year6,month6,dt6);
                return convertToObject(re);

            case RANGETITLE:
                String st3 = params[2];
                String[]sta3 = st3.split("/");
                String et3 = params[3];
                String[]eta3 = et3.split("/");
                int year7 = new Integer(sta3[0]);
                int month7 = new Integer(sta3[1]);
                int dt7 = new Integer(sta3[2]);
                int year8 = new Integer(eta3[0]);
                int month8 = new Integer(eta3[1]);
                int dt8 = new Integer(eta3[2]);
                String tt1 = params[1];
                re = dbh.searchByRangeAndTitle(tt1,year7,month7,dt7,year8,month8,dt8);
                return convertToObject(re);

            case RANGETITLETAG:
                String st5 = params[3];
                String[]sta5 = st5.split("/");
                String et5 = params[4];
                String[]eta5 = et5.split("/");
                int year9 = new Integer(sta5[0]);
                int month9 = new Integer(sta5[1]);
                int dt9 = new Integer(sta5[2]);
                int year10 = new Integer(eta5[0]);
                int month10 = new Integer(eta5[1]);
                int dt10 = new Integer(eta5[2]);
                String tt2 = params[1];
                String tg2 = params[2];
                re = dbh.searchByRangeAndTitleAndTag(tt2,tg2,year9,month9,dt9,year10,month10,dt10);
                return convertToObject(re);
            case RANGETAG:
                String st6 = params[2];
                String[]sta6 = st6.split("/");
                String et6 = params[3];
                String[]eta6 = et6.split("/");
                int year11 = new Integer(sta6[0]);
                int month11 = new Integer(sta6[1]);
                int dt11 = new Integer(sta6[2]);
                int year12 = new Integer(eta6[0]);
                int month12 = new Integer(eta6[1]);
                int dt12 = new Integer(eta6[2]);

                String tg3 = params[1];
                re = dbh.searchByRangeAndTag(tg3,year11,month11,dt11,year12,month12,dt12);
                return convertToObject(re);
            case TITLETAG:
                String tt4 = params[1];
                String tg4= params[2];
                re = dbh.searchByTitleAndTag(tt4,tg4);
                return convertToObject(re);
            case KEYTAG:
                String ky = params[1];
                String tg5= params[2];
                re = dbh.searchByKeyAndTag(ky,tg5);
                return convertToObject(re);



        }
        return new ArrayList<>();

//        return "Executed";
    }

    @Override
    protected void onPostExecute(final ArrayList<HashMap<String,String>> result) {
        if(result == null){
            return;
        }
        ListView lv = (ListView) mActivity.findViewById(R.id.list);
        ListViewAdapter adapter = new ListViewAdapter(mActivity, result);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("debug " + i);
//                    if (i == 0) {
//                        return;
//                    }
                HashMap<String, String> target = result.get(i);

                Intent it = new Intent((mActivity), displayItemActivity.class);
                it.putExtra(FIRST_COLUMN, target.get(FIRST_COLUMN));
                it.putExtra(SECOND_COLUMN, target.get(SECOND_COLUMN));
                it.putExtra(THIRD_COLUMN, target.get(THIRD_COLUMN));
                it.putExtra(FOURTH_COLUMN, target.get((FOURTH_COLUMN)));

                mActivity.startActivity(it);

            }
        });


//        System.out.println(result);

    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

}
